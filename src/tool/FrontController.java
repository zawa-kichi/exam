package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response
		)throws ServletException,IOException{
		try{
			// パスを取得
			String path = request.getServletPath().substring(1);
			// ファイル名を取得しクラス名を取得
			String name = path.replace(".a", "A").replace("/", ".");

			System.out.println("★ servlet path ->" + request.getServletPath());
			System.out.println("★ class name ->" + name);

			// アクションクラスのインスタンスを返却
			Action action = (Action)Class.forName(name).getDeclaredConstructor().newInstance();

			// 転移先URLを取得
			action.execute(request, response);
		}catch (Exception e){
			// エラーページへリダイレクト
			request.getRequestDispatcher("../scoremanager.main/error.jsp").forward(request,response);
		}
	}
}
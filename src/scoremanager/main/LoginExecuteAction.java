package scoremanager.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action{
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("ユーザー認証機能を起動。");

		// セッションの呼び出し
		HttpSession session = req.getSession();

		// jspファイルからIDとパスワードを抽出
		String getId = req.getParameter("id");
		String getPassword = req.getParameter("password");

		// IDとパスワードが既存のユーザー情報と一致するか検索
		TeacherDao dao = new TeacherDao();
		String id = getId;
		String password = getPassword;
		Teacher teacher = dao.login(id, password);

		// errors:エラー一覧
		Map<String, String> errors = new HashMap<String, String>();
		if (Objects.isNull(teacher)) {
			// エラー文を表示
			errors.put("","IDまたはパスワードが正しくありません。" );
				System.out.println("★ file name -> /scoremanager.main/login.jsp");
			req.getRequestDispatcher("login.jsp").forward(req, res);
		} else {
			// 取得したユーザー情報をセッションに追加
			session.setAttribute("user", teacher);
			System.out.println("★ file name -> /scoremanager.main/menu.jsp");
			req.getRequestDispatcher("menu.jsp").forward(req,res);
		}
	}
}
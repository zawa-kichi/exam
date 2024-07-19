package scoremanager.main;

import java.util.ArrayList;
import java.util.List;
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
		// エラー文を装填するリストを準備
		List<String> errors = new ArrayList<String>();
		if (Objects.isNull(teacher)) {
			// エラー文を表示
			errors.add("ログインに失敗しました。IDまたはパスワードが正しくありません。");
			req.setAttribute("errors", errors);
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
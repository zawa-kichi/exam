package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res
		)throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        session.setAttribute("user", teacher);

		// 変更する科目情報を取得
		String cd = req.getParameter("cd");
		School school = teacher.getSchool();
		SubjectDao dao = new SubjectDao();
		Subject subject = dao.get(cd, school);

		// 科目の科目コードを取得
		req.setAttribute("cd", cd);
		// 科目の名前を取得
		String name = subject.getName();
		req.setAttribute("name", name);
		System.out.println("★ file name -> /scoremanager.main/student_update.jsp");
		req.getRequestDispatcher("subject_delete.jsp").forward(req, res);

	}
}
package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("学生情報変更機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		// 変更する学生情報を取得
		String no = req.getParameter("student_no");
		StudentDao dao = new StudentDao();
		Student student = dao.get(no);
		// 学生の入学年度を取得
		int entYear = student.getEntYear();
		req.setAttribute("ent_year", entYear);
		// 学生の学生番号を取得
		req.setAttribute("no", no);
		// 学生の氏名を取得
		String name = student.getName();
		req.setAttribute("name", name);
		// 学生のクラスを取得
		String classNum = student.getClassNum();
		req.setAttribute("class_num", classNum);
		// クラスセレクトボックスを設定
		ClassNumDao classNumDao = new ClassNumDao();
		School school = teacher.getSchool();
		List<String> classNumSet = classNumDao.filter(school);
		req.setAttribute("classNumSet", classNumSet);
		// 学生の在学中フラグを取得
		boolean isAttend = student.isAttend();
		req.setAttribute("is_attend", isAttend);
		System.out.println("★ file name -> /scoremanager.main/student_update.jsp");
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}
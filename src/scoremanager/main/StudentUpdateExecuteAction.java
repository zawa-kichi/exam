package scoremanager.main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("学生変更結果表示機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		// 入学年度セレクトボックスから、登録する生徒の入学年度を取得
		String getEntYear = req.getParameter("ent_year");
		int entYear = 0;
		if (Objects.nonNull(getEntYear)) {
			entYear = Integer.parseInt(getEntYear);
		}
		// 学生番号入力テキストから、登録する生徒の学生番号を取得
		String getNo = req.getParameter("no");
		String no = "";
		if (Objects.nonNull(getNo)) {
			no = getNo;
		}
		// 氏名入力テキストから、登録する生徒の氏名を取得
		String getName = req.getParameter("name");
		String name = "";
		if (Objects.nonNull(getName)) {
			name = getName;
		}
		// クラスセレクトボックスから、登録する生徒のクラスを取得
		String getClassNum = req.getParameter("class_num");
		String classNum = "";
		if (Objects.nonNull(getClassNum)) {
			classNum = getClassNum;
		}
		// 在学チェックボックスから、検索する生徒が在学中か否かを取得
		String getIsAttend =req.getParameter("is_attend");
		boolean isAttend = false;
		if (Objects.nonNull(getIsAttend)) {
			isAttend = true;
		}
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		student.setSchool(school);
		StudentDao dao = new StudentDao();
		dao.save(student);
		System.out.println("★ file name -> /scoremanager/main/student_update_done.jsp");
		req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}
}
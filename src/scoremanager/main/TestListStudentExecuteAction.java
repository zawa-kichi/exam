package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("学生別成績参照機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		// DAOの準備
		TestListStudentDao testListStudentDao = new TestListStudentDao();
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		// 入学年度セレクトボックスを設定
		List<Integer> entYearSet = new ArrayList<>();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		for (int i = year-10; i < year+1; i++) {
			entYearSet.add(i);
		}
		req.setAttribute("entYearSet", entYearSet);
		// クラスセレクトボックスを設定
		List<String> classNumSet = classNumDao.filter(school);
		req.setAttribute("classNumSet", classNumSet);
		// 科目セレクトボックスを設定
		List<Subject> subjectSet = subjectDao.filter(school);
		req.setAttribute("subjectSet", subjectSet);
		// 学生番号テキストボックスから、検索する生徒の学生番号を取得
		String getStudentNo = req.getParameter("f4");
		// 検索学生番号を返す
		String studentNo = "";
		if (Objects.nonNull(getStudentNo)) {
			studentNo = getStudentNo;
		}
		req.setAttribute("f4", studentNo);
		Student student = studentDao.get(studentNo);
		req.setAttribute("student", student);
		// tests:検索結果一覧
		List<TestListStudent> tests = testListStudentDao.filter(student);
		req.setAttribute("tests", tests);
		System.out.println("★ file name -> /scoremanager/main/test_list_subject.jsp");
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
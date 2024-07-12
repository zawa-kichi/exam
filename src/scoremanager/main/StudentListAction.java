
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("学生一覧表示機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		// 入学年度セレクトボックスから、検索する生徒の入学年度を取得
		String getEntYear = req.getParameter("f1");
		// 検索入学年度を返す
		int entYear = 0;
		if (Objects.nonNull(getEntYear)) {
			entYear = Integer.parseInt(getEntYear);
		}
		req.setAttribute("f1", entYear);
		// クラスセレクトボックスから、検索する生徒のクラスを取得
		String getClassNum =req.getParameter("f2");
		// 検索クラスを返す
		String classNum = "0";
		if (Objects.nonNull(getClassNum)) {
			classNum = getClassNum;
		}
		req.setAttribute("f2", classNum);
		// 在学チェックボックスから、検索する生徒が在学中か否かを取得
		String getIsAttend =req.getParameter("f3");
		boolean isAttend = false;
		if (Objects.nonNull(getIsAttend)) {
			isAttend = true;
		}
		// 在学中フラグを返す
		req.setAttribute("f3", isAttend);
		// students:検索結果一覧
		List<Student> students = new ArrayList<>();
		// errors:エラー一覧
		Map<String, String> errors = new HashMap<String, String>();
		// 学生情報検索
		StudentDao studentDao = new StudentDao();
		School school = teacher.getSchool();
		if (entYear != 0 && !classNum.equals("0")) {
			students = studentDao.filter(school, entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			students = studentDao.filter(school, entYear, isAttend);
		} else if (entYear == 0 && classNum.equals("0")) {
			students = studentDao.filter(school, isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください。");
			req.setAttribute("errors", errors);
			students = studentDao.filter(teacher.getSchool(), isAttend);
		}
		// 検索結果を返す
		req.setAttribute("students", students);
		// 入学年度セレクトボックスを設定
		List<Integer> entYearSet = new ArrayList<>();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		for (int i = year-10; i < year+1; i++) {
			entYearSet.add(i);
		}
		req.setAttribute("entYearSet", entYearSet);
		// クラスセレクトボックスを設定
		ClassNumDao classNumDao = new ClassNumDao();
		List<String> classNumSet = classNumDao.filter(school);
		req.setAttribute("classNumSet", classNumSet);
		System.out.println("★ file name -> /scoremanager/main/student_list.jsp");
		req.getRequestDispatcher("student_list.jsp").forward(req, res);
	}
}

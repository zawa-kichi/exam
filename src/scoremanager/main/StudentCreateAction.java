package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("学生登録機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
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
		School school = teacher.getSchool();
		List<String> classNumSet = classNumDao.filter(school);
		req.setAttribute("classNumSet", classNumSet);
		System.out.println("★ file name -> /scoremanager/main/student_create.jsp");
		req.getRequestDispatcher("student_create.jsp").forward(req, res);
	}
}
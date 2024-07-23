package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("成績参照機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		// DAOの準備
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
		System.out.println("★ file name -> /scoremanager/main/test_list.jsp");
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}
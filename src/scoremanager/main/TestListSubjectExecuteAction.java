package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("科目別成績参照機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		// DAOの準備
		TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
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
		// 科目セレクトボックスから、検索する科目を取得
		String getSubjectCd =req.getParameter("f3");
		// 検索科目を返す
		String subjectCd = "0";
		if (Objects.nonNull(getSubjectCd)) {
			subjectCd = getSubjectCd;
		}
		req.setAttribute("f3", subjectCd);
		if (entYear == 0 || classNum.equals("0") || subjectCd.equals("0")) {
			// errors:エラー一覧
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("f1", "入学年度とクラスと科目を選択してください。");
			// エラーを返す
			req.setAttribute("errors", errors);
			System.out.println("★ file name -> /scoremanager/main/test_list.jsp");
			req.getRequestDispatcher("test_list.jsp").forward(req, res);
		} else {
			Subject subject = subjectDao.get(subjectCd, school);
			req.setAttribute("subject", subject);
			// tests:検索結果一覧
			List<TestListSubject> tests = testListSubjectDao
					.filter(entYear, classNum, subject, school);
			req.setAttribute("tests", tests);
			// noMax:試験回数の最大値
			int noMax = 0;
			for (TestListSubject test : tests) {
				noMax = Math.max(noMax, Collections.max(test.getPoints().keySet()));
			}
			List<Integer> noSet = new ArrayList<>();
			for (int i = 1; i <= noMax; i++) {
				noSet.add(i);
			}
			req.setAttribute("noSet", noSet);
			System.out.println("★ file name -> /scoremanager/main/test_list_subject.jsp");
			req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
		}
	}
}
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
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	@SuppressWarnings("unchecked")
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("成績管理一覧表示機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		// セッションから検索条件を取得
		int entYear = (int) session.getAttribute("entYear");
		String classNum = (String) session.getAttribute("classNum");
		Subject subject = (Subject) session.getAttribute("subject");
		String subjectCd = subject.getCd();
		int no = (int) session.getAttribute("no");
		List<Test> tests = (List<Test>) session.getAttribute("tests");
		// DAOの準備
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		TestDao testDao = new TestDao();
		// list:保存用リスト
		List<Test> list = new ArrayList<>();
		// errors:エラー一覧
		Map<String, String> errors = new HashMap<String, String>();
		// 点数テキストボックスから入力点数を取得
		for (Test test : tests) {
			String studentNo = test.getStudent().getNo();
			String getPoint = req.getParameter("point_" + studentNo);
			if (Objects.nonNull(getPoint) && !getPoint.equals("")) {
				int point = Integer.parseInt(getPoint);
				if (point < 0 || point > 100) {
					errors.put("f2_" + studentNo, "0～100の範囲で入力してください。");
				} else {
					test.setPoint(point);
					list.add(test);
				}
			}
		}
		if (errors.size() > 0) {
			// 検索条件を返す
			req.setAttribute("f1", entYear);
			req.setAttribute("f2", classNum);
			req.setAttribute("f3", subjectCd);
			req.setAttribute("f4", no);
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
			// 回数セレクトボックスを設定
			List<Integer> noSet = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				noSet.add(i);
			}
			req.setAttribute("noSet", noSet);
			// 検索結果を返す
			req.setAttribute("tests", tests);
			// エラーを返す
			req.setAttribute("errors", errors);
			System.out.println("★ file name -> /scoremanager/main/test_regist.jsp");
			req.getRequestDispatcher("test_regist.jsp").forward(req, res);
		} else {
			testDao.save(list);
			session.removeAttribute("entYear");
			session.removeAttribute("classNum");
			session.removeAttribute("subject");
			session.removeAttribute("no");
			session.removeAttribute("tests");
			System.out.println("★ file name -> /scoremanager/main/test_regist_done.jsp");
			req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
		}
	}
}
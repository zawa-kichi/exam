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

public class TestRegistAction extends Action {
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
		// DAOの準備
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao classNumDao = new ClassNumDao();
		TestDao testDao = new TestDao();
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
		Subject subject = subjectDao.get(subjectCd, school);
		req.setAttribute("subject", subject);
		// 回数セレクトボックスから、検索する試験回数を取得
		String getNo =req.getParameter("f4");
		// 検索試験回数を返す
		int no = 0;
		if (Objects.nonNull(getNo)) {
			no = Integer.parseInt(getNo);
		}
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
		// students:検索結果一覧
		List<Test> tests = new ArrayList<>();
		// errors:エラー一覧
		Map<String, String> errors = new HashMap<String, String>();
		if (entYear != 0 && !classNum.equals("0")
				&& !subjectCd.equals("0") && no != 0) {
			if (entYear == 0 || classNum.equals("0")
					|| subjectCd.equals("0") || no == 0) {
				errors.put("f1", "入学年度とクラスと科目と回数を選択してください。");
				// エラーを返す
				req.setAttribute("errors", errors);
			} else {
				// 生徒情報検索
				tests = testDao.filter(entYear, classNum, subject, no, school);
				// セッションに各情報を登録
				session.setAttribute("entYear", entYear);
				session.setAttribute("classNum", classNum);
				session.setAttribute("subject", subject);
				session.setAttribute("no", no);
				session.setAttribute("tests", tests);
			}
		}
		// 検索結果を返す
		req.setAttribute("tests", tests);
		System.out.println("★ file name -> /scoremanager/main/test_regist.jsp");
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
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

public class StudentCreateExecuteAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("学生登録結果表示機能を起動。");
		// セッションからユーザーデータを取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		// errors:エラー一覧
		Map<String, String> errors = new HashMap<String, String>();
		// 入学年度セレクトボックスから、登録する生徒の入学年度を取得
		String getEntYear = req.getParameter("ent_year");
		int entYear = 0;
		if (Objects.nonNull(getEntYear)) {
			entYear = Integer.parseInt(getEntYear);
		}
		// 入学年度未入力の時、エラーメッセージを登録する
		if (entYear == 0) {
			errors.put("ent_year", "入学年度を選択してください");
		}
		// 学生番号入力テキストから、登録する生徒の学生番号を取得
		String getNo = req.getParameter("no");
		String no = "";
		if (Objects.nonNull(getNo)) {
			no = getNo;
		}
		// 学生番号が既存の物と重複している時、エラーメッセージを登録する
		StudentDao studentDao = new StudentDao();
		School school = teacher.getSchool();
		List<Student> students = studentDao.filter(school, false);
		for (Student student : students) {
			if (no.equals(student.getNo())) {
				errors.put("no", "学生番号が重複しています");
				break;
			}
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
		// エラーが起こっている時、登録画面に戻す
		if (Objects.nonNull(errors.get("ent_year")) || Objects.nonNull(errors.get("no"))) {
			// 入力済みの入学年度を返す
			req.setAttribute("ent_year", entYear);
			// 入力済みの学生番号を返す
			req.setAttribute("no", no);
			// 入力済みの氏名を返す
			req.setAttribute("name", name);
			// 入力済みのクラスを返す
			req.setAttribute("class_num", classNum);
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
			// エラーメッセージを返す
			req.setAttribute("errors", errors);
			System.out.println("★ file name -> /scoremanager.main/student_create.jsp");
			req.getRequestDispatcher("student_create.jsp").forward(req, res);
		} else {
			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEntYear(entYear);
			student.setClassNum(classNum);
			student.setAttend(true);
			student.setSchool(school);
			studentDao.save(student);
			System.out.println("★ file name -> /scoremanager/main/student_create_done.jsp");
			req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
		}
	}
}
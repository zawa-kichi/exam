package scoremanager.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("SubjectUpdeteExecuteAction started");

        // セッションからユーザーデータを取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            System.out.println("User not logged in");
            // ユーザーがログインしていない場合の処理
            req.setAttribute("errorMessage", "ログインしてください。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        School school = teacher.getSchool();

		// 科目コード入力テキストから、登録する科目の番号を取得
		String getCd = req.getParameter("cd");
		String cd = "";
		if (Objects.nonNull(getCd)) {
			cd = getCd;
		}
		// 科目名入力テキストから、登録する科目の名前を取得
		String getName = req.getParameter("name");
		String name = "";
		if (Objects.nonNull(getName)) {
			name = getName;
		}

        System.out.println("Request parameters: " + cd );
        System.out.println("Request parameters: " + name );


        if (cd == null || cd.isEmpty()) {
            System.out.println("Subject code is missing");
            // 科目コードが提供されていない場合のエラーハンドリング
            req.setAttribute("errorMessage", "科目コードが提供されていません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        System.out.println("Subject code: " + cd);
        System.out.println("Subject name: " + name);

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        SubjectDao dao = new SubjectDao();
        Map<String, String> errors = new HashMap<String, String>();
        try {
        	if (Objects.isNull(dao.get(cd, school))) {
				errors.put("cd", "科目が存在していません。");
				req.setAttribute("errors", errors);
		        req.setAttribute("cd", cd);
		        req.setAttribute("name", name);
                req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			} else {
	            boolean success = dao.save(subject);
	            if (success) {
	                System.out.println("Subject Updated successfully");
	                req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	            } else {
	                System.out.println("Failed to Update subject");
	                req.setAttribute("errorMessage", "科目の削除に失敗しました。");
	                req.getRequestDispatcher("error.jsp").forward(req, res);
	            }
			}
        } catch (Exception e) {
            e.printStackTrace();  // 例外の詳細をログに出力
            req.setAttribute("errorMessage", "システムエラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}

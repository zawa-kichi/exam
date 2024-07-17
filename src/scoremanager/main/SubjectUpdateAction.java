package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            // ユーザーがログインしていない場合の処理
            req.setAttribute("errorMessage", "ログインしてください。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        String cd = req.getParameter("cd");
        if (cd == null || cd.isEmpty()) {
            // 科目コードが提供されていない場合のエラーハンドリング
            req.setAttribute("errorMessage", "科目コードが提供されていません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        School school = teacher.getSchool();
        if (school == null) {
            // School が存在しない場合のエラーハンドリング
            req.setAttribute("errorMessage", "学校情報が見つかりません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, school);
        if (subject == null) {
            // エラーハンドリング：該当する科目が見つからない場合
            req.setAttribute("errorMessage", "該当する科目が見つかりません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        // 科目の情報をリクエストに設定
        req.setAttribute("cd", cd);
        req.setAttribute("name", subject.getName());

        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}

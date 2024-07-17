package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("SubjectDeleteExecuteAction started");

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

        // 科目コードと名前をリクエストパラメータから取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        System.out.println("Request parameters: " + req.getParameterMap());


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
        try {
            boolean success = dao.delete(subject);
            if (success) {
                System.out.println("Subject deleted successfully");
                req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
            } else {
                System.out.println("Failed to delete subject");
                req.setAttribute("errorMessage", "科目の削除に失敗しました。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();  // 例外の詳細をログに出力
            req.setAttribute("errorMessage", "システムエラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}

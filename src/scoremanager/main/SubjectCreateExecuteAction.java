package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            HttpSession session = req.getSession();
            Teacher teacher = (Teacher) session.getAttribute("user");
            session.setAttribute("user", teacher);

            // エラー一覧
            Map<String, String> errors = new HashMap<>();
            String cd = req.getParameter("cd");
            String name = req.getParameter("name");

            // 科目番号の長さチェック
            if (cd == null || cd.length() != 3) {
                errors.put("cd", "科目番号は3文字で入力してください");
            }

            // 科目番号の重複チェック
            SubjectDao subjectDao = new SubjectDao();
            School school = teacher.getSchool();
            List<Subject> subjects = subjectDao.filter(school);
            for (Subject subject : subjects) {
                if (cd != null && cd.equals(subject.getCd())) {
                    errors.put("cd", "科目番号が重複しています");
                    break;
                }
            }

            // 科目名の必須チェック
            if (name == null || name.isEmpty()) {
                errors.put("name", "科目名は必須です");
            }

            // エラーがある場合、登録画面に戻る
            if (!errors.isEmpty()) {
                req.setAttribute("cd", cd);
                req.setAttribute("name", name);
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("subject_create.jsp").forward(req, res);
                return;
            }

            // 新しい科目を保存
            Subject subject = new Subject();
            subject.setSchool(school);
            subject.setCd(cd);
            subject.setName(name);

            if (subjectDao.save(subject)) {
                req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
            } else {
                errors.put("save", "科目の保存に失敗しました");
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}

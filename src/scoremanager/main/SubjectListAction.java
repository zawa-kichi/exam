package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        session.setAttribute("user", teacher);

        if (teacher == null) {
            System.out.println("Teacher is null");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        System.out.println("Teacher: " + teacher);
        System.out.println("School: " + teacher.getSchool());

        if (teacher.getSchool() == null) {
            System.out.println("School is null");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        try {
            List<Subject> subjects = new ArrayList<>();
            SubjectDao subjectDao = new SubjectDao();
            School school = teacher.getSchool();

            subjects = subjectDao.filter(school);
            req.setAttribute("subjects", subjects);

            req.getRequestDispatcher("subject_list.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}

package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentCreateDone extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
        throws Exception {

        req.getRequestDispatcher("student_create_done.jsp").forward(req,res);
    }
}
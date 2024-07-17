package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateDone extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
        throws Exception {

        req.getRequestDispatcher("subject_create_done.jsp").forward(req,res);
    }
}
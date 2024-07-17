package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectUpdateDone extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
        throws Exception {

        req.getRequestDispatcher("subject_update_done.jsp").forward(req,res);
    }
}
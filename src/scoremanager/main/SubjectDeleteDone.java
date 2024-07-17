package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectDeleteDone extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
        throws Exception {

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req,res);
    }
}
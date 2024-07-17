package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response
		)throws Exception{

    	request.getRequestDispatcher("subject_create.jsp").forward(request, response);
	}
}

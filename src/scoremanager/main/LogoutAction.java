package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action{
	public void execute(HttpServletRequest request,HttpServletResponse response
		)throws Exception{
		HttpSession session = request.getSession();

		if(session.getAttribute("user")!=null){
			session.invalidate();
			request.getRequestDispatcher("logout.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}

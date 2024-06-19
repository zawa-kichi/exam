package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{

	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response
		)throws Exception{
		HttpSession session=request.getSession();
		try{
			// Daoオブジェクト化
			StudentDao sDao = new StudentDao();

			String entYearStr = "";
			String no = "";
			String name = "";
			String classNum = "";

			entYearStr = request.getParameter("ent_Year");
			no = request.getParameter("no");
			name = request.getParameter("name");
			classNum = request.getParameter("class_Num");



		}catch(Exception e){
			request.getRequestDispatcher("error.jsp");
		}
		request.getRequestDispatcher("student_create.jsp").forward(request, response);
	}
}
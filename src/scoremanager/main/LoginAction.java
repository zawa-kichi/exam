package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/scoremanager.main/Login.Action"})
public class LoginAction extends HttpServlet{
	public void doGet(
		HttpServletRequest req,HttpServletResponse res
	)throws ServletException,IOException{

		req.getRequestDispatcher("login.jsp").forward(req, res);
	}
}
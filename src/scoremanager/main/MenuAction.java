
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class MenuAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req,
		HttpServletResponse res
	) throws Exception {
		System.out.println("メニュー表示機能を起動。");
		System.out.println("★ file name -> /scoremanager.main/menu.jsp");
		req.getRequestDispatcher("menu.jsp").forward(req, res);
	}
}

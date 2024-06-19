package scoremanager.main;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import tool.Action;
import tool.Page;

public class StudentCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response
		)throws Exception{
		HttpSession session=request.getSession();

		// Daoオブジェクト化
		StudentDao sDao = new StudentDao();

		String entYearStr = "";
		String no = "";
		String name = "";
		String classNum = "";

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		Page.header(out);
			// 学生番号・氏名のいずれかが未入力の場合
			if (no == null || name == null){
				// 警告メッセージ
				out.println("このフィールドを入力してください。");
			}
			// 入学年度が未入力の場合
			if (entYearStr == ""){
				// 警告メッセージ
				out.println("入学年度を選択してください。");
			}
//			if (){
//				// 警告メッセージ
//				out.println("学生番号が重複しています。");
//			}

			entYearStr = request.getParameter("ent_Year");
			no = request.getParameter("no");
			name = request.getParameter("name");
			classNum = request.getParameter("class_Num");

		request.getRequestDispatcher("student_create.jsp").forward(request, response);
		Page.footer(out);
	}
}
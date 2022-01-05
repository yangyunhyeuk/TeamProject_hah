package controller.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemDAO;
import model.member.MemVO;

public class CheckMnameAction {

		public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			
			MemDAO mDAO = new MemDAO();
			MemVO mVO = new MemVO();


			String mname = request.getParameter("mname");
			System.out.println("check mname: " + mname);
			mVO.setMname(mname);

			PrintWriter out=response.getWriter();
			
			if (mDAO.CheckMname(mVO)) {
				System.out.println("if문 진입");
				out.print("fail");
			}

			else {
				System.out.println("else문 진입");
				out.print("success");
			}

			return null;
		}

}


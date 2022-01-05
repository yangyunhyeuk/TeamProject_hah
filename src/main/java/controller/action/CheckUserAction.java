package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemDAO;
import model.member.MemVO;

public class CheckUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		MemDAO mDAO = new MemDAO();
		MemVO mVO = new MemVO();


		String uid = request.getParameter("uid");
		System.out.println("�׼�, check user : " + uid);
		mVO.setMid(uid);

		PrintWriter out=response.getWriter();
		
		if (mDAO.CheckUser(mVO)) {
			System.out.println("if�� ����");
			out.print("fail");
		}

		else {
			System.out.println("else�� ����");
			out.print("success");
		}

		return null;
	}

}

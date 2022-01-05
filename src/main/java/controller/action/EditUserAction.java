package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemDAO;
import model.member.MemVO;

public class EditUserAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;
		
		MemDAO mDAO=new MemDAO();
		MemVO mVO=new MemVO();
		mVO.setMemail(request.getParameter("memail"));
		mVO.setMid(request.getParameter("mid"));
		mVO.setMname(request.getParameter("mname"));
		mVO.setMpw(request.getParameter("mpw"));
		
		if(mDAO.UpdateDB(mVO)) {
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("logout.do");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('회원정보수정 오류');history.go(-1);</script>");
		}
		return forward;
	}
}
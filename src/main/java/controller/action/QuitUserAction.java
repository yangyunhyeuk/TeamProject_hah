package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemDAO;
import model.member.MemVO;

public class QuitUserAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;
		HttpSession session=request.getSession();
		
		String mid=(String)session.getAttribute("mem");
		
		MemDAO mDAO=new MemDAO();
		MemVO mVO=new MemVO();
		mVO.setMid(mid);
		
		if(mDAO.DeleteDB(mVO)) {
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("logout.do");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('È¸¿øÅ»Åð È®ÀÎ');history.go(-1);</script>");
		}
		return forward;
	}

}

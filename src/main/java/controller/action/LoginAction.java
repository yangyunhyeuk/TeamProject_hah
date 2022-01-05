package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemDAO;
import model.member.MemVO;

public class LoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;

		String mid=request.getParameter("mid");
		String mpw=request.getParameter("mpw");
		String loginCnt=request.getParameter("loginCnt");
		String mode = request.getParameter("mode");
		
		int loginFail=0;
		if(loginCnt!=null) {
			loginFail=loginFail+Integer.parseInt(loginCnt);
		}
		
		MemDAO mDAO=new MemDAO();
		MemVO mVO=new MemVO();
		mVO.setMid(mid);
		mVO.setMpw(mpw);
		
		
		if(mDAO.SelectOne(mVO)!=null) {
			if(mDAO.SelectOne(mVO).getMpw().equals(mpw)) {
				HttpSession session=request.getSession();
				session.setAttribute("mem", mVO.getMid());
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setPath("main.do");
				System.out.println("mpw: "+mpw);
			}
			else {
				loginFail++;
				String str="잘못된 비밀번호입니다";
				request.setAttribute("mode", mode);
				request.setAttribute("str", str);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('비밀번호 오류');</script>");
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setPath("signPage.do?loginFail="+loginFail);
			}
		}
		else {
			String str="잘못된 아이디입니다";
			request.setAttribute("str", str);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('로그인 오류');history.go(-1);</script>");
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("signPage.do");
		}
		return forward;
	}
}
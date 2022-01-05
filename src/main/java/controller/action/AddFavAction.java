package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.mypage.ConcermDAO;
import model.mypage.ConcermVO;

public class AddFavAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;
		HttpSession session=request.getSession();
		
		String mid=(String)session.getAttribute("mem");
		String mname=request.getParameter("mname");
		int pnum=Integer.parseInt(request.getParameter("pnum"));
		String ptitle=request.getParameter("ptitle");
		String category=request.getParameter("category");
		
		ConcermDAO cDAO=new ConcermDAO();
		ConcermVO cVO=new ConcermVO();
		cVO.setMid(mid);
		cVO.setMname(mname);
		cVO.setPnum(pnum);
		cVO.setPtitle(ptitle);
		cVO.setCategory(category);
		
		if(cDAO.getConcerm(cVO)==null) {
			if(cDAO.insertConcermPost(cVO)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('관심글이 추가되었습니다.');history.go(-1);</script>");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('관심글추가 오류');history.go(-1);</script>");
			}
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('이미 관심글로 등록되어있는 글입니다.');history.go(-1);</script>");
		}
		return forward;
	}
	
}

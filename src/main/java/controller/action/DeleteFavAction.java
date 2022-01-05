package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.mypage.ConcermDAO;
import model.mypage.ConcermVO;

public class DeleteFavAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;
		HttpSession session=request.getSession();
		
		String mid=(String)session.getAttribute("mem");
		int favnum=Integer.parseInt(request.getParameter("favnum"));
		String category=request.getParameter("category");
		
		ConcermDAO cDAO=new ConcermDAO();
		ConcermVO cVO=new ConcermVO();
		cVO.setMid(mid);
		cVO.setFavnum(favnum);
		cVO.setCategory(category);
		
		
		if(cDAO.delConcermPost(cVO)) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out=response.getWriter();
//			out.println("<script>alert('관심글이 삭제되었');history.go(-1);</script>");
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("myFav.do?category=favBoard");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('관심글삭제 오류');history.go(-1);</script>");
		}
		
		return forward;
	}
}
package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.comment.FreeCoDAO;
import model.comment.FreeCoVO;
import model.comment.NoticeCoDAO;
import model.comment.NoticeCoVO;
import model.comment.StudyCoDAO;
import model.comment.StudyCoVO;

public class CDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ★ 댓글 삭제 액션 ★ //
		ActionForward forward=new ActionForward();
		
		// 뷰에서 받아와야 할 정보
		String category=request.getParameter("category"); // 카테고리 파라미터
		int cnum=Integer.parseInt(request.getParameter("cnum")); // 댓글 번호 파라미터
		int pnum=Integer.parseInt(request.getParameter("pnum")); // 글 번호 파라미터
		
		// 자유 게시판
		if(category.equals("Free")) {
			FreeCoDAO fcDAO=new FreeCoDAO();
			FreeCoVO fcVO=new FreeCoVO();
			fcVO.setCnum(cnum);
			if(fcDAO.DeleteDB(fcVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Free&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('댓글 삭제 실패!');history.go(-1);</script>");
			}
		}
		// 스터디 게시판
		else if(category.equals("Study")) {
			StudyCoDAO scDAO=new StudyCoDAO();
			StudyCoVO scVO=new StudyCoVO();
			scVO.setCnum(cnum);
			if(scDAO.DeleteDB(scVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Study&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('댓글 삭제 실패!');history.go(-1);</script>");
			}
		} 
		// 공지사항 게시판
		else if(category.equals("Notice")) {
			NoticeCoDAO ncDAO=new NoticeCoDAO();
			NoticeCoVO ncVO=new NoticeCoVO();
			ncVO.setCnum(cnum);
			if(ncDAO.DeleteDB(ncVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Notice&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('댓글 삭제 실패!');history.go(-1);</script>");
			}
		} 		
		
		return forward;
	}

}

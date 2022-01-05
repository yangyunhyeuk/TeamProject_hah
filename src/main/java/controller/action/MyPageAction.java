package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.board.FreeBoPaging;
import model.board.FreeBoVO;
import model.board.StudyBoPaging;
import model.board.StudyBoVO;
import model.member.MemDAO;
import model.member.MemVO;
import model.page.Paging;

public class MyPageAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;
		HttpSession session=request.getSession();
		
		String mid=(String)session.getAttribute("mem");
		String category=request.getParameter("category");
		String stat=request.getParameter("stat");
		String mpw=request.getParameter("mpw");
		int totalCount;
		if(stat==null) {
			stat="Free";
		}
		
		MemDAO mDAO=new MemDAO();
		MemVO mVO=new MemVO();
		mVO.setMid(mid);
		
		if(category.equals("myBoard")) {
			if(stat.equals("Free")) {

				//페이지네이션 기능추가 
				FreeBoPaging fbPage = new FreeBoPaging();
				totalCount = fbPage.getTotalCount(mVO);
				int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
				System.out.println("PLists안 Free page="+page);
				Paging paging = new Paging();
				paging.setPageNo(page);
				paging.setTotalCount(totalCount);
				
				page = ((page-1)*10)+1;
				paging.setPageSize(page+9); 
				ArrayList<FreeBoVO> list = fbPage.getList(page,paging.getPageSize(),mVO);
				request.setAttribute("category",category);
				request.setAttribute("paging", paging);
				request.setAttribute("list", list);
				// ============================================
					
			}
			else if(stat.equals("Study")) {
				
				//페이지네이션 기능추가 
				StudyBoPaging sbPage = new StudyBoPaging();
				totalCount = sbPage.getTotalCount(mVO);
				int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
				System.out.println("PLists안 Notice page="+page);
				Paging paging = new Paging();
				paging.setPageNo(page);
				paging.setTotalCount(totalCount);
				
				page = ((page-1)*10)+1;
				paging.setPageSize(page+9); 
				ArrayList<StudyBoVO> list = sbPage.getList(page,paging.getPageSize(),mVO);
				
				request.setAttribute("category",category);
				request.setAttribute("paging", paging);
				request.setAttribute("list", list);
				// ============================================
				
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('마이페이지게시글확인');history.go(-1);</script>");
			}
			request.setAttribute("stat", stat);
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("mypage.jsp");
		}
		else if(category.equals("checkUser")) {
			if(mDAO.SelectOne(mVO).getMpw().equals(mpw)) {
				MemVO data=mDAO.SelectOne(mVO);
				request.setAttribute("data", data);
				request.setAttribute("check", "t");
				request.setAttribute("category",category);
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setPath("mypage.jsp");
			}
			else {
				System.out.println("checkUser");
				request.setAttribute("check", "f");
				request.setAttribute("category",category);
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setPath("mypage.jsp");
			}
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('마이페이지확인');history.go(-1);</script>");
		}
		return forward;
	}
}
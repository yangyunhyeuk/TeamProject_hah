package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemVO;
import model.mypage.ConcermPaging;
import model.mypage.ConcermVO;
import model.page.Paging;

public class MyFavAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;
		HttpSession session=request.getSession();
		
		String mid=(String)session.getAttribute("mem");
		String category=request.getParameter("category");
		int totalCount;
		
		MemVO mVO=new MemVO();
		mVO.setMid(mid);
		System.out.println("myfav: "+category);
		if(category.equals("favBoard")) {
			ConcermPaging cPage = new ConcermPaging();
			totalCount = cPage.getTotalCount(mVO);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			System.out.println("myFav page="+page);
			Paging paging = new Paging();
			paging.setPageNo(page);
			paging.setTotalCount(totalCount);
			
			page = ((page-1)*10)+1;
			paging.setPageSize(page+9); 
			ArrayList<ConcermVO> list = cPage.getList(page,paging.getPageSize(),mVO);
			
			request.setAttribute("category",category);
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);
			// ============================================
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("mypage.jsp");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('관심글페이지확인');history.go(-1);</script>");
		}
		return forward;
	}

}

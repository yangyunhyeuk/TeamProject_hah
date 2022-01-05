package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.FreeBoPaging;
import model.board.FreeBoVO;
import model.board.NoticeBoPaging;
import model.board.NoticeBoVO;
import model.board.StudyBoPaging;
import model.board.StudyBoVO;
import model.page.Paging;

public class SearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		ActionForward forward=new ActionForward();

		// 뷰에서 받아와야 할 정보
		String category=request.getParameter("category");
		
		String condition=request.getParameter("condition");
		String content=request.getParameter("content");
		
		int totalCount; // 테이블마다 전체 데이터 개수 
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		
		// 확인
		System.out.println("PLists안 Notice page="+page);
		
		// 공지사항 게시판
		if(category.equals("Notice")) {
			
			//페이지네이션 기능추가 
			NoticeBoPaging nbPage = new NoticeBoPaging();
			totalCount = nbPage.getTotalCount(condition,content);
			Paging paging = new Paging();
			paging.setPageNo(page);
			paging.setTotalCount(totalCount);
			
			page = ((page-1)*10)+1;
			paging.setPageSize(page+9); 
			ArrayList<NoticeBoVO> list = nbPage.getList(page,paging.getPageSize(),condition,content);
			
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);
			
		}
		// 자유 게시판
		else if(category.equals("Free")) {
			
			FreeBoPaging fbPage = new FreeBoPaging();
			totalCount = fbPage.getTotalCount(condition,content);
			Paging paging = new Paging();
			paging.setPageNo(page);
			paging.setTotalCount(totalCount);
			
			page = ((page-1)*10)+1;
			paging.setPageSize(page+9);
			ArrayList<FreeBoVO> list = fbPage.getList(page,paging.getPageSize(),condition,content);
			
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);
			
		}
		// 스터디 게시판
		else if(category.equals("Study")) {
			
			StudyBoPaging sbPage = new StudyBoPaging();
			totalCount = sbPage.getTotalCount(condition,content);
			Paging paging = new Paging();
			paging.setPageNo(page);
			paging.setTotalCount(totalCount);
			
			page = ((page-1)*10)+1;
			paging.setPageSize(page+9); 
			ArrayList<StudyBoVO> list = sbPage.getList(page,paging.getPageSize(),condition,content);
			
			
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);
			
		}
		request.setAttribute("category",category);
		request.setAttribute("condition",condition);
		request.setAttribute("content",content);
		request.setAttribute("search", "dd");
		forward.setRedirect(false);
		forward.setPath("board.jsp");
		return forward;
	}

}

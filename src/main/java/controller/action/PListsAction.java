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

public class PListsAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ★ 게시판에서 글 목록 보여주는 액션 ★ //
		ActionForward forward=new ActionForward();
		
		// 뷰에서 받아와야 할 정보
		String category=request.getParameter("category");
		
		int totalCount; // 테이블마다 전체 데이터 개수 
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		
		// 확인
		System.out.println("PLists안 Notice page="+page);
		
		// 공지사항 게시판
		if(category.equals("Notice")) {
			
			NoticeBoPaging nbPage = new NoticeBoPaging();
			totalCount = nbPage.getTotalCount();
			Paging paging = new Paging();
			paging.setPageNo(page);
			paging.setTotalCount(totalCount);
			
			page = ((page-1)*10)+1;
			paging.setPageSize(page+9); 
			ArrayList<NoticeBoVO> list = nbPage.getList(page,paging.getPageSize());
			
			
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);
		}
		// 자유 게시판
		else if(category.equals("Free")) {
			
			FreeBoPaging fbPage = new FreeBoPaging();
			totalCount = fbPage.getTotalCount(null,null);
			Paging paging = new Paging();
			paging.setPageNo(page);
			paging.setTotalCount(totalCount);
			
			page = ((page-1)*10)+1;
			paging.setPageSize(page+9);
			ArrayList<FreeBoVO> list = fbPage.getList(page,paging.getPageSize(),null,null);
			
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);
		}
		// 스터디 게시판
		else if(category.equals("Study")) {
			
			StudyBoPaging sbPage = new StudyBoPaging();
			totalCount = sbPage.getTotalCount(null,null);
			Paging paging = new Paging();
			paging.setPageNo(page);
			paging.setTotalCount(totalCount);
			
			page = ((page-1)*10)+1;
			paging.setPageSize(page+9); 
			ArrayList<StudyBoVO> list = sbPage.getList(page,paging.getPageSize(),null,null);
			
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);
		}
		request.setAttribute("category",category);
		
		forward.setRedirect(false);
		forward.setPath("board.jsp");
		return forward;
	}
}
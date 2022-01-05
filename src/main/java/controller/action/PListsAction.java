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
		
		// �� �Խ��ǿ��� �� ��� �����ִ� �׼� �� //
		ActionForward forward=new ActionForward();
		
		// �信�� �޾ƿ;� �� ����
		String category=request.getParameter("category");
		
		int totalCount; // ���̺��� ��ü ������ ���� 
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		
		// Ȯ��
		System.out.println("PLists�� Notice page="+page);
		
		// �������� �Խ���
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
		// ���� �Խ���
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
		// ���͵� �Խ���
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
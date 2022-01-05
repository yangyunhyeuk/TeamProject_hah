package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.FreeBoDAO;
import model.board.FreeBoVO;
import model.board.NoticeBoDAO;
import model.board.NoticeBoVO;
import model.board.StudyBoDAO;
import model.board.StudyBoVO;

public class PEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ★ 게시글 수정 페이지로 가는 액션 ★ //
		ActionForward forward=new ActionForward();
		
		// 뷰에서 받아와야 할 정보
		String category=request.getParameter("category"); // 카테고리 파라미터
		int pnum=Integer.parseInt(request.getParameter("pnum")); // 게시글 번호 파라미터
		
		// 자유 게시판
		if(category.equals("Free")) {
			FreeBoDAO fbDAO=new FreeBoDAO();
			FreeBoVO fbVO=new FreeBoVO();
			fbVO.setPnum(pnum);
			FreeBoVO post=fbDAO.getFreePost(fbVO);
			request.setAttribute("post", post);		 // "post"로 게시글 데이터 셋!
		}
		// 스터디 게시판
		else if(category.equals("Study")) {
			StudyBoDAO sbDAO=new StudyBoDAO();
			StudyBoVO sbVO=new StudyBoVO();
			sbVO.setPnum(pnum);
			StudyBoVO post=sbDAO.getStudyPost(sbVO);
			request.setAttribute("post", post);		 // "post"로 게시글 데이터 셋!
		}			
		// 공지사항 게시판
		else if(category.equals("Notice")) {
			NoticeBoDAO nbDAO=new NoticeBoDAO();
			NoticeBoVO nbVO=new NoticeBoVO();
			nbVO.setPnum(pnum);
			NoticeBoVO post=nbDAO.getNoticePost(nbVO);
			request.setAttribute("post", post);		 // "post"로 게시글 데이터 셋!
		}					
		
		forward.setRedirect(false);
		forward.setPath("edit.jsp");
		return forward;
	}

}

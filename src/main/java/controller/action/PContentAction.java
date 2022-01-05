package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.FreeBoDAO;
import model.board.FreeBoVO;
import model.board.NoticeBoDAO;
import model.board.NoticeBoVO;
import model.board.StudyBoDAO;
import model.board.StudyBoVO;
import model.comment.FreeCoDAO;
import model.comment.FreeCoVO;
import model.comment.NoticeCoDAO;
import model.comment.NoticeCoVO;
import model.comment.StudyCoDAO;
import model.comment.StudyCoVO;

public class PContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ★ 게시글 보는 액션 ★ //
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
			
			FreeCoDAO fcDAO=new FreeCoDAO();
			FreeCoVO fcVO=new FreeCoVO();
			fcVO.setPnum(pnum);
			ArrayList<FreeCoVO> comments=fcDAO.SelectAll(fcVO);
			int cnt=fcDAO.getTotalCount(fcVO);
			System.out.println(comments);
			request.setAttribute("cnt", cnt);
			request.setAttribute("comments", comments); // "comments"로 댓글들 데이터 셋!
			
		//=========================================================================
			int size=fbDAO.getFreePostList("1").size(); // 게시글 사이즈
			int startNum=fbDAO.getFreePostList("1").get(size-1).getPnum(); // 처음 게시글 pnum
			int lastNum=fbDAO.getFreePostList("1").get(0).getPnum(); // 마지막 게시글 pnum
			
			ArrayList<FreeBoVO> test=fbDAO.getFreePostList("1");
			
			int indexNum=0; // 현재 pnum의 인덱스
			for(int i=0;i<size;i++) {
				if(test.get(i).getPnum()==pnum) {
					indexNum=i;
				}
			}
			
			int nextNum=0; // 현재 pnum기준 다음 pnum
			int prevNum=0; // 현재 pnum기준 이전 pnum
			if(indexNum==0) {
				nextNum=lastNum;
			}
			else {
				if(fbDAO.getFreePostList("1").get(indexNum-1).getPnum()==lastNum) {
					nextNum=lastNum;
				}
				else {				
					nextNum=fbDAO.getFreePostList("1").get(indexNum-1).getPnum(); // 현재 pnum기준 다음 pnum
				}				
			}
			
			if(indexNum==size-1) {
				prevNum=startNum;				
			}
			else {
				if(fbDAO.getFreePostList("1").get(indexNum+1).getPnum()==startNum) {
					prevNum=startNum;
				}
				else {				
					prevNum=fbDAO.getFreePostList("1").get(indexNum+1).getPnum(); // 현재 pnum기준 이전 pnum
				}
			}
			
			request.setAttribute("nextNum", nextNum);
			request.setAttribute("prevNum", prevNum);
			request.setAttribute("startNum", startNum);
			request.setAttribute("lastNum", lastNum);
			
//			System.out.println("마지막 게시글pnum: "+lastNum);
//			
//			System.out.println("size: "+size);
//			System.out.println("indexNum: "+indexNum);
//			System.out.println("startNum: "+startNum);
//			System.out.println("lastNum: "+lastNum);
//			System.out.println("prevNum: "+prevNum);
//			System.out.println("nextNum: "+nextNum);
		} 
		// 스터디 게시판
		else if(category.equals("Study")) {
			StudyBoDAO sbDAO=new StudyBoDAO();
			StudyBoVO sbVO=new StudyBoVO();
			sbVO.setPnum(pnum);
			StudyBoVO post=sbDAO.getStudyPost(sbVO);
			request.setAttribute("post", post);	 // "post"로 게시글 데이터 셋!
			
			StudyCoDAO scDAO=new StudyCoDAO();
			StudyCoVO scVO=new StudyCoVO();
			scVO.setPnum(pnum);
			ArrayList<StudyCoVO> comments=scDAO.SelectAll(scVO);
			int cnt=scDAO.getTotalCount(scVO);
			request.setAttribute("cnt", cnt);
			request.setAttribute("comments", comments); // "comments"로 댓글들 데이터 셋!
			
		//=========================================================================
			int size=sbDAO.getStudyPostList("1").size(); // 게시글 사이즈
			int startNum=sbDAO.getStudyPostList("1").get(size-1).getPnum(); // 처음 게시글 pnum
			int lastNum=sbDAO.getStudyPostList("1").get(0).getPnum(); // 마지막 게시글 pnum
			
			ArrayList<StudyBoVO> test=sbDAO.getStudyPostList("1");
			
			int indexNum=0; // 현재 pnum의 인덱스
			for(int i=0;i<size;i++) {
				if(test.get(i).getPnum()==pnum) {
					indexNum=i;
				}
			}
			
			int nextNum=0; // 현재 pnum기준 다음 pnum
			int prevNum=0; // 현재 pnum기준 이전 pnum
			if(indexNum==0) {
				nextNum=lastNum;
			}
			else {
				if(sbDAO.getStudyPostList("1").get(indexNum-1).getPnum()==lastNum) {
					nextNum=lastNum;
				}
				else {				
					nextNum=sbDAO.getStudyPostList("1").get(indexNum-1).getPnum(); // 현재 pnum기준 다음 pnum
				}				
			}
			
			if(indexNum==size-1) {
				prevNum=startNum;				
			}
			else {
				if(sbDAO.getStudyPostList("1").get(indexNum+1).getPnum()==startNum) {
					prevNum=startNum;
				}
				else {				
					prevNum=sbDAO.getStudyPostList("1").get(indexNum+1).getPnum(); // 현재 pnum기준 이전 pnum
				}
			}
			
			request.setAttribute("nextNum", nextNum);
			request.setAttribute("prevNum", prevNum);
			request.setAttribute("startNum", startNum);
			request.setAttribute("lastNum", lastNum);
		} 
		// 공지사항 게시판
		else if(category.equals("Notice")) {
			NoticeBoDAO nbDAO=new NoticeBoDAO();
			NoticeBoVO nbVO=new NoticeBoVO();
			nbVO.setPnum(pnum);
			NoticeBoVO post=nbDAO.getNoticePost(nbVO);
			request.setAttribute("post", post);		 // "post"로 게시글 데이터 셋!

			NoticeCoDAO ncDAO=new NoticeCoDAO();
			NoticeCoVO ncVO=new NoticeCoVO();
			ncVO.setPnum(pnum);
			ArrayList<NoticeCoVO> comments=ncDAO.SelectAll(ncVO);
			int cnt=ncDAO.getTotalCount(ncVO);
			request.setAttribute("cnt", cnt);
			request.setAttribute("comments", comments); // "comments"로 댓글들 데이터 셋!
			
		//=========================================================================
			int size=nbDAO.getNoticePostList("1").size(); // 게시글 사이즈
			int startNum=nbDAO.getNoticePostList("1").get(size-1).getPnum(); // 처음 게시글 pnum
			int lastNum=nbDAO.getNoticePostList("1").get(0).getPnum(); // 마지막 게시글 pnum
			
			ArrayList<NoticeBoVO> test=nbDAO.getNoticePostList("1");
			
			int indexNum=0; // 현재 pnum의 인덱스
			for(int i=0;i<size;i++) {
				if(test.get(i).getPnum()==pnum) {
					indexNum=i;
				}
			}
			
			int nextNum=0; // 현재 pnum기준 다음 pnum
			int prevNum=0; // 현재 pnum기준 이전 pnum
			if(indexNum==0) {
				nextNum=lastNum;
			}
			else {
				if(nbDAO.getNoticePostList("1").get(indexNum-1).getPnum()==lastNum) {
					nextNum=lastNum;
				}
				else {				
					nextNum=nbDAO.getNoticePostList("1").get(indexNum-1).getPnum(); // 현재 pnum기준 다음 pnum
				}				
			}
			
			if(indexNum==size-1) {
				prevNum=startNum;				
			}
			else {
				if(nbDAO.getNoticePostList("1").get(indexNum+1).getPnum()==startNum) {
					prevNum=startNum;
				}
				else {				
					prevNum=nbDAO.getNoticePostList("1").get(indexNum+1).getPnum(); // 현재 pnum기준 이전 pnum
				}
			}
			
			request.setAttribute("nextNum", nextNum);
			request.setAttribute("prevNum", prevNum);
			request.setAttribute("startNum", startNum);
			request.setAttribute("lastNum", lastNum);
		} 
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('게시글 진입 오류!');history.go(-1);</script>");
		}
		
		forward.setRedirect(false);
		forward.setPath("post.jsp");
		return forward;
	}

}

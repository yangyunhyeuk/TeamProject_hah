package controller.action;

import java.io.IOException;

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

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		NoticeBoDAO nbDAO = new NoticeBoDAO();
		ArrayList<NoticeBoVO> noticePnum = nbDAO.getNoticePostList("1");
		request.setAttribute("noticePnum", noticePnum);
		ArrayList<NoticeBoVO> noticeCnt = nbDAO.getNoticePostList("2");
		request.setAttribute("noticeCnt", noticeCnt);

		FreeBoDAO fbDAO = new FreeBoDAO();
		ArrayList<FreeBoVO> freePnum = fbDAO.getFreePostList("1");
		request.setAttribute("freePnum", freePnum);
		ArrayList<FreeBoVO> freeCnt = fbDAO.getFreePostList("2");
		request.setAttribute("freeCnt", freeCnt);

		StudyBoDAO sbDAO = new StudyBoDAO();
		ArrayList<StudyBoVO> studyPnum = sbDAO.getStudyPostList("1");
		request.setAttribute("studyPnum", studyPnum);
		ArrayList<StudyBoVO> studyCnt = sbDAO.getStudyPostList("2");
		request.setAttribute("studyCnt", studyCnt);

		forward.setRedirect(false);
		forward.setPath("main.jsp");
		return forward;
	}

}

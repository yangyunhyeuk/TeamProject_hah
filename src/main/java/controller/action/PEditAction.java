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
		
		// �� �Խñ� ���� �������� ���� �׼� �� //
		ActionForward forward=new ActionForward();
		
		// �信�� �޾ƿ;� �� ����
		String category=request.getParameter("category"); // ī�װ� �Ķ����
		int pnum=Integer.parseInt(request.getParameter("pnum")); // �Խñ� ��ȣ �Ķ����
		
		// ���� �Խ���
		if(category.equals("Free")) {
			FreeBoDAO fbDAO=new FreeBoDAO();
			FreeBoVO fbVO=new FreeBoVO();
			fbVO.setPnum(pnum);
			FreeBoVO post=fbDAO.getFreePost(fbVO);
			request.setAttribute("post", post);		 // "post"�� �Խñ� ������ ��!
		}
		// ���͵� �Խ���
		else if(category.equals("Study")) {
			StudyBoDAO sbDAO=new StudyBoDAO();
			StudyBoVO sbVO=new StudyBoVO();
			sbVO.setPnum(pnum);
			StudyBoVO post=sbDAO.getStudyPost(sbVO);
			request.setAttribute("post", post);		 // "post"�� �Խñ� ������ ��!
		}			
		// �������� �Խ���
		else if(category.equals("Notice")) {
			NoticeBoDAO nbDAO=new NoticeBoDAO();
			NoticeBoVO nbVO=new NoticeBoVO();
			nbVO.setPnum(pnum);
			NoticeBoVO post=nbDAO.getNoticePost(nbVO);
			request.setAttribute("post", post);		 // "post"�� �Խñ� ������ ��!
		}					
		
		forward.setRedirect(false);
		forward.setPath("edit.jsp");
		return forward;
	}

}

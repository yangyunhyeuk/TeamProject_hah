package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.FreeBoDAO;
import model.board.FreeBoVO;
import model.board.NoticeBoDAO;
import model.board.NoticeBoVO;
import model.board.StudyBoDAO;
import model.board.StudyBoVO;

public class PDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// �� �Խñ� ���� ��ư ������ �信�� �޾ƿ� ���� DB�� �����ϴ� �׼� �� //
		ActionForward forward=new ActionForward();
		
		// �信�� �޾ƿ;� �� ����
		String category=request.getParameter("category"); // ī�װ� �Ķ����
		int pnum=Integer.parseInt(request.getParameter("pnum")); // �Խñ� ��ȣ �Ķ����
		String mid=request.getParameter("mid"); // ���̵� �Ķ����
		
		// ���� �Խ���
		if(category.equals("Free")) {
			FreeBoDAO fbDAO=new FreeBoDAO();
			FreeBoVO fbVO=new FreeBoVO();
			fbVO.setPnum(pnum);
			fbVO.setMid(mid);
			if(fbDAO.delFreePost(fbVO)) {
				forward.setRedirect(true);
				forward.setPath("pLists.do?category=Free");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('�� ���� ����!');history.go(-1);</script>");
			}
		}
		// ���͵� �Խ���
		else if(category.equals("Study")) {
			StudyBoDAO sbDAO=new StudyBoDAO();
			StudyBoVO sbVO=new StudyBoVO();
			sbVO.setPnum(pnum);
			sbVO.setMid(mid);
			if(sbDAO.delStudyPost(sbVO)) {
				forward.setRedirect(true);
				forward.setPath("pLists.do?category=Study");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('�� ���� ����!');history.go(-1);</script>");
			}
		} 
		// �������� �Խ���
		else if(category.equals("Notice")) {
			NoticeBoDAO nbDAO=new NoticeBoDAO();
			NoticeBoVO nbVO=new NoticeBoVO();
			nbVO.setPnum(pnum);
			nbVO.setMid(mid);
			if(nbDAO.delNoticePost(nbVO)) {
				forward.setRedirect(true);
				forward.setPath("pLists.do?category=Notice");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('�� ���� ����!');history.go(-1);</script>");
			}
		} 		
		
		return forward;
	}
	
}

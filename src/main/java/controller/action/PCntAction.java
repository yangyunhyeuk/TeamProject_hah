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

public class PCntAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// �� ��ȸ�� +1 �ϰ� pContent.do�� ���� �׼� �� //
		ActionForward forward=new ActionForward();
		
		// �信�� �޾ƿ;� �� ����
		String category=request.getParameter("category"); // ī�װ� �Ķ����
		int pnum=Integer.parseInt(request.getParameter("pnum")); // �Խñ� ��ȣ �Ķ����
		//System.out.println(category);
		//System.out.println(pnum);
		
		// ���� �Խ���
		if(category.equals("Free")) {
			FreeBoDAO fbDAO=new FreeBoDAO();
			FreeBoVO fbVO=new FreeBoVO();
			fbVO.setPnum(pnum);
			if(fbDAO.updateFreeCnt(fbVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Free&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('��ȸ�� ������Ʈ ����!');history.go(-1);</script>");
			}
		}
		// ���͵� �Խ���
		else if(category.equals("Study")) {
			StudyBoDAO sbDAO=new StudyBoDAO();
			StudyBoVO sbVO=new StudyBoVO();
			sbVO.setPnum(pnum);
			if(sbDAO.updateStudyCnt(sbVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Study&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('��ȸ�� ������Ʈ ����!');history.go(-1);</script>");
			}
		} 
		// �������� �Խ���
		else if(category.equals("Notice")) {
			NoticeBoDAO nbDAO=new NoticeBoDAO();
			NoticeBoVO nbVO=new NoticeBoVO();
			nbVO.setPnum(pnum);
			if(nbDAO.updateNoticeCnt(nbVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Notice&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('��ȸ�� ������Ʈ ����!');history.go(-1);</script>");
			}
		}		
		
		return forward;
	}
	
}

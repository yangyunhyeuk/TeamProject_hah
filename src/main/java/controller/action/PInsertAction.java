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
import model.member.MemDAO;
import model.member.MemVO;

public class PInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// �� �Խñ� �ۼ� ��ư ������ �信�� �޾ƿ� ���� DB�� �����ϴ� �׼� �� //
		ActionForward forward=new ActionForward();
		
		String category=request.getParameter("category"); // ī�װ� �Ķ����
		String mid=request.getParameter("mid"); // ���̵� �Ķ����
		// String mname=request.getParameter("mname"); // <--PWriteAction�� �ִٸ�,
		// �г��� ���� <-- pWriteAction�� ���ٸ�,
			MemVO mVO=new MemVO();
			mVO.setMid(mid);
			MemDAO mDAO=new MemDAO();
			String mname=mDAO.SelectOne(mVO).getMname();
				
		String ptitle=request.getParameter("ptitle");
		String pcontent=request.getParameter("pcontent");
		
		
		// ���� �Խ���
		if(category.equals("Free")) {
			FreeBoDAO fbDAO=new FreeBoDAO();
			FreeBoVO fbVO=new FreeBoVO();
			fbVO.setMid(mid);
			fbVO.setMname(mname);
			fbVO.setPtitle(ptitle);
			fbVO.setPcontent(pcontent);
			if(fbDAO.insertFreePost(fbVO)) {
				forward.setRedirect(true);
				forward.setPath("pLists.do?category=Free");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('�۾��� ����!');history.go(-1);</script>");
			}
		} 
		// ���͵� �Խ���
		else if(category.equals("Study")) {
			StudyBoDAO sbDAO=new StudyBoDAO();
			StudyBoVO sbVO=new StudyBoVO();
			sbVO.setMid(mid);
			sbVO.setMname(mname);
			sbVO.setPtitle(ptitle);
			sbVO.setPcontent(pcontent);
			if(sbDAO.insertStudyPost(sbVO)) {
				forward.setRedirect(true);
				forward.setPath("pLists.do?category=Study");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('�۾��� ����!');history.go(-1);</script>");
			}
		} 
		// �������� �Խ���
		else if(category.equals("Notice")) {
			NoticeBoDAO nbDAO=new NoticeBoDAO();
			NoticeBoVO nbVO=new NoticeBoVO();
			nbVO.setMid(mid);
			nbVO.setMname(mname);
			nbVO.setPtitle(ptitle);
			nbVO.setPcontent(pcontent);
			if(nbDAO.insertNoticePost(nbVO)) {
				forward.setRedirect(true);
				forward.setPath("pLists.do?category=Notice");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('�۾��� ����!');history.go(-1);</script>");
			}
		} 
		
		return forward;
	}

}

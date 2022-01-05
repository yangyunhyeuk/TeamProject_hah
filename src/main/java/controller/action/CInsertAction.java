package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.comment.FreeCoDAO;
import model.comment.FreeCoVO;
import model.comment.NoticeCoDAO;
import model.comment.NoticeCoVO;
import model.comment.StudyCoDAO;
import model.comment.StudyCoVO;
import model.member.MemDAO;
import model.member.MemVO;

public class CInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// �� ��� �ۼ� �׼� �� //
		ActionForward forward=new ActionForward();
		
		// �信�� �޾ƿ;� �� ����
		String category=request.getParameter("category"); // ī�װ� �Ķ����
		String comm=request.getParameter("comm"); // ��� ����
		int pnum=Integer.parseInt(request.getParameter("pnum")); // �Խñ� ��ȣ �Ķ����
		String mid=request.getParameter("mid"); // ���̵� �Ķ����
		
		// �г��� ����
			MemVO mVO=new MemVO();
			mVO.setMid(mid);
			MemDAO mDAO=new MemDAO();
		String mname=mDAO.SelectOne(mVO).getMname(); // �г���
		
		
		// ���� �Խ���
		if(category.equals("Free")) {
			FreeCoDAO fcDAO=new FreeCoDAO();
			FreeCoVO fcVO=new FreeCoVO();
			fcVO.setComm(comm);
			fcVO.setPnum(pnum);
			fcVO.setMid(mid);
			fcVO.setMname(mname);
			if(fcDAO.InsertDB(fcVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Free&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('��۾��� ����!');history.go(-1);</script>");
			}
		} 
		// ���͵� �Խ���
		else if(category.equals("Study")) {
			StudyCoDAO scDAO=new StudyCoDAO();
			StudyCoVO scVO=new StudyCoVO();
			scVO.setComm(comm);
			scVO.setPnum(pnum);
			scVO.setMid(mid);
			scVO.setMname(mname);
			if(scDAO.InsertDB(scVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Study&pnum="+pnum);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('��۾��� ����!');history.go(-1);</script>");
			}
		} 
		// �������� �Խ���
		else if(category.equals("Notice")) {
			NoticeCoDAO ncDAO=new NoticeCoDAO();
			NoticeCoVO ncVO=new NoticeCoVO();
			ncVO.setComm(comm);
			ncVO.setPnum(pnum);
			ncVO.setMid(mid);
			ncVO.setMname(mname);
			if(ncDAO.InsertDB(ncVO)) {
				forward.setRedirect(true);
				forward.setPath("pContent.do?category=Notice&pnum="+pnum);
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

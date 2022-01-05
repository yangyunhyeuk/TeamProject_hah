package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemDAO;
import model.member.MemVO;
import web.mail.MailSend;

public class MailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;
		System.out.println("���� �׼� ����" + request.getParameter("uid"));
		System.out.println("���� �׼� ����" + request.getParameter("mymail"));

		MemDAO mDAO = new MemDAO();
		MemVO mVO = new MemVO();
		String mid = request.getParameter("uid");

		// public MemVO SelectOne(MemVO vo) {
		mVO.setMid(mid);

		if (mDAO.SelectOne(mVO) == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�������� �ʴ� ���̵��Դϴ�.');history.go(-1);</script>");
		} else {
			MailSend sender = new MailSend();
			String mcontent = sender.MailSend((String) request.getParameter("mymail"));
			mVO.setMpw(mcontent);
			// ������Ʈ
			mDAO.UpdatePW(mVO);

			System.out.println(mcontent);

		}

		return forward;
	}

}

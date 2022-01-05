package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.mail.MailAuth;

public class QAMailSendAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;
		
		PrintWriter out = response.getWriter();

		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content"); // 이메일
		//String content2 = request.getParameter("content2"); // 문의내용

		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new MailAuth();

		Session session = Session.getDefaultInstance(prop, auth);

		MimeMessage msg = new MimeMessage(session);

		try {

			session.setDebug(true);

			msg.setSubject(subject); // 제목
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr); // 보내는 사람

			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

			msg.setContent("<h4>답변받을 메일주소 :"+fromAddr+"</h4><hr><h3><b>문의내용</b></h3><br><p>"+content+"</p>", "text/html;charset=UTF-8"); // 내용과 인코딩
		//<h3>"+fromAddr+"님의 문의내용입니다.</h3><br><hr><h4>문의내용</h4>"+content
			//msg.setContent(content2, "text/html;charset=UTF-8"); // 내용과 인코딩

			Transport.send(msg); // 전송


		} catch (Exception e) {
			e.printStackTrace();
			out.println("<script>alert('[Fail] Sorry T^T');history.back();</script>");
			// 오류 발생시 뒤로 돌아가도록
		}

		out.println("<script>alert('[Success]Thanks!');location.href='contact.jsp';</script>");
		// 성공 시
	
		return forward;
	}

}

package web.mail;

import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;

public class MailSend {

	public String MailSend(String mymail) {

		String mcontent = null;

		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new MailAuth();

		Session session = Session.getDefaultInstance(prop, auth);

		MimeMessage msg = new MimeMessage(session);

		mcontent = RandomStringUtils.randomAlphanumeric(15);

		try {
			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress(mymail, "HAH 관리자"));
			InternetAddress to = new InternetAddress(mymail);

			mcontent = RandomStringUtils.randomAlphanumeric(15);

			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject("new_임시비밀번호입니다.", "UTF-8");
			msg.setText(mcontent, "UTF-8");

			Transport.send(msg);

		} catch (AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
		} catch (MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
		}

		return mcontent;
	}
}

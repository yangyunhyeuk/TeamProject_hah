package web.mail;

import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator {

	PasswordAuthentication pa;

	public MailAuth() {
		String mail_id = "jahamon05@gmail.com"; // 
		String mail_pw = "uxxbhigncbkzsxah"; // 

		pa = new PasswordAuthentication(mail_id, mail_pw);
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}

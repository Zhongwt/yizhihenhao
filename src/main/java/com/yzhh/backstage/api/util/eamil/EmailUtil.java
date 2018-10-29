package com.yzhh.backstage.api.util.eamil;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	private static String smtpServer = "smtp.mxhichina.com";
	private static String port = "465";
	private static String fromUserName = "zhongwentao@yizhihenhao.com";
	private static String fromUserPassword = "Zxc123456";
	private static String sign = "一职很好";
	
	@SuppressWarnings("restriction")
	public static void sendMail(String toMail,String subject,String content) {
		try{
			// 设置SSL连接、邮件环境
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			Properties props = System.getProperties();
			props.setProperty("mail.smtp.host", smtpServer);
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", port);
			props.setProperty("mail.smtp.socketFactory.port", port);
			props.setProperty("mail.smtp.auth", "true");
			// 建立邮件会话
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				// 身份认证
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromUserName, fromUserPassword);
				}
			});
			// 建立邮件对象
			MimeMessage message = new MimeMessage(session);
			// 设置邮件的发件人、收件人、主题
			// 附带发件人名字
			// message.setFrom(new InternetAddress("from_mail@qq.com",
			// "optional-personal"));
			String nick=javax.mail.internet.MimeUtility.encodeText(sign);  
			message.setFrom(new InternetAddress(nick+" <"+fromUserName+">"));
			message.setRecipients(Message.RecipientType.TO, toMail);
			message.setSubject(subject);
			// 文本
			message.setContent(content,  "text/html;charset=gbk");
			message.setSentDate(new Date());
			message.saveChanges();
			// 发送邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EmailUtil.sendMail("245473357@qq.com",  "验证码", "您的验证码为："+2323+"，请不要告诉任何人！");
	}

}

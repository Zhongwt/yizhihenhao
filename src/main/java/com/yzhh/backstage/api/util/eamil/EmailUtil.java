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

public class EmailUtil {
	
 
	public static void sendHtmlMail() {
		try {
	        //设置SSL连接、邮件环境
	        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	        Properties props = System.getProperties();
	        props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
	        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
	        props.setProperty("mail.smtp.port", "465");
	        props.setProperty("mail.smtp.socketFactory.port", "465");
	        props.setProperty("mail.smtp.auth", "true");
	        //建立邮件会话
	        Session session = Session.getDefaultInstance(props, new Authenticator() {
	            //身份认证
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("postmaster@yizhihenhao.com", "Yzhh123yzhh");
	            }
	        });
	        //建立邮件对象
	        MimeMessage message = new MimeMessage(session);
	        //设置邮件的发件人、收件人、主题
	        //附带发件人名字
//	        message.setFrom(new InternetAddress("from_mail@qq.com", "optional-personal"));
	        message.setFrom(new InternetAddress("postmaster@yizhihenhao.com"));
	        message.setRecipients(Message.RecipientType.TO, "245473357@qq.com");
	        message.setSubject("subject test");
	        //文本
	        String content="mail content test";
	        message.setText(content);
	        message.setSentDate(new Date());
	        message.saveChanges();
	        //发送邮件
	        Transport.send(message);
		} catch (Exception e) {
            System.out.println(e.toString());
        }

	}
	
	public static void main(String[] args) {
		EmailUtil.sendHtmlMail();
	}

}


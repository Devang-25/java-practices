package com.preety.rest.webservices.fileapis.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class UtilSMTPMail {

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Send Mail in using SMTP without authentication
	public void SimpleEmail() {
		String smtpHostServer = "smtp.example.com";
		String emailID = "email_me@example.com";

		Properties props = System.getProperties();
		/*
		 * We need to set the mail.smtp.host property with the SMTP server host. If the
		 * SMTP server is not running on default port (25), then you will also need to
		 * set mail.smtp.port property.
		 */

		props.put("mail.smtp.host", smtpHostServer);

		Session session = Session.getInstance(props, null);

		sendEmail(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

	/*
	 * The program is simple to understand and works well, but in real life most of
	 * the SMTP servers use some sort of authentication such as TLS or SSL
	 * authentication. So we will now see how to create Session object for these
	 * authentication protocols.
	 */

	// Send Email in SMTP with TLS Authentication
	public void TLSEmail() {
		/**
		 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
		 * authentication) Use Authentication: Yes 
		 * Port for TLS/STARTTLS: 587
		 */

		final String fromEmail = "myemailid@gmail.com"; // requires valid gmail id
		final String password = "mypassword"; // correct password for gmail id
		final String toEmail = "myemail@yahoo.com"; // can be any email id

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body");

	}

	public void SSLEmail() {

		/**
		 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
		 * authentication) Use Authentication: Yes 
		 * Port for SSL: 465
		 */
		final String fromEmail = "myemailid@gmail.com"; // requires valid gmail id
		final String password = "mypassword"; // correct password for gmail id
		final String toEmail = "myemail@yahoo.com"; // can be any email id

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		sendEmail(session, toEmail, "SSLEmail Testing Subject", "SSLEmail Testing Body");

		sendAttachmentEmail(session, toEmail, "SSLEmail Testing Subject with Attachment",
				"SSLEmail Testing Body with Attachment");

		sendImageEmail(session, toEmail, "SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");

	}

	// send mail in with attachment

	/**
	 * Utility method to send email with attachment
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public void sendAttachmentEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Create - Second part is attachment
			BodyPart messageAttachmentPart = new MimeBodyPart();
			String filename = "abc.txt";
			DataSource source = new FileDataSource(filename);
			messageAttachmentPart.setDataHandler(new DataHandler(source));
			messageAttachmentPart.setFileName(filename);
			multipart.addBodyPart(messageAttachmentPart);

			// Send the complete message parts
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			System.out.println("EMail Sent Successfully with attachment!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	// send mail in java with image
	/**
	 * Utility method to send image in email body
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public void sendImageEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "image.png";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "image_id");
			multipart.addBodyPart(messageBodyPart);

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h1>Attached Image</h1>" + "<img src='cid:image_id'>", "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Set the multipart message to the email message
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			System.out.println("EMail Sent Successfully with image!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
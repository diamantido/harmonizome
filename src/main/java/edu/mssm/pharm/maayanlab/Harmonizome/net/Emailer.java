package edu.mssm.pharm.maayanlab.Harmonizome.net;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {
	
	private static final Properties props = new Properties();
	static {
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", System.getenv("SMTP_PORT")); //"587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	}

	public static void send(String user, String topic, String body) throws UnsupportedEncodingException {
        final String host = System.getenv("SMTP_HOST");
		final String botEmail = System.getenv("FROM_EMAIL");
        final String botUser = System.getenv("FROM_USER");
		final String botPassword = System.getenv("FROM_PASS");
        String avi = System.getenv("EMAIL_TO");
        String[] cc = System.getenv("EMAIL_CC").split(",");
		String daniel = cc[0];
		String ido = cc[1];
		
        Session session = Session.getDefaultInstance(props);

        StringBuilder finalMessage = new StringBuilder();
        finalMessage.append("From: " + user);
        finalMessage.append(System.lineSeparator());
        finalMessage.append("Topic: " + topic);
        finalMessage.append(System.lineSeparator());
        finalMessage.append(System.lineSeparator());
        finalMessage.append(body);
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(botEmail, "Harmonizome Bot"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(avi));
            msg.addRecipient(Message.RecipientType.CC, new InternetAddress(daniel));
            msg.addRecipient(Message.RecipientType.CC, new InternetAddress(ido));
            msg.setSubject("Feedback from user " + user);
            msg.setText(finalMessage.toString());

            Transport transport = session.getTransport();
            transport.connect(host, botUser, botPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
        	e.printStackTrace();
        } catch (MessagingException e) {
        	e.printStackTrace();
        }
	}
}

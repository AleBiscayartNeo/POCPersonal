package org.neoris.beneficios.utils;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
	private AppConfig appConfiguration;

	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public void sendEmail(List<String> to, String user, String mensaje){

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(appConfiguration.getMailUser(), appConfiguration.getMailPassword());
					}
				});
		
		System.out.println("Email user :" +  appConfiguration.getMailUser());
		System.out.println("Email pass :" +  appConfiguration.getMailPassword());

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(appConfiguration.getMailUser()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(joinAddresses(to)));
			message.setSubject(appConfiguration.getSubject());
			System.out.println("Email subject :" +  appConfiguration.getSubject());
			message.setText("User: " + user
					+ "\n\nFeedback: " + mensaje);
			System.out.println("Email user text :" +  user);
			System.out.println("Email mensaje :" +  mensaje);
			Transport.send(message);
			System.out.println("El email se envió correctamente.");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private String joinAddresses(List<String> to){
		
		StringBuilder targetAddresses = new StringBuilder();
		for (String targetTo : to) {
			targetAddresses.append(targetTo).append(",");
			System.out.println("Email To: " +  targetTo);
		}
		
		return targetAddresses.substring(0, targetAddresses.length() - 1);
	}

}
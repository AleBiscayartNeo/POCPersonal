/**
 * 
 */
package org.neoris.beneficios.services;

import java.util.ArrayList;
import java.util.List;

import org.neoris.beneficios.dao.UserDAO;
import org.neoris.beneficios.entity.UserEmail;
import org.neoris.beneficios.utils.EmailUtil;
import org.neoris.beneficios.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EmailUtil emailUtil;


	public boolean enviarEmail(String user, String mensaje) {
		boolean respuesta = false;
		List<UserEmail> userEmails = userDAO.getUsersEmail();
		List<String> targetTo = new ArrayList<String>();
		for (UserEmail userEmail : userEmails) {
			targetTo.add(userEmail.getEmail());
		}
		try{
			emailUtil.sendEmail(targetTo, user, mensaje);
			
			respuesta = true;
			System.out.println("Respuesta = true");
		} catch (Exception e){
			e.printStackTrace();
		}
		return respuesta;
	}

	@Bean
	public EmailUtil getEmailUtil() {
		return new EmailUtil();
	}
}

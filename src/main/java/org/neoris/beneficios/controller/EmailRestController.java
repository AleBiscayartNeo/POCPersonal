/**
 * 
 */
package org.neoris.beneficios.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.neoris.beneficios.request.EmailRequest;
import org.neoris.beneficios.response.ServiceResponse;
import org.neoris.beneficios.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class EmailRestController {
	
	@Autowired(required=true)
	@Qualifier("emailService")
	private EmailService emailService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping("/email")
	public ServiceResponse enviarMail(@RequestBody EmailRequest request){
		
		String user = request.getUser();
		String mensaje = request.getMensaje();
		ServiceResponse response = new ServiceResponse();
		
		Boolean emailSuccess = emailService.enviarEmail(user, mensaje); 
		System.out.println("ENTRA AL CONTROLLER.");
		if(emailSuccess){
			response.setData("OK");
			response.setCode("0");
			System.out.println("Respuesta OK del controller");
		} else {
			response.setCode("1");
			response.setMessage("Error enviando email.");
			System.out.println("Respuesta KO del controller");
		}
		return response;
	}
	
	
}

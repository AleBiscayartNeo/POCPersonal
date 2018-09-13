package org.neoris.beneficios.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.json.JSONObject;
import org.neoris.beneficios.response.ServiceResponse;
import org.neoris.beneficios.utils.HeaderRequestInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/services")
public class NotificacionRestController {
	
	private static final String FIREBASE_SERVER_KEY = "AAAAKjXKv5o:APA91bHAnqzcNj0GjJ_kv0IAJ4GXI9efLjuoHErb0IAckhhR92lHnZQmNfZVtFJfVE-zJzM5SUJDcxk5vNcGBRCSjVggoTDkpa0P-V5vMYnZFmzb_wDuIomNHv0ON6cavI-vd6QRJhsr";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping("/notification")
	public Response sendNotification(@FormParam("titulo") String titulo, @FormParam("mensaje") String mensaje)
			throws Exception {

		ServiceResponse serviceResponse = new ServiceResponse();

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		JSONObject body = new JSONObject();
		body.put("to", "/topics/info");
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", titulo);
		notification.put("body", mensaje);

		body.put("notification", notification);

		HttpEntity<String> request = new HttpEntity<String>(body.toString());

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, request, String.class);

		if (firebaseResponse.contains("failure")) {
			serviceResponse.setCode("1");
			serviceResponse.setData(firebaseResponse);
			serviceResponse.setMessage("Error al enviar la notificación");
		} else {
			serviceResponse.setCode("0");
			serviceResponse.setMessage("Notificación enviada con éxito");
		}

		return Response.ok(serviceResponse).build();

	}
}

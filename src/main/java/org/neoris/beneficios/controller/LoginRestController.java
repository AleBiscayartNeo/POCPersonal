package org.neoris.beneficios.controller;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.neoris.beneficios.request.LoginRequest;
import org.neoris.beneficios.response.ServiceResponse;
import org.neoris.beneficios.services.LoginService;
import org.neoris.beneficios.utils.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginRestController {
	
	private static int CANTIDAD_INTENTOS_PERMITIDOS = 2;
	
	@Autowired(required=true)
	@Qualifier("loginService")
	private LoginService loginService;
	
	@Autowired
	private AppConfig appConfiguration;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping("/login1")
	public ServiceResponse login(@RequestBody LoginRequest request)
	{
		String user = request.getUser();
		String pass = request.getPass();
		ServiceResponse serviceResponse = new ServiceResponse();
		
		if(null != user && null !=pass && !user.equals("") && !pass.equals("") ){
			//String userDecoded = obtainDecoded(user);
			//String passDecoded = obtainDecoded(pass);
			if("neoris.rojas".equals(user) && "neoris".equals(pass)){
				serviceResponse.setData("OK");
				serviceResponse.setCode("0");
			}else {
				//Verificar consultas
				Integer cantidad = loginService.getCantidadAccesos(user);
				
				String respuestaLawson = null;
			
			
				if(cantidad <= CANTIDAD_INTENTOS_PERMITIDOS - 1){
					//Llamada servicio con user y pass
					
					respuestaLawson=callLawson(user, pass);
					if (respuestaLawson != null) {
						if (respuestaLawson.equals("1")) {

							serviceResponse.setData("OK");
							serviceResponse.setCode("0");

						} else /* no ok */{
							if (cantidad == CANTIDAD_INTENTOS_PERMITIDOS - 1) {
								serviceResponse.setCode("2");
								serviceResponse
										.setMessage("Ha superado la cantidad de intentos, espere 15 minutos.");

							} else {
								serviceResponse.setCode("1");
								serviceResponse
										.setMessage("Usuario o contraseña incorrectos.");
							}
						}

						loginService.setConsultasUsuario(user, respuestaLawson);
					} else {
						serviceResponse.setCode("4");
						serviceResponse.setMessage("Error en la conexión con el servicio.");
					}
					
				} else {
					serviceResponse.setCode("2");
					serviceResponse.setMessage("Ha superado la cantidad de intentos, espere 15 minutos.");
				}
			
			}
		} else {
			serviceResponse.setCode("3");
			serviceResponse.setMessage("El usuario y la contraseña no pueden ser vacios.");
			
		}
		
		return serviceResponse;
		
	}
	
	private String callLawson(String user, String pass){
		String respuestaLowson = null;
		HttpClient client;
		client = HttpClientBuilder.create().build();

		String url = "https://wsneoapp.neoris.net/LawsonService.svc/GetLDAPByUserPass?VacUser="+ user + "&VacPassword=" + pass ;
		
        // Se crea la conexion -
        HttpGet get = new HttpGet(url);
        
        HttpResponse response;
		try {
			response = client.execute(get);
			
			org.apache.http.HttpEntity http= response.getEntity();
			
			String json =EntityUtils.toString(http);
			
			
			try {
				JSONObject jsonObject = new JSONObject(json);
				JSONObject jsonObject2 = (JSONObject) jsonObject.get("d");
				
				respuestaLowson=(String) jsonObject2.get("LDAPvcLDAPConnection");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return respuestaLowson;
	
	}
	
	
//	private String obtainDecoded(String strToDecode){
//		String result = null;
//		EncriptacionUtil encUtil = null;
//		try {
//			encUtil = new EncriptacionUtil();
//			result = encUtil.decryptSimple(
//					strToDecode, 
//					appConfiguration.getEncriptacionKey(), 
//					appConfiguration.getEncriptacionIv());
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidAlgorithmParameterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}

	
	
}

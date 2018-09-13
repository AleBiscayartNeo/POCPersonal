package org.neoris.beneficios.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.neoris.beneficios.entity.Oficina;
import org.neoris.beneficios.response.ServiceResponse;
import org.neoris.beneficios.services.OficinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OficinasRestController {
	
	@Autowired(required=true)
	@Qualifier("oficinasService")
	private OficinasService oficinasService;
	
//	@Cacheable("oficinasCache")
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping("/oficinas/all")
	public ServiceResponse getOficinas(){
		
		ServiceResponse serviceResponse = new ServiceResponse();	 
		try{		
		
		ArrayList<Oficina> oficinas = oficinasService.getOficinas();
		serviceResponse.setData(oficinas);
		serviceResponse.setCode("0");
		
		} catch (Exception e){
			e.printStackTrace();
			serviceResponse.setCode("1");
			serviceResponse.setMessage("Ha ocurrido un error al consultar los datos, intente más tarde.");
		} finally {
		return serviceResponse;
		}
	}

	
}

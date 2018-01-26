/**
 * 
 */
package com.personal.beneficios.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.personal.beneficios.entity.Provincia;
import com.personal.beneficios.repository.ProvinciaRepository;

/**
 * The class ProvinciaRestController.
 *
 */
@Component
@Path("/provincias")
public class ProvinciaRestController {

	@Autowired(required=true)
	@Qualifier("provinciaRepository")
	private ProvinciaRepository provinciaRepository;
		
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getProvincias(){
		ArrayList<Provincia> provincias = null;
		
		provincias = provinciaRepository.getProvincias();
		
		return Response.ok(provincias).build();
	}
}

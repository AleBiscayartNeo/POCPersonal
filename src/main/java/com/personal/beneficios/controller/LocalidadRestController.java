/**
 * 
 */
package com.personal.beneficios.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.personal.beneficios.entity.Localidad;
import com.personal.beneficios.repository.LocalidadRepository;


/**
 * The class LocalidadRestController.
 *
 */
@Component
@Path("/localidad")
public class LocalidadRestController {

	@Autowired(required=true)
	@Qualifier("localidadRepository")
	private LocalidadRepository localidadRepository;
		
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idProvincia}")
	public Response getLocalidadPorProvincia(@PathParam(value = "idProvincia") Integer idProvincia){
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
				
			localidades = localidadRepository.getLocalidadPorProvincia(100);		
		
		return Response.ok(localidades).build();
	}
}

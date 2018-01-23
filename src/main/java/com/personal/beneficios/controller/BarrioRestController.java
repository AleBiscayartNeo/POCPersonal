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

import com.personal.beneficios.entity.Barrio;
import com.personal.beneficios.repository.BarrioRepository;


/**
 * The class ProvinciaRestController.
 *
 */
@Component
@Path("/barrio")
public class BarrioRestController {

	@Autowired(required=true)
	@Qualifier("barrioRepository")
	private BarrioRepository barrioRepository;
		
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idLocalidad}")
	public Response getBarrioPorLocalidad(@PathParam(value = "idLocalidad") Integer idLocalidad){
		ArrayList<Barrio> barrios = new ArrayList<Barrio>();
				
		barrios = barrioRepository.getBarrioPorLocalidad(idLocalidad);		
		
		return Response.ok(barrios).build();
	}
}

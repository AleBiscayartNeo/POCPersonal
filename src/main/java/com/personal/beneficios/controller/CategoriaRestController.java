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

import com.personal.beneficios.entity.Categoria;
import com.personal.beneficios.repository.CategoriaRepository;

/**
 * The class CategoriaRestController.
 *
 */
@Component
@Path("/categorias")
public class CategoriaRestController {

	@Autowired(required=true)
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;
		
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getCategorias(){
		ArrayList<Categoria> categorias = null;
		
		categorias = categoriaRepository.getCategorias();
		
		return Response.ok(categorias).build();
	}
}

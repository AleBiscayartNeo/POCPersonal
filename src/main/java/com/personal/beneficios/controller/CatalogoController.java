package com.personal.beneficios.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.personal.beneficios.dto.CatalogoPorNivelDTO;
import com.personal.beneficios.repository.CatalogoRepository;

@Component
@Path("/catalogo")
public class CatalogoController {
	
	@Autowired(required=true)
	@Qualifier("catalogoRepository")
	private CatalogoRepository catalogoRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/filtrado")
	public Response getCatalogoFiltrado(@QueryParam("idNivel") Integer idNivel, @QueryParam("idCategoria") Integer idCategoria){
		CatalogoPorNivelDTO catalogo = catalogoRepository.getCatalogoFiltrado(idNivel, idCategoria);
		return Response.ok(catalogo).build();
	}

}

package com.personal.beneficios.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.personal.beneficios.dto.CatalogoPorNivelDTO;
import com.personal.beneficios.repository.CatalogoRepository;

@Component
@Path("/catalogo")
public class CatalogoController {
	
	@Autowired(required=true)
	@Qualifier("catalogoRepository")
	private CatalogoRepository catalogoRepository;
	
	@Cacheable("catalogoCache")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/filtrado")
	public Response getCatalogoFiltrado(@QueryParam("idNivel") Integer idNivel, @QueryParam("idCategoria") Integer idCategoria){
		CatalogoPorNivelDTO catalogo = catalogoRepository.getCatalogoFiltrado(idNivel, idCategoria);
		return Response.ok(catalogo).build();
	}

}

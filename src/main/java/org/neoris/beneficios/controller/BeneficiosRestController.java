
package org.neoris.beneficios.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.neoris.beneficios.dto.BeneficioCategoriaDTO;
import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.response.ServiceResponse;
import org.neoris.beneficios.services.BeneficiosService;
import org.neoris.beneficios.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beneficios/")
public class BeneficiosRestController {
	
	@Autowired(required=true)
	@Qualifier("beneficiosService")
	private BeneficiosService beneficiosService;
	
	@Autowired(required=true)
	@Qualifier("loginService")
	private LoginService loginService;
	
	//@Cacheable("beneficiosCache")
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping("{categoriaId}/{oficinaId}")
	public ServiceResponse getBeneficios(
			@PathVariable Integer categoriaId,
			@PathVariable Integer oficinaId) {

		ServiceResponse serviceResponse = new ServiceResponse();

		
		try {
			ArrayList<Beneficio> beneficios = beneficiosService
					.getBeneficiosOficina(categoriaId, oficinaId);
			serviceResponse.setData(beneficios);
 			serviceResponse.setCode("0");

		} catch (Exception e) {
			serviceResponse.setCode("1");
			serviceResponse
					.setMessage("Ha ocurrido un error al consultar los datos, intente más tarde.");
		} finally {
			return serviceResponse;
		}
	
	}	
	
	@Cacheable("categoriasCache")
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "categorias/{idOficina}")
	public ServiceResponse getCantidadCategorias(@PathVariable Integer idOficina) {
		

//	    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
//	    String getDynamicUriValue(@PathVariable String id) {

		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			
			ArrayList<BeneficioCategoriaDTO> categorias= beneficiosService.getCantidadPorCategoria(idOficina);

			serviceResponse.setData(categorias);
			serviceResponse.setCode("0");

		} catch (Exception e) {
			serviceResponse.setCode("1");
			serviceResponse
					.setMessage("Ha ocurrido un error al consultar los datos, intente más tarde.");
		} finally {
			return serviceResponse;
		}
	}

	
}

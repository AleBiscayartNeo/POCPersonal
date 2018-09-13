package org.neoris.beneficios.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.neoris.beneficios.response.ServiceResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheResetController {


	@Caching(evict = {@CacheEvict(value="oficinasCache", allEntries=true), @CacheEvict (value="beneficiosCache", allEntries=true), @CacheEvict (value="categoriasCache", allEntries=true)})
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping("/reset")
	public ServiceResponse resetCache(){
		ServiceResponse serviceResponse = new ServiceResponse();
		return serviceResponse;	
		
	}
	
}

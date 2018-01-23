/**
 * 
 */
package com.personal.beneficios.controller;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
}

package org.neoris.beneficios.controller;

import javax.ws.rs.Path;

import org.neoris.beneficios.services.RetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("/retos")
public class RetosRestController {

	@Autowired(required=true)
	@Qualifier("retosService")
	private RetosService retosService;
}

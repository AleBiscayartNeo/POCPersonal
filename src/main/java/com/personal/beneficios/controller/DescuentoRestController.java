/**
 * 
 */
package com.personal.beneficios.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.personal.beneficios.dto.DescuentoDTO;
import com.personal.beneficios.entity.Categoria;
import com.personal.beneficios.entity.Descuento;
import com.personal.beneficios.entity.Nivel;
import com.personal.beneficios.entity.Proveedor;
import com.personal.beneficios.entity.Provincia;
import com.personal.beneficios.repository.DescuentoRepository;

/**
 * The class DescuentoRestController.
 *
 */
@Component
@Path("/descuento")
public class DescuentoRestController {

	@Autowired(required=true)
	@Qualifier("descuentoRepository")
	private DescuentoRepository descuentoRepository;
		
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getDescuentos(){
		ArrayList<Descuento> descuentos = null;
		
		descuentos = descuentoRepository.getDescuentos();
		
		return Response.ok(descuentos).build();
	}
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idDescuento}")
	public Response getDescuentoPorID(@PathParam(value = "idDescuento")Integer idDescuento){
		Descuento descuento;
		
		descuento = descuentoRepository.getDescuentoPorID(idDescuento);
		
		return Response.ok(descuento).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nuevo")
	public Response agregarDescuento(DescuentoDTO descuento){
		descuentoRepository.agregarDescuento(descuento);
		return Response.status(Status.OK).entity(descuento).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editar")
	public Response editarDescuento(DescuentoDTO descuento){
		descuentoRepository.editarDescuento(descuento);
		return Response.status(Status.OK).entity(descuento).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/eliminar")
	public Response elminarDescuento(@QueryParam(value="idDescuento") Integer idDescuento){
		
		descuentoRepository.eliminarDescuento(idDescuento);
		
		return Response.status(Status.OK).entity(idDescuento).build();
	}
	
}

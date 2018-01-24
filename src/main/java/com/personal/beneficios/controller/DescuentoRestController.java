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
	
	@Path("/nuevo")
	public void agregarDescuento(DescuentoDTO descuento){
		
		Descuento descuentoNuevo = new Descuento();
		descuentoNuevo.setId(descuento.getId());
		descuentoNuevo.setNombre(descuento.getNombre());
		descuentoNuevo.setDescripcion(descuento.getDescripcion());
		descuentoNuevo.setDescripcionCorta(descuento.getDescripcionCorta());
		descuentoNuevo.setVigenciaDesde(descuento.getVigenciaDesde());
		descuentoNuevo.setVigenciaHasta(descuento.getVigenciaHasta());
		descuentoNuevo.setImagen(descuento.getImagen());
		descuentoNuevo.setLegales(descuento.getLegales());
		
		Nivel nivel = new Nivel();
		nivel.setId(descuento.getIdNivel());
		descuentoNuevo.setNivel(nivel);
		
		Proveedor proveedor = new Proveedor();
		proveedor.setId(descuento.getIdProveedor());
		descuentoNuevo.setProveedor(proveedor);
		
		Categoria categoria = new Categoria();
		categoria.setId(descuento.getIdCategoria());
		descuentoNuevo.setCategoria(categoria);
		
		descuentoRepository.agregarDescuento(descuentoNuevo);
		
	}
}

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
import com.personal.beneficios.dto.ProveedorDTO;
import com.personal.beneficios.entity.Categoria;
import com.personal.beneficios.entity.Descuento;
import com.personal.beneficios.entity.Nivel;
import com.personal.beneficios.entity.Proveedor;
import com.personal.beneficios.repository.ProveedorRepository;

/**
 * The class DescuentoRestController.
 *
 */
@Component
@Path("/proveedor")
public class ProveedorRestController {

	@Autowired(required=true)
	@Qualifier("proveedorRepository")
	private ProveedorRepository proveedorRepository;
		
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getProveedores(){
		ArrayList<Proveedor> proveedores = null;
		
		proveedores = proveedorRepository.getProveedores();
		
		return Response.ok(proveedores).build();
	}
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idProveedor}")
	public Response getProveedorPorID(@PathParam(value = "idProveedor")Integer idProveedor){
		Proveedor proveedor;
		
		proveedor = proveedorRepository.getProveedoresPorID(idProveedor);
		
		return Response.ok(proveedor).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nuevo")
	public Response agregarProveedor(ProveedorDTO proveedor){
		
		Proveedor proveedorNuevo = new Proveedor();
		cargarProveedor(proveedor, proveedorNuevo);		
		proveedorRepository.agregarProveedor(proveedorNuevo);
		
		return Response.status(Status.OK).entity(proveedor).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editar")
	public Response editarProveedor(ProveedorDTO proveedor){
		
		Proveedor proveedorEditar = new Proveedor();
		proveedorEditar.setId(proveedor.getIdProveedor());
		cargarProveedor(proveedor, proveedorEditar);
		proveedorRepository.editarProveedor(proveedorEditar);
		
		return Response.status(Status.OK).entity(proveedor).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/eliminar")
	public Response elminarProveedor(@QueryParam(value="idproveedor") Integer idproveedor){
		
		Proveedor proveedorEliminar = new Proveedor();
		proveedorEliminar.setId(idproveedor);
		
		proveedorRepository.eliminarProveedor(proveedorEliminar);
		
		return Response.status(Status.OK).entity(proveedorEliminar).build();
	}
	
	private void cargarProveedor(ProveedorDTO proveedor, Proveedor proveedorNuevo){
		proveedorNuevo.setLogo(proveedor.getLogo());
		proveedorNuevo.setHorarioAtencion(proveedor.getHorarioAtencion());
		proveedorNuevo.setRazonSocial(proveedor.getRazonSocial());
		
	}
}

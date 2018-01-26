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

import com.personal.beneficios.dto.SucursalDTO;
import com.personal.beneficios.entity.Localidad;
import com.personal.beneficios.entity.Proveedor;
import com.personal.beneficios.entity.Provincia;
import com.personal.beneficios.entity.Sucursal;
import com.personal.beneficios.repository.SucursalRepository;



/**
 * The class sucursalRestController.
 *
 */
@Component
@Path("/sucursal")
public class SucursalRestController {

	@Autowired(required=true)
	@Qualifier("sucursalRepository")
	private SucursalRepository sucursalRepository;
		
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getSucursales(){
		ArrayList<Sucursal> sucursales = null;
		
		sucursales = sucursalRepository.getSucursales();
		
		return Response.ok(sucursales).build();
	}
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idSucursal}")
	public Response getSucursalPorID(@PathParam(value = "idSucursal")Integer idSucursal){
		Sucursal sucursal;
		
		sucursal = sucursalRepository.getSucursalPorID(idSucursal);
		
		return Response.ok(sucursal).build();
	}
	
	@SuppressWarnings("finally")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/proveedor/{idProveedor}")
	public Response getSucursalPorIdProveedor(@PathParam(value = "idProveedor")Integer idProveedor){
		ArrayList<Sucursal> sucursales;
		
		sucursales = sucursalRepository.getSucursalPorIdProveedor(idProveedor);
		
		return Response.ok(sucursales).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nuevo")
	public Response agregarSucursal(SucursalDTO sucursal){
		sucursalRepository.agregarSucursal(sucursal);
		return Response.status(Status.OK).entity(sucursal).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editar")
	public Response editarSucursal(SucursalDTO sucursal){
		sucursalRepository.editarSucursal(sucursal);
		return Response.status(Status.OK).entity(sucursal).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/eliminar")
	public Response elminarSucursal(@QueryParam(value="idSucursal") Integer idsucursal){
		sucursalRepository.eliminarSucursal(idsucursal);
		return Response.status(Status.OK).entity(idsucursal).build();
	}
}

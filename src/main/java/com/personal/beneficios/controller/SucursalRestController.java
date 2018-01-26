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
import com.personal.beneficios.entity.Barrio;
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
		
		Sucursal sucursalNuevo = new Sucursal();
		cargarSucursal(sucursal, sucursalNuevo);
		
		sucursalRepository.agregarSucursal(sucursalNuevo);
		
		return Response.status(Status.OK).entity(sucursalNuevo).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editar")
	public Response editarSucursal(SucursalDTO sucursal){
		
		Sucursal sucursalEditar = new Sucursal();
		sucursalEditar.setId(sucursal.getId());
		cargarSucursal(sucursal, sucursalEditar);
		
		sucursalRepository.editarSucursal(sucursalEditar);
		
		return Response.status(Status.OK).entity(sucursalEditar).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/eliminar")
	public Response elminarSucursal(@QueryParam(value="idSucursal") Integer idsucursal){
		sucursalRepository.eliminarSucursal(idsucursal);
		return Response.status(Status.OK).entity(idsucursal).build();
	}
	
	private void cargarSucursal(SucursalDTO sucursal, Sucursal sucursalNuevo){
		
		sucursalNuevo.setCalle(sucursal.getCalle());
		sucursalNuevo.setNumero(sucursal.getNumero());
		sucursalNuevo.setInformacionAdicional(sucursal.getInformacionAdicional());	
		sucursalNuevo.setTelefono(sucursal.getTelefono());
		sucursalNuevo.setLongitud(sucursal.getLongitud());
		sucursalNuevo.setLatitud(sucursal.getLatitud());
		
		Provincia provincia = new Provincia();
		provincia.setId(sucursal.getIdProvincia());
		sucursalNuevo.setProvincia(provincia);
		
		Localidad localidad =  new Localidad();
		localidad.setId(sucursal.getIdLocalidad());
		sucursalNuevo.setLocalidad(localidad);
		
		Barrio barrio = new Barrio();
		barrio.setId(sucursal.getIdBarrio());
		sucursalNuevo.setBarrio(barrio);
		
		Proveedor proveedor = new Proveedor();
		proveedor.setId(sucursal.getIdProveedor());
		sucursalNuevo.setProveedor(proveedor);
		
	}
}

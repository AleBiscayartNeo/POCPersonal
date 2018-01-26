/**
 * 
 */
package com.personal.beneficios.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.beneficios.dto.SucursalDTO;
import com.personal.beneficios.entity.Descuento;
import com.personal.beneficios.entity.Localidad;
import com.personal.beneficios.entity.Proveedor;
import com.personal.beneficios.entity.Provincia;
import com.personal.beneficios.entity.Sucursal;

/**
 * The class SucursalRepository
 *
 */
@Service("sucursalRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class SucursalRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Sucursal> getSucursales() {
		
	Query query  = entityManager.createQuery("SELECT sucursal FROM Sucursal sucursal");
	return (ArrayList<Sucursal>) query.getResultList();
		
	}
	
	@Transactional(readOnly=true)
	public Sucursal getSucursalPorID(Integer idSucursal) {
	
	Query query  = entityManager.createQuery("SELECT sucursal FROM Sucursal sucursal where sucursal.id=:id");
	query.setParameter("id", idSucursal);
	return  (Sucursal) query.getSingleResult();
	}	
	
	@Transactional(readOnly=true)
	public ArrayList<Sucursal> getSucursalPorIdProveedor(Integer idProveedor) {
	
	Query query  = entityManager.createQuery("SELECT sucursal FROM Sucursal sucursal where sucursal.proveedor.id=:id");
	query.setParameter("id", idProveedor);
	return (ArrayList<Sucursal>) query.getResultList();
	}
	
	
	public void agregarSucursal(SucursalDTO sucursal){
		Sucursal sucursalNuevo = new Sucursal();
		cargarSucursal(sucursal, sucursalNuevo);
		entityManager.persist(sucursalNuevo);
	}
	
	
	public void editarSucursal(SucursalDTO sucursal){
		Sucursal sucursalNuevo = new Sucursal();
		cargarSucursal(sucursal, sucursalNuevo);
	}
	
	
	public void eliminarSucursal(Integer idSucursal){
		Sucursal sucursal = entityManager.find(Sucursal.class, idSucursal);

		entityManager.remove(sucursal);
	}
	
	private void cargarSucursal(SucursalDTO sucursal, Sucursal sucursalNuevo){
		
		sucursalNuevo.setCalle(sucursal.getCalle());
		sucursalNuevo.setNumero(sucursal.getNumero());
		if (sucursal.getInformacionAdicional() != null){
			sucursalNuevo.setInformacionAdicional(sucursal.getInformacionAdicional());
		}			
		sucursalNuevo.setTelefono(sucursal.getTelefono());
		sucursalNuevo.setLongitud(sucursal.getLongitud());
		sucursalNuevo.setLatitud(sucursal.getLatitud());
		
		if (sucursal.getDescripcionBarrio() != null){
			sucursalNuevo.setBarrio(sucursal.getDescripcionBarrio());
		}
		Provincia provincia = new Provincia();
		provincia.setId(sucursal.getIdProvincia());
		sucursalNuevo.setProvincia(provincia);
		
		Localidad localidad =  new Localidad();
		localidad.setId(sucursal.getIdLocalidad());
		sucursalNuevo.setLocalidad(localidad);
		
		sucursalNuevo.setBarrio(sucursal.getDescripcionBarrio());
		
		Proveedor proveedor = new Proveedor();
		proveedor.setId(sucursal.getIdProveedor());
		sucursalNuevo.setProveedor(proveedor);
		
	}
}

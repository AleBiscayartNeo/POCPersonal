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

import com.personal.beneficios.entity.Descuento;
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
	
	
	public void agregarSucursal(Sucursal sucursal){
		entityManager.persist(sucursal);
	}
	
	
	public void editarSucursal(Sucursal sucursal){
		entityManager.persist(sucursal);
	}
	
	
	public void eliminarSucursal(Integer idSucursal){
		Sucursal sucursal = entityManager.find(Sucursal.class, idSucursal);

		entityManager.remove(sucursal);
	}
}

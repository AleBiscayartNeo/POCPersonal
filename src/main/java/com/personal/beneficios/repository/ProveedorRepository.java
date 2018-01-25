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

import com.personal.beneficios.entity.Proveedor;

/**
 * The class DescuentoRepository
 *
 */
@Service("proveedorRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class ProveedorRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Proveedor> getProveedores() {
		
	Query query  = entityManager.createQuery("SELECT proveedor FROM Proveedor proveedor");
	return (ArrayList<Proveedor>) query.getResultList();
		
	}
	
	@Transactional(readOnly=true)
	public Proveedor getProveedoresPorID(Integer idproveedor) {
	
	Query query  = entityManager.createQuery("SELECT proveedor FROM Proveedor proveedor where proveedor.id=:id");
	query.setParameter("id", idproveedor);
	return  (Proveedor) query.getSingleResult();
	}		
	

	public void agregarProveedor(Proveedor proveedor){
		entityManager.persist(proveedor);
	}
	

	public void editarProveedor(Proveedor proveedor){
		entityManager.persist(proveedor);
	}
	

	public void eliminarProveedor(Proveedor proveedor){
		entityManager.remove(proveedor);
	}
}

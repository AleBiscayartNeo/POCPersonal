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

import com.personal.beneficios.dto.ProveedorDTO;
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
	

	public void agregarProveedor(ProveedorDTO proveedor){
		Proveedor proveedorNuevo = new Proveedor();
		cargarProveedor(proveedor, proveedorNuevo);
		entityManager.persist(proveedorNuevo);
	}
	

	public void editarProveedor(ProveedorDTO proveedor){
		Proveedor proveedorNuevo = entityManager.find(Proveedor.class, proveedor.getIdProveedor());
		cargarProveedor(proveedor, proveedorNuevo);
	}
	

	public void eliminarProveedor(Integer idProveedor){
		Proveedor proveedor = entityManager.find(Proveedor.class, idProveedor);

		entityManager.remove(proveedor);
	}
	
	private void cargarProveedor(ProveedorDTO proveedor, Proveedor proveedorNuevo){
		proveedorNuevo.setLogo(proveedor.getLogo());
		proveedorNuevo.setHorarioAtencion(proveedor.getHorarioAtencion());
		proveedorNuevo.setRazonSocial(proveedor.getRazonSocial());
		
	}
}

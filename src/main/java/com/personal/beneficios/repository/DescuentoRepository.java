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

/**
 * The class DescuentoRepository
 *
 */
@Service("descuentoRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class DescuentoRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Descuento> getDescuentos() {
		
	Query query  = entityManager.createQuery("SELECT descuento FROM Descuento descuento");
	return (ArrayList<Descuento>) query.getResultList();
		
	}
	
	@Transactional(readOnly=true)
	public Descuento getDescuentoPorID(Integer idDescuento) {
	
	Query query  = entityManager.createQuery("SELECT descuento FROM Descuento descuento where descuento.id=:id");
	query.setParameter("id", idDescuento);
	return  (Descuento) query.getSingleResult();
	}	
	
	@Transactional(readOnly=true)
	public void agregarDescuento(Descuento descuento){
		entityManager.persist(descuento);
	}
	
	@Transactional(readOnly=true)
	public void editarDescuento(Descuento descuento){
		entityManager.persist(descuento);
	}
	
	@Transactional(readOnly=true)
	public void eliminarDescuento(Descuento descuento){
		entityManager.remove(descuento);
	}
}

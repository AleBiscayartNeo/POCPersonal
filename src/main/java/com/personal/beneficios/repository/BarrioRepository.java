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

import com.personal.beneficios.entity.Barrio;


/**
 * The class BarrioRepository
 *
 */
@Service("barrioRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class BarrioRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Barrio> getBarrioPorLocalidad(Integer idLocalidad) {
	
		Query query  = entityManager.createQuery("SELECT barrio FROM Barrio barrio where barrio.localidad.id=:id");
		 query.setParameter("id", idLocalidad);
		 return (ArrayList<Barrio>) query.getResultList();
	}		
		
}

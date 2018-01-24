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

import com.personal.beneficios.entity.Localidad;

/**
 * The class LocalidadRepository
 *
 */
@Service("localidadRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class LocalidadRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	
	@Transactional(readOnly=true)
	public ArrayList<Localidad> getLocalidadPorProvincia(Integer idProvincia) {
	
		Query query  = entityManager.createQuery("SELECT localidad FROM Localidad localidad where localidad.provincia.id=:id");
		 query.setParameter("id", idProvincia);
		 return (ArrayList<Localidad>) query.getResultList();
	}		
		
}

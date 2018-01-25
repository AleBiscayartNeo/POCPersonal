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

import com.personal.beneficios.entity.Categoria;

/**
 * The class CategoriaRepository.
 *
 */
@Service("categoriaRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class CategoriaRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Categoria> getCategorias() {
		
	Query query  = entityManager.createQuery("SELECT categoria FROM Categoria categoria");
	return (ArrayList<Categoria>) query.getResultList();
		
	}
}

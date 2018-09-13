package org.neoris.beneficios.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Categoria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("categoriaDAO")
@Transactional(propagation=Propagation.REQUIRED)

public class CategoriaDAOImpl implements CategoriaDAO{

	@PersistenceContext
	public EntityManager entityManager;
	
	public Categoria getCategoria(Integer idCategoria) {
		Query query  = entityManager.createQuery("select categoria from Categoria categoria where categoria.idCategoria=:id");
		 query.setParameter("id", idCategoria);
		 return (Categoria)query.getSingleResult();
	}

	public ArrayList<Categoria> getCategorias() {
		Query query  = entityManager.createQuery("select categoria from Categoria categoria");
		 return (ArrayList<Categoria>) query.getResultList();
	}

}

package org.neoris.beneficios.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Reto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("RetoDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class RetoDAOImpl implements RetoDAO {
	
	@PersistenceContext
	public EntityManager entityManager;


	public Reto getReto(Integer idReto) {
		Query query  = entityManager.createQuery("select reto from Reto reto where reto.retoId=:retoId");
		 query.setParameter("retoId", idReto);
		 return (Reto)query.getSingleResult();
	}

}

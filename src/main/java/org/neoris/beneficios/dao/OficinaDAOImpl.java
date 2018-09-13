package org.neoris.beneficios.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.neoris.beneficios.entity.Oficina;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("oficinaDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class OficinaDAOImpl implements OficinaDAO {
	
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly=true)
	public Oficina getOficina(Integer oficinaId) {
		Query query  = entityManager.createQuery("select oficina from Oficina oficina where oficina.idOficina=:oficinaId");
		query.setParameter("oficinaId", oficinaId);
		return (Oficina)query.getSingleResult();
	}

	@Transactional(readOnly=true)
	public ArrayList<Oficina> getOficinas() {
		Query query  = entityManager.createQuery("select oficina from Oficina oficina");
		return (ArrayList<Oficina>) query.getResultList();
	}

	
}

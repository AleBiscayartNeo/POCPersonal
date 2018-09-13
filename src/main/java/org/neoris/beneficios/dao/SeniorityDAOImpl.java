package org.neoris.beneficios.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Seniority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("SeniorityDAO")
@Transactional(propagation = Propagation.REQUIRED)

public class SeniorityDAOImpl implements SeniorityDAO{

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly = true)
	public Seniority getSeniority(int seniorityId) {
		Query query = entityManager
				.createQuery("select seniority from Seniority seniority where seniority.idSeniority=:seniorityId");
		query.setParameter("seniorityId", seniorityId);
		return (Seniority) query.getSingleResult();
	}

}

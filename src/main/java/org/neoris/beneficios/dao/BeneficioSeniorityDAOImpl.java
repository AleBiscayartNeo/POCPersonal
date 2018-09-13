package org.neoris.beneficios.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.entity.BeneficioSeniority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("beneficioSeniorityDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class BeneficioSeniorityDAOImpl implements BeneficioSeniorityDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly=true)
	public BeneficioSeniority getBeneficioSeniority(Integer idBeneficio) {
		Query query  = entityManager.createQuery("select beneficio from BeneficioSeniority beneficio where beneficio.idBeneficio=:idBeneficio");
		 query.setParameter("beneficioId", idBeneficio);
		 return (BeneficioSeniority)query.getSingleResult();
	}
	
	@Transactional(readOnly=true)
	public Beneficio getBeneficiosSeniority(Integer idSeniority) {
		Query query  = entityManager.createQuery("select beneficio from BeneficioSeniority beneficio where beneficio.idSeniority=:idSeniority");
		 query.setParameter("beneficioId", idSeniority);
		 return (Beneficio)query.getSingleResult();
	}

	

}

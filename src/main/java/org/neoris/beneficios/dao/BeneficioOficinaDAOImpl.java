package org.neoris.beneficios.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.entity.BeneficioOficina;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("beneficioOficinaDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class BeneficioOficinaDAOImpl implements BeneficioOficinaDAO{

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly=true)
	public BeneficioOficina getBeneficio(int idBeneficio) {
		Query query  = entityManager.createQuery("select beneficio from BeneficioOficina beneficio where beneficio.idBeneficio=:idBeneficio and oficinaId=:oficinaId");
		 query.setParameter("beneficioId", idBeneficio);
		 return (BeneficioOficina)query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Beneficio> getBeneficios(Integer categoriaId, Integer oficinaId) {
		Query query  = entityManager.createQuery("select beneficioOfi.beneficio from BeneficioOficina beneficioOfi where beneficioOfi.beneficioOficinaPK.idOficina=:idOficina and beneficioOfi.beneficio.idCategoria=:categoriaId");
		 query.setParameter("categoriaId", categoriaId);
		 query.setParameter("idOficina", oficinaId);
		 return (ArrayList<Beneficio>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Long getBeneficiosCategoria(Integer categoriaId, Integer oficinaId) {
		Query query  = entityManager.createQuery("select count(*) from BeneficioOficina beneficioOfi where beneficioOfi.beneficioOficinaPK.idOficina=:idOficina and beneficioOfi.beneficio.idCategoria=:categoriaId");
		 query.setParameter("categoriaId", categoriaId);
		 query.setParameter("idOficina", oficinaId);
		 return (Long) query.getSingleResult();
	}
	

}

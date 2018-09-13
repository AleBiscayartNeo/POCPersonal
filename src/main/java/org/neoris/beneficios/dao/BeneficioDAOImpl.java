package org.neoris.beneficios.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Beneficio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("beneficioDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class BeneficioDAOImpl implements BeneficioDAO {
	
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly=true)
	public Beneficio getBeneficio(Integer beneficioId) {
		Query query  = entityManager.createQuery("select beneficio from Beneficio beneficio where beneficio.beneficioId=:beneficioId");
		 query.setParameter("beneficioId", beneficioId);
		 return (Beneficio)query.getSingleResult();
	}
	
	@Transactional(readOnly=true)
	public ArrayList<Beneficio> getBeneficios(Integer categoriaId) {
		Query query  = entityManager.createQuery("select beneficio from Beneficio beneficio where beneficio.categoria.idCategoria=:categoriaId");
		 query.setParameter("categoriaId", categoriaId);
		 return (ArrayList<Beneficio>) query.getResultList();
	}

	public long getCantidadPorCategoria(Integer categoriaId) {
		Query query  = entityManager.createQuery("select count(*) from Beneficio beneficio where beneficio.idCategoria=:categoriaId");
		 query.setParameter("categoriaId", categoriaId);
		return (Long) query.getSingleResult();
	}

}

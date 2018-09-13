package org.neoris.beneficios.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Valoracion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("ValoracionDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class ValoracionDAOImpl implements ValoracionDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly = true)
	public Valoracion getValoracion(int valoracionId) {
		Query query = entityManager
				.createQuery("select valoracion from Valoracion valoracion where valoracion.idUsuario=:valoracionId");
		query.setParameter("valoracionId", valoracionId);
		return (Valoracion) query.getSingleResult();
	}

}
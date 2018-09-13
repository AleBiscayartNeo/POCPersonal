package org.neoris.beneficios.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.neoris.beneficios.entity.Configuracion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("configuracionDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class ConfiguracionDAOImpl implements ConfiguracionDAO{
	
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly=true)
	public Configuracion getConfiguracion(Integer configuracionId) {
		Query query  = entityManager.createQuery("select configuracion from Configuracion configuracion where configuracion.parametro=:configuracionId");
		 query.setParameter("configuracionId", configuracionId);
		 return (Configuracion)query.getSingleResult();
	}

	@Transactional(readOnly=true)
	public ArrayList<Configuracion> getConfiguraciones() {
		Query query  = entityManager.createQuery("select configuracion from Configuracion configuracion");
		 return (ArrayList<Configuracion>) query.getResultList();
	}

}

package org.neoris.beneficios.dao;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("UsuarioDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext
	public EntityManager entityManager;

	
	@Transactional(readOnly = true)
	public Usuario getUsuario(String user) {

		Usuario usuario = null;
		try {
			Query query = entityManager
					.createQuery("select usuario from Usuario usuario where usuario.user=:user");
			query.setParameter("user", user);

			usuario = (Usuario) query.getSingleResult();

		} catch (NoResultException e) {
			usuario = null;
		}

		return usuario;
	}
	
	//Ver cantidad en los ultimos 15 minutos
	public Integer getConsultasUsuario(String usuario) {
		
		Date hoy = new Date();
		Integer count=0;
		Usuario user=null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -15);
		Date hoy15 = calendar.getTime();
		try {
			Query query = entityManager
					.createQuery("select usuario from Usuario usuario where usuario.user=:user and usuario.ultimoAcceso between :hoy15 and :hoy");
			query.setParameter("user", usuario);
			query.setParameter("hoy", hoy);
			query.setParameter("hoy15", hoy15);

			user = (Usuario) query.getSingleResult();

		} catch (NoResultException e) {
			user = null;
		}
		
		if(null != user && null!=user.getCount()){
			count = user.getCount();
		}
		return count;
	}
	
	public void addConsultaUsuario(String usuario) {
		
		
		Usuario user = new Usuario();
		user.setUser(usuario);
		user.setUltimoAcceso(new Date());
		user.setCount(1);
		
		entityManager.persist(user);

	}

	public void updateConsultaUsuario(Usuario usuario, int error) {
		if(error==1){
			usuario.setCount(usuario.getCount() +1);
		}
			else if(error==2){
				usuario.setCount(1);
			
		}else{
			usuario.setCount(0);
		}
		usuario.setUltimoAcceso(new Date());
		entityManager.persist(usuario);

	}



}

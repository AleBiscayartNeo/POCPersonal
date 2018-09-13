package org.neoris.beneficios.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neoris.beneficios.entity.User;
import org.neoris.beneficios.entity.UserAdministrador;
import org.neoris.beneficios.entity.UserEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class UserDAOImpl implements UserDAO {
	
	
	@PersistenceContext
	public EntityManager entityManager;

	
	@Transactional(readOnly=false)
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Transactional(readOnly=true)
	public User getUser(long userId) {
		 Query query  = entityManager.createQuery("select user from User user where user.userId=:userId");
		 query.setParameter("userId", userId);
		 return (User)query.getSingleResult();
	}

	@Transactional(readOnly=true)
	public User getUser(String email) {
		return (User) entityManager.createQuery("select user from User user where"
				+ " user.email=:email").setParameter("email", email).getSingleResult();
	}

	@Transactional(readOnly=true)
	public User getUserById(long userId) {
		
		 Query query  = entityManager.createQuery("select user from User user where user.userId=:userId");
		 query.setParameter("userId", userId);
		 return (User)query.getSingleResult();
	}
	
	@Transactional(readOnly=true)
	public UserAdministrador getUserAdmin(String user){
		 UserAdministrador userAdm = null;
		 Query query  = entityManager.createQuery("select user from UserAdministrador user where user.user=:paramUser and user.activo = 'Y'");
		 query.setParameter("paramUser", user);
		 try{
			 userAdm = (UserAdministrador)query.getSingleResult(); 
		 } catch (NoResultException nre){
			 //No trajo resultados.
		 }
		 
		 return userAdm;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<UserEmail> getUsersEmail(){
		Query query  = entityManager.createQuery("select user from UserEmail user where user.activo = 'Y'");
		 return (ArrayList<UserEmail>) query.getResultList();
	}
}

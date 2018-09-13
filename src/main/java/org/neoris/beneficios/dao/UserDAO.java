package org.neoris.beneficios.dao;

import java.util.ArrayList;

import org.neoris.beneficios.entity.User;
import org.neoris.beneficios.entity.UserAdministrador;
import org.neoris.beneficios.entity.UserEmail;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDAO {

	
	public User addUser(User user);
	public User getUser(long userId);
	public User getUser(String email);
	
	public UserAdministrador getUserAdmin(String user);
	public ArrayList<UserEmail> getUsersEmail();
}

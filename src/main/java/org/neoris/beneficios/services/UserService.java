package org.neoris.beneficios.services;

import java.util.List;

import org.neoris.beneficios.dto.UserAdminDTO;
import org.neoris.beneficios.entity.User;


public interface UserService {
	
	
	public User getUser(long userId);
	public User getUser(String email);
	public User addUser(String firstName,String lastName,String email,String sex,String password);
	
	public UserAdminDTO isUserAdmin(String user);
	public List<String> getUsersEmail();
	public boolean isUserAdminWeb(String user);
	

}

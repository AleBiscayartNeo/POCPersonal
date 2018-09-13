package org.neoris.beneficios.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neoris.beneficios.dao.UserDAO;
import org.neoris.beneficios.dto.UserAdminDTO;
import org.neoris.beneficios.entity.User;
import org.neoris.beneficios.entity.UserAdministrador;
import org.neoris.beneficios.entity.UserEmail;
import org.neoris.beneficios.utils.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	private AppConfig appConfiguration;
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	
	public User getUser(long userId) {
		return userDAO.getUser(userId);
	}

	public User getUser(String email) {
		return userDAO.getUser(email);
	}

	
	public User addUser(String firstName,String lastName,String email,String sex,String password) {
		
		User user = new User();
		user.setCreateDate(new Date());
		user.setUpdatedDate(new Date());
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setSex(sex);
		user.setPassword(password);
		
		
		
		return userDAO.addUser(user);
	}

	public UserAdminDTO isUserAdmin(String user) {
		UserAdminDTO userAdminDTO = null;
		UserAdministrador userAdm = userDAO.getUserAdmin(user);
		if(userAdm != null){
			userAdminDTO = new UserAdminDTO();
			userAdminDTO.setEncriptacionKey(appConfiguration.getEncriptacionKey());
			userAdminDTO.setEncriptacionIv(appConfiguration.getEncriptacionIv());
		}
		return userAdminDTO;
	}

	public List<String> getUsersEmail() {
		List<String> emails = null;
		List<UserEmail> users = userDAO.getUsersEmail();
		if(users != null){
			emails = new ArrayList<String>();
			for (UserEmail userEmail : users) {
				emails.add(userEmail.getEmail());
			}
		}
		return emails;
	}
	
	public boolean isUserAdminWeb(String user) {

		boolean valido = false;
		UserAdministrador userAdm = userDAO.getUserAdmin(user);
		if (null != userAdm) {
			valido = true;
		}

		return valido;
	}

}

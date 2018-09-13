package org.neoris.beneficios.services;


import java.util.Calendar;
import java.util.Date;

import org.neoris.beneficios.dao.UsuarioDAO;
import org.neoris.beneficios.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("loginService")
@Transactional(propagation = Propagation.REQUIRED)
public class LoginServiceImpl implements LoginService {
	
	@Autowired(required = true)
	private UsuarioDAO usuarioDAO;
	
	public Integer getCantidadAccesos(String user) {
		return usuarioDAO.getConsultasUsuario(user);
	}
	
	public Usuario getUsuario(String user) {
		return usuarioDAO.getUsuario(user);
	}
	
	public void setConsultasUsuario(String user, String respuestaLowson){
		
		int error = 0;
		Usuario usuario=usuarioDAO.getUsuario(user);
	
		if(null != usuario){
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, -15);
			Date hoy15 = calendar.getTime();
						
			//menos de 15 min
			if(usuario.getUltimoAcceso().after(hoy15) && respuestaLowson.equals("0")){
				error=1;						
			} 
			else if(!usuario.getUltimoAcceso().after(hoy15) && respuestaLowson.equals("0")){
				error=2;						
			}
			//actualizar		
			usuarioDAO.updateConsultaUsuario(usuario,error);	
		} else {
			usuarioDAO.addConsultaUsuario(user);			
		}
		
		
	}
	
	
}

package org.neoris.beneficios.services;

import org.neoris.beneficios.entity.Usuario;

public interface LoginService {
	
	public Integer getCantidadAccesos(String user);
	
	public Usuario getUsuario(String user);
	
	public void setConsultasUsuario(String usuario, String respuestaLowson);

}

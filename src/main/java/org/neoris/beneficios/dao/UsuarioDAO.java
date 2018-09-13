package org.neoris.beneficios.dao;

import org.neoris.beneficios.entity.Usuario;

public interface UsuarioDAO {

	
	public Usuario getUsuario(String user);
	
	public Integer getConsultasUsuario(String usuario);
	
	public void addConsultaUsuario(String usuario);

	public void updateConsultaUsuario(Usuario usuario, int count);
	
}

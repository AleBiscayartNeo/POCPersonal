/**
 * 
 */
package org.neoris.beneficios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usad_usuario_adm")
@Entity
public class UserAdministrador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6401011061317842698L;

	@Id
	@Column(name="usad_adm_user", nullable=false)
	private String user;
	
	@Column(name="usad_activo")
	private String activo;

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the activo
	 */
	public String getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(String activo) {
		this.activo = activo;
	}

}

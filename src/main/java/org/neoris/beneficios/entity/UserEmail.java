/**
 * 
 */
package org.neoris.beneficios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usem_usuario_email")
@Entity
public class UserEmail implements Serializable{

	private static final long serialVersionUID = -5651017257393021521L;

	@Id
	@Column(name="usem_user", nullable=false)
	private String user;
	
	@Column(name="usem_activo")
	private String activo;
	
	@Column(name="usem_email")
	private String email;
	

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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}

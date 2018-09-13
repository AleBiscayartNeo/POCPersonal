package org.neoris.beneficios.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usu_usuario")
@Entity
public class Usuario {

	@Id
	@GeneratedValue
	@Column(name = "usu_id", nullable = false)
	private int idUsuario;

	@Column(name = "ri_id", nullable = true)
	private int idRetoImagen;

	@Column(name = "usu_user", nullable = true)
	private String user;

	@Column(name = "usu_id_fb", nullable = true)
	private String idFb;

	@Column(name = "usu_last_access", nullable = true)
	private Date ultimoAcceso;

	@Column(name = "usu_estado", nullable = true)
	private int estado;

	@Column(name = "usu_token", nullable = true)
	private String token;

	@Column(name = "usu_check", nullable = true)
	private int check;

	@Column(name = "usu_count", nullable = true)
	private Integer count;
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdRetoImagen() {
		return idRetoImagen;
	}

	public void setIdRetoImagen(int idRetoImagen) {
		this.idRetoImagen = idRetoImagen;
	}


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIdFb() {
		return idFb;
	}

	public void setIdFb(String idFb) {
		this.idFb = idFb;
	}

	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}

package org.neoris.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "val_valoracion")
@Entity
public class Valoracion {

	@Id
	@GeneratedValue
	@Column(name = "usu_id", nullable = false)
	private int idUsuario;

	@Column(name = "ben_id", nullable = false)
	private int idBeneficio;

	@Column(name = "val_valor", nullable = true)
	private int valor;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdBeneficio() {
		return idBeneficio;
	}

	public void setIdBeneficio(int idBeneficio) {
		this.idBeneficio = idBeneficio;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}

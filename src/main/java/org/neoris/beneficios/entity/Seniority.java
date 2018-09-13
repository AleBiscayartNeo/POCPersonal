package org.neoris.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sen_seniority")
@Entity
public class Seniority {

	@Id
	@GeneratedValue
	@Column(name = "sen_id", nullable = false)
	private int idSeniority;

	@Column(name = "sen_nombre", nullable = true)
	private String nombreSeniority;

	public int getIdSeniority() {
		return idSeniority;
	}

	public void setIdSeniority(int idSeniority) {
		this.idSeniority = idSeniority;
	}

	public String getNombreSeniority() {
		return nombreSeniority;
	}

	public void setNombreSeniority(String nombreSeniority) {
		this.nombreSeniority = nombreSeniority;
	}

}

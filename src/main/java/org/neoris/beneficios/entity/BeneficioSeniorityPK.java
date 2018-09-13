package org.neoris.beneficios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable()
public class BeneficioSeniorityPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2894664049538550503L;

	@Column(name="ben_id", nullable=false)
	private int idBeneficio;
	
	@Column(name="sen_id", nullable=false)
	private int idSeniority;

	public int getIdBeneficio() {
		return idBeneficio;
	}

	public void setIdBeneficio(int idBeneficio) {
		this.idBeneficio = idBeneficio;
	}

	public int getIdSeniority() {
		return idSeniority;
	}

	public void setIdSeniority(int idSeniority) {
		this.idSeniority = idSeniority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idBeneficio;
		result = prime * result + idSeniority;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeneficioSeniorityPK other = (BeneficioSeniorityPK) obj;
		if (idBeneficio != other.idBeneficio)
			return false;
		if (idSeniority != other.idSeniority)
			return false;
		return true;
	}
}

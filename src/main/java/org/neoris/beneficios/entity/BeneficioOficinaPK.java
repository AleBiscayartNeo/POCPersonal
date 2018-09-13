package org.neoris.beneficios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable()
public class BeneficioOficinaPK implements Serializable{
	
	/**	
	 * 
	 */
	private static final long serialVersionUID = -8685868762077798857L;

	@Column(name="ben_id", nullable=false)
	private int idBeneficio;
	
	@Column(name="ofi_id", nullable=false)
	private int idOficina;
	
	public int getIdBeneficio() {
		return idBeneficio;
	}

	public void setIdBeneficio(int idBeneficio) {
		this.idBeneficio = idBeneficio;
	}

	public int getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(int idOficina) {
		this.idOficina = idOficina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idBeneficio;
		result = prime * result + idOficina;
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
		BeneficioOficinaPK other = (BeneficioOficinaPK) obj;
		if (idBeneficio != other.idBeneficio)
			return false;
		if (idOficina != other.idOficina)
			return false;
		return true;
	}
	
	

}

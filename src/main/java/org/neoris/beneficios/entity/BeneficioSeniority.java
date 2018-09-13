package org.neoris.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="bs_beneficio_seniority")
@Entity
public class BeneficioSeniority {

	@EmbeddedId
	private BeneficioSeniorityPK beneficioSeniorityPK;

	public BeneficioSeniorityPK getBeneficioSeniorityPK() {
		return beneficioSeniorityPK;
	}

	public void setBeneficioSeniorityPK(BeneficioSeniorityPK beneficioSeniorityPK) {
		this.beneficioSeniorityPK = beneficioSeniorityPK;
	}

	
}

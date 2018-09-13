package org.neoris.beneficios.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="bo_beneficio_oficina")
@Entity
public class BeneficioOficina implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6597413354065898863L;

//	@AttributeOverrides({ @AttributeOverride(name = "idBeneficio", column = @Column(name = "ben_id")),
//	@AttributeOverride(name = "idOficina", column = @Column(name = "idOficina")) })

	@EmbeddedId
	private BeneficioOficinaPK beneficioOficinaPK;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ben_id", insertable = false, updatable = false)
	private Beneficio beneficio;

	
	@Column(name="bo_estado", nullable=true)
	private int estado;


	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public BeneficioOficinaPK getBeneficioOficinaPK() {
		return beneficioOficinaPK;
	}

	public void setBeneficioOficinaPK(BeneficioOficinaPK beneficioOficinaPK) {
		this.beneficioOficinaPK = beneficioOficinaPK;
	}
	
	
	


}

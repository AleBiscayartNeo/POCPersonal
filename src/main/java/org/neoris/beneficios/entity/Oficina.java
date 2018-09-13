package org.neoris.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ofi_oficina")
@Entity
public class Oficina {

	@Id
	@GeneratedValue
	@Column(name="ofi_id", nullable=false)
	private int idOficina;
	
	@Column(name="ofi_nombre", nullable=true)
	private String nombreOficina;

	public int getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(int idOficina) {
		this.idOficina = idOficina;
	}

	public String getNombreOficina() {
		return nombreOficina;
	}

	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}
	
	
}

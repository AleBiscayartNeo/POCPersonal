package org.neoris.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ret_reto")
@Entity
public class Reto {

	@Id
	@GeneratedValue
	@Column(name="ret_id", nullable=false)
	private int idReto;
	
	@Column(name="ret_titulo", nullable=true)
	private String tituloReto;
	
	@Column(name="ret_consigna", nullable=true)
	private String consignaReto;
	
	@Column(name="ret_premio", nullable=true)
	private String premioReto;

	@Column(name="ret_estado", nullable=true)
	private int estadoReto;

	public int getIdReto() {
		return idReto;
	}

	public void setIdReto(int idReto) {
		this.idReto = idReto;
	}

	public String getTituloReto() {
		return tituloReto;
	}

	public void setTituloReto(String tituloReto) {
		this.tituloReto = tituloReto;
	}

	public String getConsignaReto() {
		return consignaReto;
	}

	public void setConsignaReto(String consignaReto) {
		this.consignaReto = consignaReto;
	}

	public String getPremioReto() {
		return premioReto;
	}

	public void setPremioReto(String premioReto) {
		this.premioReto = premioReto;
	}

	public int getEstadoReto() {
		return estadoReto;
	}

	public void setEstadoReto(int estadoReto) {
		this.estadoReto = estadoReto;
	}
	
		
}

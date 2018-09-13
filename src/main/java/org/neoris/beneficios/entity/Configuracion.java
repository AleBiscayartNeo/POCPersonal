package org.neoris.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="con_configuracion")
@Entity
public class Configuracion {

	@Id
	@Column(name="con_parametro", nullable=false)
	private String parametro;
	
	@Column(name="con_valor", nullable=true)
	private String valor;

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}

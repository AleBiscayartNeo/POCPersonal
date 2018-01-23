/**
 * 
 */
package com.personal.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The class Nivel
 *
 */
@Table(name="nivel")
@Entity
public class Nivel {

	@Id
	@GeneratedValue
	@Column(name="idNivel", nullable=false)
	public Integer id;
	
	@Column(name="descripcion", nullable=false)
	public String descripcion;

	/**
	 * @return the nivel
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

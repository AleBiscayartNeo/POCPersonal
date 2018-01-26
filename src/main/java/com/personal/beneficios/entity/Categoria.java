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
 * The class Categoria.
 *
 */
@Table(name="categoria")
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue
	@Column(name="idCategoria", nullable=false)
	private Integer id;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
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

/**
 * 
 */
package com.personal.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The class barrio.
 *
 */
@Table(name="barrio")
@Entity
public class Barrio {

	@Id
	@GeneratedValue
	@Column(name="idBarrio", nullable=false)
	private Integer id;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idLocalidad", insertable = false, updatable = false)
	private Localidad localidad;

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

	/**
	 * @return the localidad
	 */
	public Localidad getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}


}

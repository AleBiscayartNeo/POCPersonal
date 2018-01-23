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
 * The class Localidad.
 *
 */
@Table(name="localidad")
@Entity
public class Localidad {

	@Id
	@GeneratedValue
	@Column(name="idNivel", nullable=false)
	private Integer id;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idProvincia", insertable = false, updatable = false)
	private Provincia provincia;

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

	/**
	 * @return the provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
}

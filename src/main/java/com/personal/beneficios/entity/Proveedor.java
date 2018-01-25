/**
 * 
 */
package com.personal.beneficios.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The class Proveedor.
 *
 */
@Table(name="proveedor")
@Entity
public class Proveedor {

	@Id
	@GeneratedValue
	@Column(name="idProveedor", nullable=false)
	private Integer id;
	
	@Column(name="razonSocial", nullable=false)
	private String razonSocial;
	
	@Column(name="horarioAtencion", nullable=false)
	private String horarioAtencion;
	
	@Column(name="logo", nullable=false)
	private String logo;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "proveedor")
	private Set<Sucursal> sucursales = new HashSet<Sucursal>();	

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
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the horarioAtencion
	 */
	public String getHorarioAtencion() {
		return horarioAtencion;
	}

	/**
	 * @param horarioAtencion the horarioAtencion to set
	 */
	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @return the sucursales
	 */
	public Set<Sucursal> getSucursales() {
		return sucursales;
	}

	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(Set<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
}

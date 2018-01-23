/**
 * 
 */
package com.personal.beneficios.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The class Descuento
 *
 */
@Table(name="descuento")
@Entity
public class Descuento {
	
	@Id
	@GeneratedValue
	@Column(name="idDescuento", nullable=false)
	private int id;
	
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	@Column(name="descripcionCorta", nullable=false)
	private String descripcionCorta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idNivel", insertable = false, updatable = false)
	private Nivel nivel;
	
	@Column(name="vigenciaDesde", nullable=false)
	private Date vigenciaDesde;
	
	@Column(name="vigenciaHasta", nullable=false)
	private Date vigenciaHasta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idProveedor", insertable = false, updatable = false)
	private Proveedor proveedor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCategoria", insertable = false, updatable = false)
	private Categoria categoria;
	
	@Column(name="imagen", nullable=false)
	private String imagen;
	
	@Column(name="legales", nullable=false)
	private String legales;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the descripcionCorta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta the descripcionCorta to set
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * @return the nivel
	 */
	public Nivel getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the vigenciaDesde
	 */
	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}

	/**
	 * @param vigenciaDesde the vigenciaDesde to set
	 */
	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	/**
	 * @return the vigenciaHasta
	 */
	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}

	/**
	 * @param vigenciaHasta the vigenciaHasta to set
	 */
	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * @param legales the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}
	
}

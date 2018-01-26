/**
 * 
 */
package com.personal.beneficios.dto;

import java.util.ArrayList;
import java.util.Date;

import com.personal.beneficios.entity.Categoria;
import com.personal.beneficios.entity.Nivel;
import com.personal.beneficios.entity.Proveedor;

/**
 * The class DescuentoGeolocalizadoDTO
 *
 */

public class DescuentoGeolocalizadoDTO {
	
	
	private int id;
	
	private String nombre;
	
	private String descripcion;
		
	private String descripcionCorta;
	
	private Date vigenciaDesde;
		
	private Date vigenciaHasta;
			
	private String imagen;
	
	private String legales;
	
	private Nivel nivel;
		
	private Proveedor proveedor;
	
	private Categoria categoria;
	
	private ArrayList<SucursalDTO> sucursales;

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
	 * @return the sucursales
	 */
	public ArrayList<SucursalDTO> getSucursales() {
		return sucursales;
	}

	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(ArrayList<SucursalDTO> sucursales) {
		this.sucursales = sucursales;
	}

}

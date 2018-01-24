package com.personal.beneficios.dto;

import java.util.Date;

public class DescuentoDTO {
	
	private int id;
	private String nombre;
	private String descripcion;
	private String descripcionCorta;
	private int idNivel;
	private Date vigenciaDesde;
	private Date vigenciaHasta;
	private int idProveedor;
	private int idCategoria;
	private String imagen;
	private String legales;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public int getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}
	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}
	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}
	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}
	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getLegales() {
		return legales;
	}
	public void setLegales(String legales) {
		this.legales = legales;
	}
}

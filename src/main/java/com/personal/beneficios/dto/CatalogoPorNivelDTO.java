package com.personal.beneficios.dto;

import java.util.List;

import com.personal.beneficios.entity.Categoria;
import com.personal.beneficios.entity.Descuento;

public class CatalogoPorNivelDTO {
	
	List<Descuento> descuentos;
	List<Categoria> categorias;
	
	public List<Descuento> getDescuentos() {
		return descuentos;
	}
	public void setDescuentos(List<Descuento> descuentos) {
		this.descuentos = descuentos;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	

}

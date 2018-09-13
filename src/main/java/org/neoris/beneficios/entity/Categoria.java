package org.neoris.beneficios.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="cat_categoria")
//@Entity
public class Categoria {
	
	@Id
	@GeneratedValue
	@Column(name="cat_id", nullable=false)
	private int idCategoria;
	
	@Column(name="cat_nombre", nullable=true)
	private String nombreCategoria;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "benefit")
//	private Set<Beneficio> beneficios = new HashSet<Beneficio>();
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

//	public Set<Beneficio> getBeneficios() {
//		return beneficios;
//	}
//
//	public void setBeneficios(Set<Beneficio> beneficios) {
//		this.beneficios = beneficios;
//	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	

}

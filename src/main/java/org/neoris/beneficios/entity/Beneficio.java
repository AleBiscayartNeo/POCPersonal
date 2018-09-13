package org.neoris.beneficios.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="ben_beneficio")
@Entity
public class Beneficio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7162271032707326679L;


	@Id
	@GeneratedValue
	@Column(name="ben_id", nullable=false)
	private int idBeneficio;
    
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "beneficio")
	private Set<BeneficioOficina> beneficioOficina = new HashSet<BeneficioOficina>();	
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "cat_id", insertable = false, updatable = false)
//	private Categoria categoria;
	

	@Column(name = "cat_id", insertable = false, updatable = false)
	private Integer idCategoria;
	
	@Column(name="ben_estado", nullable=true)
	private int estado;
	
	@Column(name="ben_nombre", nullable=true)
	private String nombre;
	
	@Column(name="ben_descripcion", nullable=true)
	private String decripcion;
	
	@Column(name="ben_path_img", nullable=true)
	private String img;
	
	@Column(name="ben_seniority_desde", nullable=true)
	private String senDesde;
	
	@Column(name="ben_seniority_hasta", nullable=true)
	private String senHasta;
	
	@Column(name="ben_titulo_push", nullable=true)
	private String tituloPush;

	public int getId() {
		return idBeneficio;
	}

	public void setId(int id) {
		this.idBeneficio = id;
	}

//	public Categoria getCategoria() {
//		return categoria;
//	}
//
//	public void setCategoria(Categoria categoria) {
//		this.categoria = categoria;
//	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int idStado) {
		this.estado = idStado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDecripcion() {
		return decripcion;
	}

	public void setDecripcion(String decripcion) {
		this.decripcion = decripcion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSenDesde() {
		return senDesde;
	}

	public void setSenDesde(String senDesde) {
		this.senDesde = senDesde;
	}

	public String getSenHasta() {
		return senHasta;
	}

	public void setSenHasta(String senHasta) {
		this.senHasta = senHasta;
	}

	public String getTituloPush() {
		return tituloPush;
	}

	public void setTituloPush(String tituloPush) {
		this.tituloPush = tituloPush;
	}

}

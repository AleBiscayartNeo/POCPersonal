package org.neoris.beneficios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ri_retos_img")
@Entity
public class ImagenReto {

	@Id
	@GeneratedValue
	@Column(name="ri_id", nullable=false)
	private int idImagenReto;
	
	@Column(name="ret_id", nullable=false)
	private int idReto;
	
	@Column(name="ri_pat_img", nullable=true)
	private String path;

	public int getIdImagenReto() {
		return idImagenReto;
	}

	public void setIdImagenReto(int idImagenReto) {
		this.idImagenReto = idImagenReto;
	}

	public int getIdReto() {
		return idReto;
	}

	public void setIdReto(int idReto) {
		this.idReto = idReto;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}

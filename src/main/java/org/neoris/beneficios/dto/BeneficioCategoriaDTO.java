/**
 * 
 */
package org.neoris.beneficios.dto;

/**
 * @author gonzalo.folli
 *
 */
public class BeneficioCategoriaDTO {

	private int idCategoria;
	private int idOficina;
	private String nombreCategoria;
	private Long cantidad;
	
	

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the idOficina
	 */
	public int getIdOficina() {
		return idOficina;
	}

	/**
	 * @param idOficina the idOficina to set
	 */
	public void setIdOficina(int idOficina) {
		this.idOficina = idOficina;
	}

}

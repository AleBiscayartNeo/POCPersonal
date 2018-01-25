/**
 * 
 */
package com.personal.beneficios.dto;

import java.util.List;

/**
 * The class SucursalProveedorDTO.
 *
 */
public class SucursalProveedorDTO {

	private ProveedorDTO proveedorDTO;
	
	private List<SucursalDTO> sucursales;

	/**
	 * @return the proveedorDTO
	 */
	public ProveedorDTO getProveedorDTO() {
		return proveedorDTO;
	}

	/**
	 * @param proveedorDTO the proveedorDTO to set
	 */
	public void setProveedorDTO(ProveedorDTO proveedorDTO) {
		this.proveedorDTO = proveedorDTO;
	}

	/**
	 * @return the sucursales
	 */
	public List<SucursalDTO> getSucursales() {
		return sucursales;
	}

	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(List<SucursalDTO> sucursales) {
		this.sucursales = sucursales;
	}
	
	
}

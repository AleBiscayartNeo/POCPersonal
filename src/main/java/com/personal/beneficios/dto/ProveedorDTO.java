/**
 * 
 */
package com.personal.beneficios.dto;

import java.util.List;

/**
 * The class ProveedorDTO.
 *
 */
public class ProveedorDTO {

	private String razonSocial;
	
	private String horarioAtencion;
	
	private List<SucursalDTO> sucursales;

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

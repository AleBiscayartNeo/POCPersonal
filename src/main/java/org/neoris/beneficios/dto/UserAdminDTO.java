/**
 * 
 */
package org.neoris.beneficios.dto;

/**
 * The class UserAdminDTO.
 *
 */
public class UserAdminDTO {

	private String encriptacionKey;
	
	private String encriptacionIv;

	/**
	 * @return the encriptacionKey
	 */
	public String getEncriptacionKey() {
		return encriptacionKey;
	}

	/**
	 * @param encriptacionKey the encriptacionKey to set
	 */
	public void setEncriptacionKey(String encriptacionKey) {
		this.encriptacionKey = encriptacionKey;
	}

	/**
	 * @return the encriptacionIv
	 */
	public String getEncriptacionIv() {
		return encriptacionIv;
	}

	/**
	 * @param encriptacionIv the encriptacionIv to set
	 */
	public void setEncriptacionIv(String encriptacionIv) {
		this.encriptacionIv = encriptacionIv;
	}
}

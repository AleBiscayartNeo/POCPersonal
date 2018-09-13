/**
 * 
 */
package org.neoris.beneficios.request;

/**
 * The class EmailRequest.
 *
 */

public class EmailRequest {

	private String user;
	
	private String mensaje;

	/**
	 * @return the user
	 */
	
	public String getUser() {
		return user;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param user the user to set
	 */
	
	public void setUser(String user) {
		this.user = user;
	}

	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}

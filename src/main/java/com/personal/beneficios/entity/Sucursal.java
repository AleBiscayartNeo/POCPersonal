/**
 * 
 */
package com.personal.beneficios.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author antoa
 * The class Sucursal.
 *
 */
@Table(name="descuento")
@Entity
public class Sucursal {
	
		@Id
		@GeneratedValue
		@Column(name="idSucursal", nullable=false)
		private int id;
		
		@Column(name="calle", nullable=false)
		private String calle;
		
		@Column(name="nro", nullable=false)
		private String numero;
		
		@Column(name="informacionAdicional", nullable=false)
		private String informacionAdicional;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "idProvincia", insertable = false, updatable = false)
		private Provincia provincia;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "idLocalidad", insertable = false, updatable = false)
		private Localidad localidad;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "idBarrio", insertable = false, updatable = false)
		private Barrio barrio;
					
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "idProveedor", insertable = false, updatable = false)
		private Proveedor proveedor;
					
		@Column(name="latitud", nullable=false)
		private String latitud;
		
		@Column(name="longitud", nullable=false)
		private String longitud;
		
		@Column(name="telefono", nullable=false)
		private Integer telefono;

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the calle
		 */
		public String getCalle() {
			return calle;
		}

		/**
		 * @param calle the calle to set
		 */
		public void setCalle(String calle) {
			this.calle = calle;
		}

		/**
		 * @return the numero
		 */
		public String getNumero() {
			return numero;
		}

		/**
		 * @param numero the numero to set
		 */
		public void setNumero(String numero) {
			this.numero = numero;
		}

		/**
		 * @return the informacionAdicional
		 */
		public String getInformacionAdicional() {
			return informacionAdicional;
		}

		/**
		 * @param informacionAdicional the informacionAdicional to set
		 */
		public void setInformacionAdicional(String informacionAdicional) {
			this.informacionAdicional = informacionAdicional;
		}

		/**
		 * @return the provincia
		 */
		public Provincia getProvincia() {
			return provincia;
		}

		/**
		 * @param provincia the provincia to set
		 */
		public void setProvincia(Provincia provincia) {
			this.provincia = provincia;
		}

		/**
		 * @return the localidad
		 */
		public Localidad getLocalidad() {
			return localidad;
		}

		/**
		 * @param localidad the localidad to set
		 */
		public void setLocalidad(Localidad localidad) {
			this.localidad = localidad;
		}

		/**
		 * @return the barrio
		 */
		public Barrio getBarrio() {
			return barrio;
		}

		/**
		 * @param barrio the barrio to set
		 */
		public void setBarrio(Barrio barrio) {
			this.barrio = barrio;
		}

		/**
		 * @return the proveedor
		 */
		public Proveedor getProveedor() {
			return proveedor;
		}

		/**
		 * @param proveedor the proveedor to set
		 */
		public void setProveedor(Proveedor proveedor) {
			this.proveedor = proveedor;
		}

		/**
		 * @return the latitud
		 */
		public String getLatitud() {
			return latitud;
		}

		/**
		 * @param latitud the latitud to set
		 */
		public void setLatitud(String latitud) {
			this.latitud = latitud;
		}

		/**
		 * @return the longitud
		 */
		public String getLongitud() {
			return longitud;
		}

		/**
		 * @param longitud the longitud to set
		 */
		public void setLongitud(String longitud) {
			this.longitud = longitud;
		}

		/**
		 * @return the telefono
		 */
		public Integer getTelefono() {
			return telefono;
		}

		/**
		 * @param telefono the telefono to set
		 */
		public void setTelefono(Integer telefono) {
			this.telefono = telefono;
		}



}

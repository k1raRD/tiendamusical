/**
 * 
 */
package com.k1rard.tiendamusicalservices.enums;

/**
 * @author k1rard
 * Enumeracion para los perfiles del sistema.
 */
public enum RolEnum {

	CLIENTE(4L, "CLIENTE"),
	
	ADMINISTRADOR(3L, "ADMINISTRADOR");
	
	/**
	 * Clave del perfil.
	 */
	private Long clave;
	/**
	 * Descripcion del perfil.
	 */
	private String descripcion;
	
	/**
	 * Constructor que inicializa las claves y descripciones de los datos de la enumeracion 
	 * para los perfiles del sistema
	 * @param clave {@link Long} Clave del perfil.
	 * @param descripcion {@link String} Decripcion del perfil.
	 */
	private RolEnum(Long clave, String descripcion) {
		this.clave = clave;
		this.descripcion = descripcion;
	}

	/**
	 * @return the clave
	 */
	public Long getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(Long clave) {
		this.clave = clave;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}

package com.k1rard.tiendamusicalentities.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author k1rard
 * Clase que contiene entidades de perfiles de usuarios.
 */
@Entity
@Table(name = "rol")
public class Rol extends Common{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRol")
	private Long idRol;
	
	@Column(name = "nombre", length = 45, nullable = false)
	private String nombre;

	/**
	 * @return the idRol
	 */
	public Long getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

/**
 * 
 */
package com.k1rard.tiendamusicalweb.session;

import com.k1rard.tiendamusicalentities.entities.Persona;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que mantendra la informacion en la sesion del usuario 
 */
@Named
@SessionScoped
public class SessionBean {
	
	/**
	 * Objeto persona que se mantendra en la sesion.
	 */
	private Persona persona;
	
	@PostConstruct
	public void init() {
		System.out.println("Creando sesion...");
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}

/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import com.k1rard.tiendamusicalentities.entities.Persona;

/**
 * @author k1rard
 * Interface que contiene los metodos de logica de negocio para el registro de usuarios en tienda musical
 */
public interface RegistroService {

	/**
	 * Metodo que permite registrar una persona en el sistema.
	 * @param persona {@link Persona} objeto con la persona a registrarse
	 * @return {@link Persona} objeto persona registrada.
	 */
	Persona registrarPersona(Persona persona);
}

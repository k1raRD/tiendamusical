/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import com.k1rard.tiendamusicalentities.entities.Persona;

/**
 * @author k1rard
 * Interface que contiene los metodos de logica de negocio para la pantalla de informacion personal.
 */
public interface InfoPersonalService {

	
	/**
	 * Metodo que permite actualizar una informacion personal del usuario en sesion.
	 * @param persona {@link Persona} Objeto que contiene la informacion del usuario en sesion.
	 * @return {@link Persona} persona actualizada.
	 */
	Persona actualizarPersona(Persona persona);
}

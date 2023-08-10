/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import java.util.List;

import com.k1rard.tiendamusicalentities.entities.Rol;

/**
 * @author k1rard
 * Interface que contiene los metodos de logica de negocio para la pantalla de administracion de perfiles.
 */
public interface AdminPerfilesService {

	/**
	 * Metodo que permite consultar la lista de perfiles.
	 * @return {@link List} perfiles consultados.
	 */
	List<Rol> consultarPerfiles(); 
	
	/**
	 * Metodo que permite guardar un perfil
	 * @param rol {@link Rol} Objeto con el rol o perfil a guardar.
	 * @return {@link Rol} Rol obtenido ya guardado
	 */
	Rol guardarPefil(Rol rol);
}

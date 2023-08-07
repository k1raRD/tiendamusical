/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import java.util.List;

import com.k1rard.tiendamusicalentities.dto.AlbumTopTenDTO;

/**
 * @author k1rard
 * Interface que contiene los metodos de la logica de negocio de los albums
 */
public interface AlbumService {

	/**
	 * Metodo que permite consultar la lista de albums en el top ten de mas vendidos
	 * @return {@link List} lista de top ten albums.
	 */
	List<AlbumTopTenDTO> consultarAlbumsTopTen();
}

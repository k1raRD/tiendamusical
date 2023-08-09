/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import java.util.List;

import com.k1rard.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.k1rard.tiendamusicalentities.entities.Album;

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
	
	/**
	 * Metodo que permite consultar el listado de albums.
	 * @return {@link List} lista de albums consultados.
	 */
	List<Album> consultarAlbums();
}

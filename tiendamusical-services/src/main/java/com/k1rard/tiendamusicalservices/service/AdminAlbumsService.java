/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import java.util.List;

import com.k1rard.tiendamusicalentities.entities.Album;
import com.k1rard.tiendamusicalentities.entities.Artista;
import com.k1rard.tiendamusicalentities.entities.Disquera;

/**
 * @author k1rard
 * Interface que permite la logica de negocio para la administracion de albums.
 */
public interface AdminAlbumsService {

	/**
	 * Metodo que permite consultar el catalogo de disqueras.
	 * @return {@link List} lista de disqueras
	 */
	List<Disquera> consultarDisqueras();
	
	/**
	 * Metodo que permite consultar el catalogo de artistas.
	 * @return {@link List} lista de artistas
	 */
	List<Artista> consultarArtistas();
	
	/**
	 * Metodo que permite guardar un album
	 * @param album {@link Album} Objeto a guardar el album
	 * @return {@link Album} Objeto album guardado.
	 */
	Album guardarAlbum(Album album);
}

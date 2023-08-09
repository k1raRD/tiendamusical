/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import java.util.List;

import com.k1rard.tiendamusicalentities.entities.Artista;
import com.k1rard.tiendamusicalentities.entities.Genero;
import com.k1rard.tiendamusicalentities.entities.Nacionalidad;
import com.k1rard.tiendamusicalentities.entities.SubGenero;

/**
 * 
 * @author k1rard
 * Interface que contiene los metodos de logica de negocio para la administracion de artistas.
 */
public interface AdminArtistaService {

	/**
	 * Metodo que permite consultar la lista de artistas.
	 * @return {@link List} listado de artistas consultados.
	 */
	List<Artista> consultarArtistas();
	
	/**
	 * Metodo que permite consultar el listado de nacionalidades.
	 * @return {@link List} lista de nacionalidades consultadas
	 */
	List<Nacionalidad> consultarNacionalidades();
	
	/**
	 * Metodo que permite consultar el listado de generos.
	 * @return {@link List} lista de generos consultados.
	 */
	List<Genero> consultarGeneros();
	
	/**
	 * Metodo que permite consultar el listado de subGeneros.
	 * @param idGenero {@link Long} Identificador del genero seleccionado.
	 * @return {@link List} lista de subGeneros consultados.
	 */
	List<SubGenero> consultarSubGeneroPorGenero(Long idGenero);
	
	/**
	 * Metodo que permite guardar un artista en la base de datos
	 * @param artista {@link Artista} Objeto artista a guardar o actualizar
	 * @return {@link Artista} artista guardado.
	 */
	Artista guardarArtista(Artista artista);
}

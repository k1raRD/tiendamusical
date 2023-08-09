/**
 * 
 */
package com.k1rard.tiendamusicaldata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.k1rard.tiendamusicalentities.entities.SubGenero;

/**
 * @author k1rard
 * DAO que contiene los metodos del CRUD para la tabla de subgenero en la base de datos.
 */
public interface SubGeneroDAO extends JpaRepository<SubGenero, Long>{

	/**
	 * Metodo que permite consultar con Spring JPA los subgeneros de un genero seleccionado.
	 * @param idGenero {@link Long} Identificador del genero.
	 * @return {@link List} lista de subgeneros consultados.
	 */
	@Query("SELECT sb FROM SubGenero sb WHERE sb.genero.idGenero = :idGenero")
	List<SubGenero> findAllByGenero(@Param("idGenero") Long idGenero);
}

/**
 * 
 */
package com.k1rard.tiendamusicaldata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k1rard.tiendamusicalentities.entities.Genero;

/**
 * @author k1rard
 * DAO que contiene los metodos del CRUD para la tabla genero.
 */
public interface GeneroDAO extends JpaRepository<Genero, Long>{

}

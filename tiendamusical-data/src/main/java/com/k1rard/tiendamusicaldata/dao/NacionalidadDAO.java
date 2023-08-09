/**
 * 
 */
package com.k1rard.tiendamusicaldata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k1rard.tiendamusicalentities.entities.Nacionalidad;

/**
 * @author k1rard
 * DAO que contiene el CRUD para las transacciones con Spring JPA hacia la tabla de nacionalidad.
 */
public interface NacionalidadDAO extends JpaRepository<Nacionalidad, Long>{

}

/**
 * 
 */
package com.k1rard.tiendamusicaldata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k1rard.tiendamusicalentities.entities.Rol;

/**
 * @author k1rard
 * Interface que contiene los metodos del CRUD con Spring JPA para la tabla rol.
 */
public interface RolDAO extends JpaRepository<Rol, Long>{

}

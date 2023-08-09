package com.k1rard.tiendamusicaldata.dao;

import com.k1rard.tiendamusicalentities.entities.Disquera;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author k1rard
 * Clase que representa el DAO para el CRUD hacia la tabla de Disquera.
 */
public interface DisqueraDAO extends JpaRepository<Disquera, Long> {
}

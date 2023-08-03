package com.k1rard.tiendamusicaldata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k1rard.tiendamusicalentities.entities.Factura;

/**
 * @author k1rard
 * Interface que define los metodos para realizar el CRUD de la tabla de factura.
 */
public interface FacturaDAO extends JpaRepository<Factura, Long>{
	
	
}

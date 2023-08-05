package com.k1rard.tiendamusicaldata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k1rard.tiendamusicalentities.entities.Factura;
import com.k1rard.tiendamusicalentities.entities.Persona;

/**
 * @author k1rard
 * Interface que define los metodos para realizar el CRUD de la tabla de factura.
 */
public interface FacturaDAO extends JpaRepository<Factura, Long>{
	
	/**
	 * Metodo que permite consultar la lista de facturas de la persona que ha realizado compras.
	 * @param persona {@link Persona} objeto que contiene la persona en sesion.
	 * @return {@link List} lista de facturas de la persona
	 */
	List<Factura> findAllByPersona(Persona persona);
}

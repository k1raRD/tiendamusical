package com.k1rard.tiendamusicaldata.dao;

import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author k1rard
 * Interface que implementa los metodos genericos para el CRUD con SPRING JPA
 * hacia la tabla de carrito_album
 */
public interface CarritoAlbumDAO extends JpaRepository<CarritoAlbum, Long> {
}

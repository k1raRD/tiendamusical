/**
 * 
 */
package com.k1rard.tiendamusicaldata.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.k1rard.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.k1rard.tiendamusicalentities.entities.Album;

/**
 * @author k1rard
 * Clase DAO que contiene los metodos del CRUD con JPA para la tabla de albums
 */
@Repository
public interface AlbumDAO extends JpaRepository<Album, Long> {

	@Query("SELECT new com.k1rard.tiendamusicalentities.dto.AlbumTopTenDTO(ca, SUM(ca.cantidad) as cantidadSuma) "
			+ "FROM CarritoAlbum ca "
			+ "INNER JOIN ca.album a "
			+ "WHERE ca.estatus = 'PAGADO' "
			+ "GROUP BY a.nombre")
	Page<AlbumTopTenDTO> consultarAlbumsTopTen(Pageable pageable);
}

package com.k1rard.tiendamusicaldata.dao;

import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalentities.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author k1rard
 * Clase que representa el DAO para el CRUD hacia la tabla de Artistas.
 */
public interface ArtistaDAO extends JpaRepository<Artista, Long> {

    @Query("SELECT new com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO(a, ar) "
            + "FROM Album a "
            + "INNER JOIN a.artista ar "
            + "INNER JOIN ar.subGenero sg "
            + "INNER JOIN sg.genero g "
            + "WHERE ar.nombre LIKE %:filtro% "
            + "OR g.descripcion LIKE %:filtro% "
            + "OR sg.descripcion LIKE %:filtro% "
            + "OR a.nombre LIKE %:filtro% "
            + "ORDER BY ar.nombre")
    List<ArtistaAlbumDTO> consultarArtistasAlbumsPorFiltro(@Param("filtro") String filtro);
}

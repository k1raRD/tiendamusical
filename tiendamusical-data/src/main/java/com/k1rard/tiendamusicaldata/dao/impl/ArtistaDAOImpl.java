package com.k1rard.tiendamusicaldata.dao.impl;

import com.k1rard.tiendamusicaldata.common.CommonDAO;
import com.k1rard.tiendamusicaldata.dao.ArtistaDAO;
import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalentities.entities.Artista;

import java.util.List;

/**
 * @author k1rard
 * Clase que implementa el CRUD Generico para realizar las transacciones a la tabla de artista
 */
public class ArtistaDAOImpl extends CommonDAO<Artista, ArtistaDAO> {

    public List<ArtistaAlbumDTO> consultarArtistaAlbumPorFiltro(String filtro) {
        return repository.consultarArtistasAlbumsPorFiltro(filtro);
    }
}

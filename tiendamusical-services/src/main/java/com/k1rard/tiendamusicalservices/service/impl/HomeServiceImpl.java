package com.k1rard.tiendamusicalservices.service.impl;

import com.k1rard.tiendamusicaldata.dao.ArtistaDAO;
import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalservices.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author k1rard
 * Clase que implementa los metodos de logica de negocio de la interface de HomeService
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ArtistaDAO artistaDAOImpl;

    @Override
    public List<ArtistaAlbumDTO> consultarAlbumsFiltro(String filtro) {
        return artistaDAOImpl.consultarArtistasAlbumsPorFiltro(filtro);
    }
}

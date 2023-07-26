package com.k1rard.tiendamusicalservices.service;

import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;

import java.util.List;

/**
 * @author k1rard
 * Interface que define los metodos de logica de negocio para la pantalla del home
 */
public interface HomeService {

    /**
     * Metodo que permite consultar los albums con base al filtro que ingrese el cliente en el buscador de su home.
     * @param filtro {@link String} Texto ingresado por el usuario.
     * @return {@link List} Lista de album que coinciden con el texto ingresado.
     */
    List<ArtistaAlbumDTO> consultarAlbumsFiltro(String filtro);
}

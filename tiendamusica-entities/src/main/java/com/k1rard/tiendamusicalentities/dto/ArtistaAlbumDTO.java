package com.k1rard.tiendamusicalentities.dto;

import com.k1rard.tiendamusicalentities.entities.Album;
import com.k1rard.tiendamusicalentities.entities.Artista;

import java.io.Serializable;

/**
 * @author k1rard
 * Clase DTO personalizada para obtener la informacion de los albums que el cliente busca
 * a traves del filtro de busqueda del dashboard.
 */
public class ArtistaAlbumDTO implements Serializable {

    /**
     * Objeto que contiene la información del artista.
     */
    private Artista artista;
    /**
     * Objeto que obtiene la información del album.
     */
    private Album album;

    public ArtistaAlbumDTO() {
        super();
    }

    /**
     * Contructor que inicializa la informacion consultada para la busqueda de albums.
     * @param artista {@link Artista} Objeto que contiene la informacion del artista (banda o solista).
     * @param album {@link Album} Objeto que contiene la informacion del album.
     */
    public ArtistaAlbumDTO(Album album, Artista artista) {
        this.album = album;
        this.artista = artista;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}

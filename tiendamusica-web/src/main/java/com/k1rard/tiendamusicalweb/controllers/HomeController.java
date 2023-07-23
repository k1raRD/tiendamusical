package com.k1rard.tiendamusicalweb.controllers;

import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalservices.service.HomeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author k1rard
 * Clase que controla el flujo de informacion para la pantalla de home de cualquier tipo de usuario.
 */
@Named
@ViewScoped
public class HomeController {

    /**
     * Texto ingresado por el cliente en el buscador
     */
    private String filtro;

    /**
     * Lista obtenida a partir del filtro ingresado en el buscador
     */
    private List<ArtistaAlbumDTO> artistasAlbumDTO;

    /**
     * Se inyecta el objeto de Spring para obtener los metodos de logica de negocio
     */
    @Autowired
    private HomeService homeServiceImpl;

    /**
     * Metodo que inicializa la pantalla
     */
    @PostConstruct
    public void init() {
        System.out.println("Inicializando el home...");
    }

    /**
     * Metodo que permite obtener los albums de los artistas encontrados en la base de datos
     * con respecto al filtro ingresado por el cliente.
     */
    public void consultarAlbumsArtistasPorFiltro() {
        this.artistasAlbumDTO =  this.homeServiceImpl.consultarAlbumsFiltro(this.filtro);

        if(this.artistasAlbumDTO != null) {
            this.artistasAlbumDTO.forEach(a -> {
                System.out.println("Artista: " + a.getArtista().getNombre());
            });
        }
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<ArtistaAlbumDTO> getArtistasAlbumDTO() {
        return artistasAlbumDTO;
    }

    public void setArtistasAlbumDTO(List<ArtistaAlbumDTO> artistasAlbumDTO) {
        this.artistasAlbumDTO = artistasAlbumDTO;
    }

    public HomeService getHomeServiceImpl() {
        return homeServiceImpl;
    }

    public void setHomeServiceImpl(HomeService homeServiceImpl) {
        this.homeServiceImpl = homeServiceImpl;
    }
}
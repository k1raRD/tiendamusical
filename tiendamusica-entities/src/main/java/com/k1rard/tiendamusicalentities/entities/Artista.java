package com.k1rard.tiendamusicalentities.entities;

import jakarta.persistence.*;

/**
 * @author k1rard
 * Clase que representa entidades de artistas de bandas o singles
 */
@Entity
@Table(name = "artista")
public class Artista extends Common{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArtista")
    private Long idArtista;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "imagen", length = 100)
    private String imagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="idNacionalidad")
    private Nacionalidad nacionalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSubGenero")
    private SubGenero subGenero;

    public Long getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Long idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public SubGenero getSubGenero() {
        return subGenero;
    }

    public void setSubGenero(SubGenero subGenero) {
        this.subGenero = subGenero;
    }
}

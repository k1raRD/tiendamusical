package com.k1rard.tiendamusicalentities.entities;

import jakarta.persistence.*;

/**
 * @author k1rard
 * Clase que representa entidades de sibgenero de musica
 */
@Entity
@Table(name = "subgenero")
public class SubGenero extends Common{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubGenero")
    private Long idSubGenero;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGenero")
    private Genero genero;

    public Long getIdSubGenero() {
        return idSubGenero;
    }

    public void setIdSubGenero(Long idSubGenero) {
        this.idSubGenero = idSubGenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}

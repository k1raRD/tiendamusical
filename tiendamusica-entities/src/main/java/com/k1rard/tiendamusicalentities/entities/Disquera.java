package com.k1rard.tiendamusicalentities.entities;

import jakarta.persistence.*;

/**
 * @author k1rard
 * Clase que representa entidades de disquera.
 */
@Entity
@Table(name = "disquera")
public class Disquera extends Common{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDisquera;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    public Long getIdDisquera() {
        return idDisquera;
    }

    public void setIdDisquera(Long idDisquera) {
        this.idDisquera = idDisquera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

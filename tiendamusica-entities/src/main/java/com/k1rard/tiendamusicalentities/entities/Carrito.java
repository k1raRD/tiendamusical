package com.k1rard.tiendamusicalentities.entities;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author k1rard
 * Clase que representa entidades de carrito de compras de una persona o usuario.
 */
@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarrito")
    private Long idCarrito;

    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @OneToMany(mappedBy = "carrito", fetch = FetchType.EAGER)
    private List<CarritoAlbum> carritosAlbum;

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<CarritoAlbum> getCarritosAlbum() {
        return carritosAlbum;
    }

    public void setCarritosAlbum(List<CarritoAlbum> carritosAlbum) {
        this.carritosAlbum = carritosAlbum;
    }


}

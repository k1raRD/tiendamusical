package com.k1rard.tiendamusicalentities.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author k1rard
 * Clase que representa la cantidad de entidades de albums en el carrito de la persona.
 */
@Entity
@Table(name = "carrito_album")
public class CarritoAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarritoAlbum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCarrito")
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "idAlbum")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "fechaCompra")
    private LocalDateTime fechaCompra;

    public Long getIdCarritoAlbum() {
        return idCarritoAlbum;
    }

    public void setIdCarritoAlbum(Long idCarritoAlbum) {
        this.idCarritoAlbum = idCarritoAlbum;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEStatus(String estatus) {
        this.estatus = estatus;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}

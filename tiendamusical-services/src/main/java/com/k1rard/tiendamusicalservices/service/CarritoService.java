package com.k1rard.tiendamusicalservices.service;

import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalentities.entities.Carrito;
import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;

/**
 * @author k1rard
 * Interface que define los metodos de logica de negocio para el carrito de compras.
 */
public interface CarritoService {

    /**
     * Metodo que permite guardar los albums a comprar en el carrito de compra
     * @param artistaAlbumDTO {@link ArtistaAlbumDTO} objeto con la informacion del album del carrito.
     * @param carrito {@link Carrito} Objeto con la informacion del carrito del usuario.
     * @param cantidadAlbumSeleccionada {@link Integer} cantidad seleccionada del album por el usuario.
     */
    CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDTO artistaAlbumDTO, Carrito carrito, Integer cantidadAlbumSeleccionada);

    /**
     * Metodo que permie calcular el total de la compra
     * @param carrito {@link Carrito} objeto con al informacion del carrito a calcular
     * @return {@link Double} Regresa la cantidad total calculada.
     */
    Double calcularTotal(Carrito carrito);

    /**
     * Metodo que permite eliminar un album del carrito.
     * @param carritoAlbum {@link CarritoAlbum} Objeto con el album del carrito a eliminar.
     */
    void eliminarAlbumCarrito(CarritoAlbum carritoAlbum);

    /**
     * Metodo que permite actualizar la cantidad del album a comprar en el carrito.
     * @param carritoAlbum {@link CarritoAlbum} Objeto con el album a actualizar en el carrito.
     * @param carrito {@link Carrito} Objeto con el carrito de compras del usuario.
     */
    Double actualizarAlbumCantidad(CarritoAlbum carritoAlbum, Carrito carrito);
}

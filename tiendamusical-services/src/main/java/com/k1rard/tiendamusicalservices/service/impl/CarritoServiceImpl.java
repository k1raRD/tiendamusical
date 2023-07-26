package com.k1rard.tiendamusicalservices.service.impl;

import com.k1rard.tiendamusicaldata.dao.CarritoAlbumDAO;
import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalentities.entities.Carrito;
import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalservices.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author k1rard
 * Clase que implementa los metodos de la logica de negocio de carrito service para el carrito de compras
 */
@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoAlbumDAO carritoAlbumDAOImpl;

    @Override
    public CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDTO artistaAlbumDTO, Carrito carrito, Integer cantidadAlbumSeleccionada) {
        CarritoAlbum carritoAlbum = new CarritoAlbum();
        carritoAlbum.setAlbum(artistaAlbumDTO.getAlbum());
        carritoAlbum.setCarrito(carrito);
        carritoAlbum.setCantidad(cantidadAlbumSeleccionada);
        carritoAlbum.setEStatus("PENDIENTE");

        this.carritoAlbumDAOImpl.save(carritoAlbum);

        return carritoAlbum;
    }

    @Override
    public Double calcularTotal(Carrito carrito) {
        AtomicReference<Double> total = new AtomicReference<>(0.0);

        // Se realiza el calculo del total de la compra.
        carrito.getCarritosAlbum().forEach(ca -> {
            total.updateAndGet(v -> v + ca.getAlbum().getValor() * ca.getCantidad());
        });

        return total.get();
    }

    @Override
    public void eliminarAlbumCarrito(CarritoAlbum carritoAlbum) {
        this.carritoAlbumDAOImpl.delete(carritoAlbum);
    }

    @Override
    public Double actualizarAlbumCantidad(CarritoAlbum carritoAlbum, Carrito carrito) {
        this.carritoAlbumDAOImpl.save(carritoAlbum);
        return this.calcularTotal(carrito);
    }
}

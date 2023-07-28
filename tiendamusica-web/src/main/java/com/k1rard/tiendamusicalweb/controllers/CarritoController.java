package com.k1rard.tiendamusicalweb.controllers;

import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalservices.service.CarritoService;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author k1rard
 * Clase que se encarga de controlar el flujo de la pantalla del carrito de compras
 */
@Named
@ViewScoped
public class CarritoController {

    /**
     * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
     */
    private static final Logger LOGGER = LogManager.getLogger(CarritoController.class);

    /**
     * Objeto que contiene la informacion en sesion del usuario
     */
    @Inject
    private SessionBean sessionBean;

    /**
     * Objeto que realiza la logica de negocio para el carrito de compra.
     */
    @Autowired
    private CarritoService carritoServiceImpl;

    /**
     * Inicializa la informacion de la pantalla y del carrito
     */
    @PostConstruct
    public void init() {
        this.sessionBean.setPaso(0);
    }

    /**
     * Metodo que permite calcular el total de la compra con respecto a los albums en el carrito.
     */
    public Double calcularTotal() {
        LOGGER.info("Calculando total...");
        Double total = this.carritoServiceImpl.calcularTotal(sessionBean.getPersona().getCarrito());
        this.sessionBean.setTotal(total);
        return total;
    }

    /**
     * Metodo que permite eliminar un album del carrito del usuario.
     * @param carritoAlbum {@link CarritoAlbum} Objeto con el album a eliminar el carrito.
     */
    public void eliminarAlbumCarrito(CarritoAlbum carritoAlbum) {
        LOGGER.info("Eliminando album carrito " + carritoAlbum.getAlbum().getNombre());
        this.carritoServiceImpl.eliminarAlbumCarrito(carritoAlbum);
        this.sessionBean.getPersona().getCarrito().getCarritosAlbum().remove(carritoAlbum);
    }

    /**
     * Metodo que permite actualizar la cantidad y los totales del album a comprar.
     * @param carritoAlbum {@link CarritoAlbum} Objeto que contiene el album a actualizar y su cantidad.
     */
    public void actualizarCantidadCarrito(CarritoAlbum carritoAlbum) {
        this.carritoServiceImpl.actualizarAlbumCantidad(carritoAlbum, this.sessionBean.getPersona().getCarrito());
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public CarritoService getCarritoServiceImpl() {
        return carritoServiceImpl;
    }

    public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
        this.carritoServiceImpl = carritoServiceImpl;
    }


}

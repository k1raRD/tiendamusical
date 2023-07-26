package com.k1rard.tiendamusicalweb.controllers;

import com.k1rard.tiendamusicalweb.utils.CommonUtils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author k1rard
 * Controlador que se encarga del flujo y acciones de la barra de navegacion
 */
@Named
@ViewScoped
public class NavBarController {

    /**
     * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
     */
    private static final Logger LOGGER = LogManager.getLogger(NavBarController.class);

    /**
     *
     */
    @PostConstruct
    public void init(){
        LOGGER.info("Inicializando pantalla carrito...");
    }

    /**
     * Metodo que permite redireccionar a la pantalla del carrito de compras
     */
    public void redireccionar(){
        try {
            CommonUtils.redireccionar("/pages/cliente/carrito.xhtml");
        } catch (IOException e) {
            CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!", "Hubo un problema al momento de intentar ingresar al carrito, favor de intentarlo mas tarde.");
            LOGGER.error(e.getMessage());
        }
    }
}

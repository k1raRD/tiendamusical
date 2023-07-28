package com.k1rard.tiendamusicalweb.controllers;

import com.k1rard.tiendamusicalweb.session.SessionBean;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author k1rard
 * Controlador que maneja el flujo de cambio de pasos en el proceso de compra de productos.
 */
@Named
@ViewScoped
public class PasosController {

    /**
     * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
     */
    private static final Logger LOGGER = LogManager.getLogger(PasosController.class);

    /**
     * Objeto que contiene la informacion en sesion del usuario
     */
    @Inject
    private SessionBean sessionBean;

    @PostConstruct
    public void init() {
        LOGGER.info("Ingresando a PasosController");
    }

    /**
     * Metodo que permite redireccionar al siguiente paso del proceso de compra del producto
     * @param url {@link String} url con la pantalla siguiente a mostrar.
     * @param paso {@link Integer} numero del paso siguiente de la compra.
     */
    public void cambiarPasos(String url, int paso) {
        try {
            this.sessionBean.setPaso(paso);
            CommonUtils.redireccionar(url);
        } catch (IOException e) {
            CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!", "Hubo un problema al tratar de ingresar al siguiente paso de la compra, favor de intentarlo mas tarde");
        }
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}

package com.k1rard.tiendamusicalweb.session;

import com.k1rard.tiendamusicalweb.utils.CommonUtils;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.IOException;

/**
 * @author k1rard
 * Clase que permite cerrar la sesion del usuario y redireccionar a la pantalla de login
 */
@Named
@ViewScoped
public class SessionClose {

    /**
     * Metodo que permite cerrar la sesion del usuario.
     */
    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            CommonUtils.redireccionar("/login.xhtml");
        } catch (IOException e) {
            CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!", "Hubo un problema al tratar de regresar al login, favor de intentar mas tarde");
            throw new RuntimeException(e);
        }
    }
}

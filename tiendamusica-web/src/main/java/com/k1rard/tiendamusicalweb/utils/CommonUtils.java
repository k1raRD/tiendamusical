/**
 * 
 */
package com.k1rard.tiendamusicalweb.utils;

import java.io.IOException;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

/**
 * @author k1rard
 * Clase generada para crear funciones globales o comunes entre clases del proyecto
 */
public class CommonUtils {
	
	/**
	 * Metodo que permite mostrar un mensaje al usuario.
	 * @param severity {@link Severity} objeto que indica el tipo de mensaje a mostrar
	 * @param summary {@link String} Titulo del mensaje.
	 * @param detail {@link String} Detalle del mensaje;
	 */
	public static void mostrarMensaje(Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	/**
	 * Metodo que permite redireccionar entre paginas del aplicativo
	 * @param url {@link String} direccion o pantalla a redireccionar 
	 * @throws IOException {@link IOException} excepcion en caso de error al no encontrar la pagina
	 */
	public static void redireccionar(String url) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String contextPath = externalContext.getRequestContextPath();
		externalContext.redirect(contextPath + url);
	}
}

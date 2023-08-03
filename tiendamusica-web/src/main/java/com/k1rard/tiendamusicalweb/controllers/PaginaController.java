/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que controla el flujo para cambiar de pantalla.
 */
@Named
@ViewScoped
public class PaginaController {

	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(PaginaController.class);
	
	/**
	 * Inicializa la carga del controlador de paginas.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando controlador de Pagina controller...");
	}
	
	
	/**
	 * Metodo que permite redireccionar entre pantallas del aplicativo.
	 * @param pagina {@link String} pagina a ingresar.
	 */
	public void redireccionarPagina(String pagina) {
		try {
			CommonUtils.redireccionar(pagina);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un erro al cambiar a la pantalla de " + pagina);
			e.printStackTrace();
		}
	}
}

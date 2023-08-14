/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.k1rard.tiendamusicalentities.entities.Carrito;
import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalentities.entities.Rol;
import com.k1rard.tiendamusicalservices.service.RegistroService;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que controla el flujo para el formulario de registro de persona.
 */
@Named
@ViewScoped
public class RegistroController {
	
	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o
	 * en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(RegistroController.class);

	/**
	 * Objeto con la persona a registrarse
	 */
	private Persona persona;
	
	/**
	 * Objeto que contiene los metodos de logica de negocio para el registro de usuario.
	 */
	@Autowired
	private RegistroService registroService;
	
	/**
	 * Metodo que permite inicializar la pantalla de registro. 
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando la pantalla de registro...");
	}
	
	public void initializer() {
		LOGGER.info("Inicializando los objetos de persona...");
		this.limiparComponentes();
	}
	
	/**
	 * Metodo que permite limpiar o inicializar componentes.
	 */
	public void limiparComponentes() {
		this.persona = new Persona();
		this.persona.setRol(new Rol());
		this.persona.setCarrito(new Carrito());
	}
	
	/**
	 * Metodo que permite guardar un nuevo usuario en el sistema.
	 */
	public void guardar() {
		Persona personaGuardada = this.registroService.registrarPersona(this.persona);
		
		if(personaGuardada.getIdPersona() != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "El usuario " + personaGuardada.getNombre() + "se ha registrado exitosamente.");
			this.limiparComponentes();
		} else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Ocurrio un error al registrarse, favor de intentarlo mas tarde.");
		}
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the registroService
	 */
	public RegistroService getRegistroService() {
		return registroService;
	}

	/**
	 * @param registroService the registroService to set
	 */
	public void setRegistroService(RegistroService registroService) {
		this.registroService = registroService;
	}
	
	
}

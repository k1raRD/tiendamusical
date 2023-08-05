/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalservices.service.InfoPersonalService;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que controla el flujo de informacion de la pantalla de infopersonal del usuario.
 */
@Named
@ViewScoped
public class InfoPersonalController {

	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(InfoPersonalController.class);
	
	/**
	 * Objeto que contiene la informacion en sesion de la persona.
	 */
	@Inject
	private SessionBean sessionBean;
	
	/**
	 * Objeto que contiene los metodos de logica de negocio de la informacion personal de la persona.
	 */
	@Autowired
	private InfoPersonalService infoPersonalService;
	
	/**
	 * Inicializa la informacion de la pantalla
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Iniciando la pantalla de informacion personal...");
	}
	
	/**
	 * Metodo que permite actualizar la informacio de la persona en sesion.
	 */
	public void actualizarPersona() {
		Persona personaActualizada = this.infoPersonalService.actualizarPersona(sessionBean.getPersona());
		
		if(personaActualizada != null) {
			this.sessionBean.setPersona(personaActualizada);
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tu informacion ha sido actualizada, vuelve a iniciar sesion para aplicar los cambios");
		}
	}

	/**
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * @return the infoPersonalService
	 */
	public InfoPersonalService getInfoPersonalService() {
		return infoPersonalService;
	}

	/**
	 * @param infoPersonalService the infoPersonalService to set
	 */
	public void setInfoPersonalService(InfoPersonalService infoPersonalService) {
		this.infoPersonalService = infoPersonalService;
	}
	
	
}

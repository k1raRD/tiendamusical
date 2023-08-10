/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.k1rard.tiendamusicalentities.entities.Rol;
import com.k1rard.tiendamusicalservices.service.AdminPerfilesService;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que controla el flujo de informacion de la pantalla de administracion de perfiles.
 */
@Named
@ViewScoped
public class AdminPerfilesController {
	
	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o
	 * en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AdminPerfilesController.class);

	/**
	 * Perfiles a mostrar en el datatable.
	 */
	private List<Rol> perfiles;
	
	/**
	 * Perfiles filtrados del datatable.
	 */
	private List<Rol> perfilesFiltrados;
	
	/**
	 * Objeto que contiene los metodos de logica de negocio para la administracion de perfiles.
	 */
	@Autowired
	private AdminPerfilesService adminPerfilesService;
	
	/**
	 * Objeto con el perfil o rol a actualizar o guardar
	 */
	private Rol perfil;
	
	/**
	 * Inicializando pantalla de administracion de perfiles
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando la pantalla de administracion de perfiles...");
	}
	
	
	/**
	 * Metodo que permite inicializar la informacion al inicializar la vista de perfiles.
	 */
	public void initializer() {
		this.consultar();
		this.limpiarComponentes();
	}
	
	/**
	 * Metodo que permite consultar el listado de perfiles para el datatable.
	 */
	public void consultar() {
		LOGGER.info("Consultando la informacion de perfiles desde la base de datos");
		this.perfiles = this.adminPerfilesService.consultarPerfiles();
	}
	
	/**
	 * Metodo que permite limpiar los componentes de la pantalla
	 */
	public void limpiarComponentes() {
		this.perfil = new Rol();
	}
	
	/**
	 * Metodo que permite guardar un perfil
	 */
	public void guardar() {
		Rol rolGuardado =  this.adminPerfilesService.guardarPefil(this.perfil);
		
		if(rolGuardado.getIdRol() != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!", "Perfil guardado/actualizo con exito.");
			
			this.consultar();
			PrimeFaces.current().executeScript("PF('dlgPerfil').hide()");
		} else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "Hubo un error al tratar de guardar o actualizar, favor de intentarlo mas tarde.");
		}
	}
	
	/**
	 * Metodo que permite cargar el perfil seleccionado del datatable para actualizar
	 * @param perfil {@link Rol} perfil seleccionado.
	 */
	public void cargarPerfil(Rol perfil) {
		this.perfil = perfil;
	}

	/**
	 * @return the perfiles
	 */
	public List<Rol> getPerfiles() {
		return perfiles;
	}

	/**
	 * @param perfiles the perfiles to set
	 */
	public void setPerfiles(List<Rol> perfiles) {
		this.perfiles = perfiles;
	}

	/**
	 * @return the perfilesFiltrados
	 */
	public List<Rol> getPerfilesFiltrados() {
		return perfilesFiltrados;
	}

	/**
	 * @param perfilesFiltrados the perfilesFiltrados to set
	 */
	public void setPerfilesFiltrados(List<Rol> perfilesFiltrados) {
		this.perfilesFiltrados = perfilesFiltrados;
	}

	/**
	 * @return the adminPerfilesService
	 */
	public AdminPerfilesService getAdminPerfilesService() {
		return adminPerfilesService;
	}

	/**
	 * @param adminPerfilesService the adminPerfilesService to set
	 */
	public void setAdminPerfilesService(AdminPerfilesService adminPerfilesService) {
		this.adminPerfilesService = adminPerfilesService;
	}


	/**
	 * @return the perfil
	 */
	public Rol getPerfil() {
		return perfil;
	}


	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Rol perfil) {
		this.perfil = perfil;
	}
	
	
	
}

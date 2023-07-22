/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import java.io.Serializable;

import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalservices.service.LoginService;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author k1rard
 * Controlador que se encarga del flujo de la pantalla login.xhtml
 */
@Named
@ViewScoped
public class LoginController implements Serializable{

	/**
	 * Identificador del objeto serializado
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Usuario capturado por la persona
	 */
	private String usuario;

	/**
	 * Propiedad de la logica de negocio inyectada con JSF y Spring.
	 */
	@Autowired
	private LoginService loginServiceImpl;

	/**
	 * Password capturado por la persona
	 */
	private String password;
	
	@PostConstruct
	public void init() {
		System.out.println("Inicializando login...");
	}
	
	/**
	 * Metodo que permite al usuario ingresar a la pantalla home.
	 */
	public void entrar() {

		Persona personaConsultada = this.loginServiceImpl.consultarUsuarioLogin(this.usuario, this.password);

		if (personaConsultada != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "EXITOSO!", "Bienvenido al home!");
		} else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!", "El usuario y/o password son incorrectos");
		}
	}
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public LoginService getLoginServiceImpl() {
		return loginServiceImpl;
	}

	public void setLoginServiceImpl(LoginService loginServiceImpl) {
		this.loginServiceImpl = loginServiceImpl;
	}
}

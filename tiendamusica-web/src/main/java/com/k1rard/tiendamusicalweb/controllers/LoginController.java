/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalservices.service.LoginService;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author k1rard Controlador que se encarga del flujo de la pantalla
 *         login.xhtml
 */
@Named
@ViewScoped
public class LoginController implements Serializable {

	/**
	 * Identificador del objeto serializado
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o
	 * en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

	/**
	 * Usuario capturado por la persona
	 */
	private String usuario;

	/**
	 * Password capturado por la persona
	 */
	private String password;

	/**
	 * Propiedad de la logica de negocio inyectada con JSF y Spring.
	 */
	@Autowired
	private LoginService loginServiceImpl;

	@Inject
	private SessionBean sessionBean;

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
			try {
				// SE FILTRAN LOS ALBUMS CONSULTADOS EN EL CARRITO POR EL estatus de PENDIENTE

				if (personaConsultada.getRol().getIdRol() == 4) {
					List<CarritoAlbum> filtrados = personaConsultada.getCarrito().getCarritosAlbum().stream()
							.filter(ca -> ca.getEstatus().equals("PENDIENTE")).collect(Collectors.toList());

					personaConsultada.getCarrito().setCarritosAlbum(filtrados);

					LOGGER.info("Albums del carrito filtrados...");
				}
				
				this.sessionBean.setPersona(personaConsultada);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				session.setAttribute("sessionBean", this.sessionBean);
				CommonUtils.redireccionar("/pages/commons/dashboard.xhtml");
				
			} catch (IOException e) {
				e.printStackTrace();
				CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "ERROR!",
						"Formato incorrecto en el cual se ingresa a la pantalla deseada");
			}
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
	
	@Produces
	@Named("sessionBean")
	public SessionBean produceSessionBean() {
		return this.sessionBean;
	}
}

/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalservices.service.CarritoService;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author k1rard
 * Clase que controla el flujo de la pantalla del detalle.xhtml
 */
@Named
@ViewScoped
public class DetalleController {
	
	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(DetalleController.class);
	
	/**
	 * Cantidad del producto seleccionado
	 */
	private Integer cantidadAlbumSeleccionada;

	/**
	 * Objeto que contiene los metodos de la logica de negocio del carrito.
	 */
	@Autowired
	private CarritoService carritoServiceImpl;

	/**
	 * Objeto que contiene la informaicon del usuario en sesion.
	 */
	@Inject
	private SessionBean sessionBean;
	
	
	/**
	 * Inicializa la pantalla del detalle
	 */
	@PostConstruct
	public void init() {
		this.cantidadAlbumSeleccionada = 1;
	}

	/**
	 * Metodo que permite agregar el album seleccionado por el usuario al carrito de compra
	 * @param artistaAlbumDTO {@link ArtistaAlbumDTO} objeto con el album seleccionado
	 */
	public void agregarAlbumCarrito(ArtistaAlbumDTO artistaAlbumDTO) {
		LOGGER.info("Agregando album al carrito de compra..." + ", cantidad " + this.cantidadAlbumSeleccionada);

		CarritoAlbum carritoAlbumAgregado =  this.carritoServiceImpl.guardarAlbumsCarrito(artistaAlbumDTO, this.sessionBean.getPersona().getCarrito(), cantidadAlbumSeleccionada);

		this.sessionBean.getPersona().getCarrito().getCarritosAlbum().add(carritoAlbumAgregado);
	}

	/**
	 * @return the cantidadAlbumSeleccionada
	 */
	public Integer getCantidadAlbumSeleccionada() {
		return cantidadAlbumSeleccionada;
	}

	/**
	 * @param cantidadAlbumSeleccionada the cantidadAlbumSeleccionada to set
	 */
	public void setCantidadAlbumSeleccionada(Integer cantidadAlbumSeleccionada) {
		this.cantidadAlbumSeleccionada = cantidadAlbumSeleccionada;
	}

	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
}

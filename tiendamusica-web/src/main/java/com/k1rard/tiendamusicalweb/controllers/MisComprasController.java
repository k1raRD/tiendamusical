/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalentities.entities.Factura;
import com.k1rard.tiendamusicalservices.service.FacturaService;
import com.k1rard.tiendamusicalweb.session.SessionBean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que controla el flujo de informacion de la pantalla de miscompras del cliente.
 */
@Named
@ViewScoped
public class MisComprasController {

	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(MisComprasController.class);
	
	/**
	 * Lista a mostrarse en la tabla de facturas del cliente.
	 */
	private List<Factura> facturas;
	
	/**
	 * Lista de albums en el carrito comprado de la factura.
	 */
	private List<CarritoAlbum> carritosAlbum;
	
	/**
	 * Objeto que contiene los metodos para realizar la logica de negocio para las facturas
	 */
	@Autowired
	private FacturaService facturaService;
		
	/**
	 * Objeto que contiene la informacion en sesion de la persona.
	 */
	@Inject
	private SessionBean sessionBean;
	
	/**
	 * Inicializa la informacion de la pantalla de compra.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando pantalla de mis compras.");
	}
	
	/**
	 * Metodo que permite consultar la lista de facturas por persona.
	 */
	public void consultarFacturasPorPersona() {
		String nombreCompleto = this.sessionBean.getPersona().getNombre() + " " + this.sessionBean.getPersona().getPrimerApellido() + " " + this.sessionBean.getPersona().getSegundoApellido(); 
		LOGGER.info("Consultando las facturas de..." + nombreCompleto);
		setFacturas(this.facturaService.consultarFacturasPersona(this.sessionBean.getPersona())); 
	}
	
	
	/**
	 * Metodo que permite mostrar informacion del detalle de la compra.
	 * @param carritosAlbum {@link CarritoAlbum} lista de albums que contiene la faactura.
	 */
	public void mostrarDetalle(List<CarritoAlbum> carritosAlbum) {
		setCarritosAlbum(carritosAlbum);
	}

	/**
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * @param facturas the facturas to set
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/**
	 * @return the facturaService
	 */
	public FacturaService getFacturaService() {
		return facturaService;
	}

	/**
	 * @param facturaService the facturaService to set
	 */
	public void setFacturaService(FacturaService facturaService) {
		this.facturaService = facturaService;
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
	 * @return the carritosAlbum
	 */
	public List<CarritoAlbum> getCarritosAlbum() {
		return carritosAlbum;
	}

	/**
	 * @param carritosAlbum the carritosAlbum to set
	 */
	public void setCarritosAlbum(List<CarritoAlbum> carritosAlbum) {
		this.carritosAlbum = carritosAlbum;
	}
	
	
	
}

/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalentities.entities.Factura;
import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalservices.client.ReportesServiceClient;
import com.k1rard.tiendamusicalservices.service.CarritoService;
import com.k1rard.tiendamusicalservices.service.FacturaService;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.Response;

/**
 * @author k1rard
 * Controlador que se encarga de generar la factura y reporte con el webservice de Reportes.
 * Controla del flujo de la compra realizada por el cliente.
 */
@Named
@ViewScoped
public class PagoController {
	
	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(PagoController.class);
	
	/**
	 * Objeto que contiene la informacion en sesion del usuario.
	 */
	@Inject
	private SessionBean sessionBean;
	
	/**
	 * Objeto que contiene los metodos que realiza la logica de negocio de la generacion de la factura.
	 */
	@Autowired
	private FacturaService facturaServiceImpl;
	
	/**
	 * Objeto que contiene los metodos de la logica de negocio para la actualizacion de los productos a comprar
	 */
	@Autowired
	private CarritoService carritoServiceImpl;
	
	/**
	 * Objeto que contiene el cliente que permite consumir el webservice de reportes.
	 */
	@Autowired
	private ReportesServiceClient reportesServiceClient;

	/**
	 * Inicializa la funcionalidad para el proceso de pago.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando el proceso de pago");
	}
	
	/**
	 * Metodo que permite guardar la factura y generar la orden de compra.
	 */
	public void guardarFatura() {
		LOGGER.info("Guardando factura...");
		
		// Se obtiene la respuesta de la orden de compra generada por Paypal.
		HttpResponse<Order> order =  this.sessionBean.getOrder();
		Persona persona = this.sessionBean.getPersona();
		
		// Se ejecuta la funcion que permite guardar la factura y orden del cliente.
		Factura factura = new Factura();
		Factura facturaGenerada = this.facturaServiceImpl.guardarFactura(factura, order.result(), persona);
		
		// Si se genero la factura en la base de datos.
		if(facturaGenerada != null ) {
			
			// Se actualiza el estatus de los productos del carrito de la persona y se le asigna la factura generada
			boolean productosCarritoActualizados = 
					this.carritoServiceImpl.actualizarCarritoAlbum(persona.getCarrito().getCarritosAlbum(), facturaGenerada);
			
			if(productosCarritoActualizados) {
				
				String cliente = persona.getNombre() + persona.getPrimerApellido() + persona.getSegundoApellido();
				
				// Se consume el webservice para generar el reporte con jasper.
				Response response = this.reportesServiceClient.generarReporte(order.result().id(), persona.getEmail(), cliente);
				
				LOGGER.info("Response: " + response.getStatus());
				
				this.sessionBean.getPersona().getCarrito().setCarritosAlbum(new ArrayList<CarritoAlbum>());
				
				this.cambiarPasos("/pages/cliente/confirmacion.xhtml", 2);
			}
		} else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR!", "No se genero la factura");
		}
	}
	
    /**
     * Metodo que permite redireccionar al siguiente paso del proceso de compra del producto
     * @param url {@link String} url con la pantalla siguiente a mostrar.
     * @param paso {@link Integer} numero del paso siguiente de la compra.
     */
    public void cambiarPasos(String url, int paso) {
        try {
            this.sessionBean.setPaso(paso);
            CommonUtils.redireccionar(url);
        } catch (IOException e) {
            CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!", "Hubo un problema al tratar de ingresar al siguiente paso de la compra, favor de intentarlo mas tarde");
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
	 * @return the facturaServiceImpl
	 */
	public FacturaService getFacturaServiceImpl() {
		return facturaServiceImpl;
	}

	/**
	 * @param facturaServiceImpl the facturaServiceImpl to set
	 */
	public void setFacturaServiceImpl(FacturaService facturaServiceImpl) {
		this.facturaServiceImpl = facturaServiceImpl;
	}

	/**
	 * @return the carritoServiceImpl
	 */
	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	/**
	 * @param carritoServiceImpl the carritoServiceImpl to set
	 */
	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}

	/**
	 * @return the reportesServiceClient
	 */
	public ReportesServiceClient getReportesServiceClient() {
		return reportesServiceClient;
	}

	/**
	 * @param reportesServiceClient the reportesServiceClient to set
	 */
	public void setReportesServiceClient(ReportesServiceClient reportesServiceClient) {
		this.reportesServiceClient = reportesServiceClient;
	}
	
	
	
}

/**
 * 
 */
package com.k1rard.tiendamusicalweb.session;

import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalentities.entities.Persona;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que mantendra la informacion en la sesion del usuario 
 */
@Named("sessionBean")
@SessionScoped
public class SessionBean {
	
	/**
	 * Objeto persona que se mantendra en la sesion.
	 */
	private Persona persona;
	
	/**
	 * Objeto que contendra la informacion del detalle del album seleccionado por el cliente.
	 */
	private ArtistaAlbumDTO artistaAlbumDTO;

	/**
	 * Total generado de la compra en sesion
	 */
	private Double total;

	/**
	 * Orden generada por paypal
	 */
	private HttpResponse<Order> order;

	/**
	 * Numero del paso actual del proceso de compra.
	 */
	private Integer paso;
	
	@PostConstruct
	public void init() {
		System.out.println("Creando sesion...");
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
	 * @return the artistaAlbumDTO
	 */
	public ArtistaAlbumDTO getArtistaAlbumDTO() {
		return artistaAlbumDTO;
	}

	/**
	 * @param artistaAlbumDTO the artistaAlbumDTO to set
	 */
	public void setArtistaAlbumDTO(ArtistaAlbumDTO artistaAlbumDTO) {
		this.artistaAlbumDTO = artistaAlbumDTO;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setOrder(HttpResponse<Order> response) {
		this.order = response;
	}

	public HttpResponse<Order> getOrder() {
		return order;
	}

	public Integer getPaso() {
		return paso;
	}

	public void setPaso(Integer paso) {
		this.paso = paso;
	}
	
}

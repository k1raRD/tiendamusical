/**
 * 
 */
package com.k1rard.tiendamusicalentities.dto;

import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;

/**
 * @author k1rard
 * Clase DTO para generar la grafica de TOP 10 de albums mas vendidos para el administrador
 */
public class AlbumTopTenDTO {

	/**
	 * Objeto con la informacion del album del carrito
	 */
	private CarritoAlbum carritoAlbum;
	
	/**
	 * Cantidad total vendida por album
	 */
	private long cantidadSuma;
	
	/**
	 * Constructor default
	 */
	public AlbumTopTenDTO() {
		super();
	}
	
	/**
	 * Constructor que permite inicializar la informacion del objeto carritoAlbum y cantidadSuma del album.
	 * @param carritoAlbum {@link CarritoAlbum} Objeto con la informacion del album vendido.
	 * @param cantidadSuma {@link Integer} suma total de albums vendidos.
	 */
	public AlbumTopTenDTO(CarritoAlbum carritoAlbum, long cantidadSuma) {
		this.carritoAlbum = carritoAlbum;
		this.cantidadSuma = cantidadSuma;
	}

	/**
	 * @return the carritoAlbum
	 */
	public CarritoAlbum getCarritoAlbum() {
		return carritoAlbum;
	}

	/**
	 * @param carritoAlbum the carritoAlbum to set
	 */
	public void setCarritoAlbum(CarritoAlbum carritoAlbum) {
		this.carritoAlbum = carritoAlbum;
	}

	/**
	 * @return the cantidadSuma
	 */
	public long getCantidadSuma() {
		return cantidadSuma;
	}

	/**
	 * @param cantidadSuma the cantidadSuma to set
	 */
	public void setCantidadSuma(long cantidadSuma) {
		this.cantidadSuma = cantidadSuma;
	}
	
	
}

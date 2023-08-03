/**
 * 
 */
package com.k1rard.tiendamusicalservices.service;

import com.k1rard.tiendamusicalentities.entities.Factura;
import com.k1rard.tiendamusicalentities.entities.Persona;
import com.paypal.orders.Order;

/**
 * @author k1rard
 * Interface que define los metodos de logica de negocio para la generacion de factura.
 */
public interface FacturaService {

	/**
	 * Metodo que permite generar la factura de compra del pedido del cliente.
	 * @param factura {@link Factura} objeto con la informacion de la factura generada.
	 * @param order {@link Order} Objeto con la orden generada por Paypal.
	 * @param persona {@link Persona} Objeto con la informacion de la persona a la que se le asigna la factura.
	 * @return factura {@link Factura} factura guardada en la base de datos.
	 */
	Factura guardarFactura(Factura factura, Order order, Persona persona);
}

/**
 * 
 */
package com.k1rard.tiendamusicalservices.client;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author k1rard
 * Clase cliente que consume el webservice de reportes.
 */
@Service
public class ReportesServiceClient {
	
	@Value("${spring.base.url.ws.reportes}")
	String urlReportesWS;

	/**
	 * Metodo que permite consumir el webservice para generar el reporte jasper.
	 * @param orderID {@link String} Orden de compra del cliente.
	 * @param destinatario {@link String} Correo o email del cliente.
	 * @param cliente {@link String} nombre completo del cliente.
	 * @return {@link Response} respuesta generada con el webservice.
	 */
	public Response generarReporte(String orderID, String destinatario, String cliente) {
		ClientConfig clientConfig = new ClientConfig();
		
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(this.urlReportesWS).path("generarReporte");
		
		Form form = new Form();
		form.param("orderId", orderID);
		form.param("cliente", cliente);
		form.param("destinatario", destinatario);
		
		Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
		
		return response;
	}
}

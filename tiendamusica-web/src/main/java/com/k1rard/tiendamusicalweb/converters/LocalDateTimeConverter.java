/**
 * 
 */
package com.k1rard.tiendamusicalweb.converters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.k1rard.tiendamusicalweb.utils.DateUtils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * @author k1rard
 * Clase converter que permite convertir el tipo de dato LocalDateTime a un String.
 */
@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter implements Converter<LocalDateTime>{

	@Override
	public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
		return LocalDateTime.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
		LocalDateTime fecha = value;
		Date fechaPersonalizada = Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
		
		return DateUtils.convertDateToString("dd/MM/yyyy", fechaPersonalizada);
	}

}

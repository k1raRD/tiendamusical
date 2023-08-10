/**
 * 
 */
package com.k1rard.tiendamusicalweb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author k1rard
 * Clase de utileria para las fecha del proyecto
 */
public class DateUtils {

	/**
	 * Metodo que permite convertir una fecha con formato en un String.
	 * @param formato {@link String} Formato de fecha (EX: dd/MM/yyyy).
	 * @param fecha {@link Date} objeto con la fecha a convertir a String.
	 * @return {@link String} Cadena de la fecha con formato
	 */
	public static String convertDateToString(String formato, Date fecha) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		String fechaConverted = simpleDateFormat.format(fecha);
		return fechaConverted;
	}
}

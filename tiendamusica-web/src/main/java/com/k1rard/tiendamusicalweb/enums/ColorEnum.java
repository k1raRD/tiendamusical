/**
 * 
 */
package com.k1rard.tiendamusicalweb.enums;

/**
 * @author k1rard
 * Enumeracion con los colores de la grafica del administrador
 */
public enum ColorEnum {

	COLOR_ROJO(1, "rgba(255, 45, 0, 0.5)"),
	
	COLOR_AMARILLO(2, "rgba(255, 255, 0, 0.5)"),
	
	COLOR_VERDE(3, "rgba(35, 255, 0, 0.5)"),
	
	COLOR_MORADO(4, "rgba(174, 0, 255, 0.5)"),
	
	COLOR_AZUL(5, "rgba(0, 19, 255, 0.5)");
	
	private int valor;
	
	private String descripcion;

	ColorEnum(int valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}

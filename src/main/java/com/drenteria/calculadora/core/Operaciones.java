package com.drenteria.calculadora.core;

/**
 * Enumeracion que contiene las operaciones basicas permitidas por el programa
 * @author Daniel
 *
 */
public enum Operaciones {
	
	SUMA("suma"),
	RESTA("resta"),
	MULTIPLICACION("multiplicacion"),
	DIVISION("division"),
	POTENCIACION("potenciacion");
	
	private String nombreOperacion;
	
	private Operaciones(String nombre) {
		this.nombreOperacion = nombre;
	}

	public String getNombreOperacion() {
		return nombreOperacion;
	}
}

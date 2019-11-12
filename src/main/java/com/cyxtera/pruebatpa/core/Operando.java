package com.cyxtera.pruebatpa.core;

/**
 * Clase POJO creada para poder admitir un via REST Json un nuevo operando
 * a una sesion de calculadora
 * @author Daniel
 *
 */
public class Operando {
	
	public String valor;
	
	public String idSesion;
	
	public Operando() {
		valor = "";
		idSesion = "";
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}

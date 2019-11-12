package com.cyxtera.pruebatpa.api;

/**
 * Clase POJO creada para poder admitir un via REST Json una operacion en una sesion
 * a una sesion de calculadora
 * @author Daniel
 *
 */
public class Operacion {
	
	private String operacion;
	
	private String idSesion;
	
	public Operacion() {
		operacion = "";
		idSesion = "";
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

}

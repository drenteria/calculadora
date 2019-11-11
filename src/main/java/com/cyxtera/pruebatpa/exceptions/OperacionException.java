package com.cyxtera.pruebatpa.exceptions;

public class OperacionException extends Exception {
	
	public OperacionException(String mensaje) {
		super(mensaje);
	}
	
	public OperacionException(String mensaje, Throwable error) {
		super(mensaje, error);
	}

}

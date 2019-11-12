package com.cyxtera.pruebatpa.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cyxtera.pruebatpa.exceptions.OperacionException;

public class OperacionesFactory {
	
	private static final Logger logger = LogManager.getLogger();
	
	/**
	 * Determina si una operacion dada hace parte de las operaciones permitidas en la
	 * calculadora
	 * @param nombreOperacion Nombre de la operacion solicitada
	 * @return <code>true</code> si es una operacion permitida. <code>false</code> en caso contrario.
	 */
	private static Operaciones esOperacionValida(String nombreOperacion) {
		for (Operaciones op : Operaciones.values()) {
			if(op.getNombreOperacion().equalsIgnoreCase(nombreOperacion)) {
				return op;
			}
		}
		return null;
	} 
	
	/**
	 * Metodo para obtener una operación matematica para la calculadora
	 * @param nombreOperacion Nombre de la operacion solicitada
	 * @return Una instancia de la clase <code>IOperacion</code> correspondiente a la operación pedida
	 * @throws OperacionException Si el nombre de la operacion solicitada no concuerda con ninguna de las opciones permitidas
	 */
	public static IOperacion getOperacion(String nombreOperacion) throws OperacionException {
		
		logger.debug("OperacionesFactory - Operacion solicitada -> " + nombreOperacion);
		Operaciones op = esOperacionValida(nombreOperacion);
		
		if(op == null) {
			logger.error("OperacionesFactory - Operacion no permitida");
			throw new OperacionException("Operacion no permitida para la Calculadora");
		}
		
		switch(op) {
			case SUMA:
				return new OpSuma();
			case RESTA:
				return new OpResta();
			case MULTIPLICACION:
				return new OpMultiplicacion();
			case DIVISION:
				return new OpDivision();
			case POTENCIACION:
				return new OpPotenciacion();
			default:
				return null;
		}
		
	}
	
	public static IOperacion getOperacion(Operaciones operacion) throws OperacionException {
		return getOperacion(operacion.getNombreOperacion());
	}
	
	
	

}

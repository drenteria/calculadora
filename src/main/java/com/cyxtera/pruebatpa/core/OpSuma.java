package com.cyxtera.pruebatpa.core;

import java.util.List;

import com.cyxtera.pruebatpa.exceptions.OperacionException;



/**
 * Clase de implementación de la operacion Suma
 * 
 * @author Daniel
 *
 */
public class OpSuma implements IOperacion {

	@Override
	public Double ejecutarOperacion(List<Double> operandos) throws OperacionException {
		/*
		 * La operación suma es la mas sencilla, simplemente suma todos los operandos sin restriccion
		 */
		Double resultado = 0D;
		try {
			for(Double operando : operandos) {
				resultado += operando;
			}
		} catch (Exception e) {
			throw new OperacionException("Error al ejecutar operacion Suma", e);
		}
		return resultado;
	}

}

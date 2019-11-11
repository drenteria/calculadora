package com.cyxtera.pruebatpa.core;

import java.util.List;

import com.cyxtera.pruebatpa.exceptions.OperacionException;


public class OpPotenciacion implements IOperacion {

	@Override
	public Double ejecutarOperacion(List<Double> operandos) throws OperacionException {
		
		Double resultado = 1D;
		try {
			if(operandos.isEmpty())
				return 0D;
			else {
				Double operador1 = operandos.remove(0);
				resultado = Math.pow(operador1, 1D);
				for(Double operando : operandos) {
					resultado = Math.pow(resultado, operando);
				}
			}
			
			
		} catch (Exception e) {
			throw new OperacionException("Error al ejecutar operacion Potenciacion", e);
		}
		return resultado;
	}

}

package com.cyxtera.pruebatpa.core;

import java.util.List;

import com.cyxtera.pruebatpa.exceptions.OperacionException;

public class OpMultiplicacion implements IOperacion {

	@Override
	public Double ejecutarOperacion(List<Double> operandos) throws OperacionException {
		
		Double resultado = 1D;
		try {
			if(operandos.isEmpty()) {
				return 0D;
			}
			for(Double operando : operandos) {
				resultado *= operando;
			}
		} catch (Exception e) {
			throw new OperacionException("Error al ejecutar operacion Multiplicacion", e);
		}
		return resultado;
	}

}

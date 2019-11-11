package com.cyxtera.pruebatpa.core;

import java.util.List;

import com.cyxtera.pruebatpa.exceptions.OperacionException;



public class OpDivision implements IOperacion {

	@Override
	public Double ejecutarOperacion(List<Double> operandos) throws OperacionException {
		
		Double resultado = 1D;
		try {
			
			if(operandos.contains(new Double(0D))) {
				throw new IllegalArgumentException("La lista de operadores contiene al menos un valor cero. No es posible ejecutar la operacion");
			}
			
			if (operandos.isEmpty())
				return 0D;
			else if(operandos.size() == 1)
				resultado = operandos.get(0);
			else if (operandos.size() == 2)
				resultado = operandos.get(0) / operandos.get(1);
			else {
				Double operando1 = operandos.remove(0);
				Double operando2 = operandos.remove(0);
				resultado = operando1 / operando2;
				for(Double operandoN : operandos) {
					resultado /= operandoN;
				}
			}
		} catch (Exception e) {
			throw new OperacionException("Error al ejecutar operacion Division", e);
		}
		return resultado;
	}

}

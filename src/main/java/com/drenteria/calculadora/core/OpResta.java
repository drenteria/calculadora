package com.drenteria.calculadora.core;

import java.util.List;

import com.drenteria.calculadora.exceptions.OperacionException;


/**
 * Clase de implementación de la operacion Resta
 * 
 * @author Daniel
 *
 */
public class OpResta implements IOperacion {

	@Override
	public Double ejecutarOperacion(List<Double> operandos) throws OperacionException {
		
		Double resultado = 0D;
		
		try {
			if (operandos.isEmpty())
				return resultado;
			else if(operandos.size() == 1)
				resultado = operandos.get(0);
			else if (operandos.size() == 2)
				resultado = operandos.get(0) - operandos.get(1);
			else {
				Double operando1 = operandos.remove(0);
				Double operando2 = operandos.remove(0);
				resultado = operando1 - operando2;
				for(Double operandoN : operandos) {
					resultado -= operandoN;
				}
			}
			
		} catch (Exception e) {
			throw new OperacionException("Error al ejecutar operacion Resta", e);
		}
		return resultado;
		
	}

}

package com.drenteria.calculadora.core;

import java.util.List;

import com.drenteria.calculadora.exceptions.OperacionException;

/**
 * 
 * Clase Interfaz para las operaciones matematicas solicitadas para la prueba
 * 
 * @author Daniel
 *
 */
public interface IOperacion {
	
	/**
	 * Ejecuta la operacion solicitada en la clase implementadora
	 * @param operandos Listado de los operandos para el calculo
	 * @return Resultado de la operacion solicitada sobre todos los operandos
	 */
	public Double ejecutarOperacion(List<Double> operandos) throws OperacionException;

}

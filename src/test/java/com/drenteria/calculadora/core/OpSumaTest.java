package com.drenteria.calculadora.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.drenteria.calculadora.exceptions.OperacionException;

class OpSumaTest {
	
	private static OpSuma suma;
	
	private static ArrayList<Double> operandos;
	
	@BeforeAll
	public static void prepararPrueba() {
		suma = new OpSuma();
		operandos = new ArrayList<Double>();
	}

	/**
	 * Se suma una lista que tenga un solo operando
	 * Se espera como resultado el mismo numero
	 */
	@Test
	void testSumaUnDigito() {
		Double operando = Math.random();
		operandos.add(operando);
		try {
			assertEquals(operando, suma.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * Una lista vacia espera como resultado cero (0)
	 */
	@Test
	void testSumaListaVacia(){
		operandos.clear();
		try {
			assertEquals(0D, suma.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} 
	}
	
	/**
	 * Una lista nula genera una excepción
	 */
	@Test
	void testSumaListaNula() {
		try {
			suma.ejecutarOperacion(null);
			fail("Se espera una excepcion");
		} catch (OperacionException e) {
			assertTrue(e.getMessage().contains("Error al ejecutar"));
		}
	}
	
	/**
	 * Se suma una lista de tamaño y valores fijos
	 */
	@Test
	void testSumaListaTamanioFijo() {
		operandos.add(10D);
		operandos.add(15D);
		operandos.add(106D);
		operandos.add(21D);
		try {
			assertEquals(152D, suma.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * La operacion puede admitir numeros negativos, que se suman normalmente
	 */
	@Test
	void testSumaNumerosNegativos() {
		operandos.add(-10D);
		operandos.add(30D);
		operandos.add(-20D);
		try {
			assertEquals(0D, suma.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * Una lista de tamaño y numeros aleatorios
	 */
	@Test
	void testSumaListaTamanioRandom() {
		
		/*
		 * Se genera una lista de tamaño aleatorio entre 1 y 20
		 * y se generan numeros dobles aleatorios. Estos se suman tanto en la prueba como
		 * en la instancia de la operacion
		 */
		int tamanio = new Random().nextInt(19) + 1;
		Double esperado = 0D;
		for(int i = 0; i < tamanio; i++) {
			Double operando = new Random().nextDouble();
			operandos.add(operando);
			esperado += operando;
		}
		
		try {
			assertEquals(esperado, suma.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
		
	}

}

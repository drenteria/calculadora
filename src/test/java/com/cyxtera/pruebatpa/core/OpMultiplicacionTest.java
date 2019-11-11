package com.cyxtera.pruebatpa.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cyxtera.pruebatpa.exceptions.OperacionException;

class OpMultiplicacionTest {

	private static OpMultiplicacion multiplicacion;

	private static ArrayList<Double> operandos;

	@BeforeAll
	public static void prepararPrueba() {
		multiplicacion = new OpMultiplicacion();
		operandos = new ArrayList<Double>();
	}
	
	/**
	 * Una lista vacia espera como resultado cero (0)
	 */
	@Test
	void testMultListaVacia(){
		operandos.clear();
		try {
			assertEquals(0D, multiplicacion.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} 
	}
	
	/**
	 * Una lista nula genera una excepción
	 */
	@Test
	void testMultListaNula() {
		try {
			multiplicacion.ejecutarOperacion(null);
			fail("Se espera una excepcion");
		} catch (OperacionException e) {
			assertTrue(e.getMessage().contains("Error al ejecutar"));
		}
	}
	
	/**
	 * Se multiplica una lista de tamaño y valores fijos
	 */
	@Test
	void testMultListaTamanioFijo() {
		operandos.add(2D);
		operandos.add(3D);
		operandos.add(9D);
		operandos.add(10D);
		try {
			assertEquals(540D, multiplicacion.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * La operacion puede admitir numeros negativos, que se multiplican normalmente
	 */
	@Test
	void testMultNumerosNegativos() {
		operandos.add(2D);
		operandos.add(3D);
		operandos.add(9D);
		operandos.add(-10D);
		try {
			assertEquals(-540D, multiplicacion.ejecutarOperacion(operandos));
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
	void testMultListaTamanioRandom() {
		
		/*
		 * Se genera una lista de tamaño aleatorio entre 1 y 20
		 * y se generan numeros dobles aleatorios. Estos se suman tanto en la prueba como
		 * en la instancia de la operacion
		 */
		int tamanio = new Random().nextInt(19) + 1;
		Double esperado = 1D;
		for(int i = 0; i < tamanio; i++) {
			Double operando = new Random().nextDouble();
			operandos.add(operando);
			esperado *= operando;
		}
		
		try {
			assertEquals(esperado, multiplicacion.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
		
	}
	
	/**
	 * Se multiplica una lista que tenga un solo operando
	 * Se espera como resultado el mismo numero
	 */
	@Test
	void testMultUnDigito() {
		Double operando = Math.random();
		operandos.add(operando);
		try {
			assertEquals(operando, multiplicacion.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}

}

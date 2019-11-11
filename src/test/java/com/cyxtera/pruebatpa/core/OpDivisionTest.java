package com.cyxtera.pruebatpa.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cyxtera.pruebatpa.exceptions.OperacionException;

class OpDivisionTest {
	
	private static OpDivision division;

	private static ArrayList<Double> operandos;

	@BeforeAll
	public static void prepararPrueba() {
		division = new OpDivision();
		operandos = new ArrayList<Double>();
	}

	/**
	 * Una lista vacia espera como resultado cero (0)
	 */
	@Test
	void testDivListaVacia() {
		operandos.clear();
		try {
			assertEquals(0D, division.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Una lista nula genera una excepción
	 */
	@Test
	void testDivListaNula() {
		try {
			division.ejecutarOperacion(null);
			fail("Se espera una excepcion");
		} catch (OperacionException e) {
			assertTrue(e.getMessage().contains("Error al ejecutar"));
		}
	}
	
	/**
	 * Se divide una lista que tenga un solo operando 
	 * Se espera como resultado el mismo numero
	 */
	@Test
	void testDivUnDigito() {
		Double operando = Math.random();
		operandos.add(operando);
		try {
			assertEquals(operando, division.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * Se divide una lista de tamaño y valores fijos
	 */
	@Test
	void testDivListaTamanioFijo() {
		operandos.add(1000D);
		operandos.add(10D);
		operandos.add(2D);
		try {
			assertEquals(50D, division.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * Lista que contiene valor cero. Se espera una excepción
	 */
	@Test
	void testDivListaConCero() {
		operandos.add(1000D);
		operandos.add(0D);
		operandos.add(2D);
		try {
			division.ejecutarOperacion(operandos);
			fail("Se esperaba una excepción");
		} catch (OperacionException e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * Se divide una lista de tamaño y valores fijos
	 * con valores negativos
	 */
	@Test
	void testDivValoresNegativos() {
		operandos.add(1000D);
		operandos.add(-10D);
		operandos.add(2D);
		try {
			assertEquals(-50D, division.ejecutarOperacion(operandos));
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
	void testDivListaTamanioRandom() {

		ArrayList<Double> operandosAuxiliar = new ArrayList<Double>();

		/*
		 * Se genera una lista de tamaño aleatorio entre 2 y 20 y se generan numeros
		 * dobles aleatorios. Estos se dividen tanto en la prueba como en la instancia de
		 * la operacion
		 */
		int tamanio = new Random().nextInt(18) + 2;
		Double esperado = 1D;
		for (int i = 0; i < tamanio; i++) {
			Double operando = new Random().nextDouble() * 100;
			if (operando.equals(0D))
				operando = 1D;
			operandos.add(operando);
			operandosAuxiliar.add(operando);
		}

		Double op1 = operandosAuxiliar.remove(0);
		esperado = op1 / esperado;
		Double op2 = operandosAuxiliar.remove(0);
		esperado = esperado / op2;

		for (Double operando : operandosAuxiliar) {
			esperado /= operando;
		}

		try {
			assertEquals(esperado, division.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
		

}

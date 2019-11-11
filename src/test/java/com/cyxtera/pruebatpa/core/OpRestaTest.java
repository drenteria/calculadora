package com.cyxtera.pruebatpa.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cyxtera.pruebatpa.exceptions.OperacionException;

class OpRestaTest {

	private static OpResta resta;

	private static ArrayList<Double> operandos;

	@BeforeAll
	public static void prepararPrueba() {
		resta = new OpResta();
		operandos = new ArrayList<Double>();
	}

	/**
	 * Una lista vacia espera como resultado cero (0)
	 */
	@Test
	void testRestaListaVacia() {
		operandos.clear();
		try {
			assertEquals(0D, resta.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Una lista nula genera una excepción
	 */
	@Test
	void testRestaListaNula() {
		try {
			resta.ejecutarOperacion(null);
			fail("Se espera una excepcion");
		} catch (OperacionException e) {
			assertTrue(e.getMessage().contains("Error al ejecutar"));
		}
	}

	/**
	 * Se resta una lista que tenga un solo operando Se espera como resultado el
	 * mismo numero
	 */
	@Test
	void testRestaUnDigito() {
		Double operando = Math.random();
		operandos.add(operando);
		try {
			assertEquals(operando, resta.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}

	/**
	 * Se resta una lista de tamaño y valores fijos
	 */
	@Test
	void testRestaListaTamanioFijo() {
		operandos.add(1000D);
		operandos.add(1D);
		operandos.add(10D);
		operandos.add(100D);
		try {
			assertEquals(889D, resta.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}

	/**
	 * Se resta una lista con valores negativos
	 */
	@Test
	void testRestaListaNegativos() {
		operandos.add(10D);
		operandos.add(-20D);
		operandos.add(10D);
		try {
			assertEquals(20D, resta.ejecutarOperacion(operandos));
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
	void testRestaListaTamanioRandom() {

		ArrayList<Double> operandosAuxiliar = new ArrayList<Double>();

		/*
		 * Se genera una lista de tamaño aleatorio entre 2 y 20 y se generan numeros
		 * dobles aleatorios. Estos se restan tanto en la prueba como en la instancia de
		 * la operacion
		 */
		int tamanio = new Random().nextInt(18) + 2;
		Double esperado = 0D;
		for (int i = 0; i < tamanio; i++) {
			Double operando = new Random().nextDouble() * 100;
			operandos.add(operando);
			operandosAuxiliar.add(operando);
		}

		Double op1 = operandosAuxiliar.remove(0);
		esperado = op1 - esperado;
		Double op2 = operandosAuxiliar.remove(0);
		esperado = esperado - op2;

		for (Double operando : operandosAuxiliar) {
			esperado -= operando;
		}

		try {
			assertEquals(esperado, resta.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}

	}

}

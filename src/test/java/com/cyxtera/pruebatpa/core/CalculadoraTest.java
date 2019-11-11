package com.cyxtera.pruebatpa.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cyxtera.pruebatpa.exceptions.OperacionException;

class CalculadoraTest {

	private static Calculadora laCalculadora;

	@BeforeAll
	private static void prepararPrueba() {
		laCalculadora = new Calculadora();
	}

	@Test
	void testGetIdSesion() {
		assertNotNull(laCalculadora.getIdSesion());
	}

	@Test
	void testGetListaOperandos() {
		assertNotNull(laCalculadora.getListaOperandos());
	}

	@Test
	void testAdicionarOperando() {
		// Adicionar operandos numericos
		laCalculadora.limpiarLista();
		assertTrue(laCalculadora.adicionarOperando("10"));
		assertTrue(laCalculadora.adicionarOperando("30"));
		assertEquals(2, laCalculadora.getListaOperandos().size());

		// Adicionar operandos literales - fallo
		assertFalse(laCalculadora.adicionarOperando("mamá"));
		assertEquals(2, laCalculadora.getListaOperandos().size());
	}

	@Test
	void testEjecutarOperacionListaVacia() {
		// Adicionar operandos numericos
		laCalculadora.limpiarLista();
		try {
			laCalculadora.ejecutarOperacion(Operaciones.SUMA.getNombreOperacion());
		} catch (Exception e) {
			assertTrue(e instanceof OperacionException);
		}
	}

	@Test
	void testEjecutarOperacionSuma() {
		// Adicionar operandos numericos
		laCalculadora.limpiarLista();
		for (int i = 1; i <= 5; i++) {
			laCalculadora.adicionarOperando(String.valueOf(i));
		}
		try {
			assertEquals(15, laCalculadora.ejecutarOperacion(Operaciones.SUMA.getNombreOperacion()));
		} catch (OperacionException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testEjecutarOperacionNoExiste() {
		// Adicionar operandos numericos
		laCalculadora.limpiarLista();
		for (int i = 1; i <= 5; i++) {
			laCalculadora.adicionarOperando(String.valueOf(i));
		}
		try {
			assertEquals(15, laCalculadora.ejecutarOperacion("OperacionInventada"));
			fail("OperacionInventada no esta permitida");
		} catch (OperacionException e) {
			assertTrue(e instanceof OperacionException);
		}
	}

	@Test
	void testEliminarOperando() {
		// Adicionar operandos numericos
		laCalculadora.limpiarLista();
		for (int i = 0; i < 10; i++) {
			laCalculadora.adicionarOperando(String.valueOf(i));
		}
		assertTrue(laCalculadora.eliminarUltimoOperando());
		assertTrue(laCalculadora.eliminarUltimoOperando());
		assertEquals(8, laCalculadora.getListaOperandos().size());
	}

}

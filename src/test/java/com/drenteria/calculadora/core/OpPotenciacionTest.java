package com.drenteria.calculadora.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.drenteria.calculadora.exceptions.OperacionException;

class OpPotenciacionTest {

	private static OpPotenciacion potencia;

	private static ArrayList<Double> operandos;

	@BeforeAll
	public static void prepararPrueba() {
		potencia = new OpPotenciacion();
		operandos = new ArrayList<Double>();
	}
	
	/**
	 * Una lista vacia espera como resultado cero (0)
	 */
	@Test
	void testPotListaVacia(){
		operandos.clear();
		try {
			assertEquals(0D, potencia.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} 
	}
	
	/**
	 * Una lista nula genera una excepción
	 */
	@Test
	void testPotListaNula() {
		try {
			potencia.ejecutarOperacion(null);
			fail("Se espera una excepcion");
		} catch (OperacionException e) {
			assertTrue(e.getMessage().contains("Error al ejecutar"));
		}
	}
	
	/**
	 * Se potencia una lista de tamaño y valores fijos
	 */
	@Test
	void testPotListaTamanioFijo() {
		operandos.add(2D);
		operandos.add(3D);
		operandos.add(3D);
		try {
			assertEquals(512D, potencia.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * Se potencia una lista de tamaño y valores fijos
	 * con un valor en cero
	 */
	@Test
	void testPotListaConCeros() {
		operandos.add(2D);
		operandos.add(0D);
		operandos.add(3D);
		try {
			assertEquals(1D, potencia.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}
	
	/**
	 * La operacion puede admitir numeros negativos, que se potencian normalmente
	 */
	@Test
	void testPotNumerosNegativos() {
		operandos.add(2D);
		operandos.add(-1D);
		try {
			assertEquals(0.5, potencia.ejecutarOperacion(operandos));
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
	void testPotListaTamanioRandom() {
		
		/*
		 * Se genera una lista de tamaño aleatorio entre 1 y 5
		 * y se generan numeros dobles aleatorios. Estos se potencian tanto en la prueba como
		 * en la instancia de la operacion
		 */
		int tamanio = new Random().nextInt(4) + 1;
		Double esperado = 1D;
		for(int i = 0; i < tamanio; i++) {
			Double operando = new Random().nextDouble();
			operandos.add(operando);
			if(i == 0) {
				esperado = operando;
			} else {
				esperado = Math.pow(esperado, operando);
			}
		}
		
		try {
			assertEquals(esperado, potencia.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
		
	}
	
	/**
	 * Se potencia una lista que tenga un solo operando
	 * Se espera como resultado el mismo numero
	 */
	@Test
	void testPotUnDigito() {
		Double operando = Math.random();
		operandos.add(operando);
		try {
			assertEquals(operando, potencia.ejecutarOperacion(operandos));
		} catch (OperacionException e) {
			fail(e.getMessage());
		} finally {
			operandos.clear();
		}
	}

}

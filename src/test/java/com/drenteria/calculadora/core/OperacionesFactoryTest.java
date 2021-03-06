package com.drenteria.calculadora.core;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.drenteria.calculadora.exceptions.OperacionException;

class OperacionesFactoryTest {

	@Test
	void testFactoryEnum() {
		try {
			IOperacion suma = OperacionesFactory.getOperacion(Operaciones.SUMA);
			assertTrue(suma instanceof OpSuma);
			
			IOperacion resta = OperacionesFactory.getOperacion(Operaciones.RESTA);
			assertTrue(resta instanceof OpResta);
			
			IOperacion multiplicacion = OperacionesFactory.getOperacion(Operaciones.MULTIPLICACION);
			assertTrue(multiplicacion instanceof OpMultiplicacion);
			
			IOperacion division = OperacionesFactory.getOperacion(Operaciones.DIVISION);
			assertTrue(division instanceof OpDivision);
			
			IOperacion potencia = OperacionesFactory.getOperacion(Operaciones.POTENCIACION);
			assertTrue(potencia instanceof OpPotenciacion);
			
		} catch (OperacionException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFactoryString() {
		try {
			IOperacion suma = OperacionesFactory.getOperacion("suma");
			assertTrue(suma instanceof OpSuma);
			
			IOperacion resta = OperacionesFactory.getOperacion("resta");
			assertTrue(resta instanceof OpResta);
		} catch (OperacionException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFactorySumaFail() {
		try {
			IOperacion operacion = OperacionesFactory.getOperacion("summa");
		} catch (OperacionException e) {
			assertTrue(e.getMessage().contains("Operacion no permitida"));
		}
	}

}

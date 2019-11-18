package com.drenteria.calculadora.api;

import java.util.ArrayList;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.drenteria.calculadora.core.Calculadora;

/**
 * Clase singleton que se encarga de tener en memoria las calculadoras de sesion
 * que seran accedidas desde el servicio CalcService
 * 
 * @author Daniel
 *
 */
public class CalcManager {

	private static CalcManager instance;

	public ArrayList<Calculadora> listado;

	private static final Logger log = LogManager.getLogger();

	private CalcManager() {
		this.listado = new ArrayList<Calculadora>();
		log.info("Creada instancia de CalcManager");
	}

	public static CalcManager getInstance() {
		if (instance == null)
			instance = new CalcManager();
		return instance;
	}

	public synchronized Calculadora nuevaCalculadora() {
		Calculadora nuevaCalc = new Calculadora();
		listado.add(nuevaCalc);
		log.info("Se adiciona nueva calculadora con id de sesion -> " + nuevaCalc.getIdSesion());
		log.info("Tamaño de la lista -> " + listado.size());
		return nuevaCalc;
	}

	public Calculadora buscarCalculadora(String idSesion) {
		for (Calculadora calc : listado) {
			if (idSesion.equals(calc.getIdSesion())) {
				log.info("Calculadora encontrada en la lista -> " + idSesion);
				return calc;
			}
		}
		log.error("Calculadora no existe en lista -> " + idSesion);
		return null;
	}

	public synchronized Boolean removerCalculadora(String idSesion) {
		Boolean resultado = Boolean.FALSE;
		ListIterator<Calculadora> iterador = listado.listIterator();
		while (iterador.hasNext()) {
			Calculadora actual = iterador.next();
			if (actual.getIdSesion().equals(idSesion)) {
				iterador.remove();
				resultado = Boolean.TRUE;
				break;
			}
		}
		if (resultado) {
			log.info("Se remueve calculadora con id de sesion -> " + idSesion);
			log.info("Tamaño de la lista -> " + listado.size());
		}
		return resultado;
	}

}

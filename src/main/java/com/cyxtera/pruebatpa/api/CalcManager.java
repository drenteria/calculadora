package com.cyxtera.pruebatpa.api;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cyxtera.pruebatpa.core.Calculadora;

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

}

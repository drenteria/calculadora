package com.drenteria.calculadora.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.drenteria.calculadora.exceptions.OperacionException;


/**
 * Clase principal de la pueba de Calculadora. Esta clase impelmenta la logica de
 * generar una sesión, adicionar operandos y ejecutar la operación solicitada.
 * 
 * @author Daniel
 *
 */
public class Calculadora {
	
	private String idSesion;
	
	private ArrayList<Double> listadoOperandos;
	
	private IOperacion operacion;
	
	private static final Logger logger = LogManager.getLogger();
	
	/**
	 * Constructur de Clase
	 */
	public Calculadora() {
		generarIdSesion();
		listadoOperandos = new ArrayList<Double>();
		infoEnLog("Se ha creado la calculadora");
	}
	
	/**
	 * Genera un Id de sesion para la instancia actual de Calculadora
	 */
	private void generarIdSesion()
	{
		idSesion = UUID.randomUUID().toString();
	}
	
	/**
	 * Getter del atributo idSesion
	 * @return Valor actual del idSesion para la instancia de Calculadora
	 */
	public String getIdSesion() {
		return this.idSesion;
	}
	
	/**
	 * Obtiene una Lista con los operandos actuales almacenados en la calculadora
	 * @return Listado de los actuales operandos
	 */
	public synchronized List<Double> getListaOperandos(){
		if(this.listadoOperandos == null) {
			errorEnLog("Lista de operandos nula. Se crea nueva lista");
			return new ArrayList<Double>();
		}
		return this.listadoOperandos;
	}
	
	/**
	 * Obtiene una representacion de la lista de operandos actual
	 * @return Un string con los valores actuales de la lista de operandos
	 */
	private String getValoresListaOperandos() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int conteo = 0;
		int tamanoLista = getListaOperandos().size();
		for(Double operando : getListaOperandos()) {
			sb.append(operando);
			if(conteo < tamanoLista - 1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Adiciona un operando numérico a la lista de operandos actual
	 * @param operando String que representa el operando a adicionar a la lista de cálculo
	 * @return <code>true</code> si el operando es un numero valido y puede ser adicionado a la lista. <code>false</code> en caso contrario
	 */
	public boolean adicionarOperando(String operando) {
		try {
			Double auxOp = Double.valueOf(operando);
			this.getListaOperandos().add(auxOp);
			infoEnLog("Operando adicionado -> " + operando);
			return true;
		}
		catch (NumberFormatException e) {
			errorEnLog("No se adiciona operando no numerico", e);
			return false;
		}
		catch (Exception e) {
			errorEnLog("Error al adicionar operando", e);
			return false;
		}
		
	}
	
	/**
	 * Valida y ejecuta la operacion solicitada si es valida dentro del contexto de operaciones actuales
	 * @param nombreOperacion Nombre de la operacion solicitada
	 * @return El resultado numerico de la operacion solicitada
	 */
	public Double ejecutarOperacion(String nombreOperacion) throws OperacionException {
		
		Double resultado = 0D;
		
		if(getListaOperandos().isEmpty()) {
			errorEnLog("Lista de operandos vacía. No se puede ejecutar operación");
			throw new OperacionException("Lista de operandos vacía. Se requiere al menos un operando");
		}
		operacion = OperacionesFactory.getOperacion(nombreOperacion);
		infoEnLog("Operacion solicitada: " + nombreOperacion + " Operandos -> " + getValoresListaOperandos());
		
		resultado = operacion.ejecutarOperacion(getListaOperandos());
		infoEnLog("Resultado Operacion: " + String.valueOf(resultado));
		
		/*
		 * La lista de operandos se limpia y se adiciona el valor del ultimo resultado
		 * para permitir ejecutar nuevas operaciones
		 */
		limpiarLista();
		adicionarOperando(resultado.toString());
		
		return resultado;
	}
	
	/**
	 * Permite eliminar el ultimo operando insertados a la lista
	 * @return <code>true</code> si se pudo eliminar el operando o si la lista ya esta vacia. <code>false</code> en caso contrario
	 */
	public Boolean eliminarUltimoOperando() {
		Boolean resultado = true;
		try {
			int tamanoLista = getListaOperandos().size();
			if (tamanoLista != 0) {
				Double removido = getListaOperandos().remove(tamanoLista - 1);
				infoEnLog("Operando removido -> " + removido.toString());
			}
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}
	
	/**
	 * Elimina todos los operandos del listado de calculo
	 * @return <code>true</code> si se pudo limpiar la lista o si la lista ya esta vacia. <code>false</code> en caso contrario
	 */
	public boolean limpiarLista() {
		Boolean resultado = true;
		try {
			getListaOperandos().clear();
			infoEnLog("Se ha limpiado el listado de operandos");
		} catch (Exception e) {
			errorEnLog("Error al limpiar listado de operandos", e);
			resultado = false;
		}
		return resultado;
	}
	
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		sb.append("idSesion=").append(this.getIdSesion());
		sb.append("Operandos=");
		getValoresListaOperandos();
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Escribe el mensaje en el log a nivel INFO
	 * @param mensaje Texto a escribir en log
	 */
	private void infoEnLog(String mensaje) {
		StringBuffer buffer = new StringBuffer("idSesion->");
		buffer.append(getIdSesion()).append(" - ");
		buffer.append(mensaje);
		logger.info(buffer.toString());
			
	}
	
	/**
	 * Escribe el mensaje en el log a nivel ERROR
	 * @param mensaje Mensaje a escribir
	 * @param ex Excepcion que causa el error, si aplica
	 */
	private void errorEnLog(String mensaje, Throwable ex) {
		StringBuffer buffer = new StringBuffer("idSesion->");
		buffer.append(getIdSesion()).append(" - ");
		buffer.append(mensaje);
		if (ex != null)
			logger.error(buffer.toString(), ex);
		else
			logger.error(buffer.toString());
			
	}
	
	/**
	 * Escribe el mensaje en el log a nivel ERROR
	 * @param mensaje Texto a escribir en log
	 */
	private void errorEnLog(String mensaje) {
		errorEnLog(mensaje, null);
	}

}

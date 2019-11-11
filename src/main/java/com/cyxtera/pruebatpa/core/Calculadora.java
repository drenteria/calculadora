package com.cyxtera.pruebatpa.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cyxtera.pruebatpa.exceptions.OperacionException;


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
	
	/**
	 * Constructur de Clase
	 */
	public Calculadora() {
		generarIdSesion();
		listadoOperandos = new ArrayList<Double>();
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
		if(this.listadoOperandos == null)
			return new ArrayList<Double>();
		return this.listadoOperandos;
	}
	
	/**
	 * Obtiene una representacion de la lista de operandos actual
	 * @return Un string con los valores actuales de la lista de operandos
	 */
	public String getValoresListaOperandos() {
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
			return true;
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
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
			throw new OperacionException("Lista de operandos insuficiente. Se requiere al menos un operando");
		}
		operacion = OperacionesFactory.getOperacion(nombreOperacion);
		return operacion.ejecutarOperacion(getListaOperandos());
	}
	
	/**
	 * Permite eliminar el ultimo operando insertados a la lista
	 * @return <code>true</code> si se pudo eliminar el operando o si la lista ya esta vacia. <code>false</code> en caso contrario
	 */
	public Boolean eliminarUltimoOperando() {
		Boolean resultado = true;
		try {
			int tamanoLista = getListaOperandos().size();
			if (tamanoLista != 0)
				getListaOperandos().remove(tamanoLista - 1);
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
		} catch (Exception e) {
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

}

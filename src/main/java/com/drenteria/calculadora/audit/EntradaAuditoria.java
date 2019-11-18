package com.drenteria.calculadora.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EntradaAuditoria implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String idSesion;
	
	@Column
	private String operacion;
	
	@Column
	private String valor;
	
	@Column
	private Date fechaEntrada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}	
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer("EntradaAuditoria=[");
		sb.append("idSesion=").append(idSesion).append(", ");
		sb.append("operacion=").append(operacion).append(", ");
		sb.append("valor=").append(valor).append(", ");
		sb.append("fechaEntrada=").append(fechaEntrada.getTime());
		sb.append("]");
		return sb.toString();
	}
	

}

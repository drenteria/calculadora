package com.cyxtera.pruebatpa.audit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.cyxtera.pruebatpa.persistence.UtilHibernate;

public class AuditoriaManager {
	
	private static final Logger auditLogger = LogManager.getLogger();
	
	
	public AuditoriaManager() {
		
	}
	
	public void registrarAuditoriaCalc(String idSesion, String operacion, String valor) {
		EntradaAuditoria entry = new EntradaAuditoria();
		try {
			entry.setIdSesion(idSesion);
			entry.setOperacion(operacion);
			entry.setValor(valor);
			entry.setFechaEntrada(Calendar.getInstance().getTime());
			Session session = UtilHibernate.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(entry);
			session.getTransaction().commit();
			auditLogger.info("Se ha almacenado en base de datos la entrada de auditoria -> " + entry.toString());
		} catch (Exception e) {
			auditLogger.error("No fue posible almacenar entrada de auditoria para el idSesion -> " + idSesion, e);
		}
	}
	
	public List<EntradaAuditoria> obtenerAuditoriaParaSesion(String idSesion){
		auditLogger.info("Se solicita auditoria para el idSesion " + idSesion);
		ArrayList<EntradaAuditoria> listaPorSesion = new ArrayList<EntradaAuditoria>();
		
		Session session = UtilHibernate.getSessionFactory().openSession();
		listaPorSesion.addAll(session.createQuery("from CalcAuditoria", EntradaAuditoria.class).list());
		
		auditLogger.info("Acciones encontradas para el idSesion " + idSesion + ": " + listaPorSesion.size());
		return listaPorSesion;
	}

}

package com.cyxtera.pruebatpa.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilHibernate {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
			sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
	
	private static void closeSessionFactory() {
		getSessionFactory().close();
	}
	
	

}

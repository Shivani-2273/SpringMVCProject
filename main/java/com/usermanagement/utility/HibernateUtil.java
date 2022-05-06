package com.usermanagement.utility;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.usermanagement.model.Address;
import com.usermanagement.model.User;


public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// setting properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/usermanagement");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "123");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.FORMAT_SQL, "true");

				configuration.setProperties(settings);
				
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Address.class);

				sessionFactory = configuration.buildSessionFactory();
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sessionFactory;

	}
}

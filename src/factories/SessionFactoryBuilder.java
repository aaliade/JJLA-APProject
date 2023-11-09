package factories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.User;

public class SessionFactoryBuilder {
	
private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactroy(){
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass (User.class).buildSessionFactory();
		}
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
}


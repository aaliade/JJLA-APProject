package factories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Employee;
import models.User;

public class SessionFactoryBuilder {
	
private static SessionFactory sessionFactory;
	 
	public static SessionFactory getUserSessionFactroy(){
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass (User.class).buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static SessionFactory getEmployeeSessionFactroy(){
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass (Employee.class).buildSessionFactory();
		}
		return sessionFactory;
	}
	
	
	public static void closeSessionFactory() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
}


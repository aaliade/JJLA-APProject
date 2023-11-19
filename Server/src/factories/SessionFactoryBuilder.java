package factories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Customer;
import models.Employee;

public class SessionFactoryBuilder {
	private static SessionFactory employeeSessionFactory;
	private static SessionFactory customerSessionFactory;
	
	public static SessionFactory getEmployeeSessionFactroy(){
		if (employeeSessionFactory == null) {
			employeeSessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass (Employee.class).buildSessionFactory();
		}
		return employeeSessionFactory;
	}
	
	public static SessionFactory getCustomerSessionFactroy(){
		if (customerSessionFactory == null) {
			customerSessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass (Customer.class).buildSessionFactory();
		}
		return customerSessionFactory;
	}
	
	public static void closeCustomerSessionFactory() {
	    if (customerSessionFactory != null && !customerSessionFactory.isClosed()) {
	    	customerSessionFactory.close();
	    }
	}
	
	public static void closeEmployeeeSessionFactory() {
	    if (employeeSessionFactory != null && !employeeSessionFactory.isClosed()) {
	    	employeeSessionFactory.close();
	    }
	}
}
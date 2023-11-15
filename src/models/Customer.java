package models;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import factories.SessionFactoryBuilder;

@Entity(name="customer")
@PrimaryKeyJoinColumn(name = "username")
@Table(name = "customer")
public class Customer extends User implements Serializable { //in order for the class to be sent across a network it needs to be serialized 
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "custID")
	private int custID;
	
	@Column(name = "accountBalance")
	private float accountBalance;
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	public Customer() { //default constructor
		super();
		this.custID = 0;
		this.accountBalance = 0.0f;
		logger.info("Customer initialized");
	}
	
	public Customer(String username, String password, String firstName, String lastName, String phone,String address, String email,String usertype, int custID, float accountBalance) { //primary constructor
		super(username,password,firstName,lastName,phone,email,address,usertype);
		this.custID = custID;
		this.accountBalance = accountBalance;
		logger.info("Input accepted, Customer initialized");
	}
	
	public Customer(Customer cust) { //copy constructor
		super(cust.getUsername(), cust.getPassword(), cust.getFirstName(), cust.getLastName(), cust.getPhone(), cust.getEmail(),
				cust.getAddress(), cust.getUserType());
		this.custID = cust.custID;
		this.accountBalance = cust.accountBalance;
		logger.info("Customer copied");
	}	
	
	//Accessors and Mutators 
	public int getCustID() {
		logger.info("Customer ID returned");
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
		logger.info("Input accepted, Customer ID set");
	}

	public float getAccountBalance() {
		logger.info("Customer Account Balanced returned");
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
		logger.info("Input accepted, Customer Account Balance set");
	}
	
	public boolean create() {
	    Session session = null;
	    Transaction transaction = null;
	    Logger logger = LogManager.getLogger(getClass());

	    try {
	        session = SessionFactoryBuilder.getCustomerSessionFactroy().getCurrentSession();
	        transaction = session.beginTransaction();
	        session.save(this);
	        transaction.commit();
	        session.close();
	        return true;
	    } catch (HibernateException e) {
	        // Log and handle HibernateException
	        logger.error("Error occurred while creating customer", e);
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        return false;
	    } catch (Exception e) {
	        // Log and handle other exceptions
	        logger.error("Error occurred while creating customer", e);
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        return false;
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}
	
	public boolean findCustomer(String username) {
		Session session = null;
	    Transaction transaction = null;
	    Logger logger = LogManager.getLogger(getClass());
		 try {
			 session = SessionFactoryBuilder.getCustomerSessionFactroy().getCurrentSession();
			 transaction = session.beginTransaction();
			 Customer customer = session.get(Customer.class, username);
			 
			 if(customer!=null) {
				System.out.println(customer.getUsername());
				System.out.println(customer.getFirstName());
				System.out.println(customer.getLastName());
				System.out.println(customer.getAddress());
				System.out.println(customer.getCustID());
				System.out.println(customer.getEmail());
				transaction.commit();
				session.close();
				return true;
			 }else {
				 System.out.println("Customer Not Found");
				 transaction.commit();
				 session.close();
				 return false;
			 }
		 }catch (HibernateException e) {
		        // Log and handle HibernateException
		        logger.error("Error occurred while searching for customer", e);
		        if (transaction != null && transaction.isActive()) {
		            transaction.rollback();
		        }
		        return false;
		    } catch (Exception e) {
		        // Log and handle other exceptions
		        logger.error("Error occurred while searching for customer", e);
		        if (transaction != null && transaction.isActive()) {
		            transaction.rollback();
		        }
		        return false;
		    } finally {
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }
	}

	public void update() {
	    Session session = null;
	    Transaction transaction = null;
	    Logger logger = LogManager.getLogger(getClass());

	    try {
	        session = SessionFactoryBuilder.getCustomerSessionFactroy().getCurrentSession();
	        transaction = session.beginTransaction();
	        Customer cust = (Customer) session.get(Customer.class, this.custID);
	        cust.setAccountBalance(accountBalance);
	        cust.setCustID(custID);
	        cust.setFirstName(cust.getFirstName());
	        cust.setLastName(cust.getLastName());
	        cust.setAddress(cust.getAddress());
	        cust.setEmail(cust.getEmail());
	        cust.setPassword(cust.getPassword());
	        cust.setPhone(cust.getPhone());

	        session.update(cust);
	        transaction.commit();
	    } catch (Exception e) {
	        // Log and handle exceptions
	        logger.error("Error occurred during update operation", e);
	        if (transaction != null) {
	            transaction.rollback();
	        }
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}
	
	//read all method is in the user class
	
	//I should try to remove from customer first then from user

	// Delete by username
	public void delete() {
	    Session session = null;
	    Transaction transaction = null;
	    Logger logger = LogManager.getLogger(getClass());

	    try {
	        session = SessionFactoryBuilder.getUserSessionFactroy().getCurrentSession();
	        User user = (User) session.get(User.class, this.getUsername());
	        transaction = session.beginTransaction();
	        session.delete(user);
	        transaction.commit();
	    } catch (Exception e) {
	        // Log and handle exceptions
	        logger.error("Error occurred during delete operation", e);
	        if (transaction != null) {
	            transaction.rollback();
	        }
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

}

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
	
	public Customer find(String username) {
		Session session = null;
	    Transaction transaction = null;
	    Logger logger = LogManager.getLogger(getClass());
	    Customer customer = null;
		 try {
			 session = SessionFactoryBuilder.getCustomerSessionFactroy().getCurrentSession();
			 transaction = session.beginTransaction();
			 customer = session.get(Customer.class, username);
			 transaction.commit();
			 session.close();
			 return customer;
		 }catch (HibernateException e) {
		        // Log and handle HibernateException
		        logger.error("Error occurred while searching for customer", e);
		        if (transaction != null && transaction.isActive()) {
		            transaction.rollback();
		        }
		    } catch (Exception e) {
		        // Log and handle other exceptions
		        logger.error("Error occurred while searching for customer", e);
		        if (transaction != null && transaction.isActive()) {
		            transaction.rollback();
		        }
		    } finally {
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }
		 return customer;
	}

	public boolean update(Customer cust) {
	    Session session = null;
	    Transaction transaction = null;
	    Logger logger = LogManager.getLogger(getClass());

	    try {
	        session = SessionFactoryBuilder.getCustomerSessionFactroy().getCurrentSession();
	        transaction = session.beginTransaction();
	        
	        Customer existingCustomer = (Customer) session.get(Customer.class, cust.getUsername());
	        
	        existingCustomer.setAccountBalance(cust.getAccountBalance());
            existingCustomer.setFirstName(cust.getFirstName());
            existingCustomer.setLastName(cust.getLastName());
            existingCustomer.setAddress(cust.getAddress());
            existingCustomer.setEmail(cust.getEmail());
            existingCustomer.setPassword(cust.getPassword());
            existingCustomer.setPhone(cust.getPhone());

	        System.out.println(cust.getFirstName());
	        System.out.println(cust.getLastName());
	        
	        session.update(existingCustomer);
	        transaction.commit();
	        return true;
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
	    return false;
	}
	
	public boolean delete(Object user) {
		Session session = null;
		Transaction transaction = null;
		Logger logger = LogManager.getLogger(getClass());
		try {
			session = SessionFactoryBuilder.getCustomerSessionFactroy().getCurrentSession();
			transaction = session.beginTransaction();
			Customer existingCustomer = session.get(Customer.class, ((Customer) user).getUsername());
			
			session.delete(existingCustomer);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			// Log and handle HibernateException
			logger.error("Error occurred while deleting user", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} catch (Exception e) {
			// Log and handle other exceptions
			logger.error("Error occurred while deleting user", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return false;
	}
	
	//read all method is in the user class
	
	//I should try to remove from customer first then from user

	

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}


}

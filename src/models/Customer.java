package models;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import factories.SessionFactoryBuilder;

@Entity(name="customer")
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
		try {
			session = SessionFactoryBuilder.getCustomerSessionFactroy().getCurrentSession();
			transaction = session.beginTransaction();
			session.save(this);
			transaction.commit();
			session.close();
			return true;
		}catch (HibernateException e) {
            // Handle HibernateException
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                session.close();
                return false;
            }
            e.printStackTrace(); // Log or handle the exception as appropriate
        } catch (Exception e) {
            // Handle other exceptions
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                session.close();
                return false;
            }
            e.printStackTrace(); // Log or handle the exception as appropriate
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
		return false;
	}

	public void update() {
	    Session session = SessionFactoryBuilder.getEmployeeSessionFactroy().getCurrentSession();
	    Transaction transaction = null;

	    try {
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
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        logger.error("Error occurred during update operation", e);
	    } finally {
	        session.close();
	    }
	}

	//read all method is in the user class
	
	//I should try to remove from customer first then from user

	//delete by username
	public void delete() {
	    Session session = SessionFactoryBuilder.getUserSessionFactroy().getCurrentSession();
	    User user = (User) session.get(User.class, this.getUsername());
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        session.delete(user);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        logger.error("Error occurred during delete operation", e);
	    } finally {
	        session.close();
	    }
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

}

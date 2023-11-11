
package models;


import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Customer implements Serializable { //in order for the class to be sent across a network it needs to be serialized 
	private int custID;
	private float accountBalnace;
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	public Customer() { //default constructor
		this.custID = 0;
		this.accountBalnace = 0.0f;
		logger.info("Customer initialized");
	}
	
	public Customer(int custID, float accountBalnace, String username) { //primary constructor
		super();
		this.custID = custID;
		this.accountBalnace = accountBalnace;
		logger.info("Input accepted, Customer initialized");
	}
	
	public Customer(Customer cust) { //copy constructor
		super();
		this.custID = cust.custID;
		this.accountBalnace = cust.accountBalnace;
		logger.info("Customer copied");
	}	
	
	//Accessors and Mutators 
	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
		logger.info("Input accepted, Customer ID set");
	}

	public float getAccountBalnace() {
		return accountBalnace;
	}

	public void setAccountBalnace(float accountBalnace) {
		this.accountBalnace = accountBalnace;
		logger.info("Input accepted, Customer Account balance set");
	}

}

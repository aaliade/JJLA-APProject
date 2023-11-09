package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer {
	private int custID;
	private float accountBalnace;
	private String username;
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	public Customer() { //default constructor
		this.custID = 0;
		this.accountBalnace = 0.0f;
		this.username= "";
		logger.info("Customer initialized");
	}
	
	public Customer(int custID, float accountBalnace, String username) { //primary constructor
		super();
		this.custID = custID;
		this.accountBalnace = accountBalnace;
		this.username = username;
		logger.info("Input accepted, Customer initialized");
	}
	
	public Customer(Customer cust) { //copy constructor
		super();
		this.custID = cust.custID;
		this.accountBalnace = cust.accountBalnace;
		this.username = cust.username;
		logger.info("Customer copied");
	}
	
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

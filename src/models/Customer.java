package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer extends User{
	private int custID;
	private int accountBalance;
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	//Default Constructor
	public Customer(){
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.phone = "";
		this.email = "";
		this.custID = 0;
		this.accountBalance = 0;
		logger.info("Customer initialized");
	}
	
	//Primary Constructor
	public Customer(String username, String password, String firstName, String lastName, String phone, String email, int custID, int accountBalance) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.custID = custID;
		this.accountBalance =accountBalance;
		logger.info("Input accepted, Customer initialized");
	}
	
	//Copy Constructor
	public Customer(Customer cust) {
		this.username = cust.username;
		this.password = cust.password;
		this.firstName = cust.firstName;
		this.lastName = cust.lastName;
		this.phone = cust.phone;
		this.email = cust.email;
		this.custID = cust.custID;
		this.accountBalance = cust.accountBalance;
		logger.info("Customer copied");
	}


	public int getcustID() {
		return custID;
	}

	public void setcustID(int custID) {
		this.custID = custID;
		logger.info("Input accepted, Customer ID set");
	}

	public int getaccountBalance() {
		return accountBalance;
	}

	public void setaccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
		logger.info("Input accepted, Account Balance set");
	}
	
}
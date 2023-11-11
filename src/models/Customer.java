package models;
 
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer extends User implements Serializable { //in order for the class to be sent across a network it needs to be serialized 
	
	private static final long serialVersionUID = 1L;
	private int custID;
	private float accountBalance;
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	public Customer() { //default constructor
		super();
		this.custID = 0;
		this.accountBalance = 0.0f;
		logger.info("Customer initialized");
	}
	
	public Customer(String username, String password, String firstName, String lastName, String phone, String email, int custID, float accountBalance) { //primary constructor
		this.username = username;
		this.password = password;
		this.firstname = firstName;
		this.lastname = lastName;
		this.phone = phone;
		this.email = email;
		this.custID = custID;
		this.accountBalance = accountBalance;
		logger.info("Input accepted, Customer initialized");
	}
	
	public Customer(Customer cust) { //copy constructor
		this.username = cust.username;
		this.password = cust.password;
		this.firstname = cust.firstname;
		this.lastname = cust.lastname;
		this.phone = cust.phone;
		this.email = cust.email;
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
	
	@Override
	public String toString() {
		logger.info("Customer Information returned");
		return("Username: " + username + "Password: " + password + "First Name: " + firstname + "Last Name: " + lastname + "Phone: " + phone + "Email: " + email 
				+ "ID: " + custID +"Account Balance: " + accountBalance);
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

}

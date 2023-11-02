package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class User {
	
	private String username, password, firstName, lastName, phone, email;
	
	private static final Logger logger = LogManager.getLogger(User.class);

	//Default Constructor
	public User(){
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.phone = "";
		this.email = "";
		logger.info("User initialized");
	}
	
	//Primary Constructor
	public User(String username, String password, String firstName, String lastName, String phone, String email) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		logger.info("Input accepted, User initialized");
	} 
	
	//Copy Constructor
	public User(User user){
		this.username = user.username;
		this.password = user.password;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.phone = user.phone;
		this.email = user.email;
		logger.info("User copied");
	}
	
}

package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class User {
	
	protected String username, password, firstName, lastName, phone, email;
	
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getfirstName() {
		return firstName;
	}
	
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getlastName() {
		return lastName;
	}
	
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}

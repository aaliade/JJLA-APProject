package models;
<<<<<<< HEAD


import java.util.ArrayList;
import java.util.List;
import javax.persistence.InheritanceType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

=======
 
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Columns;

import factories.SessionFactoryBuilder;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

	//@Id
	@Column(name="username")
	private String username;
	
<<<<<<< HEAD
	@Transient
	//@Column(name="password")
	private String password;
	//@Column(name="firstName")
	private String firstName;
	//@Column(name="lastName")
	private String lastName;
	//@Column(name="phoneNum")
	private String phone;
	//@Column(name="email")
	private String email;
	//@Column(name="address")
	private String address;
	//@Column(name="userType")
	private String userType;
	
=======
	protected String username, password, firstname, lastname, phone, email;
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
	
	private static final Logger logger = LogManager.getLogger(User.class);

	//Default Constructor
	public User(){
		this.username = "";
		this.password = "";
		this.firstname = "";
		this.lastname = "";
		this.phone = "";
		this.email = "";
		this.address = "";
		this.userType = "";
		logger.info("User initialized");
	}
	
<<<<<<< HEAD
	//Primary Constructor
	public User(String username, String password, String firstName, String lastName, String phone, String email,
			String address, String usertype) {
=======
	public void setUsername(String username) {
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
		this.username = username;
<<<<<<< HEAD
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.userType = usertype;
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
		this.address = user.address;
		this.userType = user.userType;
		logger.info("User copied");
=======
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
	}
<<<<<<< HEAD

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
=======
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public abstract boolean login();

>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
}

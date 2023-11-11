package models;
 
import java.io.Serializable;
import java.sql.Date;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.Session;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Type;

<<<<<<< HEAD
import factories.SessionFactoryBuilder;


@Entity(name="employee")
@Table(name = "employee")

public class Employee implements Serializable{ //Composite-id class must implement Serializable
	//username			
	@Id
	@Column(name = "username")
	private String username;
	@Column(name="empID")
=======
public class Employee extends User implements Serializable{ //in order for the class to be sent across a network it needs to be serialized 
	
	private static final long serialVersionUID = 1L;
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
	private int empID;
	@Column(name = "empRole")
	private String empRole;
	@Column(name = "hireDate")
	@Type(type = "models.CustomDate") // Reference to UserType implementation because we made a custom date class
	private Date hireDate;
	
	private static final Logger logger = LogManager.getLogger(Employee.class);
	
	//Default Constructor
	public Employee(){
		super();
		this.empID = 0;
		this.empRole = "";
		this.hireDate = null;
		logger.info("Employee initialized");
	}
	
<<<<<<< HEAD
	/*//Primary Constructor
	public Employee(int empID, String empRole, Date hireDate, String username, String password, String firstName, String lastName, String phone, String email,
			String address, String usertype) {
		super(username,password,firstName,lastName,phone,email,address,usertype);
=======
	//Primary Constructor
	public Employee(String username, String password, String firstname, String lastname, String phone, String email, int empID, String empRole, Date hireDate) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
		this.empID = empID;
		this.empRole = empRole;
		this.hireDate = hireDate;
		logger.info("Input accepted, Employee initialized");
	}*/
	
	//Primary Constructor 2
	public Employee(String username, int empID, String empRole, Date hireDate) {
			this.username = username;
			this.empID = empID;
			this.empRole = empRole;
			this.hireDate = hireDate;
			logger.info("Input accepted, Employee initialized");
	}
	
	
	
	//Copy Constructor
	public Employee(Employee emp) {
<<<<<<< HEAD
		//super();
=======
		this.username = emp.username;
		this.password = emp.password;
		this.firstname = emp.firstname;
		this.lastname = emp.lastname;
		this.phone = emp.phone;
		this.email = emp.email;
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
		this.empID = emp.empID;
		this.empRole = emp.empRole;
		this.hireDate = emp.hireDate;
		logger.info("Employee copied");
	}

	public int getEmpID() {
		logger.info("Employee ID returned");
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
		logger.info("Input accepted, Employee ID set");
	}

	public String getEmpRole() {
		logger.info("Employee Role returned");
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
		logger.info("Input accepted, Employee Role set");
	}

	public Date getHireDate() {
		logger.info("Employee Hire Date returned");
		return hireDate;
	}

	public void setHireDate(Date date) {
		this.hireDate = date;
		logger.info("Input accepted, Employee Hire Date set");
	}
	
	@Override
	public String toString() {
		logger.info("Employee Information returned");
		return("Username: " + username + "Password: " + password + "First Name: " + firstname + "Last Name: " + lastname + "Phone: " + phone + "Email: " + email 
				+ "ID: " + empID + "Role: " + empRole + "Hire Date: " + hireDate);
	}
	
	
	public String getDateToString() {
		return hireDate.toString();
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void createEmp() {
		Session session = SessionFactoryBuilder.getEmployeeSessionFactroy().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
	}
	
	/*
	public void update() {
		Session session = SessionFactoryBuilder.getEmployeeSessionFactroy().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		User user = (User) session.get(User.class, this.username);
		user.setAddress(address);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUsername(username);
		user.setUserType(userType);
		session.update(user);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> readAll(){
		List<User> userList = new ArrayList<>();
		Session session = SessionFactoryBuilder.getUserSessionFactroy().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		userList = (List<User>) session.createQuery("From user").getResultList();
		transaction.commit();
		session.close();
		return userList;
	}
	
	
	public void delete() {
		Session session = SessionFactoryBuilder.getUserSessionFactroy().getCurrentSession();
		User user = (User) session.get(User.class, this.username);
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();
	}*/
}

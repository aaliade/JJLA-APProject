package models;

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

import factories.SessionFactoryBuilder;


@Entity(name="employee")
@Table(name = "employee")

public class Employee implements Serializable{ //Composite-id class must implement Serializable
	//username			
	@Id
	@Column(name = "username")
	private String username;
	@Column(name="empID")
	private int empID;
	@Column(name = "empRole")
	private String empRole;
	@Column(name = "hireDate")
	@Type(type = "models.CustomDate") // Reference to UserType implementation because we made a custom date class
	private Date hireDate;
	
	private static final Logger logger = LogManager.getLogger(Employee.class);
	
	//Default Constructor
	public Employee(){
		this.empID = 0;
		this.empRole = "";
		this.hireDate = new Date(1,1,1111);
		logger.info("Employee initialized");
	}
	
	/*//Primary Constructor
	public Employee(int empID, String empRole, Date hireDate, String username, String password, String firstName, String lastName, String phone, String email,
			String address, String usertype) {
		super(username,password,firstName,lastName,phone,email,address,usertype);
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
		//super();
		this.empID = emp.empID;
		this.empRole = emp.empRole;
		this.hireDate = emp.hireDate;
		logger.info("Employee copied");
	}


	public int getEmpID() {
		return empID;
	}


	public void setEmpID(int empID) {
		this.empID = empID;
		logger.info("Input accepted, Employee ID set");
	}


	public String getEmpRole() {
		return empRole;
	}


	public void setEmpRole(String empRole) {
		this.empRole = empRole;
		logger.info("Input accepted, Employee Role set");
	}


	public Date getHireDate() {
		return hireDate;
	}
	
	
	public String getDateToString() {
		return hireDate.toString();
	}


	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
		logger.info("Input accepted, Employee Hire Date set");
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

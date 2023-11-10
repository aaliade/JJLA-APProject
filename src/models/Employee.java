package models;
 
import java.io.Serializable;
import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee extends User implements Serializable{ //in order for the class to be sent across a network it needs to be serialized 
	
	private static final long serialVersionUID = 1L;
	private int empID;
	private String empRole;
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
	
	//Primary Constructor
	public Employee(String username, String password, String firstname, String lastname, String phone, String email, int empID, String empRole, Date hireDate) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.empID = empID;
		this.empRole = empRole;
		this.hireDate = hireDate;
		logger.info("Input accepted, Employee initialized");
	}
	
	//Copy Constructor
	public Employee(Employee emp) {
		this.username = emp.username;
		this.password = emp.password;
		this.firstname = emp.firstname;
		this.lastname = emp.lastname;
		this.phone = emp.phone;
		this.email = emp.email;
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

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}
}

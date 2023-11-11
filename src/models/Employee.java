package models;

import java.io.Serializable;
import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee extends User implements Serializable{ //in order for the class to be sent across a network it needs to be serialized 
	private int empID;
	private String empRole;
	private Date hireDate;
	
	private static final Logger logger = LogManager.getLogger(Employee.class);
	
	//Default Constructor
	public Employee(){
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.phone = "";
		this.email = "";
		this.empID = 0;
		this.empRole = "";
		this.hireDate = null;
		logger.info("Employee initialized");
	}
	
	//Primary Constructor
	public Employee(String username, String password, String firstName, String lastName, String phone, String email, int empID, String empRole, Date hireDate) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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
		this.firstName = emp.firstName;
		this.lastName = emp.lastName;
		this.phone = emp.phone;
		this.email = emp.email;
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

	public void setHireDate(Date date) {
		this.hireDate = date;
		logger.info("Input accepted, Employee Hire Date set");
	}
}

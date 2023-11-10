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
		this.empID = 0;
		this.empRole = "";
		this.hireDate = new Date(1,1,1111);
		logger.info("Employee initialized");
	}
	
	//Primary Constructor
	public Employee(int empID, String empRole, Date hireDate) {
		super();
		this.empID = empID;
		this.empRole = empRole;
		this.hireDate = hireDate;
		logger.info("Input accepted, Employee initialized");
	}
	
	//Copy Constructor
	public Employee(Employee emp) {
		super();
		this.empID = emp.empID;
		this.empRole = emp.empRole;
		this.hireDate = emp.hireDate;
		logger.info("Employee copied");
	}
	
	//Accessors and Mutators 
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

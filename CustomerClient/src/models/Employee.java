package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity(name = "employee")
@PrimaryKeyJoinColumn(name = "username")
@Table(name = "employee")
public class Employee extends User implements Serializable { // in order for the class to be sent across a network it
																// needs to be serialized
	private static final long serialVersionUID = 1L;
	// no need to use @Id as the joined table is seen as a reference identifier
	@Column(name = "empID")
	private int empID;
	@Column(name = "empRole")
	private String empRole;
	@Temporal(TemporalType.DATE) // set the type to match the data type in mysql
	@Column(name = "hireDate")
	private Date hireDate;

	private static final Logger logger = LogManager.getLogger(Employee.class);

	// Default Constructor
	public Employee() {
		super();
		this.empID = 0;
		this.empRole = "";
		this.hireDate = null;
		logger.info("Employee initialized");
	}

	// Primary Constructor
	public Employee(int empID, String empRole, Date hireDate, String username, String password, String firstName,
			String lastName, String phone, String email, String address, String usertype) {
		super(username, password, firstName, lastName, phone, email, address, usertype);
		this.empID = empID;
		this.empRole = empRole;
		this.hireDate = hireDate;
		logger.info("Input accepted, Employee initialized");
	}

	// Primary Constructor 2
	public Employee(int empID, String empRole, Date date) {
		this.empID = empID;
		this.empRole = empRole;
		this.hireDate = date;
		logger.info("Input accepted, Employee initialized");
	}

	// Copy Constructor
	public Employee(Employee emp) {
		super(emp.getUsername(), emp.getPassword(), emp.getFirstName(), emp.getLastName(), emp.getPhone(),
				emp.getEmail(), emp.getAddress(), emp.getUserType());
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
	}

	public Date getHireDate() {
		logger.info("Employee Hire Date returned");
		return hireDate;
	}

	public void setHireDate(Date date) {
		this.hireDate = date;
		logger.info("Input accepted, Employee Hire Date set");
	}

	public String getDateToString() {
		return hireDate.toString();
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empRole=" + empRole + ", hireDate=" + hireDate + "]";
	}

	@Override
	public boolean login() {
		return false;
	}
}

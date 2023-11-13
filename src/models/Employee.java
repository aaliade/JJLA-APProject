
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Type;

import factories.SessionFactoryBuilder;

@Entity(name = "employee")
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

	public boolean create() {
		Session session = null;
		Transaction transaction = null;
		Logger logger = LogManager.getLogger(getClass());

		try {
			session = SessionFactoryBuilder.getEmployeeSessionFactroy().getCurrentSession();
			transaction = session.beginTransaction();
			session.save(this);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			// Log and handle HibernateException
			logger.error("Error occurred while creating employee", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			return false;
		} catch (Exception e) {
			// Log and handle other exceptions
			logger.error("Error occurred while creating employee", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void update() {
		Session session = null;
		Transaction transaction = null;
		Logger logger = LogManager.getLogger(getClass());

		try {
			session = SessionFactoryBuilder.getEmployeeSessionFactroy().getCurrentSession();
			transaction = session.beginTransaction();
			Employee emp = (Employee) session.get(Employee.class, this.empID);
			emp.setEmpID(empID);
			emp.setUsername(emp.getUsername());
			emp.setEmpRole(empRole);
			emp.setHireDate(hireDate);

			emp.setFirstName(emp.getFirstName());
			emp.setLastName(emp.getLastName());
			emp.setAddress(emp.getAddress());
			emp.setEmail(emp.getEmail());
			emp.setPassword(emp.getPassword());
			emp.setPhone(emp.getPhone());

			session.update(emp);
			transaction.commit();
		} catch (HibernateException e) {
			// Log and handle HibernateException
			logger.error("Error occurred while updating employee", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} catch (Exception e) {
			// Log and handle other exceptions
			logger.error("Error occurred while updating employee", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void delete() {
		Session session = null;
		Transaction transaction = null;
		Logger logger = LogManager.getLogger(getClass());

		try {
			session = SessionFactoryBuilder.getUserSessionFactroy().getCurrentSession();
			User user = (User) session.get(User.class, this.getUsername());
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		} catch (HibernateException e) {
			// Log and handle HibernateException
			logger.error("Error occurred while deleting user", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} catch (Exception e) {
			// Log and handle other exceptions
			logger.error("Error occurred while deleting user", e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

}

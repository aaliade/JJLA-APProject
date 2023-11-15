package controller;

import java.util.Date;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Customer;
import models.Employee;
import models.User;
import networking.CustomerClient;
import networking.EmployeeClient;
import view.CustomerDashboard;
import view.EmployeeDashboard;
import view.LoginView;
import view.SignUpView;

public class GuiController {
	
	//Views
	private LoginView loginView;
	private SignUpView signupView;
	private CustomerDashboard customerDashboard;
	private EmployeeDashboard employeeDashBoard;
	
	//Clients
	private CustomerClient customerClient;
	private EmployeeClient employeeClient;
	
	//Models
	private Employee employee;
	private Customer customer;
	
	//Logger
	private static final Logger logger = LogManager.getLogger(GuiController.class);
	
	public GuiController() {
		this.loginView = new LoginView(this);
		logger.info("Controller initialized, Login page displayed");
	}
	
	public void signUpUser(JFrame frame) {
		clearFrame(frame);
		this.signupView = new SignUpView(this,frame);
		logger.info("Sign up page displayed");
	}
	
	public void loginCustomer(JFrame frame) {
		clearFrame(frame);
		this.customerDashboard = new CustomerDashboard(this,frame);
		logger.info("Customer logged in, Dashboard displayed");
	}
	
	public void loginEmployee(JFrame frame) {
		clearFrame(frame);
		this.employeeDashBoard = new EmployeeDashboard(this,frame);
		logger.info("Employee logged in, Dashboard displayed");
	}
	
	public void goBackToLoginPage(JFrame frame) {
		frame.dispose(); //terminates frame
		this.loginView = new LoginView(this);
		logger.info("Returned to Login page");
	}
	
	public void clearFrame(JFrame frame) {
		frame.getContentPane().removeAll(); //Clear Frame
		frame.repaint();
		logger.info("Page cleared");
	}
	
	public boolean FindEmployee(String username) {
		employeeClient = new EmployeeClient();
		employeeClient.sendAction("Find Employee");
		employeeClient.findEmployee(username);
		employeeClient.receiveResponse();
		employee = employeeClient.getEmployee();
		if(employee!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkPassword(String password, String user) {
		if(user.equals("Employee")) {
			if(employee.getPassword().equals(password)) {
				return true;
			}else {
				return false;
			}
		}else if(user.equals("Customer")){
			if(customer.getPassword().equals(password)) {
				return true;
			}else {
				return false;
			}
		}
		return false; //will never reach here	
	}
	
	public boolean FindCustomer(String username) {
		customerClient = new CustomerClient();
		customerClient.sendAction("Find Customer");
		customerClient.findCustomer(username);
		customerClient.receiveResponse();
		customer = customerClient.getCustomer();
		if(customer!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean CreateCustomerObject(String username, String password, String firstName, String lastName, String phone,String address, String email,String usertype, int custID, float accountBalance){
		customerClient = new CustomerClient();
		Customer customer = new Customer(username, password,  firstName,  lastName,  phone, address,  email, usertype,  custID,  accountBalance);
		
		customerClient.sendAction("Add Customer");
		System.out.println("Action sent");
		customerClient.sendCustomer(customer);
		System.out.println("Object sent");
		customerClient.receiveResponse();
		System.out.println("Response recieved");
		customerClient.closeConnection();
		return true;
	}
	
	public boolean CreateEmployeeObject(int empID, String empRole, Date hireDate, String username, String password, String firstName, String lastName, String phone, String email,
			String address, String usertype){
		employeeClient = new EmployeeClient();
		Employee employee = new Employee( empID,  empRole,  hireDate,  username,  password,  firstName,  lastName,  phone,  email, address,  usertype);
		
		employeeClient.sendAction("Add Employee");
		System.out.println("Action sent");
		employeeClient.sendEmployee(employee);
		System.out.println("Object sent");
		employeeClient.receiveResponse();
		System.out.println("Response recieved");
		employeeClient.closeConnection();
		return true;
	}
	
	public static void main(String args[]) {
		new GuiController();
	}
	
}

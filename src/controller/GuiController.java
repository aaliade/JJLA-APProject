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
import view.LoginView;
import view.SignUpView;

public class GuiController {
	
	//Views
	private LoginView loginView;
	private SignUpView signupView;
	private CustomerDashboard customerDashboard;
	
	//Clients
	private CustomerClient customerClient;
	private EmployeeClient employeeClient;
	
	//Models
	private User user;
//	private Employee employee;
//	private Customer customer;
	
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
	
	public void loginUser(JFrame frame) {
		clearFrame(frame);
		this.customerDashboard = new CustomerDashboard(this,frame);
		logger.info("Customer logged in, Dashboard displayed");
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
	
	public void CreateCustomerObject(String username, String password, String firstName, String lastName, String phone,String address, String email,String usertype, int custID, float accountBalance){
		customerClient = new CustomerClient();
		Customer customer = new Customer(username, password,  firstName,  lastName,  phone, address,  email, usertype,  custID,  accountBalance);
		
		customerClient.sendAction("Add Customer");
		System.out.println("Action sent");
		customerClient.sendCustomer(customer);
		System.out.println("Object sent");
		customerClient.receiveResponse();
		System.out.println("Response recieved");
		customerClient.closeConnection();
	}
	
	public void CreateEmployeeObject(int empID, String empRole, Date hireDate, String username, String password, String firstName, String lastName, String phone, String email,
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
	}
	
	public static void main(String args[]) {
		new GuiController();
	}
	
}

package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Customer;
import models.Employee;
import models.Message;
import views.DashBoard;
import views.Login;
import views.SignUp;

public class CustomerClientController {

	private static final Logger logger = LogManager.getLogger(CustomerClientController.class);
	
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;
	
	//Models
	private Customer customer = null;
		
	//Views
	private Login loginView;
	private SignUp signupView;
	private DashBoard DashboardView;
	
	
	public CustomerClientController() {
		this.loginView = new Login(this);
		this.createConnection();
		this.configureStreams();
		logger.info("Customer Client initialized");
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket(InetAddress.getLocalHost(), 8888);
			System.out.println("Employee Client established connection");
			logger.info("Employee Client established connection");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Employee Client failed to establish connection: " + ex.getMessage());
		}
	}
	
	private void configureStreams() {
		try {
			this.objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			this.objIs = new ObjectInputStream(connectionSocket.getInputStream());
			
			logger.info("Employee Client streams initialized");
		} catch(IOException ex) {
			ex.printStackTrace();
			logger.error("Employee Client failed to initialise streams: " + ex.getMessage());
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
			logger.info("Employee Client connection closed");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Employee Client failed to close connection: " + ex.getMessage());
		}
	}
	
	public void signUpUser(JFrame frame) {
		clearFrame(frame);
		this.signupView = new SignUp(this,frame);
		logger.info("Sign up page displayed");
	}
	
	public void loginEmployee(JFrame frame) {
		clearFrame(frame);
		this.DashboardView = new DashBoard(this,frame);
		logger.info("Customer logged in, Dashboard displayed");
	}
	
	public void goBackToLoginPage(JFrame frame) {
		frame.dispose(); //terminates frame
		this.loginView = new Login(this);
		logger.info("Returned to Login page");
	}
	
	public void clearFrame(JFrame frame) {
		frame.getContentPane().removeAll(); //Clear Frame
		frame.repaint();
		logger.info("Page cleared");
	}
	
	public boolean checkPassword(String password) {
		if(customer.getPassword().equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean SearchCustomer(String username) {
		customer = null;
		
		sendAction("Find Customer");
		findCustomer(username);
		receiveResponse();
		
		if(customer!=null) {
			System.out.println(customer.getUsername());
			System.out.println(customer.getFirstName());
			System.out.println(customer.getLastName());
			System.out.println(customer.getAddress());
			System.out.println(customer.getCustID());
			System.out.println(customer.getEmail());
			return true;
		}else {
			return false;
		}
	}
	
	
	public void findCustomer(String username) {
		try {
			objOs.writeObject(username);
			logger.info("Username sent to server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Error Sending username " + ex.getMessage());
		}
	}
	
	public void sendAction(String action) {
		this.action = action;
		try {
			objOs.writeObject(action);
			logger.info("Action sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Action failed: " + ex.getMessage());
		}
	}
	
	public void sendCustomer(Customer customer) {
		try {
			objOs.writeObject(customer);
			logger.info("Customer sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Customer failed to be sent: " + ex.getMessage());
		}
	}
	
	public void sendMessage(Message message) {
		try {
			objOs.writeObject(message);
			logger.info("Customer Message sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Customer Message failed to be sent: " + ex.getMessage());
		}
	}
	
	public void receiveResponse() {
		try {
			if (action.equalsIgnoreCase("Add Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Customer Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Customer Record added to Database");
				}
			}
			if (action.equalsIgnoreCase("Send Message")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Message added to Database");
				}
			}if (action.equalsIgnoreCase("Find Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					this.customer = (Customer) objIs.readObject();
				}else {
					logger.info("Customer not found from database");
				}
			}
		} catch (ClassCastException ex) {
			ex.printStackTrace();
			logger.error("Class Cast exception: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			logger.error("Class Not Found exception: " + ex.getMessage());
		} catch (IOException ex) {
			logger.error("Failed to recieve response: " + ex.getMessage());
		}
	}
	
	public boolean CreateCustomerObject(String username, String password, String firstName, String lastName, String phone,String address, String email,String usertype, int custID, float accountBalance){
		Customer customer = new Customer(username, password,  firstName,  lastName,  phone, address,  email, usertype,  custID,  accountBalance);
		sendAction("Add Customer");
		System.out.println("Action sent");
		sendCustomer(customer);
		System.out.println("Object sent");
		receiveResponse();
		System.out.println("Response recieved");
		return true;
	}
	
	public static void main(String[] args) {
		new CustomerClientController();
	}
	
}

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
import models.Message;
import models.Payment;
import views.DashBoard;
import views.Login;
import views.SignUp;

public class CustomerClientController {

	private Socket connectionSocket;
	private static ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;
	
	private static final Logger logger = LogManager.getLogger(CustomerClientController.class);
	
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
	
	public void signUpUser(JFrame frame) {
		clearFrame(frame);
		this.signupView = new SignUp(this,frame);
		logger.info("Sign up page displayed");
	}
	
	public void loginCustomer(JFrame frame) {
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
	
	public boolean CreateCustomerObject(String username, String password, String firstName, String lastName, String phone,String address, String email,String usertype, int custID, float accountBalance){
		Customer addcustomer = new Customer(username, password,  firstName,  lastName,  phone, address,  email, usertype,  custID,  accountBalance);
		
		sendAction("Add Customer");
		System.out.println("Action sent");
		sendCustomer(addcustomer);
		System.out.println("Object sent");
		receiveResponse();
		System.out.println("Response recieved");
		closeConnection();
		return true;
	}
	
	public boolean checkPassword(String password, String user) {
			if(customer.getPassword().equals(password)) {
				return true;
			}else {
				return false;
			}
	}
	
	public boolean FindCustomer(String username) {
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
	
	private void createConnection() {
		try {
			connectionSocket = new Socket(InetAddress.getLocalHost(), 8888);
			logger.info("Customer Client established connection");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Customer Client failed to establish connection: " + ex.getMessage());
		}
	}
	
	private void configureStreams() {
		try {
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			logger.info("Customer Client streams initialized");
		} catch(IOException ex) {
			ex.printStackTrace();
			logger.error("Customer Client failed to initialise streams: " + ex.getMessage());
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
			logger.info("Customer Client connection closed");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Customer Client failed to close connection: " + ex.getMessage());
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
	
	public void sendPayment(Payment payment) {
		try {
			objOs.writeObject(payment);
			logger.info("Payment sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Payment failed: " + ex.getMessage());
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
			}
			if (action.equalsIgnoreCase("Add Payment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Payment Processed Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Payment added to Database");
				}
			}if (action.equalsIgnoreCase("Find Customer")) {
				this.customer = null; //Removes the previous stored object
				this.customer = (Customer) objIs.readObject(); //Stores new object
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
	
	public static void main(String args[]) {
		new CustomerClientController();
	}
}

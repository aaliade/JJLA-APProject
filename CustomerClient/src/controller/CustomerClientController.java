package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Customer;
import models.Employee;
import models.Equipment;
import models.Message;
import views.DashBoard;
import views.Login;
import views.SignUp;

public class CustomerClientController {

	public static final Logger logger = LogManager.getLogger(CustomerClientController.class);
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;

	// Models
	private Customer customer = null;
	private Equipment[] equipmentList;

	// Views
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
		} catch (IOException ex) {
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
		this.signupView = new SignUp(this, frame);
		logger.info("Sign up page displayed");
	}

	public void loginEmployee(JFrame frame) {
		clearFrame(frame);
		this.DashboardView = new DashBoard(this, frame);
		logger.info("Customer logged in, Dashboard displayed");
	}

	public void goBackToLoginPage(JFrame frame) {
		frame.dispose(); // terminates frame
		this.loginView = new Login(this);
		logger.info("Returned to Login page");
	}

	public void clearFrame(JFrame frame) {
		frame.getContentPane().removeAll(); // Clear Frame
		frame.repaint();
		logger.info("Page cleared");
	}

	public boolean checkPassword(String password) {
		if (customer.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean SearchCustomer(String username) {
		customer = null;

		sendAction("Find Customer");
		findCustomer(username);
		receiveResponse();

		if (customer != null) {
			System.out.println(customer.getUsername());
			System.out.println(customer.getFirstName());
			System.out.println(customer.getLastName());
			System.out.println(customer.getAddress());
			System.out.println(customer.getCustID());
			System.out.println(customer.getEmail());
			return true;
		} else {
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
					JOptionPane.showMessageDialog(null, "Customer Added Successfully", "Add Record Status",
							JOptionPane.INFORMATION_MESSAGE);
					logger.info("Customer Record added to Database");
				}
			}
			if (action.equalsIgnoreCase("Send Message")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully", "Add Record Status",
							JOptionPane.INFORMATION_MESSAGE);
					logger.info("Message added to Database");
				}
			}
			if (action.equalsIgnoreCase("Find Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					this.customer = (Customer) objIs.readObject();
				} else {
					logger.info("Customer not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Equipment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Equipment were successfully found in database",
							"Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					equipmentList = (Equipment[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Equipment was found in database, Will Update Shortly",
							"Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Equipment not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Lighting")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Lighting equipment were successfully found in database",
							"Lighting Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					equipmentList = (Equipment[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Lighting Equipment was found in database, Will Update Shortly",
							"Lighting Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Lighting Equipment not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Sound")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Sound equipment were successfully found in database",
							"Sound Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					equipmentList = (Equipment[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Sound Equipment was found in database, Will Update Shortly",
							"Sound Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Sound Equipment not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Power")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Power equipment were successfully found in database",
							"Power Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					equipmentList = (Equipment[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Power Equipment was found in database, Will Update Shortly",
							"Power Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Power Equipment not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Staging")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Staging equipment were successfully found in database",
							"Staging Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					equipmentList = (Equipment[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Staging Equipment was found in database, Will Update Shortly",
							"Staging Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Staging Equipment not found from database");
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

	public void getEquipmentsFromDatabase() {
		sendAction("Get Equipment");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	
	public void getLightingEquipmentsFromDatabase() {
		sendAction("Get Lighting");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	
	public void getSoundEquipmentsFromDatabase() {
		sendAction("Get Sound");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	
	public void getPowerEquipmentsFromDatabase() {
		sendAction("Get Power");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	
	public void getStagingEquipmentsFromDatabase() {
		sendAction("Get Staging");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}

	public boolean CreateCustomerObject(String username, String password, String firstName, String lastName,
			String phone, String address, String email, String usertype, int custID, float accountBalance) {
		Customer customer = new Customer(username, password, firstName, lastName, phone, address, email, usertype,
				custID, accountBalance);
		sendAction("Add Customer");
		System.out.println("Action sent");
		sendCustomer(customer);
		System.out.println("Object sent");
		receiveResponse();
		System.out.println("Response recieved");
		return true;
	}

	public int getCurrentEquipmentCount() {
		return equipmentList.length;
	}

	public Vector<Object> updateEquipmentViewPanel(int index) {
		Vector<Object> row = new Vector<>();
		System.out.println("Index: " + index);
		row.add(equipmentList[index].getequipID());

		row.add(equipmentList[index].getequipName());
		row.add(equipmentList[index].getCategory());
		row.add(equipmentList[index].getrentalRate());
		row.add(equipmentList[index].getdescription());
		row.add(equipmentList[index].getstatus());
		return row;
	}

	public static void main(String[] args) {
		logger.info("Customer Client Test Info message");
		new CustomerClientController();
	}

}

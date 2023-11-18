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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Customer;
import models.Employee;
import models.Equipment;
import models.Event;
import models.Invoice;
import models.Message;
import models.Receipt;
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
	private Invoice[] invoiceList;
	private Message[] messageList;
	private Event[] eventList;
	private Receipt[] receiptList;

	// Views
	private Login loginView;
	private SignUp signupView;
	private DashBoard DashboardView;
	
	private boolean Deleted;

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
	
	public boolean GetDeleteStatus() {
		return this.Deleted;
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
			if (action.equalsIgnoreCase("Get Invoice")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Invoice was successfully found in database",
							"Invoice Search", JOptionPane.INFORMATION_MESSAGE);
					invoiceList = (Invoice[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Invoice was found in database, Will Update Shortly",
							"Invoice Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Invoice not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Event")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Event was successfully found in database",
							"Event Search", JOptionPane.INFORMATION_MESSAGE);
					eventList = (Event[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No event was found in database, Will Update Shortly",
							"Event Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Event not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Message")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Message was successfully found in database",
							"Message Search", JOptionPane.INFORMATION_MESSAGE);
					messageList = (Message[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Message was found in database, Will Update Shortly",
							"Message Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Message not found from database");
				}
			}
			if (action.equalsIgnoreCase("Get Receipt")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Receipt was successfully found in database",
							"Receipt Search", JOptionPane.INFORMATION_MESSAGE);
					receiptList = (Receipt[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Receipt was found in database, Will Update Shortly",
							"Receipt Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Receipt not found from database");
				}
			}if (action.equalsIgnoreCase("Get Equipment By Category")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Equipment were successfully found of this category in database",
							"Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					equipmentList = null;
					equipmentList = (Equipment[]) objIs.readObject();
				} else {
					JOptionPane.showMessageDialog(null, "No Equipment was found in database by this category, Will Update Shortly",
							"Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Customer not found from database");
				}
			}if (action.equalsIgnoreCase("Update Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Customer successfully Updated",
							"Equipment Search", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Failed To Update User",
							"Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Customer not found from database");
				}
			}if (action.equalsIgnoreCase("Delete Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Customer successfully Deleted",
							"Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					this.Deleted = true;
				} else {
					JOptionPane.showMessageDialog(null, "Failed To Delete User",
							"Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Customer not found from database");
					this.Deleted = false;
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
	
	public void getEquipmentsFromDatabaseByCategory(String category) {
		sendAction("Get Equipment By Category");
		System.out.println("Action sent");
		sendAction(category);
		System.out.println("Category sent");
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
	
	public void getInvoiceFromDatabase() {
		sendAction("Get Invoice");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	
	public void getEventFromDatabase() {
		sendAction("Get Event");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	
	public void getMessageFromDatabase() {
		sendAction("Get Message");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	
	public void getReceiptFromDatabase() {
		sendAction("Get Receipt");
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
	}

	public void UpdateCustomerObject(String addressField, String emailField, String  firstNameField, 
			String lastNameField, String passwordField, String phoneField, String usernameField) {
		if(!addressField.isEmpty()) {
			customer.setAddress(addressField);
		}if(!emailField.isEmpty()) {
			customer.setEmail(emailField);
		}if(!firstNameField.isEmpty()) {
			customer.setFirstName(firstNameField);
		}if(!lastNameField.isEmpty()) {
			customer.setLastName(lastNameField);
		}if(!passwordField.isEmpty()) {
			customer.setPassword(passwordField);
		}if(!phoneField.isEmpty()) {
			customer.setPhone(phoneField);
		}if(!usernameField.isEmpty()) {
			customer.setUsername(usernameField);
		}
		
		sendAction("Update Customer");
		System.out.println("Action sent");
		sendCustomer(customer);
		System.out.println("Object sent");
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
	public void DeleteUser() {
		sendAction("Delete Customer");
		System.out.println("Action sent");
		sendCustomer(customer);
		System.out.println("Object sent");
		receiveResponse();
		System.out.println("Response recieved");
	}
	public void setCustomerInfo(JLabel custIDLabel, JLabel accountBalanceLabel, JLabel addressLabel,
			JLabel emailLabel,JLabel firstNameLabel, JLabel lastNameLabel, JLabel phoneLabel,
			JLabel userTypeLabel, JLabel usernameLabel) {
		custIDLabel.setText("Cutomer ID: " + Integer.toString(customer.getCustID()));
		accountBalanceLabel.setText ("Account Balance: " + Float.toString(customer.getAccountBalance()));
		addressLabel.setText("Address: " + customer.getAddress());
		emailLabel.setText("Email: " +customer.getEmail());
		firstNameLabel.setText("First Name: " + customer.getFirstName());
		lastNameLabel.setText("Last Name: " +customer.getLastName());
		phoneLabel.setText("Phone: " + customer.getPhone());
		userTypeLabel.setText("User Type: " +customer.getUserType());
		usernameLabel.setText("Username: " + customer.getUsername());
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

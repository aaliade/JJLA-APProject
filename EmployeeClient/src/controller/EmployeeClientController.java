package controller;

import java.io.IOException;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Employee;
import models.Equipment;
import models.Event;

import views.DashBoard;
import views.Login;
import views.SignUp;

public class EmployeeClientController {
	private static final Logger logger = LogManager.getLogger(EmployeeClientController.class);
	
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;

	//Models
	private Employee employee = null;
	
	//Views
	private Login loginView;
	private SignUp signupView;
	private DashBoard DashboardView;
	
	public EmployeeClientController() {
		this.loginView = new Login(this);
		this.createConnection();
		this.configureStreams();
		logger.info("Employee Client initialized");
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
	
	public boolean CreateEmployeeObject(int empID, String empRole, int day,int month, int year, String username, String password, String firstName, String lastName, String phone, String email,
			String address, String usertype){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = year + "-" + month + "-" + day; // Format: yyyy-MM-dd
		Date hireDate = null;
		try {
			hireDate = dateFormat.parse(dateString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Employee addemployee = new Employee( empID,  empRole,  hireDate,  username,  password,  firstName,  lastName,  phone,  email, address,  usertype);
		
		sendAction("Add Employee");
		System.out.println("Action sent");
		sendEmployee(addemployee);
		System.out.println("Object sent");
		receiveResponse();
		System.out.println("Response recieved");
		return true;
	}
	
	public boolean SearchEmployee(String username) {
		employee = null;
	
		sendAction("Find Employee");
		findEmployee(username);
		receiveResponse();
		
		if(employee!=null) {
			System.out.println(employee.getUsername());
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
			System.out.println(employee.getAddress());
			System.out.println(employee.getEmpID());
			System.out.println(employee.getEmail());
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkPassword(String password) {
		if(employee.getPassword().equals(password)) {
			return true;
		}else {
			return false;
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
	
	public void sendEmployee(Employee employee) {
		try {
			objOs.writeObject(employee);
			logger.info("Employee sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Employee failed to be sent: " + ex.getMessage());
		}
	}
	
	/*public void sendMessage(Message message) {
		try {
			objOs.writeObject(message);
			logger.info("Employee Message sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Employee Message failed to be sent: " + ex.getMessage());
		}
	}*/ 
	
	public void sendEquipment(Equipment equipment) {
		try {
			objOs.writeObject(equipment);
			logger.info("Equipment sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Equipment failed to be sent: " + ex.getMessage());
		}
	}
	
	public void sendEvent(Event event) {
		try {
			objOs.writeObject(event);
			logger.info("Event sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Event failed to be sent: " + ex.getMessage());
		}
	}
	
	public void findEmployee(String username) {
		try {
			objOs.writeObject(username);
			logger.info("Username sent to server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Error Sending username " + ex.getMessage());
		}
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void receiveResponse() {
		try {
			if (action.equalsIgnoreCase("Add Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Employee Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Employee Record added to Database");
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
			if (action.equalsIgnoreCase("Add Equipment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Equipment Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Equipment added to Database");
				}
			}
			if (action.equalsIgnoreCase("Add Event")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Event Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Event added to Database");
				}
			}
			if (action.equalsIgnoreCase("Find Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					logger.info("Employee found from database");
					employee = (Employee) objIs.readObject();
				}else {
					logger.info("Employee not found from database");
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
	
	public static void main(String[] args) {
		logger.info("Employee Client Test Info message");
		new EmployeeClientController();
	}
}

package controller;

import java.io.IOException;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Employee;
import models.Equipment;
import models.Event;
import models.Message;
import views.DashBoard;
import views.Login;
import views.SignUp;

public class EmployeeClientController {
	private static final Logger logger = LogManager.getLogger(EmployeeClientController.class);
	
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;
	
	private Message[] messageList;

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
	
	public void getMessagesFromDatabase() {
		sendAction("Get Messages");
		sendAction(employee.getUsername());
		this.action = "Get Messages";
		System.out.println("Action sent");
		receiveResponse();
		System.out.println("Response recieved");
		
	}
	
	public void CreateEventObject(String eventID, String eventName, int day, int month, int year, String eventLocation) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = year + "-" + month + "-" + day; // Format: yyyy-MM-dd
		Date eventDate = null;
		try {
			eventDate = dateFormat.parse(dateString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		Event newEvent = new Event( eventID,  eventName,  eventDate,  eventLocation);
		sendAction("Add Event");
		System.out.println("Action sent");
		sendEvent(newEvent);
		System.out.println("Object sent");
		receiveResponse();
		System.out.println("Response recieved");
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
	
	public void sendMessage(String messageID, String content, String reciever) {
		Message message = new Message(messageID, employee.getUsername(), reciever, content, new Date());
		sendAction("Send Message");
		try { 
			objOs.writeObject(message);
			sendAction(employee.getUserType());
			this.action = "Send Message";
			logger.info("Customer Message sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Customer Message failed to be sent: " + ex.getMessage());
		}
	}
	
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
	
	public int getCurrentMessageCount() {
		return messageList.length;
	}
	
	public Vector<Object> updateMessageViewPanel(int index) {
		Vector<Object> row = new Vector<>();
		System.out.println("Index: " + index);
		row.add(messageList[index].getSenderID());
		row.add(messageList[index].getContent());
		return row;
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
			}if (action.equalsIgnoreCase("Get Messages")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Messges were successfully found in database",
							"Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Employee found from database");
					this.messageList = (Message[]) objIs.readObject();
				}else {
					JOptionPane.showMessageDialog(null, "No Message was found in database, Will Update Shortly",
							"Equipment Search", JOptionPane.ERROR_MESSAGE);
					logger.info("Employee not found from database");
				}
			}if (action.equalsIgnoreCase("Send Message")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Messges Successfully Send",
							"Equipment Search", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Employee found from database");
					this.messageList = (Message[]) objIs.readObject();
				}else {
					JOptionPane.showMessageDialog(null, "Message Failed to Send",
							"Equipment Search", JOptionPane.ERROR_MESSAGE);
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

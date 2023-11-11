package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
 
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Employee;
import models.Equipment;
import models.Event;
import models.Message;
 
public class EmployeeClient {
	
	private static final Logger logger = LogManager.getLogger(Employee.class);
	
	private Socket connectionSocket;
	private static ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;
	
	public EmployeeClient() {
		this.createConnection();
		this.configureStreams();
		logger.info("Employee Client initialized");
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1", 8888);
			logger.info("Employee Client established connection");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Employee Client failed to establish connection: " + ex.getMessage());
		}
	}
	
	private void configureStreams() {
		try {
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
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
	
	public void sendMessage(Message message) {
		try {
			objOs.writeObject(message);
			logger.info("Employee Message sent to Server");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Employee Message failed to be sent: " + ex.getMessage());
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
}
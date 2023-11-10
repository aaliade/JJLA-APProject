package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Customer;
import models.Message;
import models.Payment;

public class CustomerClient {
	
	private static final Logger logger = LogManager.getLogger(CustomerClient.class);
	
	private Socket connectionSocket;
	private static ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;
	
	public CustomerClient() {
		this.createConnection();
		this.configureStreams();
		logger.info("Customer Client initialized");
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1", 8888);
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

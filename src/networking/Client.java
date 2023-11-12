package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import models.Employee;
import models.Customer;
import models.Equipment;
import models.Event;
import models.Message;
import models.Payment;

public class Client {
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action;
	
	public Client() {
		this.createConnection();
		this.configureStreams();
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1", 8888);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void configureStreams() {
		try {
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendAction(String action) {
		this.action = action;
		try {
			objOs.writeObject(action);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendEmployee(Employee employee) {
		try {
			objOs.writeObject(employee);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendCustomer(Customer customer) {
		try {
			objOs.writeObject(customer);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendEvent(Event event) {
		try {
			objOs.writeObject(event);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendMessage(Message message) {
		try {
			objOs.writeObject(message);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendPayment(Payment payment) {
		try {
			objOs.writeObject(payment);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void receiveResponse() {
		try {
			if (action.equalsIgnoreCase("Add Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Employee Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Customer Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Equipment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Equipment Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Message")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Payment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Payment Processed Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Event")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Record Added Successfully",
							"Add Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
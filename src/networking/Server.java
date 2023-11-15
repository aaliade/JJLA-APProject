
package networking;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Customer;
import models.Employee;
import factories.DBConnectorFactory;

public class Server {
	private ObjectOutputStream ObjOS;
	private ObjectInputStream ObjIS;
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	DBConnectorFactory connectorFactory;
	private static Connection dBConn = null;
	private Statement stmt;
	private ResultSet result = null;
	private static Connection dbConn = null;
	public static final Logger logger = LogManager.getLogger(Server.class);

	public Server() {
		this.createConnection();
		this.waitForRequests();
	}

	private void createConnection() {
		try {
			// create new instance of the ServerSocket listening on port 8888
			serverSocket = new ServerSocket(8888);
			logger.info("Server started and waiting for connections");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Caught IOException");
		}
	}

	private void configureStreams() {
		try {
			// Instantiate the output stream, using the getOuputStream method of the socket
			// object as argument to the constructor
			ObjOS = new ObjectOutputStream(connectionSocket.getOutputStream());

			// Instantiate the input stream, using the getOutputStream method of the socket
			// object as argument to the constructor
			ObjIS = new ObjectInputStream(connectionSocket.getInputStream());
			logger.info("Streams configured for communication.");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Caught IOException");
		}
	}

	// This is for the server
	private void closeConnection() {
		try {
			ObjOS.close(); // close object output stream
			ObjIS.close(); // close object input stream
			connectionSocket.close(); // close connection socket
			logger.info("Connection closed.");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Caught IOException");
		}
	}

	private static Connection getDatabaseConnection() {
		if (dBConn == null) { // checks if database connection is null
			try {
				// Using method from DBConnector Factory
				dBConn = DBConnectorFactory.getDatabaseConnection(); // Obtaining the database connection
				JOptionPane.showMessageDialog(null, "DB Connection Established", "Connection status",
						JOptionPane.INFORMATION_MESSAGE); // if connection is successful a message dialog will be shown
				logger.info("Database Connection Established.");
			} catch (Exception ex) { // check for exceptions
				JOptionPane.showConfirmDialog(null, "Could not connect\n", "connection failure",
						JOptionPane.ERROR_MESSAGE);
				logger.error("Caught SQLException");
			}
		}
		return dBConn;
	}

	private void addCustomerToDatabase(Customer customer) {
		try {
			if (customer.create()) {
				ObjOS.writeObject(true); // Return true to customer if successful
				logger.info("Customer added to file successfully.");
			} else {
				ObjOS.writeObject(false); // returns false if execution fails
				logger.warn("Failed to add customer to file.");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error("Caught IOException");
		}
	}

	private void addEmployeeToDatabase(Employee employee) {
		try {
			if (employee.create()) {
				ObjOS.writeObject(true); // Return true to customer if successful
				logger.info("Employee added to file successfully.");
			} else {
				ObjOS.writeObject(false); // returns false if execution fails
				logger.warn("Failed to add employee to file.");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error("Caught IOException");
		}
	}

	private Customer findCustomer(String username) {
		Customer cust = new Customer();
		Customer custObj = cust.findCustomer(username); // creating a new instance for the customer class
		return custObj;
	}

	private Employee findEmployee(String username) {
		Employee employee = new Employee();
		Employee empObj = employee.findEmployee(username);
		return empObj;
	}

	private void waitForRequests() {
		String action = "";   // Initializing an empty string to store the action received
		getDatabaseConnection();  //Obtaining the database connection
		try {
			while (true) {
				connectionSocket = serverSocket.accept();  // Accepting the connection socket from the server
				logger.info("Connection accepted");
				this.configureStreams(); //// Configuring the input and output streams for communication
				try {
					action = (String) ObjIS.readObject();
					logger.info("Received action from client");

					if (action.equals("Add Employee")) {  
						Employee empObj = (Employee) ObjIS.readObject(); // Reading an Employee object from the Object Input Stream and adding to it
						addEmployeeToDatabase(empObj);
						ObjOS.writeObject(true);   // Writing a Boolean value to the Object Output Stream
						logger.info("employee added to file");

					} else if (action.equals("Find Employee")) { // Reading an int representing empID from the Object Input Stream and finding the employee
						String username = (String) ObjIS.readObject();
						Employee empObj = findEmployee(username);
						ObjOS.writeObject(empObj); // Writing the Employee object to the Object Output Stream
						logger.info("Found employee by ID: " + empObj);

					} else if (action.equals("Add Customer")) {
						Customer custObj = null;
						custObj = (Customer) ObjIS.readObject();   // Reading a Customer object from the Object Input Stream
						addCustomerToDatabase(custObj);
						ObjOS.writeObject(true);   // Writing a Boolean value to the Object Output Stream
						logger.info("Customer added to file");

					} else if (action.equals("Find Customer")) { // Reading an int representing custID from the Object Input Stream and finding the customer
						String username = (String) ObjIS.readObject();
						Customer custObj = findCustomer(username); 
						ObjOS.writeObject(custObj);
						logger.info("Found customer by ID: " + custObj);
					}
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (ClassCastException ex) {
					ex.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.closeConnection();  // Closing the connection and associated streams   
		}  
	}catch(EOFException ex)
	{
		System.out.println("Client has terminated connections with the server"); // Printing a message when the client
		// terminates the connection
		ex.printStackTrace();
		logger.warn("Client has terminated connections with the server");
	}catch(IOException ex)
	{
		ex.printStackTrace();
		logger.error("Caught IOException");
	}

}

public static void main(String args[]) {
	new Server();
}

}

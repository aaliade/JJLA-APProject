package networking;

import java.util.Date;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import models.Customer;
import models.Employee;
import models.Equipment;
import models.Event;
import models.Invoice;
import models.Message;
import models.Receipt;
import models.UserAccount;
import factories.DBConnectorFactory;

public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private int clientCount;

	public static final Logger logger = LogManager.getLogger(Server.class);

	public Server() {
		try {
			// connecting to port 8888
			serverSocket = new ServerSocket(8888);
			System.out.println("Server Listening on port 8888...");
			System.out.println("Server starting at: " + new Date());

			while (true) {
				connectionSocket = serverSocket.accept();
				System.out.println("Server starting a thread for client: " + (++clientCount) + " at " + new Date());
				logger.info("Server is starting a thread for client " + clientCount);

				// creating a new thread for each client
				ClientHandler clientThread;

				try {
					ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
					clientThread = new ClientHandler(connectionSocket, context);
					Thread thread = new Thread((Runnable) clientThread); // thread object passing clientHandler obj
					thread.start(); // starting the thread
				} catch (IOException e) {
					e.printStackTrace();
				} // instance for client handler passing the socket object
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ClientHandler implements Runnable {
		private final Socket clientSocket;

		// Database
		private static DBConnectorFactory connectorFactory;
		private static Connection dBConn = null;

		private Statement stmt;
		private ResultSet result = null;

		private static ClassPathXmlApplicationContext context;

		public ClientHandler(Socket clientSocket, ClassPathXmlApplicationContext context) throws IOException {
			this.clientSocket = clientSocket;
			this.context = context;
		}

		public void initializeSpringContext() {
			// Load the Spring ApplicationContext
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		}

		@Override
		public void run() {
			// Ensure that context is not null
			if (context == null) {
				throw new IllegalStateException("Spring context not initialized");
			}
			try (Socket clientConnect = this.clientSocket;
					ObjectOutputStream ObjOS = new ObjectOutputStream(this.clientSocket.getOutputStream());
					ObjectInputStream ObjIS = new ObjectInputStream(this.clientSocket.getInputStream());) {
				String action = ""; // Initializing an empty string to store the action received
				getDatabaseConnection(); // Obtaining the database connection
				while (true) {
					try {
						logger.info("Connection accepted");
						try {
							action = (String) ObjIS.readObject();
							logger.info("Received action from client");

							if (action.equals("Add Employee")) {
								UserAccount<Employee> employeeService = 
										context.getBean("employeeService", UserAccount.class);
								
								employeeService = (UserAccount<Employee>) ObjIS.readObject(); 
								try {
									if (employeeService.create()) {
										ObjOS.writeObject(true); // Return true to employee if successful
										logger.info("Employee added to file successfully.");
									} else {
										ObjOS.writeObject(false); // returns false if execution fails
										logger.warn("Failed to add employee to file.");
									}
								} catch (IOException ioe) {
									ioe.printStackTrace();
									logger.error("Caught IOException");
								}
							} else if (action.equals("Find Employee")) {
								action = (String) ObjIS.readObject();
								// String customerUsername = (String) ObjIS.readObject();
								UserAccount<Employee> employeeService = (UserAccount<Employee>) context
										.getBean("employeeService");
								// Customer foundCustomer = customerService.find(customerUsername);
								if (employeeService.find(action) == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(employeeService.find(action));
									logger.info("Found employee by username");
								}
							} else if (action.equals("Add Customer")) {
								UserAccount<Customer> customerService = context.getBean("customerService",
										UserAccount.class);
								customerService = (UserAccount<Customer>) ObjIS.readObject(); 
								if (customerService.create()) {
									ObjOS.writeObject(true); // Return true to customer if successful
									logger.info("Customer added to database successfully.");
								} else {
									ObjOS.writeObject(false); // returns false if execution fails
									logger.warn("Failed to add customer to database.");
								}
							} else if (action.equals("Find Customer")) { // Reading an int representing custID from the
																			// Object Input Stream and finding the
																			// customer
								action = (String) ObjIS.readObject();
								// String customerUsername = (String) ObjIS.readObject();
								UserAccount<Customer> customerService = (UserAccount<Customer>) context
										.getBean("customerService");
								// Customer foundCustomer = customerService.find(customerUsername);
								if (customerService.find(action) == null) {
									ObjOS.writeObject(false);
									logger.info("Customer not found.");
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(customerService.find(action));
									logger.info("Found customer by username");
								}
							} else if (action.equals("Update Customer")) { // Reading an int representing custID from
																			// the Object Input Stream and finding the
																			// customer
								Customer updateCust = (Customer) ObjIS.readObject();
								if (updateCust.update(updateCust)) {
									ObjOS.writeObject(true);
								} else {
									ObjOS.writeObject(false);
									logger.info("Updated customer");
								}
							} else if (action.equals("Delete Customer")) { // Reading an int representing custID from
																			// the Object Input Stream and finding the
																			// customer
								
								Customer customerService = (Customer) context.getBean("customerService",
										UserAccount.class);
								customerService = (Customer) ObjIS.readObject();
								if (customerService.delete(customerService)) {
									ObjOS.writeObject(true);
								} else {
									ObjOS.writeObject(false);
									logger.info("Deleted customer");
								}
								
							} else if (action.equals("Get Equipment")) {
								Equipment defaulEquip = new Equipment();
								Equipment[] equipmentList = defaulEquip.selectAll();
								if (equipmentList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(equipmentList);
									logger.info("Found equipments in database");
								}
							} else if (action.equals("Get Equipment By Category")) {
								String category = (String) ObjIS.readObject();
							}else if(action.equals("Get Messages")) {
								String username = (String) ObjIS.readObject();
								Message message = new Message();
								Message[] messageList = message.selectAllMessages(username);
								if (messageList == null) {
									System.out.print("Print from server: No message Found");
									ObjOS.writeObject(false);
								} else {
									System.out.print("Print from server: message Found");
									ObjOS.writeObject(true);
									ObjOS.writeObject(messageList);
									logger.info("Found equipments in database");
								}
							}
							else if(action.equals("Get Equipment By Category")) {
								String category = (String)  ObjIS.readObject();
								Equipment defaulEquip = new Equipment();
								Equipment[] equipmentList = defaulEquip.selectAvailableEquipmentByCategory(category);
								if (equipmentList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(equipmentList);
									ObjOS.flush();
									logger.info("Found equipments in database");
								}
							} else if (action.equals("Get Lighting")) {
								Equipment lighting = new Equipment();
								Equipment[] lightingList = lighting.selectAvailableEquipmentByCategory("Lighting");
								if (lightingList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(lightingList);
									logger.info("Found lighting equipments in database");
								}
							} else if (action.equals("Get Sound")) {
								Equipment sound = new Equipment();
								Equipment[] soundList = sound.selectAvailableEquipmentByCategory("Sound");
								if (soundList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(soundList);
									logger.info("Found sound equipments in database");
								}
							} else if (action.equals("Get Power")) {
								Equipment power = new Equipment();
								Equipment[] powerList = power.selectAvailableEquipmentByCategory("Power");
								if (powerList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(powerList);
									logger.info("Found power equipments in database");
								}
							} else if (action.equals("Get Staging")) {
								Equipment staging = new Equipment();
								Equipment[] stagingList = staging.selectAvailableEquipmentByCategory("Staging");
								if (stagingList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(stagingList);
									logger.info("Found staging equipments in database");
								}
							} else if (action.equals("Get Invoice")) {
								Invoice defaulInvoice = new Invoice();
								Invoice[] invoiceList = defaulInvoice.selectAllInvoices();
								if (invoiceList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(invoiceList);
									logger.info("Found invoice in database");
								}
							} else if (action.equals("Get Receipt")) {
								Receipt defaulReceipt = new Receipt();
								Receipt[] receiptList = defaulReceipt.selectAllReceipts();
								if (receiptList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(receiptList);
									logger.info("Found receipt in database");
								}
							} else if (action.equals("Get Message")) {
								Message defaulMessage = new Message();
								Message[] messageList = defaulMessage.selectAllMessages();
								if (messageList == null) {
									ObjOS.writeObject(false);
								} else {
									ObjOS.writeObject(true);
									ObjOS.writeObject(messageList);
									logger.info("Found message in database");
								}
							}   else if (action.equals("Send Message")) {
								Message defaulMessage = (Message) ObjIS.readObject();
								String user = (String) ObjIS.readObject();
								defaulMessage.insertMessage(defaulMessage, dBConn, user);
							} else if (action.equals("Add Event")) {
								Event defaultEvent = (Event) ObjIS.readObject();
								defaultEvent.insert(defaultEvent, dBConn);
							} 
						} catch (ClassNotFoundException ex) {
							ex.printStackTrace();
						} catch (ClassCastException ex) {
							ex.printStackTrace();
						}
					} catch (EOFException ex) {
						System.out.println("Client has terminated connections with the server"); // Printing a message
																									// when the client
																									// terminates the
																									// connection
						logger.warn("Client has terminated connections with the server");
						break;
					} catch (IOException ex) {
						ex.printStackTrace();
						logger.error("Caught IOException");
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					connectionSocket.close(); // Close the client socket
					logger.info("Connection closed for " + connectionSocket.getInetAddress());
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("Exception occurred while closing client socket: " + e.getMessage());
				}
			}
		}

		private void getDatabaseConnection() {
			if (dBConn == null) { // checks if database connection is null
				try {
					String url = "jdbc:mysql://localhost:3306/grizzly’sentertainmentequipmentrental"; // defines the URL
																										// of the
																										// connection
					dBConn = DriverManager.getConnection(url, "root", ""); // connecting with database

					connectorFactory = new DBConnectorFactory();
					dBConn = DBConnectorFactory.getDatabaseConnection();
					logger.info("Database Connection Established.");
				} catch (Exception ex) { // check for exceptions
					JOptionPane.showMessageDialog(null, "Could not connect\n", "connection failure",
							JOptionPane.ERROR_MESSAGE);
					logger.error("Caught SQLException");
				}
			}
		}
	}

	public static void main(String args[]) {
		logger.info("Test Info message");
		new Server();
	}

}
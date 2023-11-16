package networking;

import java.util.Date;
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

	private int clientCount; 
	
	public Server(){
		try {
			//connectint to port 8002
			serverSocket= new ServerSocket(8002);
			System.out.println("Server starting at:"+ new Date());
			
			while (true) {
				connectionSocket= serverSocket.accept();
				System.out.println("Server starting a thread for cleint: "+ (++clientCount) + "at" + new Date());
				logger.info("Server is starting a thread for client " + clientCount);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//creating a new thread for each client 
				ClientHandler clientThread = new ClientHandler (connectionSocket); // instance for client handler passing the socket object
				Thread thread = new Thread((Runnable) clientThread); //thread object passing clientHandler obj
				thread.start(); //starting the thread
	}
	
	class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final DataOutputStream dataOutputStream;
        private final DataInputStream dataInputStream;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
            	// Initialize output and input streams for communication with the client
                this.dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                this.dataInputStream = new DataInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
            	logger.error("Error in streams.", e);
                throw new RuntimeException("Error in creating streams.", e);
            }
        }

		@Override
		public void run() {
			try 
   		 // Create a BufferedReader to read data from the client's input stream
   		(BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
   		// Create a BufferedWriter to write data to the client's output stream
   	     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) 
   		{
   		// Read messages from the client
   			String message;
   			while ((message = reader.readLine()) != null) {
   	        logger.info("Message received from client: " + message);
   	        
   	        // Process the message and send a response back to the client
   	        writer.write("Server received: " + message + "\n");
   	        writer.flush();
   	        }
   		}catch(IOException e){
   			e.printStackTrace();
   			logger.error("Exception occurred in client handler: " + e.getMessage());
   		}finally {
   			try {
   				clientSocket.close();  // Close the client socket
   				logger.info("Connection closed for " + clientSocket.getInetAddress());
   			}catch (IOException e) {
   				e.printStackTrace();
   				logger.error("Exception occurred while closing client socket: " + e.getMessage());
   	        }
   		}
   	}
   }
	
	private void configureStreams() {
		try {
			//Instantiate the output stream, using the getOuputStream method of the socket object as argument to the constructor
			ObjOS = new ObjectOutputStream (connectionSocket.getOutputStream());
			
			//Instantiate the input stream, using the getOutputStream method of the socket object as argument to the constructor 
			ObjIS= new ObjectInputStream (connectionSocket.getInputStream());
			 logger.info("Streams configured for communication.");
		}catch(IOException ex) {
			ex.printStackTrace();
			logger.error("Caught IOException");
		}
	}
	
	private static Connection getDatabaseConnection() {
		if (dBConn == null) { //checks if database connection is null
			try {
				String url= "jdbc:mysql://localhost:3306/grizzly’sentertainmentequipmentrental"; //defines the URL of the connection
				dBConn = DriverManager.getConnection(url,"root",""); //connecting with database 
				
				JOptionPane.showMessageDialog(null, "DB Connection Established","Connection status",JOptionPane.INFORMATION_MESSAGE); //if connection is successful a message dialog will be shown
				logger.info("Database Connection Established.");
			} catch (SQLException ex) { //check for exceptions
				JOptionPane.showConfirmDialog(null, "Could not connect\n","connection failure", JOptionPane.ERROR_MESSAGE);
				logger.error("Caught SQLException");
				
			}
		}
		return dBConn;  
	}
	
	private void closeConnection() {
		try {
			ObjOS.close();  //close object output stream
			ObjIS.close();  //close object input stream
			connectionSocket.close();  //close connection socket 
			logger.info("Connection closed.");
		}catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Caught IOException");
		}
	}
	
	private void addCustomerToFile(Customer customer) {
		//sql query to insert data information to database 
		String sql = "INSERT INTO grizzly’sentertainmentequipmentrenta (custId,accountBalance)" + "VALUES ("+null+",'" + customer.getCustID()+ "','" + customer.getAccountBalance()+ "');";
		
		try {
			Statement stmt = dbConn.createStatement(); //creating a statement for database connection
			
			if((stmt.executeUpdate(sql)==1)) {
				ObjOS.writeObject(true); //Return true to customer if successful
				logger.info("Customer added to file successfully.");
			}else {
				ObjOS.writeObject(false); //returns false if execution fails
				logger.warn("Failed to add customer to file.");
			}
		}catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error("Caught IOException");
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error("Caught SQLException");
		}
	}
	
	private Customer findCustomerById(int custID) {
		Customer custObj = new Customer ();  //creating a new instance for the customer class
		String query = "SELECT * FROM grizzly’sentertainmentequipmentrental WHERE customer Id ="+ custID;  //constructing the SQL query 
		
		try {
			Statement stmt = dbConn.createStatement();  //creating a statement for the db connection 
			result = stmt.executeQuery(query);  // executes the SQL query and storing the result in the 'result' variable

			// Checking if the result set has any data 
			if(result.next()) {
				custObj.setCustID(result.getInt(0));
				custObj.setAccountBalance(result.getFloat(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();  //prints stack trace if SQLException occurs 
			logger.error("Caught SQLException");
		}
		return custObj;
	}
	
	private void addEmployeeToFile(Employee employee) {
		//sql query to insert employee data to database 
		String sql = "INSERT INTO grizzly’sentertainmentequipmentrental\" (empID,empRole,hireDate)" + "VALUES ("+null+",'" + employee.getEmpID()+ "','" + employee.getEmpRole() +"'.'" +  employee.getHireDate()+ "');";
		
		try {
			Statement stmt = dbConn.createStatement(); //creating a statement for database connection
			if((stmt.executeUpdate(sql)==1)) {
				ObjOS.writeObject(true); //Return true to customer if successful
				logger.info("Employee added to file successfully.");
			}else {
				ObjOS.writeObject(false); //returns false if execution fails
				logger.warn("Failed to add employee to file.");
			}
		}catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error("Caught IOException");
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error("Caught SQLException");
		}
	}
	
	private Employee findEmployeeById(int empID) {
		Employee empObj = new Employee ();  //creating a new instance for the customer class
		String query = "SELECT * FROM grizzly’sentertainmentequipmentrental WHERE customer Id ="+ empID;  //constructing the SQL query 
		
		try {
			Statement stmt = dbConn.createStatement();  //creating a statement for the db connection 
			result = stmt.executeQuery(query);  //executes the SQL query and storing the result in the 'result' variable
			
			// Checking if the result set has any data and moving the cursor to the first row if it exists
			if(result.next()) {
				empObj.setEmpID(result.getInt(0)); 
				empObj.setEmpRole(result.getString(1));
				empObj.setHireDate(result.getDate(2)); 
			}
		}catch(SQLException e) {
			e.printStackTrace();  //prints stack trace if SQLException occurs 
			logger.error("Caught SQLException");
		}
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
	                    addEmployeeToFile(empObj);
	                    ObjOS.writeObject(true);   // Writing a Boolean value to the Object Output Stream
	                    logger.info("employee added to file");
	                    
	                } else if (action.equals("Find Employee")) { // Reading an int representing empID from the Object Input Stream and finding the employee
	                    int empID = (int) ObjIS.readObject();
	                    Employee empObj = findEmployeeById(empID);
	                    ObjOS.writeObject(empObj); // Writing the Employee object to the Object Output Stream
	                    logger.info("Found employee by ID: " + empObj);
	                    
	                } else if (action.equals("Add Customer")) {
	                    Customer custObj = (Customer) ObjIS.readObject();   // Reading a Customer object from the Object Input Stream
	                    addCustomerToFile(custObj);
	                    ObjOS.writeObject(true);   // Writing a Boolean value to the Object Output Stream
	                    logger.info("Customer added to file");
	                    
	                } else if (action.equals("Find Customer")) { // Reading an int representing custID from the Object Input Stream and finding the customer
	                    int custID = (int) ObjIS.readObject();
	                    Customer custObj = findCustomerById(custID); 
	                    ObjOS.writeObject(custObj);
	                    logger.info("Found customer by ID: " + custObj);
	                }
	            } catch (ClassNotFoundException ex) {
	                ex.printStackTrace();
	            } catch (ClassCastException ex) {
	                ex.printStackTrace();
	            }
	            this.closeConnection();  // Closing the connection and associated streams
	        }
	        
	    } catch (EOFException ex) {
	        System.out.println("Client has terminated connections with the server");  //Printing a message when the client terminates the connection
	        ex.printStackTrace();
	        logger.warn("Client has terminated connections with the server");
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        logger.error("Caught IOException");
	    }
	}



public static void main(String args[]) {
	new Server();
}

}
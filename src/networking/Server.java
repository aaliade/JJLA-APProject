package networking;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server {
	private ObjectOutputStream ObjOS;
	private ObjectInputStream ObjIS;
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private static Connection dBConn = null;
	private Statement stmt;
	private ResultSet result = null;
	
	Server(){
		this.createConnection();
		this.waitForRequests();
	}
	
	private void createConnection() {
		
	try {
		//create new instance of the ServerSocket listening on port 8888
		serverSocket = new ServerSocket(8888);
	}catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	
	private void configureStreams() {
		try {
			//Instantiate the output stream, using the getOuputStream method of the socket object as argument to the constructor
			ObjOS = new ObjectOutputStream (connectionSocket.getOutputStream());
			
			//Instantiate the inout stream, using the getOutputStream method of the socket object as argument to the constructor 
			ObjIS= new ObjectInputStream (connectionSocket.getInputStream());
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private static Connection getDatabaseConnection() {
		if(dbConn == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/grizzly’sentertainmentequipmentrental";
				dbConn = DriverManger.getConnection(url,"root","");
				
				JOptionPane.showMessageDialog(null,"DB Connecion Established" , "Connectin Status", JoptionPane.INFORMATION_MESSAGE);
			}catch (SQLException ex) {
				JoptionPane.showMessageDialog(null,"Could not connect to database\n"+ex, "Connection Failure", JOptionPane.ERROR_MESSAGE);
			}
		}
		return dBConn;
	}
	
	private void closeConnection() {
		try {
			ObjOS.close(); 
			ObjIS.close();
			connectionSocket();
		}catch (IOExpection ex) {
			ex.printStackTrace();
		}
	}
	
}

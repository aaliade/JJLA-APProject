package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		//this.waitForRequests();
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
	
	
	
}

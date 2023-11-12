package multiThreading;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiThreadServer {
	private static final Logger logger = LogManager.getLogger(MultiThreadServer.class);
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private int clientCount;
	
	
	public MultiThreadServer() {
		try {
			
			this.serverSocket = new ServerSocket(8000);
			System.out.println("Server has started 8000");
			this.clientCount=0;
			
			//infinite loop 
			while(true) {
				connectionSocket = this.serverSocket.accept(); 
				this.clientCount ++; //increment the client count
				 logger.info("Server is starting a thread for client " + clientCount);
				
				System.out.println("Server is starting a thread for the client" + clientCount);
			}
		}catch (IOException e) {
			e.printStackTrace();
			 logger.warn("IOException caught");
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

}

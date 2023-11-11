package multiThreading;

import java.io.*;
import java.net.*;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import networking.Server;

public class MultiThreadServer {
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private int clientCount = 0;
    public static final Logger logger = LogManager.getLogger(Server.class);

    public MultiThreadServer() {
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server has started on " + new Date());

            while (true) {
                connectionSocket = serverSocket.accept();
                clientCount++;
                System.out.println("Starting a thread for client " + clientCount + " at " + new Date());

                ClientHandler clientHandler = new ClientHandler(connectionSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    class ClientHandler implements Runnable {
        private Socket socket;
        private DataOutputStream output;
        private DataInputStream input;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        
        @Override
        public void run() {
            try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
            ) {
                //Send a welcome message to the client
                out.writeObject("Welcome to the server!");

                while (true) {
                    // Read an object from the client
                    Object receivedObject = in.readObject();

                    // Handle the received object
                    if (receivedObject instanceof String) {
                        String message = (String) receivedObject;
                        System.out.println("Received message from client: " + message);

                        //Send a response back to the client
                        out.writeObject("Server received: " + message);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    System.out.println("Client disconnected: " + socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    public static void main(String[] args) {
        new MultiThreadServer();
    }
}

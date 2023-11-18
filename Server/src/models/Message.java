package models;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factories.DBConnectorFactory;

public class Message {
    private String messageID;
    private String senderID;
    private String receiverID;
    private String content;
    private Date timeStamp;
    private Connection dbConn = null;
    private Statement stmt = null;
    private ResultSet result = null;

    private static final Logger logger = LogManager.getLogger(Message.class);

    public Message() { // default constructor
        this.messageID = "";
        this.senderID = "";
        this.receiverID = "";
        this.content = "";
        this.timeStamp = new Date();
        logger.info("Message initialized");
        this.dbConn = DBConnectorFactory.getDatabaseConnection();
    }

    public Message(String messageID, String senderID, String receiverID, String content, Date date) { // primary constructor
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
        this.timeStamp = date;
        logger.info("Input accepted, Message initialized");
    }

    // getters and setters
    public String getMessageID() {
        logger.info("Message ID returned");
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
        logger.info("Input accepted, Message ID initialized");
    }

    public String getSenderID() {
        logger.info("Sender ID returned");
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
        logger.info("Input accepted, Sender ID initialized");
    }

    public String getReceiverID() {
        logger.info("Receiver ID returned");
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
        logger.info("Input accepted, Receiver ID initialized");
    }

    public String getContent() {
        logger.info("Message Content returned");
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        logger.info("Input accepted, Message Content initialized");
    }

    public Date getTimeStamp() {
        logger.info("Message TimeStamp returned");
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
        logger.info("Input accepted, Message TimeStamp initialized");
    }

    @Override
    public String toString() {
        logger.info("Message Information returned");
        return "Message ID: " + messageID + " | Sender ID: " + senderID + " | Receiver ID: " + receiverID
                + " | Content: " + content + " | Date: " + timeStamp;
    }
    
    public Message[] selectAllMessages() {
		String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.message;";
		Message[] messageList = null;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);
			int count = 0;
			while (result.next()) {
				count++;
			}
			result.close();
			result = stmt.executeQuery(sql);
			messageList = new Message[count];
			int i = 0;
			while (result.next()) {
                String messageID = result.getString("messageID");
                String senderID = result.getString("senderID");
                String receiverID = result.getString("receiverID");
                String content = result.getString("content");
                Date timeStamp = result.getDate("timestamp");

                System.out.println("Message ID: " + messageID + "\nSender ID: " + senderID +
                        "\nReceiver ID: " + receiverID + "\nContent: " + content + "\nTimeStamp: " + timeStamp + "\n");
                messageList[i] = new Message(messageID, senderID, receiverID, content, timeStamp); // initialize object
				i++;
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while selecting messages: " + e.getMessage());
		} finally {
			try {
				stmt.close();
				result.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement/result: " + e.getMessage());
				logger.error("Error while closing statement/result: " + e.getMessage());
			}
		}
		return messageList;
	}
    
    public Message[] selectMessagesBySender(String senderID) {
        String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.message WHERE senderID = " + senderID + ";";
		Message[] messageList = null;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);
			int count = 0;
			while (result.next()) {
				count++;
			}
			result.close();
			result = stmt.executeQuery(sql);
			messageList = new Message[count];
			int i = 0;
			while (result.next()) {
                String messageID = result.getString("messageID");
                String senderID1 = result.getString("senderID");
                String receiverID = result.getString("receiverID");
                String content = result.getString("content");
                Date timeStamp = result.getDate("timestamp");

                System.out.println("Message ID: " + messageID + "\nSender ID: " + senderID +
                        "\nReceiver ID: " + receiverID + "\nContent: " + content + "\nTimeStamp: " + timeStamp + "\n");
                messageList[i] = new Message(messageID, senderID, receiverID, content, timeStamp); // initialize object
				i++;
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while selecting messages by sender: " + e.getMessage());
		} finally {
			try {
				stmt.close();
				result.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement/result: " + e.getMessage());
				logger.error("Error while closing statement/result: " + e.getMessage());
			}
		}
		return messageList;
	}

    public void insertMessage(String senderID, String receiverID, String content) {
        String sql = "INSERT INTO grizzly’sentertainmentequipmentrental.message (senderID, receiverID, content) VALUES "
                + "(" + senderID + ", " + receiverID + ", '" + content + "');";

        try {
            stmt = dbConn.createStatement();
            int inserted = stmt.executeUpdate(sql);

            if (inserted == 1) {
                JOptionPane.showMessageDialog(null, "Message Record Inserted Successfully!", "Insert Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Message Record Inserted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Message Record Insertion Failed.", "Insert Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Message Record Insertion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while inserting Message Record: " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }

    public void updateMessage(String messageID, String newContent) {
        String sql = "UPDATE grizzly’sentertainmentequipmentrental.message SET content = '" + newContent + "' WHERE messageID = " + messageID + ";";

        try {
            stmt = dbConn.createStatement();
            int updated = stmt.executeUpdate(sql);

            if (updated == 1) {
                JOptionPane.showMessageDialog(null, "Message Record Updated Successfully!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Message Record (ID: " + messageID + ") Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Message Record Update Failed.", "Update Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Message Record (ID: " + messageID + ") Update Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while updating Message Record (ID: " + messageID + "): " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }

    public void deleteMessage(String messageID) {
        String sql = "DELETE FROM grizzly’sentertainmentequipmentrental.message WHERE messageID = " + messageID + ";";

        try {
            stmt = dbConn.createStatement();
            int deleted = stmt.executeUpdate(sql);

            if (deleted == 1) {
                JOptionPane.showMessageDialog(null, "Message Record Deleted Successfully!", "Delete Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Message Record (ID: " + messageID + ") Deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Message Record Deletion Failed.", "Delete Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Message Record (ID: " + messageID + ") Deletion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while deleting Message Record (ID: " + messageID + "): " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }
}

package models;
 
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Message {
    private int messageID;
    private int senderID;
    private int receiverID;
    private String content;
    private Date timeStamp;

    private static final Logger logger = LogManager.getLogger(Message.class);

    public Message() { // default constructor
        this.messageID = 0;
        this.senderID = 0;
        this.receiverID = 0;
        this.content = "";
        this.timeStamp = new Date(1, 1, 100);
        logger.info("Message initialized");
    }

    public Message(int messageID, int senderID, int receiverID, String content, Date date) { // primary constructor
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
        this.timeStamp = date;
        logger.info("Input accepted, Message initialized");
    }

    // getters and setters
    public int getMessageID() {
        logger.info("Message ID returned");
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
        logger.info("Input accepted, Message ID initialized");
    }

    public int getSenderID() {
        logger.info("Sender ID returned");
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
        logger.info("Input accepted, Sender ID initialized");
    }

    public int getReceiverID() {
        logger.info("Receiver ID returned");
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
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
}


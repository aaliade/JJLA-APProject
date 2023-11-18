package models;
 
import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
    private String messageID;
    private String senderID;
    private String receiverID;
    private String content;
    private Date timeStamp;

    private transient static final Logger logger = LogManager.getLogger(Message.class);

    public Message() { // default constructor
        this.messageID = "";
        this.senderID = "";
        this.receiverID = "";
        this.content = "";
        this.timeStamp = new Date();
        logger.info("Message initialized");
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
}

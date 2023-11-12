package models;
 
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Message {
	private int messageID;
	private String content;
	private Date timeStamp;
	
	private static final Logger logger = LogManager.getLogger(Message.class);
	
	public Message() { //default constructor
		this.messageID = 0;
		this.content = "";
		this.timeStamp = new Date(1,1,100);
		logger.info("Message initialized");
	}
	
	public Message(int messageID, String content, Date date) { //primary constructor
		this.messageID = messageID;
		this.content = content;
		this.timeStamp = date;
		logger.info("Input accepted, Message initialized");
	}
	
	//getters and setters 
	public int getMessageID() {
		logger.info("Message ID returned");
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
		logger.info("Input accepted, Message ID initialized");
	}
	public String getContent() {
		logger.info("Message Content returned");
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		logger.info("Input accepted, Message Content initialized");
	}
	public Date getDate() {
		logger.info("Message Date returned");
		return timeStamp;
	}
	public void setDate(Date date) {
		this.timeStamp = date;
		logger.info("Input accepted, Message TimeStamp initialized");
	} 
	

	@Override
	public String toString() {
		logger.info("Message Information returned");
		return "Message ID: " + messageID + "Content: " + content + "Date: " + timeStamp;
	}
	
}

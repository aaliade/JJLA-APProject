package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Message {
	private int messageID;
	private String Content;
	private Date timeStamp;
	
	private static final Logger logger = LogManager.getLogger(Message.class);
	
	public Message() { //default constructor
		this.messageID = 0;
		this.Content = "";
		this.timeStamp = new Date(1,1,100);
		logger.info("Message initialized");
	}
	
	public Message(int messageID, String content, Date date) { //primary constructor
		this.messageID = messageID;
		this.Content = content;
		this.timeStamp = date;
		logger.info("Input accepted, Message initialized");
	}
	
	//getters and setters 
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
		logger.info("Input accepted, Message ID initialized");
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
		logger.info("Input accepted, Message Content initialized");
	}
	public Date getDate() {
		return timeStamp;
	}
	public void setDate(Date date) {
		this.timeStamp = date;
		logger.info("Input accepted, Message TimeStamp initialized");
	} 
	
	
}

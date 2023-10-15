package models;

public class Message {
	private int messageID;
	private String Content;
	private Date timeStamp;
	
	public Message() { //default constructor
		this.messageID = 0;
		this.Content = "";
		this.timeStamp = new Date(1,1,100);
	}
	
	public Message(int messageID, String content, Date date) { //primary constructor
		this.messageID = messageID;
		this.Content = content;
		this.timeStamp = date;
	}
	
	//getters and setters 
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Date getDate() {
		return timeStamp;
	}
	public void setDate(Date date) {
		this.timeStamp = date;
	} 
	
	
}

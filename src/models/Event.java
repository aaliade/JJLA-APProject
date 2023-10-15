package models;

public class Event {
	
	private int eventID;
	private String eventName;
	private String eventDate;
	private String eventLocation;
	
	public Event() {
		eventID = 0;
		eventName = "";
		eventDate = "";
		eventLocation = "";
	}
	
	public Event(int eventID, String eventName, String eventDate, String eventLocation) {
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
	}
	
	public int geteventID() {
		return eventID;
	}
	
	public void seteventID(int eventID) {
		this.eventID = eventID;
	}
	
	public String geteventName() {
		return eventName;
	}
	
	public void seteventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String geteventDate() {
		return eventDate;
	}
	
	public void seteventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	public String geteventLocation() {
		return eventLocation;
	}
	
	public void seteventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	@Override
	public String toString() {
		return "Event ID" + eventID + "Event Name" + eventName + "Event Date" + eventDate + "Event Location" + eventLocation;
	}

}

package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Event {
	
	private int eventID;
	private String eventName;
	private String eventDate;
	private String eventLocation;
	
	private static final Logger logger = LogManager.getLogger(Event.class);
	
	public Event() {
		eventID = 0;
		eventName = "";
		eventDate = "";
		eventLocation = "";
		logger.info("Event initialized);
	}
	
	public Event(int eventID, String eventName, String eventDate, String eventLocation) {
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		logger.info("Input accepted, Event initialized");
	}
	
	public int geteventID() {
		return eventID;
	}
	
	public void seteventID(int eventID) {
		this.eventID = eventID;
		logger.info("Input accepted, Event ID set");
	}
	
	public String geteventName() {
		return eventName;
	}
	
	public void seteventName(String eventName) {
		this.eventName = eventName;
		logger.info("Input accepted, Event Name set");
	}
	
	public String geteventDate() {
		return eventDate;
	}
	
	public void seteventDate(String eventDate) {
		this.eventDate = eventDate;
		logger.info("Input accepted, Event Date set");
	}
	
	public String geteventLocation() {
		return eventLocation;
	}
	
	public void seteventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
		logger.info("Input accepted, Event Location set");
	}
	
	@Override
	public String toString() {
		logger.info("Event information returned");
		return "Event ID" + eventID + "Event Name" + eventName + "Event Date" + eventDate + "Event Location" + eventLocation;
	}

}

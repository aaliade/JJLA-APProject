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

public class Event {
	public static final Logger logger = LogManager.getLogger(Event.class);
	private String eventID;
	private String eventName;
	private String eventDate;
	private String eventLocation;
	private Connection dbConn = null;
	private Statement stmt = null;
	private ResultSet result = null;

	public Event() {
		eventID = "";
		eventName = "";
		eventDate = "";
		eventLocation = "";
		logger.info("Event initialized");
		this.dbConn = DBConnectorFactory.getDatabaseConnection();
	}

	public Event(String eventID, String eventName, String eventDate, String eventLocation) {
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		logger.info("Input accepted, Event initialized");
	}

	public String geteventID() {
		logger.info("Event ID returned");
		return eventID;
	}

	public void seteventID(String eventID) {
		this.eventID = eventID;
		logger.info("Input accepted, Event ID set");
	}

	public String geteventName() {
		logger.info("Event Name returned");
		return eventName;
	}

	public void seteventName(String eventName) {
		this.eventName = eventName;
		logger.info("Input accepted, Event Name set");
	}

	public String geteventDate() {
		logger.info("Event Date returned");
		return eventDate;
	}

	public void seteventDate(String eventDate) {
		this.eventDate = eventDate;
		logger.info("Input accepted, Event Date set");
	}

	public String geteventLocation() {
		logger.info("Event Location returned");
		return eventLocation;
	}

	public void seteventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
		logger.info("Input accepted, Event Location set");
	}

	@Override
	public String toString() {
		logger.info("Event information returned");
		return "Event ID: " + eventID + "Event Name: " + eventName + "Event Date: " + eventDate + "Event Location: "
				+ eventLocation;
	}

	public void selectAll() {
		String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.event;";

		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				int eventID = result.getInt("eventID");
				String eventName = result.getString("eventName");
				String eventDate = result.getString("eventDate");
				String eventLocation = result.getString("eventLocation");

				System.out.println("Event ID: " + eventID + "\nEvent Name: " + eventName + "\nEvent Date: " + eventDate
						+ "\nEvent Location: " + eventLocation + "\n");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while selecting events: " + e.getMessage());
		} finally {
			try {
				stmt.close();
				result.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement/result: " + e.getMessage());
				logger.error("Error while closing statement/result: " + e.getMessage());
			}
		}
	}

	public Event[] selectAllEvents() {
		String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.event;";
		Event[] eventList = null;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);
			int count = 0;
			while (result.next()) {
				count++;
			}
			result.close();
			result = stmt.executeQuery(sql);
			eventList = new Event[count];
			int i = 0;
			while (result.next()) {
				String eventID = result.getString("eventID");
				String eventName = result.getString("eventName");
				String eventDate = result.getString("eventDate");
				String eventLocation = result.getString("eventLocation");

				System.out.println("Event ID: " + eventID + "\nEvent Name: " + eventName + "\nEvent Date: " + eventDate
						+ "\nEvent Location: " + eventLocation + "\n");
				eventList[i] = new Event(eventID, eventName, eventDate, eventLocation); // initialize object
				i++;
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while selecting events: " + e.getMessage());
		} finally {
			try {
				stmt.close();
				result.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement/result: " + e.getMessage());
				logger.error("Error while closing statement/result: " + e.getMessage());
			}
		}
		return eventList;
	}
	
	public Event[] selectEventById(String eventID) {
		String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.event WHERE eventID = " + eventID + ";";
		Event[] eventList = null;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);
			int count = 0;
			while (result.next()) {
				count++;
			}
			result.close();
			result = stmt.executeQuery(sql);
			eventList = new Event[count];
			int i = 0;
			while (result.next()) {
				String eventID1 = result.getString("eventID");
				String eventName = result.getString("eventName");
				String eventDate = result.getString("eventDate");
				String eventLocation = result.getString("eventLocation");

				System.out.println("Event ID: " + eventID + "\nEvent Name: " + eventName + "\nEvent Date: " + eventDate
						+ "\nEvent Location: " + eventLocation + "\n");
				eventList[i] = new Event(eventID, eventName, eventDate, eventLocation); // initialize object
				i++;
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while selecting events by ID: " + e.getMessage());
		} finally {
			try {
				stmt.close();
				result.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement/result: " + e.getMessage());
				logger.error("Error while closing statement/result: " + e.getMessage());
			}
		}
		return eventList;
	}

	public void insert(String eventID, String eventName, String eventDate, String eventLocation) {
		String sql = "INSERT INTO grizzly’sentertainmentequipmentrental.event (eventID, eventName, eventDate, eventLocation)"
				+ "VALUES ('" + eventID + "', '" + eventName + "', '" + eventDate + "', '" + eventLocation + "');";

		try {
			stmt = dbConn.createStatement();

			int inserted = stmt.executeUpdate(sql);
			if (inserted == 1) {
				JOptionPane.showMessageDialog(null, "Event Record Inserted Successfully!", "Insertion Status",
						JOptionPane.INFORMATION_MESSAGE);
				logger.info("Event Record (ID: " + eventID + ") Inserted Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Event Record Insertion Failed.", "Insertion Status",
						JOptionPane.ERROR_MESSAGE);
				logger.error("Event Record (ID: " + eventID + ") Insertion Failed");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while inserting Event Record (ID: " + eventID + "): " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
			logger.error("Unexpected Error while inserting Event Record (ID: " + eventID + "): " + e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());
				logger.error("Error while closing statement: " + e.getMessage());
			}
		}
	}

	public void update(String UNDECIDED) {
		String sql = "UPDATE grizzly’sentertainmentequipmentrental.event " + "SET --- = '" + UNDECIDED + "'"
				+ " WHERE UNDECIDED = '" + UNDECIDED + "'";

		try {
			stmt = dbConn.createStatement();
			int updated = stmt.executeUpdate(sql);

			if (updated == 1) {
				JOptionPane.showMessageDialog(null, "Event Record Updated Successfully!", "Update Status",
						JOptionPane.INFORMATION_MESSAGE);
				logger.info(
						"Event Record (Column: " + UNDECIDED + ", Condition: " + UNDECIDED + ") Updated Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Event Record Update Failed.", "Update Status",
						JOptionPane.ERROR_MESSAGE);
				logger.error("Event Record (Column: " + UNDECIDED + ", Condition: " + UNDECIDED + ") Update Failed");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while updating Event Record (Column: " + UNDECIDED + ", Condition: " + UNDECIDED
					+ "): " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
			logger.error("Unexpected Error while updating Event Record (Column: " + UNDECIDED + ", Condition: "
					+ UNDECIDED + "): " + e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());
				logger.error("Error while closing statement: " + e.getMessage());
			}
		}
	}

	public void delete(String eventId) {
		String sql = "DELETE FROM grizzly’sentertainmentequipmentrental.event WHERE eventID = " + eventId + ";";

		try {
			stmt = dbConn.createStatement();
			int deleted = stmt.executeUpdate(sql);
			if (deleted == 1) {
				JOptionPane.showMessageDialog(null, "Event Record Deleted!", "Deletion Status",
						JOptionPane.INFORMATION_MESSAGE);
				logger.info("Event Record (ID: " + eventId + ") Deleted");
			} else {
				JOptionPane.showMessageDialog(null, "Event Record Deletion Failed.", "Deletion Status",
						JOptionPane.ERROR_MESSAGE);
				logger.error("Event Record (ID: " + eventId + ") Deletion Failed");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			logger.error("SQL Exception while deleting Event Record (ID: " + eventId + "): " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
			logger.error("Unexpected Error while deleting Event Record (ID: " + eventId + "): " + e.getMessage());
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
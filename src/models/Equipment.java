package models;

import org.apache.logging.log4j.Logger;

import factories.DBConnectorFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;


public class Equipment{
	private static final Logger logger = LogManager.getLogger(Equipment.class);
	private int equipID;
	private String equipName;
	private String description;
	private boolean status;
	private String category;
	private int rentalRate;
	private Connection dbConn = null;
	private Statement stmt = null;
	private ResultSet result = null;

	
	public Equipment() {
		equipID = 0;
		equipName = "";
		description = "";
		status = true;
		category = "";
		rentalRate = 0;
		logger.info("Equipment initialized");
		this.dbConn = DBConnectorFactory.getDatabaseConnection();
	}
	
	public Equipment(int equipID, String equipName, String description, boolean status, String category, int rentalRate) {
		this.equipID = equipID;
		this.equipName = equipName;
		this.description = description;
		this.status = status;
		this.category = category;
		this.rentalRate = rentalRate;
		logger.info("Input accepted, Equipment initialized");
	}

	public int getequipID() {
		return equipID;
	}
	
	public void setequipID(int equipID) {
		this.equipID = equipID;
		logger.info("Input accepted, Equipment ID set");
	}
	
	public String getequipName() {
		return equipName;
	}
	
	public void setequipName(String equipName) {
		this.equipName = equipName;
		logger.info("Input accepted, Equipment Name set");
	}
	
	public String getdescription() {
		return description;
	}
	
	public void setdescription(String description) {
		this.description = description;
		logger.info("Input accepted, Equipment Description set");
	}

	public boolean getstatus() {
		return status;
	}

	public void setstatus(boolean status) {
		this.status = status;
		logger.info("Input accepted, Equipment Status set");
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getrentalRate() {
		return rentalRate;
	}

	public void setrentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
		logger.info("Input accepted, Equipment Rental Rate set");
	}
	
	@Override
	public String toString() {
		logger.info("Equipment information returned");
		return "Equipment ID" + equipID + "Equipment Name" + equipName + "Description" + description + "Status" + status + "Rental Rate" + rentalRate;	
	}
	
	public void selectAll() {
        String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.equipment;";

        try {
            stmt = dbConn.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
                int equipID = result.getInt("equipID");
                String equipName = result.getString("equipName");
                String description = result.getString("description");
                boolean status = result.getBoolean("status");
                int rentalRate = result.getInt("rentalRate");

                System.out.println("Equipment ID: " + equipID + "\nEquipment Name: " + equipName +
                        "\nDescription: " + description + "\nStatus: " + status + "\nRental Rate: " + rentalRate + "\n");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while selecting equipments: " + e.getMessage());
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

	public void selectAvailableEquipmentByCategory(String category) {
	    String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.equipment WHERE category = '" + category + "' AND status = 'Available';";

	    try {
	        stmt = dbConn.createStatement();
	        result = stmt.executeQuery(sql);

	        while (result.next()) {
                int equipID = result.getInt("equipID");
                String equipName = result.getString("equipName");
                String description = result.getString("description");
                boolean status = result.getBoolean("status");
                int rentalRate = result.getInt("rentalRate");

                System.out.println("Equipment ID: " + equipID + "\nEquipment Name: " + equipName +
                        "\nDescription: " + description + "\nStatus: " + status + "\nCategory: " + category + "\nRental Rate: " + rentalRate + "\n");
            }
	    } catch (SQLException e) {
	        System.err.println("SQL Exception: " + e.getMessage());
	        logger.error("SQL Exception while selecting available equipment: " + e.getMessage());
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
	
	public void insert(int equipID, String equipName, String description, boolean status, int rentalRate) {
        String sql = "INSERT INTO grizzly’sentertainmentequipmentrental.equipment (equipID, equipName, description, status, rentalRate)"
                + "VALUES ('" + equipID + "', '" + equipName + "', '" + description + "', '" + status + "', '" + rentalRate + "');";

        try {
            stmt = dbConn.createStatement();

            int inserted = stmt.executeUpdate(sql);
            if (inserted == 1) {
                System.out.println("Equipment Record Inserted Successfully!");
                logger.info("Equipment Record (ID: " + equipID + ") Inserted Successfully");
            } else {
                System.out.println("Equipment Record Insertion Failed.");
                logger.error("Equipment Record (ID: " + equipID + ") Insertion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while inserting Equipment Record (ID: " + equipID + "): " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error while closing statement: " + e.getMessage());
                logger.error("Error while closing statement: " + e.getMessage());
            }
        }
    }

    public void update(int equipID, boolean newStatus) {
        String sql = "UPDATE grizzly’sentertainmentequipmentrental.equipment " +
                     "SET status = '" + newStatus + "'" +
                     "WHERE equipID = " + equipID + ";";

}
}
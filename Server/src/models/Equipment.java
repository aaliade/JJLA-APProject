package models;

import org.apache.logging.log4j.Logger;

import factories.DBConnectorFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;


public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	private transient static final Logger logger = LogManager.getLogger(Equipment.class);
	private String equipID;
	private String equipName;
	private String description;
	private boolean status;
	private String category;
	private double rentalRate;
	private transient Connection dbConn = null;  //Make transient to avoid serialization error
	private transient Statement stmt = null;
	private transient ResultSet result = null;

	public Equipment() {
		equipID = "";
		equipName = "";
		description = "";
		status = true;
		category = "";
		rentalRate = 0;
		logger.info("Equipment initialized");
		this.dbConn = DBConnectorFactory.getDatabaseConnection();
	}
	

	public Equipment(String equipID, String equipName, String description, boolean status, String category, double rentalRate) {
		this.equipID = equipID;
		this.equipName = equipName;
		this.description = description;
		this.status = status;
		this.category = category;
		this.rentalRate = rentalRate;
		logger.info("Input accepted, Equipment initialized");
	}
	
	public String getequipID() {
		logger.info("Equipment ID returned");
		return equipID;
	}
	
	public void setequipID(String equipID) {
		this.equipID = equipID;
		logger.info("Input accepted, Equipment ID set");
	}
	
	public String getequipName() {
		logger.info("Equipment Name returned");
		return equipName;
	}
	
	public void setequipName(String equipName) {
		this.equipName = equipName;
		logger.info("Input accepted, Equipment Name set");
	}
	
	public String getdescription() {
		logger.info("Equipment Description returned");
		return description;
	}
	
	public void setdescription(String description) {
		this.description = description;
		logger.info("Input accepted, Equipment Description set");
	}

	public boolean getstatus() {
		logger.info("Equipment Status returned");
		return status;
	}

	public void setstatus(boolean status) {
		this.status = status;
		logger.info("Input accepted, Equipment Status set");
	}
	
	public String getCategory() {
		logger.info("Equipment Status returned");
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		logger.info("Input accepted, Equipment Status set");
	}

	public double getrentalRate() {
		logger.info("Equipment Rental Rate returned");
		return rentalRate;
	}

	public void setrentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
		logger.info("Input accepted, Equipment Rental Rate set");
	}
	
	@Override
	public String toString() {
		logger.info("Equipment information returned");
		return "Equipment ID" + equipID + "Equipment Name" + equipName + "Description" + description + "Status" + status + "Rental Rate" + rentalRate;	
	}
	
	public Equipment[] selectAll() {
        String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.equipment;";
        Equipment[] equipmentList = null;
        try {
        	stmt = dbConn.createStatement();
            result = stmt.executeQuery(sql);
            int count = 0;
            while(result.next()) {
            	count++;
            }
            
            result.close();
            result = stmt.executeQuery(sql);
            equipmentList = new  Equipment[count];
            int i = 0;
            while (result.next()) {
                    String equipID = result.getString("equipID");
                    String equipName = result.getString("equipName");
                    String description = result.getString("description");
                    String category = result.getString("category");
                    boolean status = result.getBoolean("status");
                    double rentalRate = result.getDouble("rentalRate");

                    System.out.println("Equipment ID: " + equipID + "\nEquipment Name: " + equipName +
                            "\nDescription: " + description + "\nStatus: " + status + "\nCategory: " + category + "\nRental Rate: " + rentalRate + "\n");
                    equipmentList[i] = new Equipment(equipID, equipName,description,status, category, rentalRate); //initialize object
                    i++;
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
        return equipmentList;
    }

	public void selectAvailableEquipmentByCategory(String category) {
		String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.equipment WHERE category = '" + category + "';";

	    try {
	        stmt = dbConn.createStatement();
	        result = stmt.executeQuery(sql);

	        while (result.next()) {
                int equipID = result.getInt("equipID");
                String equipName = result.getString("equipName");
                String description = result.getString("description");
                boolean status = result.getBoolean("status");
                String category1 = result.getString("catgeory");
                double rentalRate = result.getDouble("rentalRate");

                System.out.println("Equipment ID: " + equipID + "\nEquipment Name: " + equipName +
                        "\nDescription: " + description + "\nStatus: " + status + "\nCategory: " + category1 + "\nRental Rate: " + rentalRate + "\n");
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
	
	public void insert(int equipID, String equipName, String description, boolean status, String category, int rentalRate) {
		String sql = "INSERT INTO grizzly’sentertainmentequipmentrental.equipment (equipID, equipName, description, status, category, rentalRate)"
	            + "VALUES ('" + equipID + "', '" + equipName + "', '" + description + "', '" + status + "', '" + category + "', '" + rentalRate + "');";

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

	public void update(String UNDECIDED) {
		String sql = "UPDATE grizzly’sentertainmentequipmentrental.equipment " + "SET --- = '" + UNDECIDED + "'" + " WHERE UNDECIDED = '" + UNDECIDED+ "'";

		try {
			stmt = dbConn.createStatement();
			int updated = stmt.executeUpdate(sql);
			
	        if (updated == 1) {
	            JOptionPane.showMessageDialog(null, "Equipment Record Updated Successfully!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
	            logger.info("Equipment Record (Column: " + UNDECIDED + ", Condition: " + UNDECIDED + ") Updated Successfully");
	        } else {
	            JOptionPane.showMessageDialog(null, "Equipment Record Update Failed.", "Update Status", JOptionPane.ERROR_MESSAGE);
	            logger.error("Equipment Record (Column: " + UNDECIDED + ", Condition: " + UNDECIDED + ") Update Failed");
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL Exception: " + e.getMessage());
	        logger.error("SQL Exception while updating Event Record (Column: " + UNDECIDED + ", Condition: " + UNDECIDED + "): " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("Unexpected Error: " + e.getMessage());
	        logger.error("Unexpected Error while updating Event Record (Column: " + UNDECIDED + ", Condition: " + UNDECIDED + "): " + e.getMessage());
	    } finally {
	        try {
	            stmt.close();
	        } catch (SQLException e) {
	            System.err.println("Error while closing statement: " + e.getMessage());
	            logger.error("Error while closing statement: " + e.getMessage());
	        }
	    }
	}

	public void delete(int equipId) {
		String sql = "DELETE FROM grizzly’sentertainmentequipmentrental.equipment WHERE equipID = " + equipId + ";";

		try {
			stmt = dbConn.createStatement();
			int deleted = stmt.executeUpdate(sql);
			if (deleted == 1) {
				JOptionPane.showMessageDialog(null, "Equipment Record Deleted!", "Deletion Status",
						JOptionPane.INFORMATION_MESSAGE);
				logger.info("Equipment Record (ID: " + equipId + ") Deleted");
			} else {
				JOptionPane.showMessageDialog(null, "Equipment Record Deletion Failed.", "Deletion Status",
						JOptionPane.ERROR_MESSAGE);
				logger.error("Equipment Record (ID: " + equipId + ") Deletion Failed");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
	        logger.error("SQL Exception while deleting Event Record (ID: " + equipId + "): " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
	        logger.error("Unexpected Error while deleting Event Record (ID: " + equipId + "): " + e.getMessage());
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
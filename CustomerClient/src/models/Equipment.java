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
	private transient Connection dbConn = null;  //Make transient to avoid serialization error ignores when sending over to server
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

}
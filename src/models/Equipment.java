package models;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Equipment extends EquipmentCategory{
	
	private int equipID;
	private String equipName;
	private String description;
	private String status;
	private int rentalRate;
	
	private static final Logger logger = LogManager.getLogger(Equipment.class);
	
	public Equipment() {
		equipID = 0;
		equipName = "";
		description = "";
		status = "";
		rentalRate = 0;
		logger.info("Equipment initialized");
	}
	
	public Equipment(int equipID, String equipName, String description, String status, int rentalRate) {
		this.equipID = equipID;
		this.equipName = equipName;
		this.description = description;
		this.status = status;
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

	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
		logger.info("Input accepted, Equipment Status set");
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
		return "Category ID" + categoryID + "Category Name" + categoryName + "Equipment ID" + equipID + 
				"Equipment Name" + equipName + "Description" + description + "Status" + status + "rentalRate" + rentalRate;	
	}
	
	
	

}

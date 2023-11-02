package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EquipmentCategory {
	
	protected int categoryID;
	protected String categoryName;
	
	private static final Logger logger = LogManager.getLogger(EquipmentCategory.class);
	
	public EquipmentCategory() {
		categoryID = 0;
		categoryName = "";
		logger.info("Equipment Category initialized");
	}
	
	public EquipmentCategory(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		logger.info("Input accepted, Equipment Category initialized");
	}
	
	public int getcategoryID() {
		return categoryID;
	}
	
	public void setcategoryID(int categoryID) {
		this.categoryID = categoryID;
		logger.info("Input accepted, Equipment Category ID set");
	}
	
	public String getcategoryName() {
		return categoryName;
	}

	public void setcategoryName(String categoryName) {
		this.categoryName = categoryName;
		logger.info("Input accepted, Equipment Category Name set");
	}
}

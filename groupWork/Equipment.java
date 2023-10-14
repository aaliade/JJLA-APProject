package Classes;

public class Equipment extends EquipmentCategory{
	
	private int equipID;
	private String equipName;
	private String description;
	private String status;
	private int rentalRate;
	
	public Equipment() {
		equipID = 0;
		equipName = "";
		description = "";
		status = "";
		rentalRate = 0;
	}
	
	public Equipment(int equipID, String equipName, String description, String status, int rentalRate) {
		this.equipID = equipID;
		this.equipName = equipName;
		this.description = description;
		this.status = status;
		this.rentalRate = rentalRate;
	}
	
	public int getequipID() {
		return equipID;
	}
	
	public void setequipID(int equipID) {
		this.equipID = equipID;
	}
	
	public String getequipName() {
		return equipName;
	}
	
	public void setequipName(String equipName) {
		this.equipName = equipName;
	}
	
	public String getdescription() {
		return description;
	}
	
	public void setdescription(String description) {
		this.description = description;
	}

	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}

	public int getrentalRate() {
		return rentalRate;
	}

	public void setrentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
	}
	
	@Override
	public String toString() {
		return "Category ID" + categoryID + "Category Name" + categoryName + "Equipment ID" + equipID + 
				"Equipment Name" + equipName + "Description" + description + "Status" + status + "rentalRate" + rentalRate;	
	}
	
	
	

}

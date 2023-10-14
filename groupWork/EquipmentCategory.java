package Classes;

public class EquipmentCategory {
	
	protected int categoryID;
	protected String categoryName;
	
	public EquipmentCategory() {
		categoryID = 0;
		categoryName = "";
	}
	
	public EquipmentCategory(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	
	public int getcategoryID() {
		return categoryID;
	}
	
	public void setcategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
	public String getcategoryName() {
		return categoryName;
	}

	public void setcategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}

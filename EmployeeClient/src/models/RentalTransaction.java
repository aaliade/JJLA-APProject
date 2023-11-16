package models;
import java.util.Date;


public class RentalTransaction {
	private int rentalId;
    private int equipID;
    private Date rentDate;
    private Date returnDate;
    private int custID;
    private double totalCost;

    public RentalTransaction() {
        this.rentalId = 0;
        this.equipID = 0;
        this.rentDate = new Date();
        this.returnDate = new Date(); 
        this.custID = 0;
        this.totalCost = 0.0;
    }
    
    public RentalTransaction(int rentalId, int equipID, Date rentDate, Date returnDate, int custID, double totalCost) {
        this.rentalId = rentalId;
        this.equipID = equipID;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.custID = custID;
        this.totalCost = totalCost;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getEquipID() {
        return equipID;
    }

    public void setEquipID(int equipID) {
        this.equipID = equipID;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
	public String toString() {
		return "Rental ID: " + rentalId +
                ", Equipment ID: " + equipID +
                ", Rent Date: " + rentDate +
                ", Return Date: " + returnDate +
                ", Customer ID: " + custID +
                ", Total Cost: " + totalCost;
	}
	
}


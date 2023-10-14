package groupWork;

public class Payment {
	private int paymentID;
	private String paymentType;
	private Date paymentDate;
	
	
	public Payment() { //defualt constructors
		this.paymentID = 0;
		this.paymentType = "";
		this.paymentDate = new Date(1,1,1000);
	}
	
	public Payment(int paymentID, String paymentType, Date date) { //primary constructors
		this.paymentID = paymentID;
		this.paymentType = paymentType;
		this.paymentDate = date;
	}
	
	//getters and setters 
	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getDate() {
		return paymentDate;
	}

	public void setDate(Date date) {
		this.paymentDate = date;
	}
	
	
	
}

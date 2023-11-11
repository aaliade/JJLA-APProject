package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Payment {
	private int paymentID;
	private String paymentType;
	private Date paymentDate;
	
	private static final Logger logger = LogManager.getLogger(Payment.class);
	
	
	public Payment() { //default constructors
		this.paymentID = 0;
		this.paymentType = "";
		this.paymentDate = new Date(1,1,1000);
		logger.info("Payment initialized");
	}
	
	public Payment(int paymentID, String paymentType, Date date) { //primary constructors
		this.paymentID = paymentID;
		this.paymentType = paymentType;
		this.paymentDate = date;
		logger.info("Input accepted, Payment initialized");
	}
	
	//getters and setters 
	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
		logger.info("Input accepted, Payment ID initialized");
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
		logger.info("Input accepted, Payment type initialized");
	}

	public Date getDate() {
		return paymentDate;
	}

	public void setDate(Date date) {
		this.paymentDate = date;
		logger.info("Input accepted, Payment Date initialized");
	}
	
}

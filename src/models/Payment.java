package models;
 
import java.util.Date;

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
		logger.info("Payment ID returned");
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
		logger.info("Input accepted, Payment ID initialized");
	}

	public String getPaymentType() {
		logger.info("Payment Type returned");
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
		logger.info("Input accepted, Payment type initialized");
	}

	public Date getDate() {
		logger.info("Payment Date returned");
		return paymentDate;
	}

	public void setDate(Date date) {
		this.paymentDate = date;
		logger.info("Input accepted, Payment Date initialized");
	}
	

	@Override
	public String toString() {
		logger.info("Payment Information returned");
		return "Payment ID: " + paymentID + "Payment Type: " + paymentType + "Date: " + paymentDate;
	}
	
}

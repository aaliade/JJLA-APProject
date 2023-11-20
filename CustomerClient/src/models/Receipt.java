package models;

import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Receipt implements Serializable{

	private static final long serialVersionUID = 1L;
	private String receiptNum;
	private String payType;
	private Date payDate;
	private double payAmt;
	private String customerID;
	private String equipMentCode;

	private static final Logger logger = LogManager.getLogger(Receipt.class);

	public Receipt() {
		this.receiptNum = "";
		this.payType = "";
		this.payDate = new Date();
		this.payAmt = 0.0;
		this.customerID = "";
		this.equipMentCode = "";
		logger.info("Receipt initialized");
	}

	public Receipt(String receiptNum, String payType, Date payDate, double payAmt, String customerID, String EqupID) {
		this.receiptNum = receiptNum;
		this.payType = payType;
		this.payDate = payDate;
		this.payAmt = payAmt;
		this.customerID = customerID;
		this.equipMentCode = EqupID;
		logger.info("Input accepted, Receipt initialized");
	}
	
	public void setCustID(String id) {
		this.customerID = "";
	}

	public String getCustID() {
		return customerID;
	}
	
	public void setEquipCode(String id) {
		this.equipMentCode = "";
	}

	public String getEquipCode() {
		return equipMentCode;
	}

	public String getReceiptNum() {
		return receiptNum;
	}

	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(double payAmt) {
		this.payAmt = payAmt;
	}

	@Override
	public String toString() {
		logger.info("Receipt Information returned");
		return "Receipt Number: " + receiptNum + " | Payment Type: " + payType + " | Payment Date: " + payDate
				+ " | Payment Amount: " + payAmt;
	}
}
package models;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Receipt {
	private String receiptNum;
	private String payType;
	private Date payDate;
	private double payAmt;

	private static final Logger logger = LogManager.getLogger(Receipt.class);

	public Receipt() {
		this.receiptNum = "";
		this.payType = "";
		this.payDate = new Date();
		this.payAmt = 0.0;
		logger.info("Receipt initialized");
	}

	public Receipt(String receiptNum, String payType, Date payDate, double payAmt) {
		this.receiptNum = receiptNum;
		this.payType = payType;
		this.payDate = payDate;
		this.payAmt = payAmt;
		logger.info("Input accepted, Receipt initialized");
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
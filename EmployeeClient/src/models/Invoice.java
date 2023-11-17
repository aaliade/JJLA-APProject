package models;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Invoice {
    private int invoiceNum;
    private int custID;
    private Date rentDate;
    private Date returnDate;
    private double cost;

    private static final Logger logger = LogManager.getLogger(Invoice.class);

    public Invoice() {
        this.invoiceNum = 0;
        this.custID = 0;
        this.rentDate = new Date();
        this.returnDate = new Date();
        this.cost = 0.0;
        logger.info("Invoice initialized");
    }

    public Invoice(int invoiceNum, int custID, Date rentDate, Date returnDate, double cost) {
        this.invoiceNum = invoiceNum;
        this.custID = custID;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.cost = cost;
        logger.info("Input accepted, Invoice initialized");
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        logger.info("Invoice Information returned");
        return "Invoice Number: " + invoiceNum + " | Customer ID: " + custID + " | Rent Date: " + rentDate
                + " | Return Date: " + returnDate + " | Cost: " + cost;
    }
}

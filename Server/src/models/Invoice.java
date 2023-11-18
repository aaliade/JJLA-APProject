package models;

import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factories.DBConnectorFactory;

public class Invoice {
    private String invoiceNum;
    private String custID;
    private Date rentDate;
    private Date returnDate;
    private double cost;
    private Connection dbConn = null;
    private Statement stmt = null;
    private ResultSet result = null;

    private static final Logger logger = LogManager.getLogger(Invoice.class);

    public Invoice() {
        this.invoiceNum = "";
        this.custID = "";
        this.rentDate = new Date();
        this.returnDate = new Date();
        this.cost = 0.0;
        logger.info("Invoice initialized");
        this.dbConn = DBConnectorFactory.getDatabaseConnection();
    }

    public Invoice(String invoiceNum, String custID, Date rentDate, Date returnDate, double cost) {
        this.invoiceNum = invoiceNum;
        this.custID = custID;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.cost = cost;
        logger.info("Input accepted, Invoice initialized");
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
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
    
    public Invoice[] selectAllInvoices() {
    	String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.invoice;";
        Invoice[] invoiceList = null;
        try {
        	stmt = dbConn.createStatement();
            result = stmt.executeQuery(sql);
            int count = 0;
            while(result.next()) {
            	count++;
            }
            result.close();
            result = stmt.executeQuery(sql);
            invoiceList = new Invoice[count];
            int i = 0;
            while (result.next()) {
            	String invoiceNum = result.getString("invoiceNum");
                String custID = result.getString("custID");
                Date rentDate = result.getDate("rentDate");
                Date returnDate = result.getDate("returnDate");
                double cost = result.getDouble("cost");

                System.out.println("Invoice Number: " + invoiceNum + "\nCustomer ID: " + custID +
                        "\nRent Date: " + rentDate + "\nReturn Date: " + returnDate + "\nCost: " + cost + "\n");
                    invoiceList[i] = new Invoice(invoiceNum, custID, rentDate, returnDate, cost); //initialize object
                    i++;
                }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while selecting invoices: " + e.getMessage());
        } finally {
            try {
                stmt.close();
                result.close();
            } catch (SQLException e) {
                System.err.println("Error while closing statement/result: " + e.getMessage());
                logger.error("Error while closing statement/result: " + e.getMessage());
            }
        }
        return invoiceList;
    }

    public Invoice[] selectInvoiceByInvoiceNum(String invoiceNum) {
        String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.invoice WHERE invoiceNum = " + invoiceNum + ";";
        Invoice[] invoiceList = null;
        try {
            stmt = dbConn.createStatement();
            result = stmt.executeQuery(sql);
            int count = 0;
            while(result.next()) {
            	count++;
            }
            
            result.close();
            result = stmt.executeQuery(sql);
            invoiceList = new Invoice[count];
            int i = 0;
            
            while (result.next()) {
                String invoiceNumResult = result.getString("invoiceNum");
                String custID = result.getString("custID");
                Date rentDate = result.getDate("rentDate");
                Date returnDate = result.getDate("returnDate");
                double cost = result.getDouble("cost");

                System.out.println("Invoice Number: " + invoiceNumResult + "\nCustomer ID: " + custID +
                        "\nRent Date: " + rentDate + "\nReturn Date: " + returnDate + "\nCost: " + cost + "\n");
                invoiceList[i] = new Invoice(invoiceNum, custID, rentDate, returnDate, cost); //initialize object
                i++;
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while selecting invoices by invoice number: " + e.getMessage());
        } finally {
        	try {
				stmt.close();
				result.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
		return invoiceList;
    }
    
    public void insertInvoice(String invoiceNum, String custID, Date rentDate, Date returnDate, double cost) {
        String sql = "INSERT INTO grizzly’sentertainmentequipmentrental.invoice "
                + "(invoiceNum, custID, rentDate, returnDate, cost) VALUES "
                + "(" + invoiceNum + ", " + custID + ", '" + rentDate + "', '" + returnDate + "', " + cost + ");";
        
        try {
            stmt = dbConn.createStatement();
            int inserted = stmt.executeUpdate(sql);

            if (inserted == 1) {
                JOptionPane.showMessageDialog(null, "Invoice Record Inserted Successfully!", "Insert Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Invoice Record Inserted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Invoice Record Insertion Failed.", "Insert Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Invoice Record Insertion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while inserting Invoice Record (Number: " + invoiceNum + "): " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }

    public void updateInvoice(String invoiceNum, double newCost) {
        String sql = "UPDATE grizzly’sentertainmentequipmentrental.invoice "
                + "SET cost = " + newCost + " WHERE invoiceNum = " + invoiceNum + ";";

        try {
            stmt = dbConn.createStatement();
            int updated = stmt.executeUpdate(sql);

            if (updated == 1) {
                JOptionPane.showMessageDialog(null, "Invoice Record Updated Successfully!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Invoice Record (Number: " + invoiceNum + ") Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Invoice Record Update Failed.", "Update Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Invoice Record (Number: " + invoiceNum + ") Update Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while updating Invoice Record (Number: " + invoiceNum + "): " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }

    public void deleteInvoice(String invoiceNum) {
        String sql = "DELETE FROM grizzly’sentertainmentequipmentrental.invoice WHERE invoiceNum = " + invoiceNum + ";";

        try {
            stmt = dbConn.createStatement();
            int deleted = stmt.executeUpdate(sql);

            if (deleted == 1) {
                JOptionPane.showMessageDialog(null, "Invoice Record Deleted Successfully!", "Delete Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Invoice Record (Number: " + invoiceNum + ") Deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Invoice Record Deletion Failed.", "Delete Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Invoice Record (Number: " + invoiceNum + ") Deletion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while deleting Invoice Record (Number: " + invoiceNum + "): " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }
}

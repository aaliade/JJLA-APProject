package models;

import java.sql.Connection;
import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factories.DBConnectorFactory;

public class Receipt {
    private int receiptNum;
    private String payType;
    private Date payDate;
    private double payAmt;
    private Connection dbConn = null;
    private Statement stmt = null;
    private ResultSet result = null;

    private static final Logger logger = LogManager.getLogger(Receipt.class);

    public Receipt() {
        this.receiptNum = 0;
        this.payType = "";
        this.payDate = new Date();
        this.payAmt = 0.0;
        logger.info("Receipt initialized");
        this.dbConn = DBConnectorFactory.getDatabaseConnection();
    }

    public Receipt(int receiptNum, String payType, Date payDate, double payAmt) {
        this.receiptNum = receiptNum;
        this.payType = payType;
        this.payDate = payDate;
        this.payAmt = payAmt;
        logger.info("Input accepted, Receipt initialized");
    }

    public int getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(int receiptNum) {
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
    
    public void selectAllReceipts() {
        String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.receipt;";

        try {
            stmt = dbConn.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
                int receiptNum = result.getInt("receiptNum");
                String payType = result.getString("payType");
                Date payDate = result.getDate("payDate");
                double payAmt = result.getDouble("payAmt");

                System.out.println("Receipt Number: " + receiptNum + "\nPayment Type: " + payType +
                        "\nPayment Date: " + payDate + "\nPayment Amount: " + payAmt + "\n");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while selecting receipts: " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }
    
    public void selectReceiptByReceiptID(int receiptNum) {
        String sql = "SELECT * FROM grizzly’sentertainmentequipmentrental.receipt WHERE receiptNum = " + receiptNum + ";";

        try {
            stmt = dbConn.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
                int receiptNumResult = result.getInt("receiptNum");
                String payType = result.getString("payType");
                Date payDate = result.getDate("payDate");
                double payAmt = result.getDouble("payAmt");

                System.out.println("Receipt Number: " + receiptNumResult + "\nPayment Type: " + payType +
                        "\nPayment Date: " + payDate + "\nPayment Amount: " + payAmt + "\n");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while selecting receipts by receipt number: " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }
    
    public void insertReceipt(String payType, Date payDate, double payAmt) {
        String sql = "INSERT INTO grizzly’sentertainmentequipmentrental.receipt (payType, payDate, payAmt) VALUES "
                + "('" + payType + "', '" + payDate + "', " + payAmt + ");";

        try {
            stmt = dbConn.createStatement();
            int inserted = stmt.executeUpdate(sql);

            if (inserted == 1) {
                JOptionPane.showMessageDialog(null, "Receipt Record Inserted Successfully!", "Insert Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Receipt Record Inserted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Receipt Record Insertion Failed.", "Insert Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Receipt Record Insertion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while inserting Receipt Record: " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }

    public void updateReceipt(int receiptNum, double newPayAmt) {
        String sql = "UPDATE grizzly’sentertainmentequipmentrental.receipt SET payAmt = " + newPayAmt + " WHERE receiptNum = " + receiptNum + ";";

        try {
            stmt = dbConn.createStatement();
            int updated = stmt.executeUpdate(sql);

            if (updated == 1) {
                JOptionPane.showMessageDialog(null, "Receipt Record Updated Successfully!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Receipt Record (Number: " + receiptNum + ") Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Receipt Record Update Failed.", "Update Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Receipt Record (Number: " + receiptNum + ") Update Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while updating Receipt Record (Number: " + receiptNum + "): " + e.getMessage());
        } finally {
        	try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error while closing statement: " + e.getMessage());	
				logger.error("Error while closing statement: " + e.getMessage());
			}
        }
    }

    public void deleteReceipt(int receiptNum) {
        String sql = "DELETE FROM grizzly’sentertainmentequipmentrental.receipt WHERE receiptNum = " + receiptNum + ";";

        try {
            stmt = dbConn.createStatement();
            int deleted = stmt.executeUpdate(sql);

            if (deleted == 1) {
                JOptionPane.showMessageDialog(null, "Receipt Record Deleted Successfully!", "Delete Status", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Receipt Record (Number: " + receiptNum + ") Deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Receipt Record Deletion Failed.", "Delete Status", JOptionPane.ERROR_MESSAGE);
                logger.error("Receipt Record (Number: " + receiptNum + ") Deletion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.error("SQL Exception while deleting Receipt Record (Number: " + receiptNum + "): " + e.getMessage());
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

package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
  

public class DBConnectorFactory {
	public static final Logger logger = LogManager.getLogger(DBConnectorFactory.class);
	private static Connection dbConn = null;
	
	public DBConnectorFactory(){
		
	}
	
	public static Connection getDatabaseConnection() {
		if (dbConn == null) {
			try {
				//if u have it without password just uncomment
				dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/grizzly’sentertainmentequipmentrental", "root", "usbw");
				//dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzly’sentertainmentequipmentrental", "root", ""); EDITED TO 3307
				logger.info("Database Connection Established");
				logger.info("Connection Established");
			} catch (SQLException e) {
                logger.error("SQL Exception while establishing a database connection: " + e.getMessage());
                e.printStackTrace();
            }catch (Exception e) {
                logger.error("Exception while establishing a database connection: " + e.getMessage());
                e.printStackTrace();
            }
		}
		return dbConn;
	}

}

package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnectorFactory {
	private static Connection dbConn = null;
	private static final Logger logger = LogManager.getLogger(DBConnectorFactory.class);
	
	public static Connection getDatabaseConnection() {
		if (dbConn == null) {
			try {
				dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Grizzly’sEntertainmentEquipmentRental", "username", "");
				
				if (dbConn != null) {
					JOptionPane.showMessageDialog(null, "Connection Established", "JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
					logger.info("Connection Established");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("Database Error" + e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("Something went wrong" + e.getMessage());
			}
		}
		return dbConn;
	}
}

package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnectorFactory {
	private static Connection dbConn = null;
	
	public static Connection getDatabaseConnection() {
		if (dbConn == null) {
			try {
				dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Grizzly’sEntertainmentEquipmentRental", "username", "");
				
				if (dbConn != null) {
					JOptionPane.showMessageDialog(null, "Connection Established", "JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dbConn;
	}
}

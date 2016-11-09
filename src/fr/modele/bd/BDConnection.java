package fr.modele.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author JGC
 *
 */
public class BDConnection {
		
	private final String url = "jdbc:mysql://localhost:3306/unknow_world";
	private final String user = "root";
	private final String password = "";

	private Connection connection;
	
	public BDConnection() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Return the connection to a database
	 * 
	 * @return The connection
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Closes the connection
	 */
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

package Dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConector {
	private static Connection conn;
	public static Connection connect(){
		if(conn == null){
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 
		
		try {			
			conn = DriverManager.getConnection( "jdbc:mysql://localhost/_rotinakids", "pavi", "pavi");
			// Do something with the Connection
		} catch (SQLException ex) { 
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		}
		
		return conn;
	}
}
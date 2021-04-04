package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class POItemDao {
	
	Connection con;
	
	public POItemDao() {
		
	}
	
	public void addPOItem(String pid, String bid, double price, int quantity) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format("INSERT INTO poitem(pid, bid, price, quantity) values('%s', '%s', '%s', '%s')",pid, bid, price, quantity);
			stmt.executeUpdate(query);  
			System.out.println(bid + " added to poitem db");
			con.close();
			} catch(Exception e) {
				System.out.println(bid + " not added to poitem database: " + e);	
			}	
	}

}

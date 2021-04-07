package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class POItemDao {
	
	Connection con;
	
	public POItemDao() {
		
	}
	
	// TODO: This could be sped up by taking cart as a parameter so only one connection needs to be opened
	public void addPOItem(String pid, String bid, double price, int quantity, Date transactionDate) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format("INSERT INTO poitem(pid, bid, price, quantity, transaction_date) values('%s', '%s', '%s', '%s', '%s')",pid, bid, price, quantity, transactionDate);
			stmt.executeUpdate(query);  
			System.out.println("Book with bid: " + bid + " added to poitem db with pid: " + pid);
			con.close();
			} catch(Exception e) {
				System.out.println(bid + " not added to poitem database: " + e);	
		}	
	}
}

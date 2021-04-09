package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.BookBean;


public class PODao {
	Connection con;

	public PODao() {

	
	}
	
	public void addAddress(String street, String province, String country, String zip, String phone) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format("INSERT INTO address(street, province, country, zip, phone) values('%s', '%s', '%s', '%s', '%s')",street, province, country, zip, phone);
			stmt.executeUpdate(query);  
			System.out.println("\n" + street + " added to address db");
			con.close();
			} catch(Exception e) {
				System.out.println("\nAddress " + street + " not added, already exists in db.");	
			}	
	}
	
	public int getAid(String street) {
		try {
			int aid = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format("SELECT aid FROM address WHERE street='%s'", street);
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				aid = Integer.parseInt(rs.getString("aid"));
			}
			con.close();
			return aid;
		} catch(Exception e) {
			System.out.println("\nCould not get aid for " + street + ": " + e);
		}
		return 0;
	}
	
	public String addPO(String email, String lname, String fname, Enum status, int aid) {
		String pid = "";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			
			String query = String.format("INSERT INTO po(email, lname, fname, status, address) values('%s', '%s', '%s', '%s', '%s')", email, lname, fname, status, aid);
			stmt.executeUpdate(query); 
			query = String.format("SELECT LAST_INSERT_ID();");
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				pid = rs.getString("last_insert_id()");
			}
			con.close();
			System.out.println("\nPurchase order with pid " + pid + " for " + email + " entered into db!");
			
			} catch(Exception e) {
				System.out.println("\nPurchase order not added: " + e);	
			}	
		return pid;
	}
	

}

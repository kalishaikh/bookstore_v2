package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.addressBean;

public class PODao {
	Connection con;

	public PODao() {
	/*
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
				System.out.println("Connected to heroku...");
			} catch (Exception e) {
				e.printStackTrace();
			} */
	
	}
	
	public void addAddress(String street, String province, String country, String zip, String phone) {
		// TODO: Address verification. Address previously entered but with different zip/phone #?? 
		// Currently address must be unique. 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format("INSERT INTO address(street, province, country, zip, phone) values('%s', '%s', '%s', '%s', '%s')",street, province, country, zip, phone);
			stmt.executeUpdate(query);  
			System.out.println(street + " added to address db");
			con.close();
			} catch(Exception e) {
				System.out.println(street + " not added to database: " + e);	
			}	
	}
	
	public int getAid(String street) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format("SELECT aid FROM address WHERE street='%s'", street);
			ResultSet rs = stmt.executeQuery(query);
			con.close();
			while (rs.next()) {
				System.out.println(rs.getString("aid"));
				return Integer.parseInt(rs.getString("aid"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addPO(String email, String lname, String fname, Enum status, int aid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			
			String query = String.format("INSERT INTO po(email, lname, fname, status, address) values('%s', '%s', '%s', '%s', '%s')", email, lname, fname, status, aid);
			stmt.executeUpdate(query); 
			con.close();
			} catch(Exception e) {
				e.printStackTrace();		
			}	
	}
	
	// Not used for anything right now. 
	public addressBean getAddressBean(int aid) {
		String street, province, country, zip, phone;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format("SELECT * FROM address WHERE aid='%s'", Integer.toString(aid));
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				street = rs.getString("street");
				province = rs.getString("province");
				country = rs.getString("country");
				zip = rs.getString("zip");
				phone = rs.getString("phone");
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeCon() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

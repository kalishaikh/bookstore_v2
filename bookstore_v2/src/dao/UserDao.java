package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * This class creates a data access for users. This DAO will create a user if a user is not already registered and it will authenticate
 * a user that is already in the database.
 */
public class UserDao {

	public static void main (String[] args) {
		
		/*
		 * Register a user
		 */
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to heroku...");
			ResultSet rs=stmt.executeQuery("INSERT INTO users ");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
			con.close();
			} catch(Exception e) {
				System.out.println(e);
				
			}
	}
}

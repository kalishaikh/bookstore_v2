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

	/*
	 *Creates a connection with the MySQL server hosted on Heroku. Inputs the parameters into the database.
	 *If an e-mail has already been registered with the database an error code of 100 is thrown.
	 *
	 * @param fname : First name of the new user
	 * @param lnam : Last name of the new user
	 * @param email : Email address of the new user
	 * @param pass : Password of the user
	 * @return 0 : Successfully registered a user with the database
	 * @return 100 : Email has already been taken
	 * 
	 */

	public int register(String fname, String lname, String pass, String email) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to Heroku...");
			String query = String.format("INSERT INTO users(fname, lname, pass, email) values('%s', '%s', '%s', '%s')",fname,lname,pass,email);
			stmt.executeUpdate(query);  
			con.close();
			} catch(Exception e) {
				System.out.println(e);
				return 100;
				
			}
		return 0;
	}
	
	
	public String login(String email, String pass) {
		
		String name = "";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to Heroku...");
			String query = String.format("SELECT fname FROM users WHERE email = '%s' AND pass = '%s'",email,pass);
			System.out.println("querey is " + query);
			ResultSet set = stmt.executeQuery(query);
			while(set.next())  
				System.out.println(set.getString(0));  
			con.close();
			} catch(Exception e) {
				System.out.println(e);
				return "100";
				
			}
		return name;
		
	}
}

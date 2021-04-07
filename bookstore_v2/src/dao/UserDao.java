package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.security.crypto.bcrypt.BCrypt;

import bean.BookBean;

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
	
	/*
	 * After authenticating using the getCredentials() method the login() method retrieves the name of the user whose email has been validated. The name is sent back to the controller
	 * so that it may be stored in the session variable to be displayed on the website.
	 * 
	 * @return : Name of a registered user queried by email address.
	 * 
	 */
	public String loginName(String email) {
		
		String name = "";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to Heroku...\nRetrieving first name");
			String query = String.format("SELECT fname FROM users WHERE email = '%s'",email);
			ResultSet set = stmt.executeQuery(query);
			if(set.next())  
				System.out.println("Name relating to email is " + set.getString(1));
				name = set.getString(1);
			con.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		return name;
		
	}
	
	/*
	 * Checks password against what is stored in the database using BCrypt salt. A query will call using an email and return 0
	 * only if the password matches the BCrypt hashing algorithm.
	 * 
	 * @param pass : Password to be checked against the database
	 * @param email : Email to be checked against the database
	 * @return 0 : If password matches what is in the database
	 * @return 100 : If there is no email that matches the email input
	 * @return 200 : If the password the user entered does not match what is in the database
	 * 
	 */
	
	public String checkCredentials(String email, String pass) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to Heroku...\nChecking if passwords match...");
			String query = String.format("SELECT pass FROM users WHERE email = '%s'",email);
			ResultSet set = stmt.executeQuery(query);
			if(set.next())  
				
				if(BCrypt.checkpw(pass, set.getString(1))) {
						System.out.println("SUCCESS Passwords match!");
						return loginName(email);
				}
				
				else if(!BCrypt.checkpw(pass, set.getString(1))) {
						System.out.println("FAILURE Passwords do not match!");
						return "200";
				}
				
			con.close();
			} catch(Exception e) {
				System.out.println(e);
				
			}
		System.out.println("FAILURE No user in database");
		return "100";
	}
	
	/*
	 * This method connects to the database and returns an ArrayList of BookBean objects. The index of the element in the ArrayList represents at what ranking it has
	 * in terms of quantity sold. In other words index "0" correlates with the most popular book and index "n" is the least popular book.
	 * This method can only be called by an admin user and thus the session should confirm whether or not the person accessing this method is an admin or not.
	 * 
	 * @return ArrayList<BookBean>;
	 */
	
	public ArrayList<BookBean> getMostBooks(){
		
		int counter = 1;
	
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to Heroku...Retrieving Book Quantity...");
			String query = String.format("select book.title, SUM(poitem.quantity) AS Quantity FROM poitem JOIN book ON  poitem.bid = book.bid GROUP BY book.title order by Quantity desc");
			ResultSet set = stmt.executeQuery(query);
			while(set.next() && counter < 11) {
				
				BookBean book = new BookBean();
				/*
				 * set.getInt(1) = Book Name
				 * set.getInt(2) = Quantity
				 */
				book.setTitle(set.getString(1));
				book.setQuantity(set.getInt(2));
				bookList.add(book);
				counter++;
			}
				
			con.close();
			} catch(Exception e) {
				System.out.println(e);
				
			}
		return bookList;
	}

	/*
	 * This method queries the database to return the highest selling genre and organizes them by descending quantity. 
	 * @return ArrayList<BookBean>
	 */
	public ArrayList<BookBean> getBestSellingGenre(){
		
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to Heroku...Retrieving Best Selling Genre...");
			String query = String.format("select book.category, SUM(poitem.quantity) AS Quantity FROM poitem JOIN book ON  poitem.bid = book.bid GROUP BY book.category order by Quantity desc");
			ResultSet set = stmt.executeQuery(query);
			while(set.next()) {
				BookBean book = new BookBean();
				book.setCategory(set.getString(1));
				book.setQuantity(set.getInt(2));
				/*
				 * set.getInt(1) = Book Id
				 * set.getInt(2) = Quantity
				 */
				bookList.add(book);
	
			}
				
			con.close();
			} catch(Exception e) {
				System.out.println(e);
				
			}
		
		return bookList;
	}
	
	/*
	 * This method returns a map of countries that have purchased the most amount of books sorting by descending quantity. 
	 * @return LinkedHashMap<String,Integer>
	 * 
	 */
	
	public LinkedHashMap<String,Integer> getCountries(){
		
		LinkedHashMap<String,Integer> map = new LinkedHashMap<String, Integer>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
			Statement stmt=con.createStatement();
			System.out.println("Connected to Heroku...Retrieving List of Countries...");
			String query = String.format("Select country, count(country) AS Registered from address GROUP BY country ORDER BY Registered desc");
			ResultSet set = stmt.executeQuery(query);
			while(set.next()) {
				/*
				 * set.getInt(1) = Country Name
				 * set.getInt(2) = Count
				 */
				map.put(set.getString(1),set.getInt(2));
	
			}
				
			con.close();
			} catch(Exception e) {
				System.out.println(e);
				
			}
		
		return map;
	}		
		
		
	}



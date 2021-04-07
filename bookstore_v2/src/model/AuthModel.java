package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dao.UserDao;
import org.springframework.security.crypto.bcrypt.BCrypt;

/*
 * Creates instances of classes that pertain to LOGIN and REGISTRATION.
 */
public class AuthModel {

	public UserDao userCon;
	
	/*
	 * Constructor must create a new instance of a UserDao
	 */
	public AuthModel() {
		
		userCon = new UserDao();
	}
	
	/*
	 * Calls the register method in the UserDao class. UserDao will create a connection with the database
	 * and register a new user if no e-mail exists.
	 * 
	 * @param fname : First name of the new user
	 * @param lnam : Last name of the new user
	 * @param email : Email address of the new user
	 * @param pass : Password of the user
	 * @return 0 : Successfully registered a user with the database
	 * @return 100 : Email has already been taken
	 * 
	 */
	public int registerUser(String fname, String pass, String lname, String email) {
		
		return userCon.register(fname, lname, pass, email);
		
	}
	
	/*
	 * Calls the login method in the UserDao class. UserDao will create a connection with the database
	 * and login the user if they are registered with the databse.
	 * @param email : Email address of the new user
	 * @param pass : Password of the user
	 * @return "name" : If login is successful return the name of the client
	 * @return 100 : Email is not registered
	 * 
	 */
	
	public String loginUser(String email, String pass) {
		
		return userCon.checkCredentials(email, pass);
	}
	
	public String bcrypt(String password) {
		
		String hashedPW = BCrypt.hashpw(password, BCrypt.gensalt(10));
		System.out.println(hashedPW);
		return hashedPW;
		
	}
	
	public LinkedHashMap<Integer,Integer> getMostBooks() {
		
		return userCon.getMostBooks();
	}
	
	public LinkedHashMap<String,Integer> getBestGenres(){
		
		return userCon.getBestSellingGenre();
	}
}

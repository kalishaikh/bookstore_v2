package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UserDao;

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
	
	public String md5Hash(String password) {
		
		String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        
        return generatedPassword;
	}
}

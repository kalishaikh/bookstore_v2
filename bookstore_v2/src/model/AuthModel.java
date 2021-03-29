package model;

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
	 * Registers a user with the Heroku Database. Client must provide the following:
	 * @param fname : First name of the new user
	 * @param lnam : Last name of the new user
	 * @param email : Email address of the new user
	 * @param pass : Password of the user
	 * @return 0 : Successfully registered a user with the database
	 * @return 100 : Email has already been taken
	 */
	public int registerUser(String fname, String pass, String lname, String email) {
	
		return userCon.register(fname, lname, pass, email);
		
	}
}

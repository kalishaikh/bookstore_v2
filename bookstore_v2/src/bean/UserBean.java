package bean;

public class UserBean {

	private String password,fname,lname,email,userType;
	private double amtSpent;

	public double getAmtSpent() {
		return amtSpent;
	}

	public void setAmtSpent(double amtSpent) {
		this.amtSpent = amtSpent;
	}

	public UserBean(String password, String fname, String lname, String email, String userType) {
		super();
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.userType = userType;
	}
	
	//Empty Constructor
	public UserBean() {
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}

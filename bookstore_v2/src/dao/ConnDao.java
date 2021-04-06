package dao;

import java.sql.*;

public class ConnDao {

	public static void main (String [] args) {
		
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce", "bbb09bc37f79b0", "7c9226ac");
		Statement stmt=con.createStatement();
		System.out.println("connected");
		ResultSet rs=stmt.executeQuery("select * from test");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
		
		rs.close();
		stmt.close();
		con.close();
		
		
		} catch(Exception e) {
			System.out.println(e);
			
		}
	}
}

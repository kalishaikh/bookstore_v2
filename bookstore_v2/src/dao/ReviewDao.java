package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import bean.ReviewBean;

public class ReviewDao {
	
	String url = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce";
	String user = "bbb09bc37f79b0";
	String password = "7c9226ac";

	public ReviewDao() {
		
	}
	
	public int insertReview (ReviewBean rev) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for insertReview in review");
		
		String insertq = "insert into review (bid,name,title,rate,content) values ('"+rev.getBid()+"','"+rev.getName()+"','"+rev.getTitle()+"','"+rev.getRate()+"','"+rev.getContent()+"')";
		
		return stmt.executeUpdate(insertq);
	}
	
	public  ArrayList<ReviewBean> retrieveReviews(int bid) throws ClassNotFoundException, SQLException {
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>(); 
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for retrieveReviews in review");
		
		ResultSet rs=stmt.executeQuery("select * from review where bid = "+bid);  
		while(rs.next()) {
			int id = rs.getInt("bid");
			String name = rs.getString("name");
			String title = rs.getString("title");
			int rate = rs.getInt("rate");
			String content = rs.getString("content");
			reviews.add(new ReviewBean(id, name, title, rate, content));
		}
		  
		
		rs.close();
		stmt.close();
		con.close();
		
		return reviews;
	}
	
	public int overallRateBook(int bid) throws ClassNotFoundException, SQLException {
		int averageRate = 0;
		int sumRate = 0;
		int counter = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for overallRateBook in review");
		
		ResultSet rs=stmt.executeQuery("select rate from review where bid = "+bid);  
		while(rs.next()) {
			sumRate += rs.getInt("rate");
			counter++;
		}
		  
		averageRate = sumRate/counter;
		
		rs.close();
		stmt.close();
		con.close();
		
		
		return averageRate;
	}


}

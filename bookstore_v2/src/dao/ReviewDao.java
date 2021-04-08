package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;

import bean.ReviewBean;

public class ReviewDao {
	
	String url = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce";
	String user = "bbb09bc37f79b0";
	String password = "7c9226ac";

	public ReviewDao() {
		
	}
	
	//add review to database
	public int insertReview (ReviewBean rev) throws SQLException, ClassNotFoundException {
		
		String title = rev.getTitle().replace("'", "''").replace('"', '\"');
		String content = rev.getContent().replace("'", "''").replace('"', '\"');
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for insertReview in review");
		
		String insertq = "insert into review (bid,name,title,rate,content,date) values ('"+rev.getBid()+"','"+rev.getName()+"','"+title+"','"+rev.getRate()+"','"+content+"','"+rev.getDate()+"')";
		
		return stmt.executeUpdate(insertq);
	}
	
	//get all reviews for a book
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
			Date date = rs.getDate("date");
			reviews.add(new ReviewBean(id, name, title, rate, content, date));
		}
		  
		Collections.reverse(reviews);
		rs.close();
		stmt.close();
		con.close();
		
		return reviews;
	}
	
	//Returns both the overall average rating of the book and the number of reviews
	public double[] overallRateBook(int bid) throws ClassNotFoundException, SQLException {
		double[] result = new double[2];
		double averageRate = 0;
		double sumRate = 0;
		double counter = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for overallRateBook in review");
		
		ResultSet rs=stmt.executeQuery("select rate from review where bid = "+bid);  
		while(rs.next()) {
			sumRate += rs.getDouble("rate");
			counter++;
		}
		if(counter != 0)  
			averageRate = sumRate/counter;
		
		result[0] = averageRate;
		result[1] = counter;
		
		rs.close();
		stmt.close();
		con.close();
				
		return result;
	}

}

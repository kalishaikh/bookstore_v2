package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.BookBean;
import bean.POItemBean;

public class POItemDao {

	Connection con;

	public POItemDao() {

	}

	// TODO: This could be sped up by taking cart as a parameter so only one
	// connection needs to be opened
	public void addPOItem(String pid, String bid, double price, int quantity, Date transactionDate) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce",
					"bbb09bc37f79b0", "7c9226ac");
			Statement stmt = con.createStatement();
			String query = String.format(
					"INSERT INTO poitem(pid, bid, price, quantity, transaction_date) values('%s', '%s', '%s', '%s', '%s')",
					pid, bid, price, quantity, transactionDate);
			stmt.executeUpdate(query);
			System.out.println("Book with bid: " + bid + " added to poitem db with pid: " + pid);
			con.close();
		} catch (Exception e) {
			System.out.println(bid + " not added to poitem database: " + e);
		}
	}

	public ArrayList<POItemBean> retrieveOrders(int bid) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce",
				"bbb09bc37f79b0", "7c9226ac");

		String query = "select * from poitem where bid=" + bid;

		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();

		ArrayList<POItemBean> arr = new ArrayList<POItemBean>();
		POItemBean poi;
		while (r.next()) {
			int pid = r.getInt("pid");
			int quantity = r.getInt("quantity");
			double price = r.getDouble("price");
			String transaction_date = r.getString("transaction_date");
			
			poi = new POItemBean(bid, pid, price, quantity, transaction_date);
			arr.add(poi);
		}

		r.close();
		p.close();
		con.close();

		return arr;

	}

}

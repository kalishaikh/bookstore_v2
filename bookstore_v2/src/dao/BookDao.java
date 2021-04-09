package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import bean.BookBean;

public class BookDao {
	
	String url = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_e71303011de1bce";
	String user = "bbb09bc37f79b0";
	String password = "7c9226ac";

	public BookDao() {
		
	}
	
	public ArrayList<BookBean> retrieveAll() throws ClassNotFoundException, SQLException {
		
		ArrayList<BookBean> allBooks = new ArrayList<BookBean>(); 
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for retrieveAll in book");
		ResultSet rs=stmt.executeQuery("select * from book");  
		while(rs.next()) {
			int bid = rs.getInt("bid");
			String title = rs.getString("title");
			String [] auth = rs.getString("author").split(";");
			ArrayList<String> author = new ArrayList<String>(getAuthor(auth));
			Double price = rs.getDouble("price");
			String category = rs.getString("category");
			String isbn = rs.getString("isbn");
			allBooks.add(new BookBean(bid, title, author, price, category, isbn));
		}
		
		Comparator<BookBean> titleCompare = this.getComparator();
		
		Collections.sort(allBooks, titleCompare);
		
		rs.close();
		stmt.close();
		con.close();
		
		return allBooks;
	}
	
	
	
	public ArrayList<BookBean> searchBook(String q) throws ClassNotFoundException, SQLException {
		
		ArrayList<BookBean> search = new ArrayList<BookBean>(); 
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for searchBook in book");
		
		ResultSet rs=stmt.executeQuery("select * from book where title like '%"+q+"%'");  
		while(rs.next()) {
			int bid = rs.getInt("bid");
			String title = rs.getString("title");
			String [] auth = rs.getString("author").split(";");
			ArrayList<String> author = new ArrayList<String>(getAuthor(auth));
			Double price = rs.getDouble("price");
			String category = rs.getString("category");
			String isbn = rs.getString("isbn");
			search.add(new BookBean(bid, title, author, price, category, isbn));
		}
	
		
		rs.close();
		stmt.close();
		con.close();
		
		return search;
	}
	
	public BookBean retrieveBookInfo(String ISBN) throws SQLException, ClassNotFoundException {
		
		BookBean book = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for retrieveBook in book");
		
		ResultSet rs=stmt.executeQuery("select * from book where isbn = '"+ISBN+"'");  
		while(rs.next()) {
			int bid = rs.getInt("bid");
			String title = rs.getString("title");
			String [] auth = rs.getString("author").split(";");
			ArrayList<String> author = new ArrayList<String>(getAuthor(auth));
			Double price = rs.getDouble("price");
			String category = rs.getString("category");
			String isbn = rs.getString("isbn");
			book = new BookBean(bid, title, author, price, category, isbn);
		}
		rs.close();
		stmt.close();
		con.close();
		
		return book;
		
	}
	
	public ArrayList<BookBean> searchCategory(String q) throws ClassNotFoundException, SQLException {
		
		ArrayList<BookBean> search = new ArrayList<BookBean>(); 
		ResultSet rs;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt=con.createStatement();
		System.out.println("Connected for searchCategory in book: "+q);
		if(q.equals("All Fiction"))
			rs=stmt.executeQuery("select * from book where category in ('Mystery & Suspense', 'Thriller', 'Sci-Fi & Fantasy', 'Romance', 'Manga', 'Historical Fiction','Classics')"); 
		else if(q.equals("All Non-Fiction"))
			rs=stmt.executeQuery("select * from book where category in ('Biography & Memoir','Business','History & Politics','Engineering','Science & Technology','Cookbooks','Faith & Spirituality')");
		else
			rs=stmt.executeQuery("select * from book where category = '"+q+"'");
		
		while(rs.next()) {
			int bid = rs.getInt("bid");
			String title = rs.getString("title");
			String [] auth = rs.getString("author").split(";");
			ArrayList<String> author = new ArrayList<String>(getAuthor(auth));
			Double price = rs.getDouble("price");
			String category = rs.getString("category");
			String isbn = rs.getString("isbn");
			search.add(new BookBean(bid, title, author, price, category, isbn));
		}
		  
		Comparator<BookBean> titleCompare = this.getComparator();
		
		Collections.sort(search, titleCompare);
		
		rs.close();
		stmt.close();
		con.close();
		
		return search;
	}
	
//	public int insertBook (String title, ArrayList<String> author, double price, String category, String isbn) throws SQLException, ClassNotFoundException {
//
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection con = DriverManager.getConnection(url, user, password);
//		Statement stmt=con.createStatement();
//		System.out.println("Connected for searchBook in book");
//		
//		String insertq = "insert into book (title,author,price,category,isbn) values ('"+title+"','"+makeAuthorString(author)+"','"+price+"','"+category+"','"+isbn+"')";
//		
//		return stmt.executeUpdate(insertq);
//	}

	
	public ArrayList<String> getAuthor (String [] auth) {
		ArrayList<String> author = new ArrayList<String>();
		
		for (String a : auth)
			author.add(a);
		
		return author;
	}
	
	public String makeAuthorString (ArrayList<String> author) {
		String authA = "";
		if (author.size() > 1) {
			for (String a: author) {
				authA = authA.concat(a+";");
			}
			authA = authA.substring(0, authA.length()-2);
		}else {
			authA = author.get(0);
		}
		return authA;
	}
	
	public Comparator<BookBean> getComparator(){
		return new Comparator<BookBean>() {
			@Override
			public int compare(BookBean obj1, BookBean obj2) {
				if (obj1 != null && obj2 != null && obj1.getTitle() != null && obj2.getTitle() != null) {
                    return obj1.getTitle().compareTo(obj2.getTitle());
                } else {
                    return -1;
                }
			}
		};
	}
	

	
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		BookDao bd = new BookDao();
//		 
//		
//		ArrayList<String> auth = new ArrayList<String>();
//		auth.add("Laura Lynne Jackson");
//		//auth.add("Patricia Shaw");
//		//auth.add("John McPhee");
//		bd.insertBook("Signs: The Secret Language Of The Universe", auth,24.00, "Faith and Spirituality", "9780399591617");
//		
//		//allBooks = bd.retrieveAll();
//		//System.out.println(allBooks.get(4).getTitle());
//	}
	
}

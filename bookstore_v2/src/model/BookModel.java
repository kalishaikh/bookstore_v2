package model;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import bean.BookBean;
import bean.ReviewBean;
import dao.BookDao;
import dao.ReviewDao;

public class BookModel {
	
	private BookDao bookData;
	private ReviewDao reviewData;

	public BookModel() {
		bookData = new BookDao();
		reviewData = new ReviewDao();
	}
	
	public ArrayList<BookBean> retrieveSearch(String search) throws ClassNotFoundException, SQLException{
		return bookData.searchBook(search);
	}
	
	public BookBean retrieveBook(String isbn) throws ClassNotFoundException, SQLException {
		return bookData.retrieveBookInfo(isbn);
	}
	
	public ArrayList<BookBean> retrieveAll() throws ClassNotFoundException, SQLException {
		return bookData.retrieveAll();
	}
	
	public ArrayList<BookBean> retrieveCatSearch(String category) throws ClassNotFoundException, SQLException {
		return bookData.searchCategory(category);
	}
	
	public ArrayList<ReviewBean> retrieveReviews(int bid) throws ClassNotFoundException, SQLException {
		return reviewData.retrieveReviews(bid);
	}
	
	public Boolean addReview(ReviewBean rev) throws ClassNotFoundException, SQLException {
		int isInserted = reviewData.insertReview(rev);
		return (isInserted == 0)? false : true;
	}
	
	public double[] overallRate(int bid) throws ClassNotFoundException, SQLException {
		return reviewData.overallRateBook(bid);
	}
	
	public String exportJSON(int bid) throws Exception {
		
		BookBean b = bookData.retrieveBook(bid);
		JsonArrayBuilder jarr = Json.createArrayBuilder();
		JsonObject tmp = Json.createObjectBuilder()
				.add("bid", b.getBid())
				.add("title", b.getTitle())
				.add("authors", b.getAuthor().get(0))
				.add("price", b.getPrice())
				.add("category", b.getCategory())
				.add("isbn", b.getIsbn())
				.build();
		
		return tmp.toString();
	}

}

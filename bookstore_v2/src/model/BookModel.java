package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import bean.BookBean;
import bean.ReviewBean;

import dao.BookDao;
import dao.ReviewDao;


public class BookModel {
	
	private static BookModel instance;
	private BookDao bookData;
	private ReviewDao reviewData;

	private BookModel() {
	}
	
	public static BookModel getInstance() throws ClassNotFoundException{
		if(instance == null) {
			instance = new BookModel();
			instance.bookData = new BookDao();
			instance.reviewData = new ReviewDao();
		}
		return instance;
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
	
	public String exportJSON(String isbn) throws Exception {
		double[] rate = new double[2];
		String result;
		BookBean bb = this.retrieveBook(isbn);
		if(bb != null) {
			JsonObjectBuilder doc = Json.createObjectBuilder();
			doc.add("BID", bb.getBid()).add("Title", bb.getTitle());
			
			JsonArrayBuilder author=Json.createArrayBuilder();
			for (String au : bb.getAuthor()) {
				author.add(au);
			}
			
			doc.add("Author", author).add("Price", bb.getPrice()).add("ISBN", bb.getIsbn());
			
			rate = this.overallRate(bb.getBid());
		
			if(rate[1] != 0) {
				doc.add("UserRating", rate[0]);
			}
	
			JsonObject value=doc.build();
	        result = value.toString();
			System.out.println("\n"+ result);
		}else {
			System.out.println("Book not found for ISBN:"+isbn);
			result = "Book not found for ISBN:"+isbn;
		}
		
		return result;
	}
	

}

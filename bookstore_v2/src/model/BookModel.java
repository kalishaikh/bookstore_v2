package model;

import java.sql.SQLException;
import java.util.ArrayList;


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

}

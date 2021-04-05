package model;

import java.sql.SQLException;
import java.util.ArrayList;


import bean.BookBean;
import dao.BookDao;

public class BookModel {
	
	private BookDao bookData; 

	public BookModel() {
		bookData = new BookDao();
	}
	
	public ArrayList<BookBean> retrieveSearch(String search) throws ClassNotFoundException, SQLException{
		return bookData.searchBook(search);
	}
	
	public BookBean retrieveBook(String isbn) throws ClassNotFoundException, SQLException {
		return bookData.retrieveBookInfo(isbn);
	}

}

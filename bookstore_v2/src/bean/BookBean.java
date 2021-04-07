package bean;

import java.util.ArrayList;

public class BookBean {
	
	private int bid;
	private String title;
	private ArrayList<String> author;
	private double price;
	private String category;
	private String isbn;
	private int quantity;
	
	public BookBean(int bid, String title, ArrayList<String> author, double price, String category, String isbn) {
		super();
		this.bid = bid;
		this.title = title;
		this.author = author;
		this.price = price;
		this.category = category;
		this.isbn = isbn;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BookBean() {
		
	}

	public int getBid() {
		return bid;
	}

	public String getTitle() {
		return title;
	}

	
	public String parseTitle() {
		String newTitle = title.replaceAll("'", "\\\\\\\'");
		return newTitle;
	}

	public ArrayList<String> getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public String authorsToString() {
		String authors = "";
		for (int i=0; i<author.size(); i++) {
			if (i != 0) {
				authors += ", ";
			}
			authors += author.get(i);
		}
		return authors;
	}
	
	

}

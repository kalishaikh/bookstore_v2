package bean;

import java.sql.Date;


public class CartItemBean {
	
	public String bid, author, title, category; 
	public double price; 
	public int quantity;
	public Date transactionDate;
	
	public CartItemBean(String bid, double price, int quantity, String title, String author, String category) {
		super();
		this.bid = bid;
		this.price = price;
		this.quantity = quantity;
		this.title = title;
		this.author = author;
		this.category = category;
	}

	public String getBid() {
		return bid;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate() {
		this.transactionDate = new Date(System.currentTimeMillis());
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	} 
		

}

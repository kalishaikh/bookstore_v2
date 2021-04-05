package bean;

public class CartItemBean {
	
	public String bid; 
	public double price; 
	public int quantity;
	
	public CartItemBean(String bid, double price, int quantity) {
		super();
		this.bid = bid;
		this.price = price;
		this.quantity = quantity;
	}

	public String getBid() {
		return bid;
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

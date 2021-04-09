package bean;

public class POItemBean {
	
	int bid;
	int pid;
	double price;
	int quantity;
	String transaction_date; 
	
	public POItemBean(int bid, int pid, double price, int quantity, String transaction_date) {
		super();
		this.bid = bid;
		this.pid = pid;
		this.price = price;
		this.quantity = quantity;
		this.transaction_date = transaction_date;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}


}

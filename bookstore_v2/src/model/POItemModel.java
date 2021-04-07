package model;

import dao.POItemDao;
import java.sql.Date;

public class POItemModel {

	private POItemDao pi;
	
	public POItemModel() {
		pi = new POItemDao();
	}
	
	public void addPOItem(String pid, String bid, double price, int quantity, Date transactionDate) {
		pi.addPOItem(pid, bid, price, quantity, transactionDate);
	}
}

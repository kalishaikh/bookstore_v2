package model;

import dao.POItemDao;

public class POItemModel {

	private POItemDao pi;
	
	public POItemModel() {
		pi = new POItemDao();
	}
	
	public void addPOItem(String pid, String bid, double price, int quantity) {
		pi.addPOItem(pid, bid, price, quantity);
	}
}

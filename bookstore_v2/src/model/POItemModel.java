package model;

import dao.BookDao;
import dao.POItemDao;
import dao.ReviewDao;

import java.sql.Date;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import bean.POItemBean;

public class POItemModel {

	private POItemDao pi;
	private static POItemModel instance;
	private POItemModel() {

	}
	
	public static POItemModel getInstance() throws ClassNotFoundException{
		if(instance == null) {
			instance = new POItemModel();
			instance.pi = new POItemDao();

		}
		return instance;
	}
	
	public void addPOItem(String pid, String bid, double price, int quantity, Date transactionDate) {
		pi.addPOItem(pid, bid, price, quantity, transactionDate);
	}
	
	public String exportJSON(String isbn) throws Exception {
		
		ArrayList<POItemBean> b = pi.retrieveOrders(isbn);
		JsonArrayBuilder jarr = Json.createArrayBuilder();
		if (b.size() != 0) {
			for (int i=0; i<b.size(); i++) {
				POItemBean p = b.get(i);
				JsonObject tmp = Json.createObjectBuilder()
						.add("bid", p.getBid())
						.add("pid", p.getPid())
						.add("price",  p.getPrice())
						.add("quantity",  p.getQuantity())
						.add("transaction_date",  p.getTransaction_date())
						.build();
				jarr.add(tmp);
			}
		
			JsonArray j = jarr.build();	
	
		return j.toString();
		
		} else {
			return "Book not found for ISBN:"+isbn;
		}
	}
}

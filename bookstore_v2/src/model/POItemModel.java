package model;

import dao.POItemDao;
import java.sql.Date;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import bean.POItemBean;

public class POItemModel {

	private POItemDao pi;
	
	public POItemModel() {
		pi = new POItemDao();
	}
	
	public void addPOItem(String pid, String bid, double price, int quantity, Date transactionDate) {
		pi.addPOItem(pid, bid, price, quantity, transactionDate);
	}
	
	public String exportJSON(int bid) throws Exception {
		
		ArrayList<POItemBean> b = pi.retrieveOrders(bid);
		JsonArrayBuilder jarr = Json.createArrayBuilder();
		for (int i=0; i<b.size(); i++) {
			POItemBean p = b.get(i);
			JsonObject tmp = Json.createObjectBuilder()
					.add("bid", bid)
					.add("pid", p.getPid())
					.add("price",  p.getPrice())
					.add("quantity",  p.getQuantity())
					.add("transaction_date",  p.getTransaction_date())
					.build();
			jarr.add(tmp);
		}
	
		JsonArray j = jarr.build();	
		
		return j.toString();
	}
}

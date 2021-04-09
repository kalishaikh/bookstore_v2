package model;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import bean.BookBean;
import bean.POItemBean;
import dao.PODao;
import dao.POItemDao;

public class POModel {
	
	private static POModel instance;
	private PODao pdao; 
	
	private POModel() {
		pdao = new PODao();
	}
	
	public static POModel getInstance() {
		if (instance == null) {
			instance = new POModel();
		}
		
		return instance;
	}
	
	public void addAddress(String street, String province, String country, String zip, String phone) {
		
		pdao.addAddress(street, province, country, zip, phone);
		
	}
	
	public int getAid(String street) {
		return pdao.getAid(street);
	}


	public String addPurchaseOrder(String email, String lname, String fname, Enum status, String address) {
		
		int aid = pdao.getAid(address);
		return pdao.addPO(email, lname, fname, status, aid);
		
	}
	
	
}

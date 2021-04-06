package bean;

import java.util.ArrayList;

public class ShoppingCartBean {

	public ArrayList<CartItemBean> cart;
	public int numItems;

	public ShoppingCartBean() {
		super();
		this.cart = new ArrayList<CartItemBean>();
	} 
	
	public void addCartItem(CartItemBean c) {
		cart.add(c);
	}
	
	public boolean hasItem(CartItemBean c) {
		
		for (int i=0; i<cart.size(); i++) {
			if (cart.get(i).getBid().equals(c.getBid())) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<CartItemBean> getCart() {
		return cart;
	}
	
	public int getQuantity(CartItemBean c) {
		for (int i=0; i<cart.size(); i++) {
			if (cart.get(i).getBid().equals(c.getBid())) {
				return cart.get(i).getQuantity();
			}
		}
		return 0;
	}
	
	public int getCartSize() {
		int numItems = 0;
		for (int i=0; i<cart.size(); i++) {
			numItems += cart.get(i).getQuantity();
		}		
		return numItems;
	}
	
	public void changeQuantity(CartItemBean c, int quantity) {
		for (int i=0; i<cart.size(); i++) {
			if (cart.get(i).getBid().equals(c.getBid())) {
				cart.get(i).setQuantity(quantity);
			}
		}
	}
	
	public double getSubtotal() {
		double subtotal = 0;
		for (int i=0; i<cart.size(); i++) {
			subtotal += cart.get(i).getQuantity()*cart.get(i).getPrice();
		}
		return subtotal;
	}
	
	public double getTax() {
		double tax = this.getSubtotal()*0.15;
		return tax;
	}
	
	public double getTotal() {
		double total = this.getSubtotal()*1.15;
		return total;
	}
	
	public void printCartItems() {
		System.out.println("********* Shopping Cart *********");
		for (int i=0; i<cart.size(); i++) {
			System.out.println("Item #" + (i+1) + " bid#: " + cart.get(i).getBid() + " quantity: " + cart.get(i).getQuantity());
		}
	}
}

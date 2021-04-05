package bean;

import java.util.ArrayList;

public class ShoppingCartBean {

	ArrayList<CartItemBean> cart;

	public ShoppingCartBean() {
		super();
		this.cart = new ArrayList<CartItemBean>();
	} 
	
	public void addCartItem(CartItemBean c) {
		cart.add(c);
	}
	
	public void printCartItems() {
		System.out.println("********* Shopping Cart *********");
		for (int i=0; i<cart.size(); i++) {
			System.out.println("Item #" + (i+1) + " bid#: " + cart.get(i).getBid());
		}
	}
}

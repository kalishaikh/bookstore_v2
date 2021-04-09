package ctrl;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartItemBean;
import bean.ShoppingCartBean;
import model.POItemModel;
import model.POModel;

/**
 * Servlet implementation class checkout_ctrl
 */
@WebServlet("/checkout/*")
public class checkout_ctrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;
	POModel pmodel;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkout_ctrl() {
        super();
        pmodel = POModel.getInstance();
        count = 1;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public enum statusType {
    	COMPLETED, 
    	DENIED;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// If user not logged in, redirect to login page 
		if (request.getSession().getAttribute("email") == null || request.getSession().getAttribute("email").equals("")) {
			String target = "/login.html";
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		// If fresh request, redirect to UI page
		if (!request.getParameterMap().containsKey("checkout")) {			
			String target = "/checkout.jspx";
			request.getRequestDispatcher(target).forward(request, response);
							
		// If not fresh request, process checkout 
		} else {
			
			// Process PO 
			String fname, lname, email, phone, address, country, province, zip, pid;
			Enum status; 
			Boolean confirmed;
			
			fname = request.getParameter("firstName");
			lname = request.getParameter("lastName");
			email = (String) request.getSession().getAttribute("email");
			phone = request.getParameter("phone");
			address = request.getParameter("address");
			country = request.getParameter("country");
			province = request.getParameter("province");
			zip = request.getParameter("zip");
			
			// Reject every third payment request 
			if (count%3!=0) {
			
				status = statusType.COMPLETED;
				request.setAttribute("confirmed", true);
				confirmed = true;
			} else {
				status = statusType.DENIED;
				confirmed = false;
				request.setAttribute("confirmed", false);
			}
			count++;
			
			pmodel.addAddress(address, province, country, zip, phone);
			pid = pmodel.addPurchaseOrder(email, lname, fname, status, address);
			
			ShoppingCartBean sc = (ShoppingCartBean) request.getSession().getAttribute("cart");
		
			// Redirect to confirmation/denial page
			if (confirmed) {
				// Add cart items to poitem db 
				this.addPOItems(sc.cart, pid);	
				System.out.println("\n************ CHECKOUT ************");
				System.out.println("ORDER STATUS: CONFIRMED");
				sc.printCartItems();
				
				// Reset shopping cart
				request.setAttribute("confirmationCart", sc);
				sc = new ShoppingCartBean();
				request.getSession().setAttribute("cart", sc);

			} else {
				System.out.println("ORDER STATUS: DENIED");
				System.out.println("Shopping cart will be persisted.");
				sc.printCartItems();
			};
			
			String target = "confirmation";
			request.getRequestDispatcher(target).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void addPOItems(ArrayList<CartItemBean> cartItems, String pid) {
		System.out.println("\nAdding cart items to poitem db...");
		POItemModel poi = null;
		try {
			poi = POItemModel.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0; i<cartItems.size(); i++) {
			CartItemBean c = cartItems.get(i);
			c.setTransactionDate();
			poi.addPOItem(pid, c.getBid(), c.getPrice(), c.getQuantity(), c.getTransactionDate());
		}
	}

}

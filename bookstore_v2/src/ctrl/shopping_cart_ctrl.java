package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ShoppingCartBean;

/**
 * Servlet implementation class shoppingCart_ctrl
 */
@WebServlet({"/shopping_cart", "/shopping_cart/*"})
public class shopping_cart_ctrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HttpSession this_session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shopping_cart_ctrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCartBean sc;
		
		// If session shopping cart has not be initialized 
		if (request.getSession().getAttribute("cart") == null) {
			
			sc = new ShoppingCartBean();
			request.getSession().setAttribute("cart", sc);
			
		} else {
			sc = (ShoppingCartBean) request.getSession().getAttribute("cart");
		}
		
		if (request.getParameter("changeQuantity") != null) {
			String bid = request.getParameter("bid");
			if (request.getParameter("changeQuantity").equals("increment")) {
				sc.increment(bid);
			} else if (request.getParameter("changeQuantity").equals("decrement")) {
				sc.decrement(bid);
			}
			
		} else if (request.getParameter("removeItem") != null){
			String bid = request.getParameter("bid");
			sc.removeItem(bid);
		} else {
		
		
			// If shopping cart button clicked, redirect to cart page 
			String target = "/shopping_cart.jspx";
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

}

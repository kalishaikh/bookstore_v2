package ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BookBean;
import bean.CartItemBean;
import bean.ShoppingCartBean;
import model.BookModel;

/**
 * Servlet implementation class bookinfo_ctrl
 */
@WebServlet({"/bookinfo_ctrl", "/bookinfo_ctrl/*"})
public class bookinfo_ctrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookinfo_ctrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession this_session = request.getSession();
		BookBean cbook;
		String bookInfo_target = "bookInfo_page.jspx";
		String error_target = "error_page.jspx";
		
		if(request.getParameter("isbn") != null && !request.getParameter("isbn").equals("")) {
			String isbn = request.getParameter("isbn");
			
			BookModel bmodel = new BookModel();
			try {
				cbook = bmodel.retrieveBook(isbn);
				if(cbook != null) {
					this_session.setAttribute("bookI", cbook);
					request.getRequestDispatcher(bookInfo_target).forward(request, response);
				}else {
					request.getRequestDispatcher(error_target).forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (request.getParameter("addToCart").equals("true")) { // Add to Cart button clicked. 
			
			this.addToCart(this_session, request);
			
		} else {
			request.getRequestDispatcher(error_target).forward(request, response);
		}
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void addToCart(HttpSession this_session, HttpServletRequest request) {
		
		CartItemBean item = new CartItemBean(request.getParameter("bid"), 
				Double.parseDouble(request.getParameter("price")), 
				1,
				request.getParameter("title"),
				request.getParameter("author"),
				request.getParameter("category"));
		ShoppingCartBean cart;
		
		// Fresh session shopping cart
		if (this_session.getAttribute("cart") == null) {
			cart = new ShoppingCartBean();
			cart.addCartItem(item);
			this_session.setAttribute("cart", cart);
			
		// Existing session shopping cart
		} else {
			cart = (ShoppingCartBean) this_session.getAttribute("cart");
			if (cart.hasItem(item)) {
				cart.changeQuantity(item, (cart.getQuantity(item)+1));
			} else { 
				cart.addCartItem(item);
			}
			this_session.setAttribute("cart", cart);
		} 
		
		System.out.println("\nBook with bid " + request.getParameter("bid") + " added to cart! Price of the book is: " + request.getParameter("price"));

		
	}

}

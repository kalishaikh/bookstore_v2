package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class Search
 */
@WebServlet({"/Search", "/Search/*"})
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search, category;
		//ServletContext context = this.getServletContext();
		HttpSession this_session = request.getSession();
		String search_target = "/searchResult_page.jspx";
		String main_target = "/main_page.jspx";
		String bookInfo_target = "/bookInfo_page.jspx";
		
		BookModel bmodel = null;
		try {
			bmodel = BookModel.getInstance();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (request.getParameter("addToCart") != null && request.getParameter("addToCart").equals("true")) { // Add to Cart button clicked. 
			
			this.addToCart(this_session, request);
			
		} else if(request.getParameter("catSearch") != null && request.getParameter("catSearch").equals("true") ) {
			category = request.getParameter("category").replace("%26", "&");
			ArrayList<BookBean> searchResult;
			
			try {
				if(category.equals("All Books")) {
					searchResult = bmodel.retrieveAll();
				}else {
					searchResult = bmodel.retrieveCatSearch(category);
				}
		
				this_session.setAttribute("searchResult", searchResult);
				this_session.setAttribute("category", category);
				this_session.setAttribute("search", "");
				request.getRequestDispatcher(search_target).forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(request.getParameter("search") != null && !request.getParameter("search").equals("")) {

			search = request.getParameter("search");
			
			this_session.setAttribute("search", search);//add persistence to the search
			
			try {
				ArrayList<BookBean> searchResult = new ArrayList<BookBean>(bmodel.retrieveSearch(search));
				if(searchResult.size() != 0)
					for(BookBean sr: searchResult)
						System.out.println(sr.getTitle());
				this_session.setAttribute("searchResult", searchResult);
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.getRequestDispatcher(search_target).forward(request, response);
		} else {//backup on server side, should be handled on client side
			if(request.getParameter("button").equals("Search"))
				request.getRequestDispatcher(search_target).forward(request, response);
			else if(request.getParameter("button").equals("bookInfo"))
				request.getRequestDispatcher(bookInfo_target).forward(request, response);
			else
				request.getRequestDispatcher(main_target).forward(request, response);
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

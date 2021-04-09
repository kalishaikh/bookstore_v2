package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import model.BookModel;

/**
 * Servlet implementation class partner_ctrl
 */
@WebServlet("/partner_ctrl")
public class partner_ctrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public partner_ctrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		
		
		if (request.getParameter("getBook") != null && request.getParameter("getBook").equals("true")) { // Add to Cart button clicked. 
			
			String isbn = request.getParameter("isbn");
			String bookJSON;
			try {
				bookJSON = BookModel.getInstance().exportJSON(isbn);
				resOut.write(bookJSON);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if(request.getParameter("getOrder") != null && request.getParameter("getOrder").equals("true") ) {
			
			// TODO 
			
		} else {
			request.getRequestDispatcher("partners.jspx").forward(request, response);
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

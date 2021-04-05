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
import model.BookModel;

/**
 * Servlet implementation class bookinfo_ctrl
 */
@WebServlet("/bookinfo_ctrl")
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
			
		}else {
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

}

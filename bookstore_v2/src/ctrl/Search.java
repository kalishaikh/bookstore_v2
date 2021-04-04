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
import model.BookModel;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
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
		String search;
		//ServletContext context = this.getServletContext();
		HttpSession this_session = request.getSession();
		String search_target = "/searchResult_page.jspx";
		String main_target = "/main_page.jspx";
		String bookInfo_target = "/bookInfo_page.jspx";
		
		BookModel bmodel = new BookModel();
		
		if(request.getParameter("search") != null && !request.getParameter("search").equals("")) {
			search = request.getParameter("search");
			
			this_session.setAttribute("search", search);//add persistence to the search
			
			try {
				ArrayList<BookBean> searchResult = new ArrayList<BookBean>(bmodel.retrieveSearch(search));
				if(searchResult.size() != 0)
					for(BookBean sr: searchResult)
						System.out.println(sr.getTitle());
				this_session.setAttribute("searchResult", searchResult);
				this_session.setAttribute("searchNum", searchResult.size());
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.getRequestDispatcher(search_target).forward(request, response);
		}
		else {//backup on server side, should be handled on client side
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

}

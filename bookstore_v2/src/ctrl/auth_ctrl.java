package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AuthModel;

/**
 * Controller that handles all requests relating to the login and registration page.
 */

@WebServlet({"/auth_ctrl", "/auth_ctrl/*"})
public class auth_ctrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public auth_ctrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		AuthModel model = new AuthModel();
		
		
		/*
		 * Detect AJAX calls below. Specifically for the login and register page
		 */
		
		if(request.getRequestURI().equals("/bookstore_v2/auth_ctrl/register")) {
			
			PrintWriter out = response.getWriter();
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			
			String hashPw = model.md5Hash(pass);
			
			int result = model.registerUser(fname, hashPw, lname, email);
			
			
			
			//Tell JavaScript (auth.js) if the result was successful or not;
			
			out.print(result);
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

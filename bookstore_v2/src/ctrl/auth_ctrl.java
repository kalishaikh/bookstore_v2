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
			
			
			int result = model.registerUser(fname, model.bcrypt(pass), lname, email);
			
			//Store the name of the user in the session variable to be displayed on the front page.
			if (result == 0) {
				
				request.getSession().setAttribute("fname", fname);
			}
			
			//Tell JavaScript (auth.js) if the result was successful or not;
			
			out.print(result);
			out.flush();
		}
		
		else if (request.getRequestURI().equals("/bookstore_v2/auth_ctrl/login")) {
			

				PrintWriter out = response.getWriter();
				
				String email = request.getParameter("email");
				String pass = request.getParameter("pass");	
				
				System.out.println("got a request from the login page");
				
				String result = model.loginUser(email, model.bcrypt(pass));
				
				if(!result.equals("100")) {
					request.getSession().setAttribute("fname", result);
				}
				
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

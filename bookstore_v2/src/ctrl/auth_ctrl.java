package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ShoppingCartBean;
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
				System.out.println("Added a session variable: " + request.getSession().getAttribute("fname"));
			}
			
			//Tell JavaScript (auth.js) if the result was successful or not;
			
			out.print(result);
			out.flush();
		}
		
		/*
		 * Detects if a user is trying to login
		 */
		else if (request.getRequestURI().equals("/bookstore_v2/auth_ctrl/login")) {
			
				PrintWriter out = response.getWriter();
				String email = request.getParameter("email");
				String pass = request.getParameter("pass");	
				String result = model.loginUser(email, pass);
				
				if (!result.equals("200") && !result.equals("100")) {
					request.getSession().setAttribute("fname", result);
					request.getSession().setAttribute("email", email);
					System.out.println("Added a session variable: " + request.getSession().getAttribute("fname"));
				}
				
				out.print(result);
				
		}
		
		/*
		 * Detects if a user is logging out
		 */
		else if (request.getRequestURI().equals("/bookstore_v2/auth_ctrl/logout")) {
			System.out.println(request.getSession().getAttribute("fname") + " Has successfully logged out");
			request.getSession().setAttribute("fname", "");
			request.getSession().setAttribute("email", "");
			ShoppingCartBean sc = new ShoppingCartBean();
			request.getSession().setAttribute("cart", sc);
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

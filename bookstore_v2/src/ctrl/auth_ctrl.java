package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.ReviewBean;
import bean.ShoppingCartBean;
import bean.UserBean;
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
		if (request.getPathInfo().equals("/register"))  {
			
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
		else if (request.getPathInfo().equals("/login")) {

				PrintWriter out = response.getWriter();
				String email = request.getParameter("email");
				String pass = request.getParameter("pass");	
				String result = model.loginUser(email, pass);
				
				ArrayList<UserBean> testListen = new ArrayList<UserBean>();
				UserBean test = new UserBean();
				test.setEmail("kas_109@hotmail.com");
				testListen.add(test);
				
 				
				request.setAttribute("login", testListen);
				
				if (!result.equals("200") && !result.equals("100")) {
					request.getSession().setAttribute("fname", result);
					request.getSession().setAttribute("email", email);
					System.out.println("Added a session variable: " + request.getSession().getAttribute("fname"));
				}
				
				if(email.equals("admin@bknj.com")) {
					request.getSession().setAttribute("admin", "1");
				}
				
				else if (email.equals("partner@bknj.com")) {
					request.getSession().setAttribute("partner", "2");
				}
				
				else {
					/*
					 * If no partner or admin logs in we set them to zero
					 */
					request.getSession().setAttribute("admin", "0");
					request.getSession().setAttribute("partner", "0");
				}
				out.print(result);
				
		}
		
		/*
		 * Detects if a user is logging out
		 */
		else if (request.getPathInfo().equals("/logout"))  {
			System.out.println(request.getSession().getAttribute("fname") + " Has successfully logged out");
			request.getSession().setAttribute("fname", "");
			request.getSession().setAttribute("email", "");
			ShoppingCartBean sc = new ShoppingCartBean();
			request.getSession().setAttribute("cart", sc);
		}
		
		/*
		 * Detect an analytics request
		 */
		
		else if (request.getPathInfo().equals("/analytics"))  {
			
			PrintWriter out = response.getWriter();
			System.out.println("Admin has requested for analytics");
			
			//html string to be returned
			String html = "";
			
			//Type of request coming from the webpage
			String type = request.getParameter("type");
			
			//0 Represents a request for most books
			if (type.equals("0")) {
				
				html = "<table class='table'>   <thead class='thead-light'> <tr> <th scope='col'>Ranking</th><th scope='col'>Book Name</th> <th scope='col'>Copies Sold</th></tr></thead> <tbody>";
				int counter = 1;
				StringBuilder build = new StringBuilder();
				build.append(html);
				
				ArrayList<BookBean> test = model.getMostBooks();
				
				for (BookBean key : test) {
					
					build.append("<tr> <th scope='row'>"+counter+"</th> <td>"+key.getTitle()+"</td> <td>"+key.getQuantity()+"</td> </tr>");
					counter += 1;
				}
				
				build.append("</tbody></table>");
				out.print(build);
				out.flush();
			}
			
			//1 Represents a request for best selling genre
			else if (type.equals("1")) {
				
				html = "<table class='table'><thead class='thead-light'> <tr> <th scope='col'>Ranking</th><th scope='col'>Genre</th> <th scope='col'>Copies Sold</th></tr></thead><tbody>";
				int counter = 1;
				StringBuilder build = new StringBuilder();
				build.append(html);
				
				ArrayList<BookBean> test = model.getBestGenres();
				
				for (BookBean key : test) {
					
					build.append("<tr> <th scope='row'>"+counter+"</th> <td>"+key.getCategory()+"</td> <td>"+key.getQuantity()+"</td> </tr>");
					counter += 1;
				}
				
				build.append("</tbody></table>");
				out.print(build);
				out.flush();
			}
			
			//2 Represents a request for Most Popular Countries
			else if (type.equals("2")) {
				
				html = "<table class='table'><thead class='thead-light'> <tr> <th scope='col'>Ranking</th><th scope='col'>Country</th> <th scope='col'>Copies Sold</th></tr></thead><tbody>";
				int counter = 1;
				StringBuilder build = new StringBuilder();
				build.append(html);
				
				LinkedHashMap<String,Integer> test = model.getCountries();
				
				Set<String> key = test.keySet();
				
				for(String currKey : key) {
					
					build.append("<tr> <th scope='row'>"+counter+"</th> <td>"+currKey+"</td> <td>"+test.get(currKey)+"</td> </tr>");
					counter += 1;
				}
				build.append("</tbody></table>");
				out.print(build);
				out.flush();
			}
			
			//3 Represents a request for users who spend the most
			else if(type.equals("3")) {
				html = "<table class='table'><thead class='thead-light'> <tr> <th scope='col'>Ranking</th><th scope='col'>User Email</th> <th scope='col'>Total Spent</th></tr></thead><tbody>";
				int counter = 1;
				StringBuilder build = new StringBuilder();
				build.append(html);
				
				ArrayList<UserBean> test = model.getTopUsers();
				
				for (UserBean key : test) {
					
					DecimalFormat df = new DecimalFormat("#.##");
					String amt = df.format(key.getAmtSpent()); 
					StringBuilder nameProtected = new StringBuilder(key.getEmail());
					for (int i = 0; i < nameProtected.length()/2; i++) {
						nameProtected.setCharAt(i, '*'); 
					}
					
					
					build.append("<tr> <th scope='row'>"+counter+"</th> <td>"+nameProtected+"</td> <td>"+amt+"</td> </tr>");
					counter += 1;
				}
				
				build.append("</tbody></table>");
				out.print(build);
				out.flush();
			}
			
			//4 represents a request for the top rated books
			
			else if (type.equals("4")) {
				html = "<table class='table'><thead class='thead-light'> <tr> <th scope='col'>Ranking</th><th scope='col'>Book Title</th> <th scope='col'>Book Rating</th></tr></thead><tbody>";
				int counter = 1;
				StringBuilder build = new StringBuilder();
				build.append(html);
				
				ArrayList<ReviewBean> test = model.getBestBooks();
				
				for (ReviewBean key : test) {
					
					DecimalFormat df = new DecimalFormat("#.##");
					String amt = df.format(key.getAvgRate()); 
					
					build.append("<tr> <th scope='row'>"+counter+"</th> <td>"+key.getTitle()+"</td> <td>"+amt+"</td> </tr>");
					counter += 1;
				}
				
				build.append("</tbody></table>");
				out.print(build);
				out.flush();
				}
			
			//testing speed of listenr vs db connection
			else if (type.equals("5")) {
				
				html = "<table class='table'><thead class='thead-light'> <tr> <th scope='col'>Ranking</th><th scope='col'>Book Title</th> <th scope='col'>Amount Sold</th></tr></thead><tbody>";
				int counter = 1;
				StringBuilder build = new StringBuilder();
				build.append(html);
				
				if (request.getServletContext().getAttribute("popularBooks") != null) {
					HashMap<String, Integer> popularBooks = new HashMap<String,Integer>();

					popularBooks = (HashMap<String, Integer>) request.getServletContext().getAttribute("popularBooks");
					
					LinkedHashMap<String, Integer> map2 = 
						    popularBooks.entrySet()
						       .stream()             
						       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
						       .collect(Collectors.toMap(e -> e.getKey(), 
						                                 e -> e.getValue(), 
						                                 (e1, e2) -> null, // or throw an exception
						                                 () -> new LinkedHashMap<String, Integer>()));
					System.out.println(map2);
					
					
					for(String currKey : map2.keySet()) {
						
						build.append("<tr> <th scope='row'>"+counter+"</th> <td>"+currKey+"</td> <td>"+map2.get(currKey)+"</td> </tr>");
						counter += 1;
					}
					build.append("</tbody></table>");
					out.print(build);
					out.flush();
					
			
				}
				else {
					
						out.print("<h1>No books have been sold yet</h1>");
						out.flush();
					
						}
					}
			
			else if (type.equals("6")) {
				
				
				html = "<table class='table'><thead class='thead-light'> <tr> <th scope='col'>Ranking</th><th scope='col'>Book Title</th> <th scope='col'>Amount Sold</th></tr></thead><tbody>";
				int counter = 1;
				StringBuilder build = new StringBuilder();
				build.append(html);
				
				ArrayList<BookBean> test = model.getMarchBooks();
				for (BookBean key : test) {
					
					build.append("<tr> <th scope='row'>"+counter+"</th> <td>"+key.getTitle()+"</td> <td>"+key.getQuantity()+"</td> </tr>");
					counter += 1;
				}
				
				build.append("</tbody></table>");
				out.print(build);
				out.flush();
			}
				
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

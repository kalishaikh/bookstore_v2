package ctrl;

import java.awt.Component;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.POModel;

/**
 * Servlet implementation class checkout_ctrl
 */
@WebServlet("/checkout/*")
public class checkout_ctrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;
	POModel pmodel;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkout_ctrl() {
        super();
        pmodel = POModel.getInstance();
        count = 1;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public enum statusType {
    	COMPLETED, 
    	DENIED;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// If fresh request, redirect to UI page
		if (!request.getParameterMap().containsKey("checkout")) {			
					
			String target = "/checkout.jspx";
			request.getRequestDispatcher(target).forward(request, response);
				
		// If not fresh request, process checkout 
		} else {
			System.out.println("TEST: " + request.getParameter("country") + " " + request.getParameter("address")); // for testing
			
		
			// Process PO 
			String fname, lname, email, phone, address, country, province, zip;
			Enum status; 
			Boolean confirmed;
			
			fname = request.getParameter("firstName");
			lname = request.getParameter("lastName");
			email = request.getParameter("email");
			phone = request.getParameter("phone");
			address = request.getParameter("address");
			country = request.getParameter("country");
			province = request.getParameter("province");
			zip = request.getParameter("zip");
			
			// Reject every third payment request 

			if (count%3!=0) {
			
				status = statusType.COMPLETED;
			//	request.setAttribute("confirmed", true);
				confirmed = true;
			} else {
				status = statusType.DENIED;
			//	request.setAttribute("confirmed", false);
				confirmed = false;
			}
			count++;
			
			pmodel.addAddress(address, province, country, zip, phone);
			pmodel.addPurchaseOrder(email, lname, fname, status, address);

			
			// Redirect to confirmation/denial page
			String target = "";
			if (confirmed) {
				target = "confirmation_page.jspx";
			} else {
				target = "checkout_denied_page.jspx";
			};
			response.sendRedirect(target);
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

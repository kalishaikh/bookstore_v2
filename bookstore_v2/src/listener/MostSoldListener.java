package listener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import bean.BookBean;
import bean.CartItemBean;
import bean.UserBean;

/**
 * Application Lifecycle Listener implementation class MostSoldListener
 *
 */
@WebListener
public class MostSoldListener implements ServletRequestListener, ServletRequestAttributeListener, HttpSessionAttributeListener, HttpSessionListener {
	
	public int counter = 0;
	public HashMap <String, Integer> popularBooks;

    /**
     * Default constructor. 
     */
    public MostSoldListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent srae)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent srae)  { 
         // TODO Auto-generated method stub
    	handleEvent(srae);
    	booksSold(srae);
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent srae)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
    void handleEvent(ServletRequestAttributeEvent event) {
    	if (event.getName().equals("login")){
    		/*
    		 * if the attribute firstName is changed then we record it
    		 * we save the counter name in a context attribute..
    		 */
    			ArrayList<UserBean> user = new ArrayList<UserBean>();
    			user = (ArrayList<UserBean>) event.getServletRequest().getAttribute("login");
    			event.getServletContext().setAttribute("user_log", user.get(0));
    			System.out.println("user Bean email = " + user.get(0).getEmail());
    	}
 
    }
    
   	
    public void booksSold(ServletRequestAttributeEvent event) {
    	
    	if(event.getName().equals("purchasedBooks")) {
    		
    		if(event.getServletContext().getAttribute("popularBooks") == null) {
    			popularBooks = new HashMap <String,Integer>();
    		}
    			
    		@SuppressWarnings("unchecked")
    		ArrayList<BookBean> cart = new ArrayList<BookBean>();
    		cart = (ArrayList<BookBean>) event.getServletRequest().getAttribute("purchasedBooks");
    	
    		for (int i = 0; i < cart.size(); i++) {
    				String title = cart.get(i).getTitle();
    				int quantity = cart.get(i).getQuantity();
    				
    				if(!popularBooks.containsKey(title)) {
    					
    					popularBooks.put(cart.get(i).getTitle(), cart.get(i).getQuantity());
    				}
    				
    				else {
    					
    					popularBooks.replace(title, popularBooks.get(title) + quantity);
    				}
    				
    		}
    			event.getServletContext().setAttribute("popularBooks", popularBooks);
    			for (String key : popularBooks.keySet()) {
    				System.out.println("Book Name: " + key + " Book Quantity: " +popularBooks.get(key));
    			}
    	}
    }
}

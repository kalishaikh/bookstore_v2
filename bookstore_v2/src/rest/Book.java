package rest;


import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import model.BookModel;

@Path("Book")
public class Book {

	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getStudent(@QueryParam("productId") String isbn) throws Exception {
		System.out.println("Received: "+isbn);
		return BookModel.getInstance().exportJSON(isbn);
	}
}

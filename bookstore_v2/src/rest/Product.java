package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.BookModel;

@Path("product")
public class Product {

	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getProductInfo(@QueryParam("bid") int bid) throws Exception {
		
		System.out.println("REST call for product catalogue received...");
		BookModel bm = new BookModel();
		String res = bm.exportJSON(bid);
		return res;
	}

}

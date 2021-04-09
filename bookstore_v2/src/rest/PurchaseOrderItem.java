package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.POItemModel;

@Path("order")
public class PurchaseOrderItem {

	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getProductInfo(@QueryParam("bid") int bid) throws Exception {
		
		System.out.println("REST call for order catalogue received...");
		POItemModel pm = new POItemModel();
		String res = pm.exportJSON(bid);
		return res;
	}

}
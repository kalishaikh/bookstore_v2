package ctrl;

import dao.PODao;
import dao.POItemDao;

public class test {
	
	public static void main(String[] args) {
	PODao p = new PODao();
//	p.addAddress("1 Yonge St", "ON", "Canada", "M1V 2S6", "647-111-1111");
//	p.getAid("16 Chapel Park Sq");
//	p.addAddress("4 Yonge St", "ON", "Canada", "M1V 2S6", "647-111-1111");
//	p.closeCon();
//	p.addPO("jessie@gmail.com", "Leung", "Jess", "COMPLETED", 14);
	POItemDao pi = new POItemDao();
	pi.addPOItem("84", "134", 24.00, 1);
	
//	System.out.println("address tested");
	}

}

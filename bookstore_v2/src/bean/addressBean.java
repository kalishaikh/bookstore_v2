package bean;

// Class currently not used for anything
public class addressBean {
	int aid; 
	String street, province, country, zip, phone;
	
	public addressBean(int aid, String street, String province, String country, String zip, String phone) {
		this.aid = aid; 
		this.street = street; 
		this.province = province; 
		this.country = country; 
		this.zip = zip; 
		this.phone = phone;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

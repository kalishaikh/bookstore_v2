package bean;

public class ReviewBean {
	
	private int bid;
	private String name;
	private String title;
	private int rate;
	private String content;
	
	public ReviewBean(int bid, String name, String title, int rate, String content) {
		super();
		this.bid = bid;
		this.name = name;
		this.title = title;
		this.rate = rate;
		this.content = content;
	}

	public int getBid() {
		return bid;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public int getRate() {
		return rate;
	}

	public String getContent() {
		return content;
	}
	
	

	

}

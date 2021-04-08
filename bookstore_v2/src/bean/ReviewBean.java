package bean;

import java.sql.Date;

public class ReviewBean {
	
	private int bid;
	private String name;
	private String title;
	private int rate;
	private String content;
	private Date date;
	
	public ReviewBean(int bid, String name, String title, int rate, String content, Date date) {
		super();
		this.bid = bid;
		this.name = name;
		this.title = title;
		this.rate = rate;
		this.content = content;
		this.date = date;
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
	
	public Date getDate() {
		return date;
	}

}

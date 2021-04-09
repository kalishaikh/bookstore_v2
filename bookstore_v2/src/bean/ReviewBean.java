package bean;

import java.sql.Date;

public class ReviewBean {
	
	private int bid;
	private String name;
	private String title;
	private int rate;
	private double avgRate;
	
	public double getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(double avgRate) {
		this.avgRate = avgRate;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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
	
	public ReviewBean() {
		
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

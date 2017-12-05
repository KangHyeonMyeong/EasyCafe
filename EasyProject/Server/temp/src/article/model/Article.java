package article.model;

import java.util.Date;

public class Article {

	private Integer num;
	private String title;
	private Date notice_time;
	
	public Article(Integer num, String title, Date notice_time) {
		this.num = num;
		this.title = title;
		this.notice_time = notice_time;
	}

	public Integer getNum() {
		return num;
	}

	public String getTitle() {
		return title;
	}

	public Date getNotice_time() {
		return notice_time;
	}
	
	
}

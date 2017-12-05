package com.cafe.model;

import java.sql.Date;

public class Notice {
	
	private int num;
	private String title;
	private Date notice_time;
	private String content;
	
	public Notice(int num, String title, Date notice_time, String content) {
		this.num = num;
		this.title = title;
		this.notice_time = notice_time;
		this.content = content;
	}

	public int getNum() {
		return num;
	}

	public String getTitle() {
		return title;
	}

	public Date getNotice_time() {
		return notice_time;
	}

	public String getContent() {
		return content;
	}

	
}

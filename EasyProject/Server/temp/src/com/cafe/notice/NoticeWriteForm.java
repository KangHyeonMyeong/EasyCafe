package com.cafe.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class NoticeWriteForm implements CommandHandler{

	private static final String FORM_VIEW = "/view/notice/noticeWrite.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		
		return FORM_VIEW;
	}

}

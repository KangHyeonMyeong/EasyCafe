package com.cafe.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.NoticeDao;
import com.cafe.model.Notice;

import mvc.command.CommandHandler;

public class NoticeWriteHandler implements CommandHandler{

	private static final String FORM_VIEW = "/view/notice/noticeWritePro.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		
		Notice notice = new Notice(
				0,
				req.getParameter("title"),
				null,
				req.getParameter("content")
				);
		
		NoticeDao dbPro = NoticeDao.getInstace();
		dbPro.write(notice);		
		
		return FORM_VIEW;
	}

}

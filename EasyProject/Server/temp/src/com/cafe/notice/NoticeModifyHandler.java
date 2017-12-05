package com.cafe.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.NoticeDao;
import com.cafe.model.Notice;

import mvc.command.CommandHandler;

public class NoticeModifyHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/notice/noticeModifyPro.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		Notice notice = new Notice(
				num,
				req.getParameter("title"),
				null,
				req.getParameter("content")
				);
		
		NoticeDao dbPro = NoticeDao.getInstace();
		dbPro.update(notice);
		
		req.setAttribute("pageNum", new Integer(pageNum));
		
		return FORM_VIEW;
	}

	

}

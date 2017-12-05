package com.cafe.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.NoticeDao;

import mvc.command.CommandHandler;

public class NoticeDeleteHandler implements CommandHandler{

	private static final String FORM_VIEW = "/view/notice/noticeDelete.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		NoticeDao dbPro = NoticeDao.getInstace();
		int delete = dbPro.delete(num);
		
		req.setAttribute("pageNum", pageNum);
		
		return FORM_VIEW;
	}

}

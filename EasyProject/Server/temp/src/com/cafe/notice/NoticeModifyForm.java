package com.cafe.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.NoticeDao;
import com.cafe.model.Notice;

import mvc.command.CommandHandler;

public class NoticeModifyForm implements CommandHandler{

	private static final String FORM_VIEW = "/view/notice/noticeModifyForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		NoticeDao dbPro = NoticeDao.getInstace();
		Notice notice = dbPro.updateGet(num);
		
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("notice", notice);
		
		return FORM_VIEW;
	}

}

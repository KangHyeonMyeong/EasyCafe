package com.cafe.notice;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.NoticeDao;

import mvc.command.CommandHandler;

public class NoticeListHandler implements CommandHandler{

	private static final String FORM_VIEW = "/view/notice/noticeList.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		
		Connection conn = null;
		
		String pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum ="1";
		}
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int count = 0;
		int number = 0;
		List noticeList = null;
		
		NoticeDao dbPro = NoticeDao.getInstace();
		count = dbPro.count();

		if(count == (currentPage-1)*pageSize) {
			currentPage -=1;
		}
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		if(count > 0) {
			noticeList = dbPro.list(startRow);
		} else {
			noticeList = Collections.EMPTY_LIST;
		}
		
		number = count-(currentPage - 1) * pageSize;
		
		req.setAttribute("currentPage", new Integer(currentPage));
		req.setAttribute("startRow", new Integer(startRow));
		req.setAttribute("endRow", new Integer(endRow));
		req.setAttribute("count", new Integer(count));
		req.setAttribute("pageSize", new Integer(pageSize));
		req.setAttribute("number", new Integer(number));
		req.setAttribute("noticeList", noticeList);
		
		return FORM_VIEW;
	}

}

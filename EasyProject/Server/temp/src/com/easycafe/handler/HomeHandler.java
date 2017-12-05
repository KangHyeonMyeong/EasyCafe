package com.easycafe.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.HomeDao;

import mvc.command.CommandHandler;

public class HomeHandler implements CommandHandler {

	private static final String FORM_VIEW = "/src/jsp/main.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum ="1";
		}
		
		List list = null;
		
		HomeDao dbPro = HomeDao.getInstance();
		list = dbPro.list();
		
		req.setAttribute("list", list);
		req.setAttribute("pageNum", new Integer(pageNum));
		
		return FORM_VIEW;
	}

}

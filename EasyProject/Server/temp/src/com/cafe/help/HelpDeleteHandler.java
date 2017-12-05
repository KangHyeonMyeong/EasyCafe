package com.cafe.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.HelpDao;

import mvc.command.CommandHandler;

public class HelpDeleteHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/help/helpDelete.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");		
		
		HelpDao dbPro = HelpDao.getInstance();		
		int delelte = dbPro.delete(num);
		
		req.setAttribute("pageNum", pageNum);
		
		return FORM_VIEW;
	}

}

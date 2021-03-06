package com.cafe.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.HelpDao;
import com.cafe.model.Help;

import mvc.command.CommandHandler;

public class HelpDetailHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/help/helpDetail.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		HelpDao dbPro = HelpDao.getInstance();
		Help help = dbPro.select(num);
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("help", help);
		
		return FORM_VIEW;
	}

}

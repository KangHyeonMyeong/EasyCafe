package com.cafe.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.HelpDao;
import com.cafe.model.Help;

import mvc.command.CommandHandler;

public class HelpModifyHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/help/helpModifyPro.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		Help help = new Help();
		help.setTitle(req.getParameter("title"));
		help.setContent(req.getParameter("content"));
		help.setNum(num);
		
		HelpDao dbPro = HelpDao.getInstance();
		dbPro.update(help);
		
		req.setAttribute("pageNum", new Integer(pageNum));
		
		
		return FORM_VIEW;
	}

}

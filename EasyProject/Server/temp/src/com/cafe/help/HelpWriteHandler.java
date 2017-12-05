package com.cafe.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.HelpDao;
import com.cafe.model.Help;

import mvc.command.CommandHandler;

public class HelpWriteHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/help/helpWritePro.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		
		Help help = new Help();
		help.setTitle(req.getParameter("title"));
		help.setContent(req.getParameter("content"));
		
		HelpDao dbPro = HelpDao.getInstance();
		dbPro.write(help);
			
		return FORM_VIEW;
	}

}

package com.cafe.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class HelpWriteForm implements CommandHandler{

	private static final String FORM_VIEW = "/view/help/helpWrite.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		return FORM_VIEW;
	}

}

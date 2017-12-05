package com.easycafe.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class MenuHandler implements CommandHandler {

	private static final String FORM_VIEW = "/src/jsp/menu.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		System.out.println("ºÒ¸®³Ä?");
		return FORM_VIEW;
	}

}

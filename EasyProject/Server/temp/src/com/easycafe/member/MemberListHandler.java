package com.easycafe.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.model.Member;

import mvc.command.CommandHandler;

public class MemberListHandler implements CommandHandler {


	private static final String FORM_VIEW = "/src/jsp/mem.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String val = req.getParameter("e_mail");
		
		try {
			//������
			return FORM_VIEW;
		}catch (Exception e) {
			System.out.println("�ڵ鷯���� �����߻�");
			return null;
		}
	}

}

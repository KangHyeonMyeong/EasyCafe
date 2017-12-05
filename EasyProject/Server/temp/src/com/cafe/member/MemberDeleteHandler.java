package com.cafe.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.MemberDao;

import mvc.command.CommandHandler;

public class MemberDeleteHandler implements CommandHandler{

	private static final String FORM_VIEW = "/view/member/memberDelete.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		MemberDao dbPro = MemberDao.getInstance();
		int delete = dbPro.MemberDelete(num);
		
		req.setAttribute("pageNum", new Integer(pageNum));
		
		return FORM_VIEW;
	}

}

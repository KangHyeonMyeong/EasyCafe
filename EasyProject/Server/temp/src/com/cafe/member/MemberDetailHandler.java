package com.cafe.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.MemberDao;
import com.cafe.model.Member;

import mvc.command.CommandHandler;

public class MemberDetailHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/member/memberDetail.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		MemberDao dbPro = MemberDao.getInstance();
		Member member = dbPro.MemberDetail(num); 
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("member", member);
		
		return FORM_VIEW;
	}

}

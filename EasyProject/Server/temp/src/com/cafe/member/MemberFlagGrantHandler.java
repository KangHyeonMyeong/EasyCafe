package com.cafe.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.dao.MemberDao;
import com.cafe.model.Member;

import mvc.command.CommandHandler;

public class MemberFlagGrantHandler implements CommandHandler{

	private static final String FORM_VIEW = "/view/member/memberFlagGrant.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		int manager_flag = 1;
		
		Member member = new Member();
			member.setNum(num);
			member.setManager_flag(manager_flag);
			
		MemberDao dbPro = MemberDao.getInstance();
		dbPro.FlagGrant(member);
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		
		return FORM_VIEW;
	}

}

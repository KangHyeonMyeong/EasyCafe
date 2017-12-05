package com.easycafe.member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.cafe.dao.MemberDao;
import com.cafe.model.Member;

import jdbc.connection.ConnectionProvider;

public class MemberListService {

	private MemberDao memberDao = new MemberDao();
	
	public Member getMember(String e_mail, String password, String name, Date birth, String phone, int manager_flag) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, e_mail);
			if(member == null) {
				System.out.println("���񽺿��� �����߻�!!");
			}
			return new Member(0 ,e_mail,password,name,birth,phone,manager_flag);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

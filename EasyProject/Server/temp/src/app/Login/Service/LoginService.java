package app.Login.Service;

import java.sql.Connection;


import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;
import dao.MemberDao;
import model.Member;


public class LoginService {
	MemberDao memberDao = new MemberDao();
	
	public void join(Member member){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.insert(conn, member);
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			 throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void secession(String e_mail){
		Connection conn =null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.deleteId(conn, e_mail);
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			 throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public String overlopCheck(String e_mail){
		Connection conn =null;
		String result = null;
		try{
			conn = ConnectionProvider.getConnection();
			result = memberDao.overlopID(conn, e_mail);
			return result;
		}catch (Exception e) {
			throw new RuntimeException();
		}
		finally {
			JdbcUtil.close(conn);
		}
	}
	
	public Member loginMember(String e_mail, String password){
		Connection conn =null;
		try{
			conn = ConnectionProvider.getConnection();
			return memberDao.selectById(conn, e_mail, password);
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	

}






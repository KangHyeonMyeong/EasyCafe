package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe.model.Member;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;


public class MemberDao {

private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	public MemberDao() {
		
	}
	
	
	public Member selectById(Connection conn, String e_mail) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from member2 where e_mail =?");
			pstmt.setString(1, e_mail);
			rs = pstmt.executeQuery();
			Member member = null;
			if(rs.next()) {
				member = new Member(
										rs.getInt("num"),
										rs.getString("e_mail"),
										rs.getString("password"),
										rs.getString("name"),
										rs.getDate("birth"),
										rs.getString("phone"),
										rs.getInt("manager_flag"));
			}
			return member;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	

	
	
	public int getMemberCount() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select count(*) from member2");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return x;
	}
	
	public List MemberList(int start) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List memberList = null;
		
		try {
			conn = ConnectionProvider.getConnection();

			pstmt = conn.prepareStatement("select * from member2 order by num desc limit ?,10");
			pstmt.setInt(1, start-1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberList = new ArrayList();
				do {
					Member member = new Member();
					member.setNum(rs.getInt("num"));
					member.setE_mail(rs.getString("e_mail"));
					member.setPassword(rs.getString("password"));
					member.setName(rs.getString("name"));
					member.setBirth(rs.getDate("birth"));
					member.setPhone(rs.getString("phone"));
					member.setManager_flag(rs.getInt("manager_flag"));
					memberList.add(member);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return memberList;
	}
	
	public Member MemberDetail(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement("select * from member2 where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setNum(rs.getInt("num"));
				member.setE_mail(rs.getString("e_mail"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getDate("birth"));
				member.setPhone(rs.getString("phone"));
				member.setManager_flag(rs.getInt("manager_flag"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return member;
	}
	
	public int FlagGrant(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("update member2 set manager_flag=? where num=?");
			pstmt.setInt(1, member.getManager_flag());
			pstmt.setInt(2, member.getNum());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public int MemberDelete(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement("delete from member2 where num=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			x=1;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return x;
		
		
	}
	
	
	
	
	
	
	
}

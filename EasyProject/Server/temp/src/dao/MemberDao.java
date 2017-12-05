package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import model.Member;

public class MemberDao {
	
	public void insert(Connection conn, Member member) throws SQLException{
		PreparedStatement pstmt = null;
		String insertQuery=
				"INSERT INTO member2(e_mail, password, name, birth, phone, manager_flag) VALUES(?,?,?,?,?,2)";
		try{
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1, member.getE_mail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getPhone());
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public String overlopID(Connection conn, String e_mail) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectQuery="SELECT e_mail FROM member2 WHERE e_mail=?";
		try{
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, e_mail);
			rs = pstmt.executeQuery();
			if(rs != null){
				if( rs.next()){
					return "overlop";
				}else{
					return "possible";
				}
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void deleteId(Connection conn , String e_mail) throws SQLException{
		PreparedStatement pstmt = null;
		String delete = "DELETE FROM member2 WHERE e_mail=?";
		try{
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, e_mail);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Member selectById(Connection conn, String e_mail , String password) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectQuery ="SELECT e_mail, password, name, birth, phone, manager_flag FROM member2 WHERE e_mail=? AND password=?";
		
		try{
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, e_mail);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if( rs != null){
				if( rs.next()){
					return new Member(rs.getString(1), null
							, rs.getString(3)
							, rs.getString(4)
							, rs.getString(5)
							, rs.getString(6)
							);
				}else{
					return null;
				}
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		

	}
	
	public void modifyPass( Connection conn, String e_mail, String newPassword) throws SQLException{
		PreparedStatement pstmt = null;
		String modifyQuery = "UPDATA member2 SET password = ? WHERE e_mail=?";
		try{
			pstmt= conn.prepareStatement(modifyQuery);
			pstmt.setString(1, e_mail);
			pstmt.setString(2, newPassword);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void modifyInfo( Connection conn , String e_mail , String phone , String birth) throws SQLException{
		PreparedStatement pstmt = null;
		String modifyQuery = "UPDATE member2 SET phone=?,birth=? WHERE e_mail=?";
		try{
			pstmt = conn.prepareStatement(modifyQuery);
			pstmt.setString(1, phone);
			pstmt.setString(2, birth);
			pstmt.setString(3, e_mail);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
}

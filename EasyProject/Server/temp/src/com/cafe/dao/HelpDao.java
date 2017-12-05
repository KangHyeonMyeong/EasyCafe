package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe.model.Help;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class HelpDao {
	
	private static HelpDao instance = new HelpDao();
	
	public static HelpDao getInstance() {
		return instance;
	}

	private HelpDao() {
		
	}
	
	// app
	public List<Help> HelpContent() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Help> helpList = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from help order by num desc");
			rs = pstmt.executeQuery();
			if(rs.next() ) {
				helpList = new ArrayList<Help>();
				do {
					Help help = new Help();
					help.setNum(rs.getInt(1));
					help.setTitle(rs.getString(2));
					help.setNotice_time(rs.getDate(3));
					help.setContent(rs.getString(4));
					helpList.add(help);
				}while(rs.next());	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			
		}
		return helpList;
	}
	
	public int count() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select count(*) from help");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return x;
	}
	
	public List<Help> list(int start) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Help> helpList = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement("select * from help order by num desc limit ?,10");
			pstmt.setInt(1, start-1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				helpList = new ArrayList<Help>();
				do {
					Help help = new Help();
					help.setNum(rs.getInt("num"));
					help.setTitle(rs.getString("title"));
					help.setNotice_time(rs.getDate("notice_time"));
					help.setContent(rs.getString("content"));
					helpList.add(help);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return helpList;
	}
	
	public Help select(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Help help = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from help where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				help = new Help();
				help.setNum(rs.getInt("num"));
				help.setTitle(rs.getString("title"));
				help.setNotice_time(rs.getDate("notice_time"));
				help.setContent(rs.getString("content"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return help;
	}


	public Help write(Help help) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("insert into help(title, content) values(?, ?)");
			pstmt.setString(1, help.getTitle());
			pstmt.setString(2, help.getContent());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return null;
	}
	
	public int delete(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("delete from help where num=?");
			pstmt.setInt(1, num);
			
			return pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public int update(Help help) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("update help set title=?, content=? where num=?");
			pstmt.setString(1, help.getTitle());
			pstmt.setString(2, help.getContent());
			pstmt.setInt(3, help.getNum());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public Help updateGet(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Help help = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from help where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				help = new Help();
				help.setNum(rs.getInt("num"));
				help.setTitle(rs.getString("title"));
				help.setNotice_time(rs.getDate("notice_time"));
				help.setContent(rs.getString("content"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return help;
	}
	
}

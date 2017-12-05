package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe.model.Notice;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class NoticeDao {
	
	private static NoticeDao instance = new NoticeDao();
	
	public static NoticeDao getInstace() {
		return instance;
	}

	private NoticeDao() {
		
	}
	
	// app
	public List<Notice> NoticeContent() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> noticeList = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from notice order by num desc");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeList = new ArrayList<Notice>();
				do {
					Notice notice = new Notice(
							rs.getInt("num"), 
							rs.getString("title"),
							rs.getDate("notice_time"),
							rs.getString("content"));
					noticeList.add(notice);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return noticeList;
	}
	
	public int count() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select count(*) from notice");
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
	
	public List<Notice> list(int start) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> noticeList = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from notice order by num desc limit ?,10");
			pstmt.setInt(1, start-1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeList = new ArrayList<Notice>();
				do {
					Notice notice = new Notice(
							rs.getInt("num"),
							rs.getString("title"),
							rs.getDate("notice_time"),
							rs.getString("content"));
					noticeList.add(notice);
				}while(rs.next());				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return noticeList;
	}
	
	public Notice select(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new Notice(
						rs.getInt("num"),
						rs.getString("title"),
						rs.getDate("notice_time"),
						rs.getString("content")
						);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return notice;
	}
	
	public Notice write(Notice notice) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("insert into notice(title, content) values(?,?)");
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
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
			pstmt = conn.prepareStatement("delete from notice where num=?");
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public int update(Notice notice) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("update notice set title=?, content=? where num=?");
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, notice.getNum());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	public Notice updateGet(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Notice notice = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new Notice(
						rs.getInt("num"),
						rs.getString("title"),
						rs.getDate("notice_time"),
						rs.getString("content"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return notice;
	}
	
}

package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe.model.Notice;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class HomeDao {
	
	private static HomeDao instance = new HomeDao();
	
	public static HomeDao getInstance() {
		return instance;
	}
	
	private HomeDao() {
		
	}

	public List list() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List noticeList = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from notice order by num desc limit 0,8");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeList = new ArrayList();
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
	
}

package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cafe.model.Terms;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class TermsDao {
	
	public Terms TermsContent() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select * from terms limit 1");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Terms(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3));			
			}
			return null;
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	
}

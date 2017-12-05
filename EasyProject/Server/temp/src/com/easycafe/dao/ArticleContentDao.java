package com.easycafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import article.model.ArticleContent;

public class ArticleContentDao {
	
	public ArticleContent insert(Connection conn, ArticleContent content) throws SQLException{
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("insert into notice_content" + "(notice_no, content) values(?,?)");
			pstmt.setInt(1, content.getNum());
			pstmt.setString(2, content.getContent());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return content;
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public ArticleContent selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from notice_content where notice_no =?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent content = null;
			if(rs.next()) {
				content = new ArticleContent(rs.getInt("notice_no"), rs.getString("content"));
			}
			return content;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import model.TableInfo;

public class TableInfoDao {
	
	/*테이블의 정보를 지정하는 매서드( 세로삽입)*/
	public void insert(Connection conn, TableInfo tableInfo)throws SQLException{
		PreparedStatement pstmt = null;
		String insertQ ="INSERT INTO table_info VALUES (?,?,?,?,?);";
		try{
			pstmt = conn.prepareStatement(insertQ);
			pstmt.setInt(1, tableInfo.getTable_number());
			pstmt.setString(2,  tableInfo.getBlanket());
			pstmt.setString(3,  tableInfo.getConsent());
			pstmt.setString(4,  tableInfo.getCushion());
			pstmt.setString(5,  tableInfo.getSeat_image());
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	/*tableInfo 객체를 받아서 그에 맞는 테이블 넘버의 정보를 수정하는 메서드*/
	public void update(Connection conn ,TableInfo tableInfo) throws SQLException{
		PreparedStatement pstmt = null;
		String updateQ ="UPDATE table_info SET blanket=?, consent=?, cushion=?, seat_image=? WHERE table_number=?";
		try{
			pstmt = conn.prepareStatement(updateQ);
			pstmt.setString(1, tableInfo.getBlanket());
			pstmt.setString(2, tableInfo.getConsent());
			pstmt.setString(3, tableInfo.getCushion());
			pstmt.setString(4, tableInfo.getSeat_image());
			pstmt.setInt(5, tableInfo.getTable_number());
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	/*테이블 번호를 파라미터로 받아 그에 맞는 테이블 정보를 반환하는 매소드*/
	public TableInfo select(Connection conn, int num) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectTable = "SELECT * FROM table_info WHERE table_number=?";
		try{
			pstmt = conn.prepareStatement(selectTable);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return new TableInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5)
						);
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	/*테이블 정보를 삭제하는 메소드*/
	public void delete(Connection conn , int table_num) throws SQLException{
		PreparedStatement pstmt = null;
		String deleteTable ="DELETE FROM table_info WHERE table_number=?";
		try{
			pstmt = conn.prepareStatement(deleteTable);
			pstmt.setInt(1, table_num);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
}

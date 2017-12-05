package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import model.CafeStatus;

public class CafeStatusDao {
	
	/*받은 센서의 데이터를 DB에 기록*/
	public void record(Connection conn, CafeStatus status ) throws SQLException{
		PreparedStatement pstmt = null;
		String recording ="INSERT INTO cafe_info VALUES"
				+ "(?,?,?,?,?,?,?,?,now());";
		try{
			pstmt = conn.prepareStatement(recording);
			pstmt.setInt(1, status.getTotal_seat());
			pstmt.setInt(2, status.getCurrent_seat());
			pstmt.setInt(3, status.getTemp_level());
			pstmt.setInt(4, status.getHumidity_level());
			pstmt.setInt(5, status.getNoise_level());
			pstmt.setString(6, status.getTissue_state());
			pstmt.setString(7, status.getTrash_can_state());
			pstmt.setString(8, status.getToilet());
			pstmt.executeUpdate();
			//기록의 최대 날짜?
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	/*가장 최근에 업데이트된 레코드를 읽어 모델로 반환해준다*/
	public CafeStatus readRecord(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		String readCurrent ="SELECT * FROM cafe_info ORDER BY regdate DESC LIMIT 1";
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(readCurrent);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//CafeStatus형태로 반환해준다.
				return new CafeStatus(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8));
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import jdbc.JdbcUtil;
import model.Sensor;

public class SensorDao {
	public void insert(Connection conn, Sensor sensor) throws SQLException{
		PreparedStatement pstmt = null;
		String insertQ = "INSERT INTO sensor VALUES (?,?,?,?)";
		try{
			pstmt = conn.prepareStatement(insertQ);
			pstmt.setString(1, sensor.getSensor_id());
			pstmt.setString(2, sensor.getCategory());
			pstmt.setString(3, sensor.getSensor_value());
			pstmt.setTimestamp(4, toTimestamp(sensor.getSensor_date()));
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	/*카테고리로 조회하여 그에 맞는 센서정보를 리스트로 반환*/
	public ArrayList<Sensor> selectByCategory(Connection conn , String category) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Sensor> list = new ArrayList<>();
		String select = "SELECT * FROM sensor WHERE category=?";
		try{
			pstmt = conn.prepareStatement(select);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if(rs != null){
				while(rs.next()){
					list.add(new Sensor(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getTimestamp(4)));
				}
				return list;
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public ArrayList<Sensor> select(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Sensor> list = new ArrayList<>();
		String select = "SELECT * FROM sensor";
		try{
			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();
			if(rs != null){
				while(rs.next()){
					list.add(new Sensor(rs.getString(1), rs.getString(2), rs.getString(3) , rs.getTimestamp(4)));
				}
				return list;
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	/*지정된 아이디로 조회하여 record를 삭제하는 메서드*/
	public void delete(Connection conn, String ID) throws SQLException{
		PreparedStatement pstmt = null;
		String delete="DELETE FROM sensor WHERE sensor_id=?";
		
		try{
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, ID);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	/* 아이디해당 토큰을 업데이트 */
	public void modifiedsensor(Connection conn, String cur_id , Sensor modi) throws SQLException{
		PreparedStatement pstmt = null;
		String update ="UPDATE sensor SET category=?,sensor_id=? WHERE sensor_id=?";
		try{
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, modi.getCategory());
			pstmt.setString(2, modi.getSensor_id());
			pstmt.setString(3, cur_id);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void update(Connection conn, String sensor_id, String value) throws SQLException{
		PreparedStatement pstmt = null;
		String update = "UPDATE sensor SET sensor_value=? WHERE sensor_id=?";
		try{
			pstmt =  conn.prepareStatement(update);
			pstmt.setString(1, value);
			pstmt.setString(2, sensor_id);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
}

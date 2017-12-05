package web.Sensor.modified;

import java.sql.Connection;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;
import dao.SensorDao;
import model.Sensor;

public class SensorModifiedService {
	SensorDao sensorDao = new SensorDao();
	public void modified(String cur_id, Sensor modi){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			sensorDao.modifiedsensor(conn, cur_id, modi);
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	public void delete(String del_id){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			sensorDao.delete(conn, del_id);
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}

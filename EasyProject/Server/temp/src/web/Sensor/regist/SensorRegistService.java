package web.Sensor.regist;

import java.sql.Connection;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;
import dao.SensorDao;
import model.Sensor;

public class SensorRegistService {
	SensorDao sensorDao = new SensorDao();
	public void registSensor(Sensor sensor){
		Connection conn = null;
		try{
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			sensorDao.insert(conn, sensor);
			
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}
		finally {
			JdbcUtil.close(conn);
			
		}
	}
}

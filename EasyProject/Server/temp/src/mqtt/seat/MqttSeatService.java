package mqtt.seat;

import java.sql.Connection;


import dao.SensorDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MqttSeatService {
	SensorDao sensorDao = new SensorDao();
	
	public void updateSeat(String id, String value){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			sensorDao.update(conn, id, value);
			conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("MqttUpdateSeatError");
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}

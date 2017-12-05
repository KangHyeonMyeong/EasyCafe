package mqtt.sensor;

import java.sql.Connection;


import dao.CafeStatusDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.CafeStatus;

public class MqttSensorService {
	CafeStatusDao cafeStatusDao = new CafeStatusDao();
	
	public void updateStatus(CafeStatus status){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			cafeStatusDao.record(conn, status);
			conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("MqttUpdateStatusError");
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
}

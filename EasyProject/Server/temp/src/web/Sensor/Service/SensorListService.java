package web.Sensor.Service;

import java.sql.Connection;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import dao.SensorDao;
import model.Sensor;

public class SensorListService {
	private SensorDao sensorDao = new SensorDao();
	
	public SensorPage getPage(String category){
		try(Connection conn = ConnectionProvider.getConnection()){
			List<Sensor> sensorList = sensorDao.selectByCategory(conn, category);
			SensorPage sensorPage = new SensorPage(category, sensorList);
			return sensorPage;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public SensorPage getPage(){
		try(Connection conn = ConnectionProvider.getConnection()){
			List<Sensor> sensorList = sensorDao.select(conn);
			SensorPage sensorPage = new SensorPage(null, sensorList);
			return sensorPage;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

package web.Sensor.Service;

import java.util.List;

import model.Sensor;

public class SensorPage {
	private String sensor_category;
	private List<Sensor> sensorList;
	public SensorPage(String sensor_category, List<Sensor> sensorList) {
		super();
		this.sensor_category = sensor_category;
		this.sensorList = sensorList;
	}
	public String getSensor_category() {
		return sensor_category;
	}
	public List<Sensor> getSensorList() {
		return sensorList;
	}
	
	
}

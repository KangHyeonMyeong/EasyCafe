package web.Sensor.Service;

import java.util.ArrayList;

import model.Sensor;

public class SensorListRequest {
	private Sensor sensor;
	private ArrayList<Sensor> sensorList = new ArrayList<>();
	public SensorListRequest(Sensor sensor, ArrayList<Sensor> sensorList) {
		super();
		this.sensor = sensor;
		this.sensorList = sensorList;
	}
	public Sensor getSensor() {
		return sensor;
	}
	public ArrayList<Sensor> getSensorList() {
		return sensorList;
	}
}

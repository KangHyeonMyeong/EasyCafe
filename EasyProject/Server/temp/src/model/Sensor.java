package model;

import java.util.Date;

public class Sensor {
	private String sensor_id;
	private String category;
	private String sensor_value;
	private Date sensor_date;
	
	public Sensor(String sensor_id, String category, String sensor_value, Date sensor_date) {
		super();
		this.sensor_id = sensor_id;
		this.category = category;
		this.sensor_value = sensor_value;
		this.sensor_date = sensor_date;
	}

	public String getSensor_id() {
		return sensor_id;
	}

	public String getCategory() {
		return category;
	}

	public String getSensor_value() {
		return sensor_value;
	}

	public Date getSensor_date() {
		return sensor_date;
	}
	
	
	
}

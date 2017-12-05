package mqtt.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import mqtt.seat.MqttSeatHandler;
import mqtt.sensor.MqttSensorHandler;


public class MQTTSubscribe implements ServletContextListener {
	
	final static String MQTTBrokerIP= "tcp://192.168.0.156:1883";
	
	private MqttSeatHandler seatHandler = new MqttSeatHandler();
	private MqttSensorHandler sensorHandler = new MqttSensorHandler();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try{
			MqttClient client = new MqttClient(MQTTBrokerIP,
					MqttClient.generateClientId(),
					new MemoryPersistence());
			
			client.connect();
			
			client.setCallback(new MqttCallback() {
				
				@Override
				public void messageArrived(String topic, MqttMessage msg) throws Exception {
					if( topic.contains("seat")) seatHandler.ControlData(msg.toString());
					else if( topic.contains("sensor")) sensorHandler.ControlData(msg.toString());
				}
				
				@Override
				public void deliveryComplete(IMqttDeliveryToken arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void connectionLost(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			client.subscribe("/cafe/#" , 1);
			System.out.println("start mqtt connection");
		}catch (MqttException e) {
			e.printStackTrace();
		}
		
	}

}

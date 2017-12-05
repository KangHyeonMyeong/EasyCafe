package mqtt.seat;

import mqtt.Listener.MqttHandler;

public class MqttSeatHandler implements MqttHandler {
	private MqttSeatService service = new MqttSeatService();

	@Override
	public void ControlData(String data) {
		parseData(data);
	}
	
	private void parseData(String data){
		String seatArray[] = data.split(",");
		for(int i = 0 ; i < seatArray.length ; i ++){
			String parse[] = seatArray[i].split(":");
			parse[1] = (parse[1].equals("1") ? "Y" : "N" );
			service.updateSeat(parse[0], parse[1]);
		}
	}

}

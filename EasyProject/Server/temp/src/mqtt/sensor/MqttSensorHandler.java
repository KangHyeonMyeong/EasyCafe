package mqtt.sensor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.CafeStatus;
import mqtt.Listener.MqttHandler;
import web.Push.Service.PushService;

public class MqttSensorHandler implements MqttHandler {
	MqttSensorService service = new MqttSensorService();
	PushService pushService = new PushService();
	Map<String, String> sensorlist;
	static int old_tissue;
	static int old_toilet;
	static int filter = 0;
	@Override
	public void ControlData(String data) {
		sensorlist = parseData(data);
		pushEvent();
	}
	
	private Map<String,String> parseData(String data){
		String sensor[] = data.split(",");
		Map<String, String> list = new HashMap<>();
		for(int i = 0; i < sensor.length ; i++){
			String parse[] = sensor[i].split(":");
			list.put(parse[0], parse[1]);
		}
		int trashLevel = (( 24 - Integer.parseInt(list.get("trash")) )* 100 / 18);
		String trashStr = ""+ trashLevel;
		CafeStatus cafeStatus = new CafeStatus(20,
				Integer.parseInt(list.get("curSeat")),
				Integer.parseInt(list.get("temp")),
				Integer.parseInt(list.get("humi")),
				50,
				list.get("tissue"),
				trashStr,
				list.get("toilet"));
		
		service.updateStatus(cafeStatus);
		return list;
	}
	
	private void pushEvent(){
		if( Integer.parseInt(sensorlist.get("toilet")) == 1 ){
			if( Integer.parseInt(sensorlist.get("toilet")) != old_toilet){
				sendMethod("화장실" , "화장실 휴지를 채워주세요.");
			}
		}
		old_toilet = Integer.parseInt(sensorlist.get("toilet"));
		
		if( Integer.parseInt(sensorlist.get("tissue")) == 1 ) {
			if( Integer.parseInt(sensorlist.get("tissue")) != old_tissue){
				sendMethod("티슈" , "매장 티슈를 채워주세요.");
			}
		}
		old_tissue = Integer.parseInt(sensorlist.get("tissue"));
		
		int curTrashLevel = ( 24 - Integer.parseInt(sensorlist.get("trash")) )* 100 / 18;
		if( curTrashLevel >= 90 ) {
			filter++;
		}else {
			filter = 0;
		}
		if(filter == 3) {
			sendMethod("쓰레기" , "쓰레기통을 비워주세요." );
		}
		System.out.println("TrashLevel:" + curTrashLevel+"% - "+ filter);
		System.out.println("Tissue:" + sensorlist.get("tissue"));
		System.out.println("Toilet:" + sensorlist.get("toilet"));
		System.out.println(new Date());
	}
	
	private void sendMethod(String main ,String content){
		ArrayList<String> tokenList = pushService.readTokenList();
		if( !tokenList.isEmpty()) {
			pushService.sendPush(tokenList, main, content);
		}
	}
}

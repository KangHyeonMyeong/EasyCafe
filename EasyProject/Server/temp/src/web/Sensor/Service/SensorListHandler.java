package web.Sensor.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class SensorListHandler implements CommandHandler {
	SensorListService service = new SensorListService();
	String FORMVIEW ="/view/sensor/sensorList.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String category = req.getParameter("category");
		SensorPage sensorPage = null;
		
		if(category != null){
			sensorPage = service.getPage(category);
		}else{
			sensorPage = service.getPage();
		}
		
		req.setAttribute("sensorPage", sensorPage);
		return FORMVIEW;
		
	}
}

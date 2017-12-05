package web.Sensor.regist;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Sensor;
import mvc.command.CommandHandler;

public class SensorRegistHandler implements CommandHandler {
	private String FORMVIEW = "/view/sensor/success.jsp";
	SensorRegistService service = new SensorRegistService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return "/view/sensor/registForm.jsp";
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Date regist_date = new Date();
		String sensor_id = req.getParameter("sensor_id");
		String category = req.getParameter("name");
		Sensor sensor = null;
		if( sensor_id !=null && category != null){
			sensor = new Sensor(sensor_id, category, "-", regist_date);
		}
		if( sensor != null){
			service.registSensor(sensor);
		}
		return FORMVIEW;
	}
}

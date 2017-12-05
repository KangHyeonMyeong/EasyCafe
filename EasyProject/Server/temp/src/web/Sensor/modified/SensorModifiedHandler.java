package web.Sensor.modified;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SensorDao;
import model.Sensor;
import mvc.command.CommandHandler;

public class SensorModifiedHandler implements CommandHandler {
	private final String FORMVIEW ="/view/sensor/success.jsp";
	private SensorModifiedService service = new SensorModifiedService();
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

	private String processForm(HttpServletRequest req, HttpServletResponse res){
		return "/view/sensor/modifiedForm.jsp";
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res){
		if(req.getParameter("action").equals("modify")){
			Sensor sensor = new Sensor(req.getParameter("modi_id") ,req.getParameter("name"), null ,null);
			String cur_id = req.getParameter("cur_id");
			service.modified(cur_id, sensor );
			return FORMVIEW;
		}else if (req.getParameter("action").equals("delete")){
			service.delete(req.getParameter("modi_id"));
			return FORMVIEW;
		}
		return FORMVIEW;
	}
}

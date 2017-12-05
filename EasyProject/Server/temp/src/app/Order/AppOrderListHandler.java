package app.Order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import model.Order;
import model.Result;
import mvc.command.CommandHandler;

public class AppOrderListHandler implements CommandHandler {
	AppOrderListService service = new AppOrderListService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req, res);
		}else if (req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		}else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String e_mail = req.getParameter("e_mail");
		JSONArray array = service.readOrderListById(e_mail);
		JSONObject object = new JSONObject();
		object.put("orderList", array);
		return object.toJSONString();
	}


	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String e_mail = req.getParameter("e_mail");
		JSONArray array = service.readOrderListById(e_mail);
		JSONObject object = new JSONObject();
		object.put("orderList", array);
		return object.toJSONString();
	}
}

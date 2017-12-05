package app.Logout.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.Logout.Service.AppLogoutService;
import model.Result;
import mvc.command.CommandHandler;

public class AppLogoutHandler implements CommandHandler {
	AppLogoutService service = new AppLogoutService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req, resp);
		}else if (req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, resp);
		}else{
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse resp){
		return "Fail";
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp){
		String e_mail = req.getParameter("e_mail");
		Result result = new Result();
		Gson gson = new Gson();
		service.deletePush(e_mail);
		result.setResultCode("200");
		result.setResultMessage("LOGOUT SUCCESS");
		return gson.toJson(result);
	}

}

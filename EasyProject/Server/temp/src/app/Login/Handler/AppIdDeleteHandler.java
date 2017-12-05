package app.Login.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.Login.Service.LoginService;
import mvc.command.CommandHandler;
import model.Result;

public class AppIdDeleteHandler implements CommandHandler {
LoginService service = new LoginService();
	
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
		String e_mail = req.getParameter("e_mail");
		service.secession(e_mail);
		Result result = new Result();
		result.setResultCode("400");
		result.setResultMessage("실패");
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp){
		String e_mail = req.getParameter("e_mail");
		service.secession(e_mail);
		Result result = new Result();
		result.setResultCode("200");
		result.setResultMessage("탈퇴성공");
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}

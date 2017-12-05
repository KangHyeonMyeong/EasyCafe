package app.Login.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import app.Login.Service.LoginService;
import mvc.command.CommandHandler;
import model.Result;

public class AppIdOverlopHandler implements CommandHandler {
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
		Result result = new Result();
		result.setResultCode("400");
		result.setResultMessage("post방식 통신 실패");
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp){
		String e_mail = req.getParameter("e_mail");
		String permission = service.overlopCheck(e_mail);
		JsonObject json = new JsonObject();
		json.addProperty("resultCode", "200");//통신에 성공시에는 200을 보냄
		json.addProperty("permission", permission);// 중복시에는 'overlop' 사용가능시 'possible' 을 보냄
		return json.toString();
	}

}

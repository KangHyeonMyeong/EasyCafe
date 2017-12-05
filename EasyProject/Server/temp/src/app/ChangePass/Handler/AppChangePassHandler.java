package app.ChangePass.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.ChangePass.Service.AppChangePassService;
import app.Login.Service.LoginService;
import model.Member;
import model.Result;
import mvc.command.CommandHandler;

public class AppChangePassHandler implements CommandHandler {
	LoginService service = new LoginService();
	AppChangePassService appService = new AppChangePassService();
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
		result.setResultMessage("Do not GET Method");
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp){
		String e_mail = req.getParameter("e_mail");
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		Member member = service.loginMember(e_mail, password);
		Result result = new Result();
		Gson gson = new Gson();
		if( member != null){
			appService.changePass(e_mail, newPassword);
			result.setResultCode("200");
			result.setResultMessage("비밀번호 변경완료");
			return gson.toJson(result);
		}else{
			result.setResultCode("400");
			result.setResultMessage("비밀번호가 일치하지 않습니다.");
			return gson.toJson(result);
		}
		
	}
}


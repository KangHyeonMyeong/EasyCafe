package app.Login.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.Login.Service.LoginService;
import mvc.command.CommandHandler;
import model.Member;
import model.PushList;
import web.Push.Service.PushService;

public class AppLoginHandler implements CommandHandler {
	LoginService service = new LoginService();
	PushService pushService = new PushService();
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
		String password = req.getParameter("password");
		Member member = service.loginMember(e_mail, password);
		
		Gson gson = new Gson();
		if( member != null){
			String token = req.getParameter("token");
			PushList user = new PushList(e_mail, member.getManage_flag(), token);
			pushService.saveUpdate(user);
			member.setResultCode("200");
			member.setResultMessage("LOGIN_OK");
			return gson.toJson(member);
		}else{
			member = new Member(null, null, null, null, null, null);
			member.setResultCode("400");
			member.setResultMessage("LOGIN_FAIL");
			return gson.toJson(member);
		}
	}

}

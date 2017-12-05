package app.ModifyInfo.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.ModifyInfo.Service.ModifyInfoService;
import model.Result;
import mvc.command.CommandHandler;

public class ModifyInfoHandler implements CommandHandler {
	ModifyInfoService modiService = new ModifyInfoService();
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
		String phone = req.getParameter("newPhone");
		String birth = req.getParameter("newBirth");
		modiService.modifyInfo(e_mail, phone, birth);
		Result result = new Result();
		result.setResultCode("200");
		result.setResultMessage("정보수정이 완료되었습니다.");
		Gson gson = new Gson();
		return gson.toJson(result);
	}
}

package app.CafeStatus.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import app.CafeStatus.Service.CafeStatusAppService;
import mvc.command.CommandHandler;
import model.CafeStatus;

public class CafestatusHandler implements CommandHandler {
	
	private CafeStatusAppService service = new CafeStatusAppService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CafeStatus cafeStatus = service.readStatus();
		Gson gson = new Gson();
		cafeStatus.setResultCode("200");
		cafeStatus.setResultMessage("조회되었습니다.");
		return gson.toJson(cafeStatus);
	}
	

}

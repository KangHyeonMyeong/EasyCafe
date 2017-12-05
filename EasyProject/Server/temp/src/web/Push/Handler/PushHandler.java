package web.Push.Handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import web.Push.Service.PushService;

public class PushHandler implements CommandHandler {
	private static final String FORM_VIEW = "/view/push/pushForm.jsp";
	private PushService service = new PushService();
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
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse resp){
		String message = req.getParameter("Message");
		String main = req.getParameter("main");
		ArrayList<String> tokenList = service.readTokenList();
		service.sendPush(tokenList, main, message);
		return FORM_VIEW;
	}
}

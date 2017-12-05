package app.MenuControll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import mvc.command.CommandHandler;

public class MenuSendHandler implements CommandHandler {
	private MenuSendService service = new MenuSendService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return service.menuList().toJSONString();
	}

}

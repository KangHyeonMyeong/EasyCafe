package app.setting.help;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.model.Help;
import com.google.gson.Gson;

import mvc.command.CommandHandler;

public class HelpContentHandler implements CommandHandler{
	
	private HelpContentService service = new HelpContentService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSubmit(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		List<Help> help = service.content();
		Gson gson = new Gson();
		return gson.toJson(help).toString();
	}

}

package app.setting.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.model.Notice;
import com.google.gson.Gson;

import mvc.command.CommandHandler;

public class NoticeContentHandler implements CommandHandler{

	private NoticeContentService service = new NoticeContentService();
	
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
		List<Notice> notice = service.content();
		Gson gson = new Gson();
		return gson.toJson(notice).toString();
	}

}

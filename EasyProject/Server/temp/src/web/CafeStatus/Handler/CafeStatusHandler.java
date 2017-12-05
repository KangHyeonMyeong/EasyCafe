package web.CafeStatus.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.CafeStatus;
import mvc.command.CommandHandler;
import web.CafeStatus.read.StatusRead;

public class CafeStatusHandler implements CommandHandler {
	private final String FORMVIEW="/view/cafestatus/cafeStatusForm.jsp";
	private StatusRead service = new StatusRead();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	
	}
	
	public String processForm(HttpServletRequest req, HttpServletResponse res){
		CafeStatus status = service.readInfo();
		
		req.setAttribute("cafeInfo", status);
		return FORMVIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res){
		CafeStatus status = service.readInfo();
		
		req.setAttribute("cafeInfo", status);
		return FORMVIEW;
	}
}

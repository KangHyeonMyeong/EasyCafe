package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler{

	private static final String FORM_VIEW =  "/src/jsp/login.jsp";
	private LoginService loginService = new LoginService();
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) 
		throws Exception {
			String e_mail = trim(req.getParameter("e_mail"));
			String password = trim(req.getParameter("password"));
			
			Map<String, Boolean> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			
			if(e_mail == null || e_mail.isEmpty())
				errors.put("e_mail", Boolean.TRUE);
			if(password == null || password.isEmpty())
				errors.put("password", Boolean.TRUE);
			
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
			
			try {
				User user = loginService.login(e_mail, password);
				req.getSession().setAttribute("authUser", user);
				res.sendRedirect( "/temp/home.do");
				return null;
			}catch (LoginFailException e) {
				errors.put("idOrPwNotMatch", Boolean.TRUE);
				return FORM_VIEW;
			}
		}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}

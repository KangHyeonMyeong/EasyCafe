package menu.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import menu.service.MenuDeleteService;
import menu.service.MenuNotFoundException;
import mvc.command.CommandHandler;

public class MenuDeleteHandler implements CommandHandler {
	private MenuDeleteService deleteService = new MenuDeleteService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String menu_name = request.getParameter("menu_name");
		
		try {
			deleteService.deleteMenu(menu_name);
			return "//view/menu/deleteSuccess.jsp";
		} catch(MenuNotFoundException e) {
			request.getServletContext().log("no Menu", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}

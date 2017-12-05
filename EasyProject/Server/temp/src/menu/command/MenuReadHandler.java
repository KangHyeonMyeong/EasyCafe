package menu.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import menu.service.MenuData;
import menu.service.MenuNotFoundException;
import menu.service.MenuReadService;
import mvc.command.CommandHandler;

public class MenuReadHandler implements CommandHandler{
	private MenuReadService readService = new MenuReadService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String menu_name = request.getParameter("menu_name");
		
		try {
			MenuData menuData = readService.getMenu(menu_name);
			request.setAttribute("menuData", menuData);
			return "/view/menu/menuDetail.jsp";
		} catch(MenuNotFoundException e) {
			request.getServletContext().log("no Menu", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		
		}
	}

}
package menu.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Menu;
import menu.service.MenuService;
import mvc.command.CommandHandler;

public class MenuMainHandler implements CommandHandler {
	private final String FORM_VIEW = "/view/menu/menuPage.jsp";
	MenuService service = new MenuService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menu_category =req.getParameter("menu_category");
		List<Menu> menuList=service.readMenu(menu_category);
		req.setAttribute("menuList", menuList);
		return FORM_VIEW;
	}

}

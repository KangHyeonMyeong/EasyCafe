package menu.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import menu.service.MenuListService;
import menu.service.MenuPage;
import mvc.command.CommandHandler;

public class MenuListHandler implements CommandHandler{
	private MenuListService listService = new MenuListService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menu_category = req.getParameter("menu_category");
		MenuPage menuPage  = null;
		
		if(menu_category != null){
			menuPage = listService.getPage(menu_category);
		}else{
			menuPage = listService.getPage();
		}
		
		req.setAttribute("menuPage", menuPage);
		return "/view/menu/menuList.jsp";
		
	}

}
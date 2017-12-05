package menu.service;

import java.util.List;

import model.Menu;

public class MenuPage {
	
	private String menu_category;
	private List<Menu> menuList;
	
	public MenuPage (String menu_category, List<Menu> menuList) {
		this.menu_category = menu_category;
		this.menuList = menuList;
	}

	public String getMenu_category() {
		return menu_category;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}
	
	
	

}

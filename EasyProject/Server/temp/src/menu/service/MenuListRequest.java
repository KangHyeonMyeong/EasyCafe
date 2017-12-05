package menu.service;

import java.util.ArrayList;

import model.Menu;

public class MenuListRequest {
	private Menu menu;
	private ArrayList<Menu> menuList = new ArrayList<>();
	
	public MenuListRequest(Menu menu, ArrayList<Menu> menuList) {
		this.menu=menu;
		this.menuList=menuList;
	}

	public Menu getMenu() {
		return menu;
	}

	public ArrayList<Menu> getMenuList() {
		return menuList;
	}
	
	
	
	
	
	
	
	
	

}

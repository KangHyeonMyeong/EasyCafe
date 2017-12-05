package menu.service;

import java.sql.Connection;
import java.util.List;



import dao.MenuDao;
import jdbc.connection.ConnectionProvider;
import model.Menu;

public class MenuListService {
	
	private MenuDao menuDao =new MenuDao();
	
	public MenuPage getPage(){
		try(Connection conn = ConnectionProvider.getConnection()){
			List<Menu> menuList = menuDao.selectMenu(conn);
			MenuPage menuPage = new MenuPage(null, menuList);
			return menuPage;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public MenuPage getPage(String menu_category){
		try(Connection conn = ConnectionProvider.getConnection()){
			List<Menu> menuList = menuDao.selectByCategory(conn, menu_category);
			MenuPage menuPage = new MenuPage(menu_category, menuList);
			return menuPage;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}

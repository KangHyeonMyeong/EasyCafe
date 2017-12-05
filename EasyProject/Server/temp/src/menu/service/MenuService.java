package menu.service;

import java.sql.Connection;
import java.util.ArrayList;


import dao.MenuDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.Menu;

public class MenuService {
	MenuDao menuDao = new MenuDao();
	public ArrayList<Menu> readMenu(String menu_category){
		Connection conn = null;
		ArrayList<Menu> menuList = null;
		try{
			conn = ConnectionProvider.getConnection();
			menuList = menuDao.selectByCategory(conn, menu_category);
			return menuList;
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

package menu.service;

import java.sql.Connection;
import java.sql.SQLException;


import dao.MenuDao;
import jdbc.connection.ConnectionProvider;
import model.Menu;

public class MenuReadService {
	
	private MenuDao menuDao = new MenuDao();
	
	public MenuData getMenu(String menu_name) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			Menu menu = menuDao.selectByName(conn, menu_name);
			if(menu == null) {
				throw new MenuNotFoundException();
			}
			return new MenuData(menu);
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
}	
	
package menu.service;

import java.sql.Connection;


import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import dao.MenuDao;
import model.Menu;

public class MenuModifyService {
	
	private MenuDao menuDao = new MenuDao();
	
	public void updateMenu(Menu menu, String menu_name) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			menuDao.updateMenu(conn, menu, menu_name);
			conn.commit();
			}catch (Exception e) {
				JdbcUtil.rollback(conn);
				System.out.println(e.getMessage());
			} finally {
				JdbcUtil.close(conn);
				
			}
		}
		
	
	
	public MenuData getMenu(String menu_name) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			Menu menu = menuDao.getMenu(conn, menu_name);
			if(menu == null) {
				throw new MenuNotFoundException();
			}
			return new MenuData(menu);
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.close(conn);

			}
			return null;

		}
	}
		
	


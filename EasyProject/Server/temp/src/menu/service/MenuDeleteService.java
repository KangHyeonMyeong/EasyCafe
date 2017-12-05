package menu.service;

import java.sql.Connection;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import dao.MenuDao;

public class MenuDeleteService {
	
	private MenuDao menuDao = new MenuDao();
	
	public void deleteMenu(String menu_name){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			menuDao.deleteMenu(conn, menu_name);
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
			
		}
	}
}
	
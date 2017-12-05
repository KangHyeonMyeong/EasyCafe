package menu.service;

import java.sql.Connection;
import java.sql.SQLException;


import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import dao.MenuDao;
import model.Menu;

public class MenuRegisterService {
	
	
	private MenuDao menuDao = new MenuDao ();
	
	public void register(Menu menu) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			menuDao.insertMenu(conn, menu);
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			
		
		} finally {
			JdbcUtil.close(conn);
			
		}
		
	}
}
	
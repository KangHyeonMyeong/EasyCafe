package app.MenuControll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.MemberDao;
import dao.MenuDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MenuSendService {
	MenuDao menuDao = new MenuDao();
	
	public JSONObject menuList(){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			return menuDao.selectAll(conn);
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

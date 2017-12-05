package app.Logout.Service;

import java.sql.Connection;

import dao.MemberDao;
import dao.PushListDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class AppLogoutService {
	MemberDao memberDao = new MemberDao();
	PushListDao pushListDao = new PushListDao();
	
	public void deletePush( String e_mail){
		Connection conn = null;
		try{
			conn =ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			pushListDao.delete(conn, e_mail);
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally{
			JdbcUtil.close(conn);
		}
	}
}

package app.ChangePass.Service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class AppChangePassService {
	MemberDao memberDao =  new MemberDao();
	
	public void changePass(String e_mail, String newPassword){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.modifyPass(conn, e_mail, newPassword);
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

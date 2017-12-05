package app.ModifyInfo.Service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyInfoService {
	MemberDao memberDao = new MemberDao();
	
	public void modifyInfo(String e_mail, String phone, String birth){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.modifyInfo(conn, e_mail, phone, birth);
			conn.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
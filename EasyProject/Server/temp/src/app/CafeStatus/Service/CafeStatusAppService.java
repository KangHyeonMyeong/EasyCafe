package app.CafeStatus.Service;

import java.sql.Connection;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;
import dao.CafeStatusDao;
import model.CafeStatus;

public class CafeStatusAppService {
	CafeStatusDao cafeStatusDao = new CafeStatusDao();
	
	public CafeStatus readStatus(){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			return cafeStatusDao.readRecord(conn);
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

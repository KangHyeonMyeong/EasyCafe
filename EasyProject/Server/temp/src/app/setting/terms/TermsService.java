package app.setting.terms;

import java.sql.Connection;

import com.cafe.dao.TermsDao;
import com.cafe.model.Terms;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class TermsService {

	TermsDao termsDao = new TermsDao();
	
	public Terms content() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return termsDao.TermsContent();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

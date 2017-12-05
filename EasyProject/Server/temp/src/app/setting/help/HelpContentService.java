package app.setting.help;

import java.sql.Connection;
import java.util.List;

import com.cafe.dao.HelpDao;
import com.cafe.model.Help;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class HelpContentService {
	
	HelpDao helpDao = HelpDao.getInstance();
	
	public List<Help> content() {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			return helpDao.HelpContent();
		}catch(Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}

}

package app.setting.notice;

import java.sql.Connection;
import java.util.List;

import com.cafe.dao.NoticeDao;
import com.cafe.model.Notice;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class NoticeContentService {

	NoticeDao noticeDao = NoticeDao.getInstace();
	
	public List<Notice> content() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return noticeDao.NoticeContent();
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

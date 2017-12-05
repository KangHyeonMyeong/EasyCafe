package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.cafe.dao.MemberDao;
import com.cafe.model.Member;

import jdbc.connection.ConnectionProvider;

public class LoginService {

	private MemberDao memberDao = MemberDao.getInstance();

	public User login(String e_mail, String password) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn,e_mail);
			if(member == null) {
				throw new LoginFailException();
			}
			if(!member.matchPassword(password)) {
				throw new LoginFailException();
			}
			return new User(member.getE_mail(), member.getName(), member.getManager_flag());
		}catch(SQLException e) {
			throw new RuntimeException();
		}
	}
}

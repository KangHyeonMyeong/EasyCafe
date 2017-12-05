package orderList.status;

import java.sql.Connection;
import java.sql.SQLException;

import dao.OrderDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class OrderStatusChangeService {
	OrderDao orderDao = new OrderDao();
	
	public void ModifyStatus(String date, String status){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			orderDao.updateOrderStatus(conn, date, status);
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

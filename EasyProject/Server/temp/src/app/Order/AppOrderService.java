package app.Order;

import java.sql.Connection;

import dao.OrderDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

import model.Order;

public class AppOrderService {
	
	OrderDao orderDao = new OrderDao();
	
	public void insert(Order order){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			orderDao.insert(conn, order);
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			 throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	

}

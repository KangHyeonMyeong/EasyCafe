package orderList;

import java.sql.Connection;
import java.util.List;


import dao.OrderDao;
import jdbc.connection.ConnectionProvider;
import model.Order;

public class OrderListService {
	
	private OrderDao orderDao = new OrderDao();
	
	
	public OrderPage getPage() {
		try(Connection conn = ConnectionProvider.getConnection()){
			List<Order> orderList = orderDao.selectOrder(conn);
			OrderPage orderPage = new OrderPage(orderList);
			return orderPage;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

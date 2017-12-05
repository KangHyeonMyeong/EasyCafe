package app.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.OrderDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.Order;

public class AppOrderListService {
	OrderDao orderDao = new OrderDao();
	public JSONArray readOrderListById(String e_mail){
		Connection conn = null;
		JSONArray array = new JSONArray();
		ArrayList<Order> list = new ArrayList<Order>();
		try{
			conn = ConnectionProvider.getConnection();
			list = orderDao.selectById(conn, e_mail);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(conn);
		}
		for(int i = 0 ; i < list.size() ; i++){
			JSONObject object = new JSONObject();
			object.put("order_id", list.get(i).getOrder_id() );
			object.put("order_name",  list.get(i).getOrder_name() );
			object.put("order_date",  list.get(i).getOrder_date() );
			object.put("order_content",  list.get(i).getOrder_content() );
			object.put("order_status",  list.get(i).getOrder_status() );
			array.add(object);
		}
		return array;
	}
}

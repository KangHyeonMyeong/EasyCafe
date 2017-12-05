package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import jdk.nashorn.internal.ir.LiteralNode.ArrayLiteralNode;
import model.Order;
import model.Result;

public class OrderDao {
	
	public void insert(Connection conn, Order order) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into orderlist values(?,?,now(),?,?)");
			pstmt.setString(1, order.getOrder_id());
			pstmt.setString(2, order.getOrder_name());
			pstmt.setString(3, order.getOrder_content());
			pstmt.setString(4, order.getOrder_status());
			pstmt.executeUpdate();
				
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<Order> selectOrder(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Order> orderList=null;
        
        try {
           pstmt = conn.prepareStatement("select * from orderlist ORDER BY order_date DESC");
           rs = pstmt.executeQuery();
           if (rs.next()) {
        	   orderList = new ArrayList<Order>();
        	   do{
        		   Order order= new Order(
            			rs.getString("order_id"),
						rs.getString("order_name"),
						rs.getString("order_date"),
						rs.getString("order_content"),
						rs.getString("order_status"));
        		   orderList.add(order);
        		   }while(rs.next());
           }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
        }
		return orderList;
    }
	
	public ArrayList<Order> selectById(Connection conn , String e_mail) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Order> list = new ArrayList<>();
		String selectQuery = "SELECT * FROM orderlist WHERE order_id = ? ORDER BY order_date DESC";
		try{
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, e_mail);
			rs = pstmt.executeQuery();
			if(rs != null){
				while(rs.next()){
					list.add(new Order(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5)
							)
						);
				}
			}
			return list;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	public void updateOrderStatus(Connection conn, String date , String status) throws SQLException{
		PreparedStatement pstmt = null;
		String updateQuery = "UPDATE orderlist SET order_status=? WHERE order_date=?";
		try{
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, status);
			pstmt.setString(2, date);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

 
}

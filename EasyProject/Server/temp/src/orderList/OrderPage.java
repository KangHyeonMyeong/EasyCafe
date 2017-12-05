package orderList;

import java.util.List;


import model.Order;

public class OrderPage {
	
	private List<Order> orderList;
	
	
	
	public OrderPage (List<Order> orderList) {
		this.orderList = orderList;
	}


	public  List<Order> getOrderList() {
		return orderList;
	}
	
}

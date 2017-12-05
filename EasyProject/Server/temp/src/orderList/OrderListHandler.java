package orderList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.command.CommandHandler;

public class OrderListHandler implements CommandHandler{
	private OrderListService orderService = new OrderListService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		OrderPage orderPage = null;
			orderPage = orderService.getPage();
		
		req.setAttribute("orderPage", orderPage);
		return "/view/order/orderList.jsp";
		
	}

}

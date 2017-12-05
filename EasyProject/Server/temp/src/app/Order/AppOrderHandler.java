package app.Order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import model.Order;
import model.Result;
import mvc.command.CommandHandler;
import web.Push.Service.PushService;

public class AppOrderHandler implements CommandHandler  {
	AppOrderService orderService = new AppOrderService();
	PushService pushService = new PushService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req, res);
		}else if (req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		}else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		Result result = new Result();
		result.setResultCode("400");
		result.setResultMessage("Fail");
		Gson gson = new Gson();
		return gson.toJson(result);
	}


	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Order order = new Order(req.getParameter("order_id"),
				req.getParameter("order_name"),
				null,
				req.getParameter("order_content"),
				req.getParameter("order_status"));
		orderService.insert(order);
		// 요기
		ArrayList<String> sendTarget = pushService.readTokenList();
		pushService.sendPush(sendTarget, "주문 접수", "주문이 왔습니다. 확인해주세요.");
		
		Result result = new Result();
		result.setResultCode("200");
		result.setResultMessage("Success");
		Gson gson = new Gson();
		return gson.toJson(result);
	}
}
	
	
	
	
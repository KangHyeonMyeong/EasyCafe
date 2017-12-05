package orderList.status;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import web.Push.Service.PushService;

public class OrderStatusChangeHandler implements CommandHandler {
	OrderStatusChangeService service = new OrderStatusChangeService();
	PushService pushservice = new PushService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String status = req.getParameter("order_status");
		String date = req.getParameter("order_date");
		String e_mail = req.getParameter("order_id");
		if( status.equals("준비중")){
			service.ModifyStatus(date, "제조완료");
		}else if ( status.equals("제조완료")){
			service.ModifyStatus(date, "수취완료");
			ArrayList<String> pushList = pushservice.readTokenByID(e_mail);
			if( !pushList.isEmpty()){
			pushservice.sendPush(pushList, "알림", "음료가 다 만들어 졌어요.찾아가세요~");
			}
		}else if ( status.equals("수취완료")){
			service.ModifyStatus(date, "거래완료");
		}
		return "/orderList.do";
	}

}

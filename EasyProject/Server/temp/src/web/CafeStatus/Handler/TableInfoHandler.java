package web.CafeStatus.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TableInfo;
import mvc.command.CommandHandler;
import web.CafeStatus.tableService.TableService;

public class TableInfoHandler implements CommandHandler {
	TableService service = new TableService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int table_num = Integer.parseInt(req.getParameter("table_num") );
		TableInfo tableinfo =  service.tableRead(table_num);
		req.setAttribute("tableinfo", tableinfo);
		return "/view/cafestatus/tableinfo.jsp";
	}

}

package web.CafeStatus.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TableInfo;
import mvc.command.CommandHandler;
import web.CafeStatus.tableService.TableService;

public class TableModifyHandler implements CommandHandler {
	TableService service = new TableService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		TableInfo tableInfo = service.getAndSave(req);
		service.tableModified(tableInfo);
		return "/cafestatusMain.do";
	}
}

package web.CafeStatus.tableService;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;
import dao.TableInfoDao;
import model.TableInfo;

public class TableService {
	TableInfoDao tableInfoDao = new TableInfoDao();
	
	public TableInfo tableRead(int table_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			TableInfo tableInfo = tableInfoDao.select(conn, table_num);
			return tableInfo;
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void tableModified(TableInfo tableInfo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			tableInfoDao.update(conn, tableInfo);
			conn.commit();
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public TableInfo getAndSave(HttpServletRequest req){
		MultipartRequest upload = null;
		String seat_image = null;
		int table_num= 0;
		String consent= null;
		String blanket= null;
		String cushion= null;
		try{
			String realFolder = "";//웹 어플리케이션상의 절대 경로 저장
			String saveFolder = "/images/table"; //파일 업로드 폴더 지정
			String encType = "utf-8"; //인코딩타입
			int maxSize = 5*1024*1024;  //최대 업로될 파일크기 5Mb
		
			ServletContext context = req.getServletContext();
			realFolder = context.getRealPath(saveFolder); 
		
			upload = new MultipartRequest(req,realFolder,maxSize,
		            encType,new DefaultFileRenamePolicy());
			
			seat_image = upload.getOriginalFileName("filename");
			table_num = Integer.parseInt(upload.getParameter("table_num"));
			consent = upload.getParameter("consent");
			blanket = upload.getParameter("blanket");
			cushion = upload.getParameter("cushion");
			//String type = upload.getContentType(name);
			return new TableInfo(table_num, blanket, consent, cushion, seat_image);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
}

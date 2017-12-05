package web.Push.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;
import dao.PushListDao;
import model.PushList;


public class PushService {
	
	PushListDao listDao = new PushListDao();
	private static final String ApiKey = "AAAAGFl9ch4:APA91bGzoskOrLhAum9sRBRHUaWQRJe2wNJQnWFz9qOuxlJbGZQ4fWUcWlXDgcLMqbfeBUiRzkNhbML43F9ndYU-2Tv7o8iF31fqLNzQRGwJKXrR4DRd3OV6uN0ngWI6dN3wG-whbvo0";
	String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);
	
	//여기도 약간 수정
	public void saveUpdate(PushList user){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			ArrayList<String> arrayList = listDao.selectById(conn, user.getE_mail());
			conn.commit();			
			if( !arrayList.isEmpty() ){
				listDao.modifiedToken(conn, user);
			}else{
				listDao.insert(conn, user);
			}
			conn.commit();
		}catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public ArrayList<String> readTokenList(){
		Connection conn = null;
		ArrayList<String> pushList = null;
		try{
			conn = ConnectionProvider.getConnection();
			pushList = listDao.selectManager(conn);
			return pushList;
		}catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//요기 새로만듬
	public ArrayList<String> readTokenByID(String e_mail){
		Connection conn = null;
		ArrayList<String> pushList = null;
		try {
			conn = ConnectionProvider.getConnection();
			pushList = listDao.selectById(conn, e_mail);
			return pushList;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void sendPush(ArrayList<String> pushList ,String main, String content){
		Sender sender = new Sender(ApiKey);
		Message message = new Message.Builder()
				.collapseKey(MESSAGE_ID)
				.delayWhileIdle(true)
				.timeToLive(10)
				.addData("main", main)
				.addData("contents", content)
				.build();
		MulticastResult result;
		try {
			result = sender.send(message, pushList, 2);
			if(result != null){
				List<Result> resultList = result.getResults();
				System.out.println("Send PushMessage");
				for(Result result2: resultList){
					System.out.println(result2.getErrorCodeName());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	
			
		
	}
}

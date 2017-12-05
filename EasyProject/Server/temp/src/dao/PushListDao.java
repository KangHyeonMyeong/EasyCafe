package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import model.PushList;

public class PushListDao {
	
	/*푸시 서비스를 위한 데이터를 저장하는 메소드*/
	public void insert(Connection conn, PushList pushList) throws SQLException{
		PreparedStatement pstmt = null;
		String insertQ = "INSERT INTO push_list VALUES (?,?,?)";
		try{
			pstmt = conn.prepareStatement(insertQ);
			pstmt.setString(1, pushList.getE_mail());
			pstmt.setString(2, pushList.getManager_flag());
			pstmt.setString(3, pushList.getPush_token());
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	/*직원권한을 이용한 토큰값을 찾아주는 메소드(직원만을 찾음)*/
	public ArrayList<String> selectManager(Connection conn) throws SQLException{
		ArrayList<String> pushArrayList = new ArrayList<>();//직원에 해당하는 레코드를 리스트로 만들어!
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectM = "SELECT push_token FROM push_list WHERE manager_flag='1'";
		try{
			pstmt = conn.prepareStatement(selectM);
			rs = pstmt.executeQuery();
			if(rs != null){
				while(rs.next()){
					pushArrayList.add(rs.getString(1));
				}
				return pushArrayList;
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	/*아이디로 조회하여 토큰값을 찾아주는 메소드(11.11일 수정)*/
	public ArrayList<String> selectById(Connection conn , String ID) throws SQLException{
		ArrayList<String> arrayList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String select = "SELECT * FROM push_list WHERE e_mail=?";
		try{
			pstmt = conn.prepareStatement(select);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				arrayList.add(rs.getString(3));
			}
			return arrayList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	/*지정된 아이디로 조회하여 record를 삭제하는 메서드*/
	public void delete(Connection conn, String ID) throws SQLException{
		PreparedStatement pstmt = null;
		String delete="DELETE FROM push_list WHERE e_mail=?";
		
		try{
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, ID);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	/* 아이디해당 토큰을 업데이트 */
	public void modifiedToken(Connection conn, PushList pushList) throws SQLException{
		PreparedStatement pstmt = null;
		String update ="UPDATE push_list SET push_token=? WHERE e_mail=?";
		try{
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, pushList.getPush_token());
			pstmt.setString(2, pushList.getE_mail());
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
}

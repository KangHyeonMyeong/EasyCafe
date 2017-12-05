package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jdbc.JdbcUtil;
import model.Menu;

public class MenuDao {
	
	
	public void insertMenu(Connection conn, Menu menu) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into menu values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, menu.getMenu_name());
			pstmt.setString(2, menu.getMenu_info());
			pstmt.setString(3, menu.getMenu_image());
			pstmt.setString(4, menu.getMenu_category());
			
			pstmt.setInt(5, menu.getPrice_HS());
			pstmt.setInt(6, menu.getPrice_HL());
			pstmt.setInt(7, menu.getPrice_IS());
			pstmt.setInt(8, menu.getPrice_IL());
			
			pstmt.executeUpdate();
		      
		} finally {
			JdbcUtil.close(pstmt);
			}
		}
	
		// selectByName 메소드
		public Menu selectByName(Connection conn, String menu_name) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("select * from menu where menu_name = ?");
				pstmt.setString(1, menu_name);
				rs = pstmt.executeQuery();
				Menu menu = null;
				if (rs.next()) {
					menu = new Menu(
							rs.getString("menu_name"),
							rs.getString("menu_info"),
							rs.getString("menu_image"),
							rs.getString("menu_category"),
							rs.getInt("price_HS"),
							rs.getInt("price_HL"),
							rs.getInt("price_IS"),
							rs.getInt("price_IL"));
							
				}
				return menu;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
	
		
		// 분류별  메뉴리스트  메소드
		public ArrayList<Menu> selectByCategory(Connection conn, String menu_category) throws Exception {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ArrayList<Menu> menuList = null;
	        
	        try {
	        	pstmt = conn.prepareStatement("select * from menu where menu_category=?");
	            pstmt.setString(1, menu_category);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	               	menuList = new ArrayList<Menu>();
	                do{
	                	Menu menu= new Menu(
	                			rs.getString("menu_name"),
								rs.getString("menu_info"),
								rs.getString("menu_image"),
								rs.getString("menu_category"),
								rs.getInt("price_HS"),
								rs.getInt("price_HL"),
								rs.getInt("price_IS"),
								rs.getInt("price_IL"));
	            
	                    menuList.add(menu);
				    }while(rs.next());
	                
				}
	            return menuList;
	        }finally {
	        	JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
	        }
			
	    }
		
		
		// 전체등록  메뉴리스트 메소드
		public List<Menu> selectMenu(Connection conn) throws Exception {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ArrayList<Menu> menuList=null;
	        
	        try {
	           pstmt = conn.prepareStatement("select * from menu");
	           
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	            	menuList = new ArrayList<Menu>();
	                do{
	                	Menu menu= new Menu(
	                			rs.getString("menu_name"),
								rs.getString("menu_info"),
								rs.getString("menu_image"),
								rs.getString("menu_category"),
								rs.getInt("price_HS"),
								rs.getInt("price_HL"),
								rs.getInt("price_IS"),
								rs.getInt("price_IL"));
	                    menuList.add(menu);
				    }while(rs.next());
				}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	        	JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
	        }
			return menuList;
			
	    }
		
		
		// menuName에 해당하는 메뉴정보를 읽어냄

		public Menu getMenu(Connection conn, String menu_name) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Menu menu = null;
			try {
				pstmt = conn.prepareStatement("select * from menu where menu_name = ?");
				pstmt.setString(1, menu_name);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					menu = new Menu();
					menu.setMenu_name(rs.getString("menu_name"));
					menu.setMenu_info(rs.getString("menu_info"));
					menu.setMenu_image(rs.getString("menu_image"));
					menu.setMenu_category(rs.getString("menu_category"));
					menu.setPrice_HS(rs.getInt("price_HS"));
					menu.setPrice_HL(rs.getInt("price_HL"));
					menu.setPrice_IS(rs.getInt("price_IS"));
					menu.setPrice_IL(rs.getInt("price_IL"));
					
					
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
					
				}
				return menu;
		}

		
		
		
	    // 메뉴정보를 수정
	    public void updateMenu(Connection conn, Menu menu, String menu_name) throws Exception {
	       PreparedStatement pstmt = null;
	        try {
	        	pstmt = conn.prepareStatement("update menu set menu_category =?, menu_info =?, menu_image =? , price_HS =?, price_HL =?, price_IS =?, price_IL =? where menu_name =?");
	            pstmt.setString(1, menu.getMenu_category());
	            pstmt.setString(2, menu.getMenu_info());
				pstmt.setString(3, menu.getMenu_image());
				pstmt.setInt(4, menu.getPrice_HS());
				pstmt.setInt(5, menu.getPrice_HL());
				pstmt.setInt(6, menu.getPrice_IS());
				pstmt.setInt(7, menu.getPrice_IL());
				pstmt.setString(8, menu.getMenu_name());
				pstmt.executeUpdate();
	       
	        } finally {
	        	 JdbcUtil.close(pstmt);
	     			
	     	}
	     	
	    }
	   
	    // name에 해당하는 메뉴정보를 삭제
	    public void deleteMenu(Connection conn, String menu_name) throws Exception {
	    	PreparedStatement pstmt = null;
	        
	        try {
				pstmt = conn.prepareStatement("delete from menu where menu_name=?");
	            pstmt.setString(1, menu_name);
	            pstmt.executeUpdate();
	     
	        } finally {
	        	 JdbcUtil.close(pstmt);
	        }	    
	    }
	    
	    public JSONObject selectAll(Connection conn) throws SQLException{
	    	JSONObject respon = new JSONObject();
	    	JSONArray array = new JSONArray();
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	String Query = "SELECT * FROM menu";
	    	try{
	    		pstmt = conn.prepareStatement(Query);
	    		rs = pstmt.executeQuery();
	    		if(rs != null ){
	    			while(rs.next()){
	    				JSONObject object = new JSONObject();
		    			object.put("menu_name", rs.getString("menu_name"));
		    			object.put("menu_info", rs.getString("menu_info"));
		    			object.put("menu_image", rs.getString("menu_image"));
		    			object.put("menu_category", rs.getString("menu_category"));
		    			object.put("price_HS", rs.getString("price_HS"));
		    			object.put("price_HL", rs.getString("price_HL"));
		    			object.put("price_IS", rs.getString("price_IS"));
		    			object.put("price_IL", rs.getString("price_IL"));
		    			array.add(object);
	    			}
	    		}
	    		
	    		respon.put("MENU", array);
	    		return respon;
	    	}finally {
	    		JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
	    }
}
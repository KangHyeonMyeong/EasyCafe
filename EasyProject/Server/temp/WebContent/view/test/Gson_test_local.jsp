<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="jdbc.JdbcUtil"%>
<%@page import="java.sql.SQLException"%>
<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title></title>
</head>
<body>

<%
// 1. JDBC 드라이버 로딩
Class.forName("com.mysql.jdbc.Driver");

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
	String jdbcDriver = "jdbc:mysql://192.168.0.39:3306/test?" +
						"useUnicode=true&characterEncoding=utf8";
	String dbUser = "kimsh";
	String dbPass = "root";
	
	String query = "select * from content";
	
	// 2. 데이터베이스 커넥션 생성
	conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
	
	// 3. Statement 생성
	pstmt = conn.prepareStatement(query);
	
	// 4. 쿼리 실행
	rs = pstmt.executeQuery();
	
	List<Map<String,String>> list = new ArrayList();
	
	// 5. 쿼리 실행 결과 출력
	while(rs.next()) {
		Map<String,String> map = new HashMap();
		map.put("content", rs.getString("content"));
		
		list.add(map);

	}
	
	// json 생성
	String json = new Gson().toJson(list);
	
	out.println(json);
} catch(SQLException ex) {
	out.println(ex.getMessage());
	ex.printStackTrace();
} finally {
	// 6. 사용한 Statement 종료
	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	
	// 7. 커넥션 종료
	if (conn != null) try { conn.close(); } catch(SQLException ex) {}
}
%>

</body>
</html>
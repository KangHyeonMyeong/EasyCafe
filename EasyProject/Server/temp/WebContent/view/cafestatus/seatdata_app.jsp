<%@page import="jdbc.JdbcUtil"%>
<%@page import="model.Sensor"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=500, user-scalable=yes"/>
<title>매장상태정보</title>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/status.js"></script>
<body>
<div class="result" style="background: white;">loading...</div>
테이블 클릭 시 상세정보를 확인 할 수 있습니다! 

</body>
</html>




<%@page import="jdbc.JdbcUtil"%>
<%@page import="model.Sensor"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<% request.setCharacterEncoding("utf-8");%>
<%
	String path_use = request.getContextPath()+"/images/useseat.png";
	String path_empty =request.getContextPath()+"/images/emptyseat.png";
	HashMap<String, String> seat_sensor = new HashMap<>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		conn = ConnectionProvider.getConnection();
		String select ="SELECT sensor_id, sensor_value FROM sensor WHERE category='Seat'";
		pstmt = conn.prepareStatement(select);
		rs = pstmt.executeQuery();
		while(rs.next()){
			String sensor_id = rs.getString(1);
			String sensor_value = rs.getString(2);
			if(sensor_value.equals("Y")){
				seat_sensor.put(sensor_id, path_use);
			}else if(sensor_value.equals("N")){
				seat_sensor.put(sensor_id, path_empty);
			}
		}
	}catch(Exception e){
		throw new RuntimeException();
	}finally{
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
	}
	request.setAttribute("list", seat_sensor);
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablestyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/tableScript.js"></script>
<table style="border: black solid 1px;">
<tr>
	<td colspan="2" rowspan="3" width="20px" class="table" onclick="tablePop(this)" id="1">A</td>
	<td width="10xp"></td>
	<td style="text-align: left;"><img alt="1번" src="${list.get('s1')}"></td>
	<td width="10px"></td>
	<td style="vertical-align: bottom;"><img alt="2번" src="${list.get('s2')}"></td>
	<td width="10px"></td>
	<td style="vertical-align: bottom;"><img alt="3번" src="${list.get('s3')}"></td>
	<td width="10px"></td>
	<td style="vertical-align: bottom;"><img alt="4번" src="${list.get('s4')}"></td>
	<td width="10px"></td>
	<td style="vertical-align: bottom;"><img alt="5번" src="${list.get('s5')}"></td>
</tr>
<tr height="10px">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td style="text-align: left;"><img alt="6번" src="${list.get('s6')}"></td>
	<td></td>
	<td colspan="3" rowspan="2" class="table" id="2" onclick="tablePop(this)">B</td>
	<td></td>
	<td colspan="3" rowspan="2" class="table" id="3" onclick="tablePop(this)">C</td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr height="10px">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td colspan="2" rowspan="3" class="table" id="4" onclick="tablePop(this)">D</td>
	<td></td>
	<td style="text-align: left;"><img alt="7번" src="${list.get('s7')}"></td>
	<td></td>
	<td><img alt="8번" src="${list.get('s8')}"></td>
	<td></td>
	<td><img alt="9번" src="${list.get('s9')}"></td>
	<td></td>
	<td><img alt="10번" src="${list.get('s10')}"></td>
	<td></td>
	<td><img alt="11번" src="${list.get('s11')}"></td>
</tr>
<tr height="10px">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td style="text-align: left;"><img alt="12번" src="${list.get('s12')}"></td>
	<td></td>
	<td style="vertical-align: bottom;"><img alt="13번" src="${list.get('s13')}"></td>
	<td></td>
	<td style="vertical-align: bottom;"><img alt="14번" src="${list.get('s14')}"></td>
	<td></td>
	<td style="vertical-align: bottom; "><img alt="15번" src="${list.get('s15')}"></td>
	<td></td>
	<td style="vertical-align: bottom;"><img alt="16번" src="${list.get('s16')}"></td>
</tr>
<tr height="10px">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td height="30px" class="table" id="5" onclick="tablePop(this)">E</td>
	<td></td>
	<td height="30px" class="table" id="6" onclick="tablePop(this)">F</td>
	<td></td>
	<td height="30px" class="table" id="7" onclick="tablePop(this)">G</td>
	<td></td>
	<td height="30px" class="table" id="8" onclick="tablePop(this)">H</td>
</tr>
<tr height="10px">
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td><img alt="17번" src="${list.get('s17')}"></td>
	<td></td>
	<td><img alt="18번" src="${list.get('s18')}"></td>
	<td></td>
	<td><img alt="19번" src="${list.get('s19')}"></td>
	<td></td>
	<td><img alt="20번" src="${list.get('s20')}"></td>
</tr>
</table>

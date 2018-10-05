<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.example.study.model.AttandantInfo"%>
<%@page import="com.example.study.model.JoinRequestInfo"%>
<%@page import="com.example.study.model.UserInfo"%>
<%@page import="com.example.study.model.ScheduleBoardInfo"%>
<%@page import="com.example.study.model.ScheduleInfo"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Stumina</title>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
	<link rel='stylesheet prefetch' href='https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Attandance/css/style.css">
	
	<style>
		a {
		    color: #FFFFFF;
		}
		h3{
			margin: 0;
			-webkit-margin-after: 0em;
		}
	</style>
</head>

<body>
	<% 
		ArrayList<UserInfo> userInfos = null;
		ArrayList<JoinRequestInfo> requestInfos = null;
		ArrayList<ScheduleInfo> scheduleInfos = null;
		ArrayList<AttandantInfo> attandantInfos = null;
		String std_name = null;
		
		SimpleDateFormat original_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");  
	    SimpleDateFormat new_format = new SimpleDateFormat("yyyy-MM-dd");
		
		if (request.getAttribute("userInfos") != null)
		{
			userInfos = (ArrayList<UserInfo>)request.getAttribute("userInfos");
		}
		else
		{%>
			
		<%}
		if (request.getAttribute("requestInfos") != null)
		{
			requestInfos = (ArrayList<JoinRequestInfo>)request.getAttribute("requestInfos");
		}
		if (request.getAttribute("scheduleInfos") != null)
		{
			scheduleInfos = (ArrayList<ScheduleInfo>)request.getAttribute("scheduleInfos");
		}
		if (request.getAttribute("attandantInfos") != null)
		{
			attandantInfos = (ArrayList<AttandantInfo>)request.getAttribute("attandantInfos");
		}
		if (request.getAttribute("std_name") != null)
		{
			std_name = request.getAttribute("std_name").toString();
		}
	%>
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header> 
	
	<div class='member'>
		<form method="post" action="${pageContext.request.contextPath}/op/modifyAttandance" style='display: inline-block;'>
		<h3 style='color: white;'>[<%=std_name %>] 스터디의 출석 체크</h2>
		<table>
			<thead>
				<tr>
				<th class="name-col">날짜</th>
				<th class="name-col">스케쥴 이름</th>
				<%
					for (UserInfo user : userInfos)
					{
						out.println("<th>" + user.getUser_name() + "</th>");
					}
				%>
				<!-- <th class="missed-col">참석률</th> -->
				</tr>
			</thead>
			<tbody>
				<%
					for(ScheduleInfo schedule : scheduleInfos)
					{
						Date original_date = original_format.parse(schedule.getSchedule_date());
						String new_date = new_format.format(original_date);
						
						out.println("<tr>");
						out.println("<td>" + new_date + "</td>");
						out.println("<td>" + schedule.getSchedule_name() + "</td>");
						out.println("<input type='hidden' name='rsch_idx' value='" + schedule.getSchedule_idx() + "'>");
						for (AttandantInfo attandant : attandantInfos)
						{	
							if (attandant.getRsch_idx().equals(schedule.getSchedule_idx()))
							{
								out.println("<td><input type='checkbox' name='" + schedule.getSchedule_idx() +"' " + (attandant.getAbsent() == 1 ? "checked" : "") + " value='" + attandant.getUser_idx() + "'></td>");
							}
						}
						out.println("</tr>");
					}
				%>
			</tbody>
		</table>
		<input type="submit" value="저장하기" style="margin-top: 15px;">
		</form>
	</div>

	<script src='https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<!-- <script src="${pageContext.request.contextPath}/Attandance/js/index.js"></script> -->
</body>
	<style>
		a:focus, a:hover {
		   color: #FFFFFF;
		   text-decoration: none;
		}
		body{
			background-image: url(${pageContext.request.contextPath}/Schedule/background.jpg);
		    background-size: cover;
		    backdrop-filter: blur(5px);
		}
	</style>
</html>

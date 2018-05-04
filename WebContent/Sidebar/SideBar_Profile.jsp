<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/Graduation_KMS/Sidebar/css/style.css">
</head>
<body>
	<div class="navi">
		  <ul></ul>
		  <span class="profile_picture"></span>
		  <ul></ul>
		  	<%
			  if (session.getAttribute("user_idx") == null) 
			  {
			  %>
			     <a href="/Graduation_KMS/Sign_In/SignIn.jsp">로그인 해 주세요.</a>
			  <%
			  }
			  else
			  {
			  %>
			     <a href="#"><%=session.getAttribute("user_name")%>님 반갑습니다!</a>
			  <%
			  }
			  %>
		    <h3>나의 스터디</h3>
		    <ul class="Sections">
		      <li><a href="#">스터디 관리</a></li>
		      <li><a href="#">참여중인 스터디</a></li>
		      <li><a href="#">출석 관리</a></li>
		    </ul>
		
		    <h3>지역별 검색</h3>
		
		    <ul class="Topics">
		      <li><a href="#">서울 / 경기</a></li>
		      <li><a href="#">경남 / 경북</a></li>
		      <li><a class="active" href="#">부산</a></li>
		      <li><a href="#">대구</a></li>
		      <li><a href="#">전남 / 전북</a></li>
		      <li><a href="#">충남 / 충북</a></li>
		      <li><a href="#">대전</a></li>
		      <li><a href="#">제주</a></li>
		    </ul>
		
		    <h3>주제별 검색</h3>
		
		    <ul class="Qpolitical">
		      <li><a href="#">IT/컴퓨터</a></li>
		      <li><a href="#">인문/사회</a></li>
		      <li><a href="#">자연/공학</a></li>
		      <li><a href="#">의학</a></li>
		      <li><a href="#">예술/체육</a></li>
		      <li><a href="#">교육</a></li>
		      <li><a href="#">기타</a></li>
		    </ul>
		</div>
</body>
</html>
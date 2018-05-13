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
		  <img src="https://u.o0bc.com/avatars/no-user-image.gif" class="img-responsive" alt="User Picture">
		  <ul></ul>
		  	<%
		  	  System.out.println("[SideBar_Profile.jsp] " + request.getRequestURI());
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
		      <li><a href="/Graduation_KMS/op/myStudies">참여중인 스터디</a></li>
		      <li><a href="#">출석 관리</a></li>
		      <li><a href="/Graduation_KMS/op/createStudy">스터디 만들기</a></li>
		    </ul>
		
		    <h3>지역별 검색</h3>
		
		    <ul class="Topics">
		    </ul>
		
		    <h3>주제별 검색</h3>
		
		    <ul class="Qpolitical">
		    </ul>
		</div>
</body>
</html>
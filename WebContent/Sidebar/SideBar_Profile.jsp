<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Sidebar/css/style.css">
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
			     <a href="${pageContext.request.contextPath}/Sign_In/SignIn.jsp">�α��� �� �ּ���.</a>
			  <%
			  }
			  else
			  {
			  %>
			     <a href="#"><%=session.getAttribute("user_name")%>�� �ݰ����ϴ�!</a>
			  <%
			  }
			  %>
		    <h3>���� ���͵�</h3>
		    <ul class="Sections">
		      <li><a href="${pageContext.request.contextPath}/op/myStudies">�������� ���͵�</a></li>
		      <!-- <li><a href="#">�⼮ ����</a></li> -->
		      <li><a href="${pageContext.request.contextPath}/op/createStudy">���͵� �����</a></li>
		    </ul>
			<!-- 
		    <h3>������ �˻�</h3>
		
		    <ul class="Topics">
		    </ul>
		
		    <h3>������ �˻�</h3>
		
		    <ul class="Qpolitical">
		    </ul> -->
		</div>
</body>
</html>
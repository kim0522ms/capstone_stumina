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
			     <a href="/Graduation_KMS/Sign_In/SignIn.jsp">�α��� �� �ּ���.</a>
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
		      <li><a href="/Graduation_KMS/op/myStudies">�������� ���͵�</a></li>
		      <li><a href="#">�⼮ ����</a></li>
		      <li><a href="/Graduation_KMS/op/createStudy">���͵� �����</a></li>
		    </ul>
		
		    <h3>������ �˻�</h3>
		
		    <ul class="Topics">
		    </ul>
		
		    <h3>������ �˻�</h3>
		
		    <ul class="Qpolitical">
		    </ul>
		</div>
</body>
</html>
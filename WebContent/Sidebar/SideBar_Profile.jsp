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
		      <li><a href="#">���͵� ����</a></li>
		      <li><a href="#">�������� ���͵�</a></li>
		      <li><a href="#">�⼮ ����</a></li>
		    </ul>
		
		    <h3>������ �˻�</h3>
		
		    <ul class="Topics">
		      <li><a href="#">���� / ���</a></li>
		      <li><a href="#">�泲 / ���</a></li>
		      <li><a class="active" href="#">�λ�</a></li>
		      <li><a href="#">�뱸</a></li>
		      <li><a href="#">���� / ����</a></li>
		      <li><a href="#">�泲 / ���</a></li>
		      <li><a href="#">����</a></li>
		      <li><a href="#">����</a></li>
		    </ul>
		
		    <h3>������ �˻�</h3>
		
		    <ul class="Qpolitical">
		      <li><a href="#">IT/��ǻ��</a></li>
		      <li><a href="#">�ι�/��ȸ</a></li>
		      <li><a href="#">�ڿ�/����</a></li>
		      <li><a href="#">����</a></li>
		      <li><a href="#">����/ü��</a></li>
		      <li><a href="#">����</a></li>
		      <li><a href="#">��Ÿ</a></li>
		    </ul>
		</div>
</body>
</html>
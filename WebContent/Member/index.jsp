<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="0">
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/Graduation_KMS/Attandance/css/style.css">
	
	<title>Stumina</title>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
	<link rel='stylesheet prefetch' href='https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
	<link rel="stylesheet" href="/Graduation_KMS/Member/css/style.css">


</head>
<body>
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header>
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<div class='member'>
	<h1>School Attendance</h1>
	<form id="newStudent">
	  <input type="text" id="addNewStudent">
	  <button type="submit">Submit</button>
	  <button id='del'>Delete All</button>
	</form>
	<table>
	  <thead>
	    <tr>
	      <th class="name-col">Student Name</th>
	      <th>1</th>
	      <th>2</th>
	      <th>3</th>
	      <th>4</th>
	      <th>5</th>
	      <th>6</th>
	      <th>7</th>
	      <th>8</th>
	      <th>9</th>
	      <th>10</th>
	      <th>11</th>
	      <th>12</th>
	      <th class="missed-col">Days Missed-col</th>
	    </tr>
	  </thead>
	  <tbody>
	  </tbody>
	</table>
	</div>
</body>

<script  src="/Graduation_KMS/Attandance/js/index.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/app2.js"></script>
<style>
		a:focus, a:hover {
		   color: #FFFFFF;
		   text-decoration: none;
		}
		body{
			background-image: url(/Graduation_KMS/Schedule/background.jpg);
		    background-size: cover;
		    backdrop-filter: blur(5px);
		}
	</style>
</html>

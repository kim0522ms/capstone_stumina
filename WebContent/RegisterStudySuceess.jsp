<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="com.example.study.model.StudyInfo" %>
<%@ page import="com.example.study.controller.StudyDBDAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Stumina</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css'>
	<link rel="stylesheet" href="/Graduation_KMS/css/style.css">
	<style>
		html {
		  background-image: url(/Graduation_KMS/Schedule/background.jpg);
		  -webkit-background-size: cover;
		  -moz-background-size: cover;
		  -o-background-size: cover;
		  background-size: cover;
		}
		.cardwrapper {
		    position: relative;
		    /* width: 100%; */
		    margin: 0 auto;
		    vertical-align: middle;
		    margin-top: 10%;
		    margin-left: 20%;
		    width: 76%;
		    display: flex;
		    background: rgba(45, 48, 66, 0.7);
		    -webkit-border-radius: 10px;
		}
	</style>
</head>
<body>
	<header>
	<jsp:include page="/Search_Bar/SearchBar.jsp" />  
	<h1> </h1>
	</header>
	
	<section class="container" style=¡±clear:both;¡±>
		<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	</section>
	<div class="cardwrapper">
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.study.model.StudyInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Stumina</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.css'>
	<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.1.8/semantic.css'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<header>
	<jsp:include page="/Search_Bar/SearchBar.jsp" />  
	<h1> </h1>
	</header>
	<%
		StudyInfo studyInfo = null;
		String img_path = null;
		if (request.getAttribute("studyInfo") != null)
		{
			studyInfo = (StudyInfo)request.getAttribute("studyInfo");
		}
		if (request.getAttribute("img_path") != null)
		{
			img_path = request.getAttribute("img_path").toString();
		}
		else
		{
			img_path = "${pageContext.request.contextPath}/Schedule/background.jpg";
		}
	%>
	<style>
		.container {
			background-image: url(<%=img_path%>);
			background-size: cover;
		    color: #444;
		    line-height: 1.6;
		    display: inline-block;
		    width: 100%;
		    height: 100%;
		}
	</style>
	<!-- style="height: 50%; background-size: contain;" -->
	<section class="container" >
		<div id="polina">
			<h1><%=studyInfo.getStd_name()%></h1>
			<p>리더 <%=studyInfo.getStd_leader() %></p>
			<p>스터디 주제 : <%=studyInfo.getStd_theme() %></a>
			<p><%=studyInfo.getStd_contents() %></p>
			<p>정원 : <%=studyInfo.getStd_maxMemberCount() %> 남은 자리 : <%=studyInfo.getStd_remainMember()%></p>
			<%
				if (studyInfo.getStd_remainMember() < 1)
				{%>
					<button>인원이 다 차버렸네요..</button>
				<%}
				else
				{%>
					<button onclick="location.href='${pageContext.request.contextPath}/op/joinStudy?std_no=<%=studyInfo.getStd_no()%>'">지금 참여하기 !</button>
				<%}
			%>
			</div>
	</section>

	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script  src="js/index.js"></script>	
</body>
</html>
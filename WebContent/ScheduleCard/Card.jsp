<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ page import="com.example.study.model.StudyInfo" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
  <title>Stumina</title>
  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto'>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'>
	<link rel='stylesheet prefetch' href='https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
	<link rel="stylesheet" href="/Graduation_KMS/ScheduleCard/css/style.css">
	
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>

<body>
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header>  
	<%
		if(request.getAttribute("studyInfos") != null)
		{
			ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>)request.getAttribute("studyInfos");
			
			for (StudyInfo studyinfo : studyInfos)
			{
			%>
				<div class="cardwrapper">
				<!-- Card Start -->
					<div class="muck-up">
					  <div class="overlay"></div>
					  <div class="top">
					    <div class="nav">
					      <p><%=studyinfo.getStd_name()%></p>
					    </div>
					    <div class="user-profile">
					      <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/profile.jpg">
					      <div class="user-details">
					        <h4><%=studyinfo.getStd_theme() %></h4>
					        <p><%=studyinfo.getStd_leader() %></p>
					      </div>
					    </div>
					  </div>
					  <div class="clearfix"></div>
					  <div class="filter-btn">
					    <a id="one" href="#"><i class="ion-ios-checkmark-outline"></i></a>
					    <a id="two" href="#"><i class="ion-ios-alarm-outline"></i></a>
					    <a id="three" href="#"><i class="ion-ios-heart-outline"></i></a>
					    <a id="all" href="#"><i class="ion-ios-star-outline"></i></a>
					    <span class="toggle-btn ion-android-funnel"></span>
					  </div>
					  <div class="clearfix"></div>
					  <div class="bottom">
					    <div class="title">
					      <h3>���͵� ����</h3>
					      <small>February 8,2015</small>
					    </div>
					    <ul class="tasks">
					      <li class="one red">
					        <span class="task-title">Make New Icon</span>
					        <span class="task-time">5pm</span>
					        <span class="task-cat">Web App</span>
					
					      </li>
					      <li class="one red">
					        <span class="task-title">Catch up with Brian</span>
					        <span class="task-time">3pm</span>
					        <span class="task-cat">Mobile Project</span>
					
					      </li>
					      <li class="two green">
					        <span class="task-title">Design Explorations</span>
					        <span class="task-time">2pm</span>
					        <span class="task-cat">Company Web site</span>
					
					      </li>
					      </li>
					      <li class="tow green hang">
					        <span class="task-title">Team Meeting</span>
					        <span class="task-time">2pm</span>
					        <span class="task-cat">Hangouts</span>
					        <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/2.jpg">
					        <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/3.jpg">
					        <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/profile.jpg">
					      </li>
					      <li class="three yellow">
					        <span class="task-title">New Projects</span>
					        <span class="task-time">2pm</span>
					        <span class="task-cat">Starting</span>
					
					
					      </li>
					
					      <li class="three yellow">
					        <span class="task-title">Lunch with Mary</span>
					        <span class="task-time">2pm</span>
					        <span class="task-cat">Grill House</span>
					      </li>
					      <li class="three yellow">
					        <span class="task-title">Team Meeting</span>
					        <span class="task-time">2pm</span>
					        <span class="task-cat">Hangouts</span>
					      </li>
					
					    </ul>
					  </div>
					</div>
					<!-- Card End -->
					<%
					} 
				}
			%>
	</div>
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>

  

    <script  src="js/index.js"></script>




</body>

</html>
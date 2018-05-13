<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ page import="com.example.study.model.StudyInfo" %>
<%@ page import="com.example.study.model.ScheduleInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Date" %>
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
    <style>
	    .ion-android-funnel:before {
			    content: "\f38b";
			    margin-top: 9px;
		}	
    </style>
</head>

<body>
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header>  
	<%
		if(request.getAttribute("studyInfos") != null)
		{
			SimpleDateFormat oldDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA );
			SimpleDateFormat newDateFormat = new SimpleDateFormat ( "yy-MM-dd", Locale.KOREA);
			Date currentTime = new Date();
			ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>)request.getAttribute("studyInfos");
			%>
			<h1>현재 가입되어있는 스터디입니다!</h1>
			<div class="cardwrapper">
			<%
			for (StudyInfo studyinfo : studyInfos)
			{
			%>
				
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
					  <div class="filter-btn" onclick="location.href='/Graduation_KMS/op/ScheduleCalender?std_no=<%=studyinfo.getStd_no()%>'">
					    <!-- 
					    <a id="one" href="#"><i class="ion-ios-checkmark-outline"></i></a>
					    <a id="two" href="#"><i class="ion-ios-alarm-outline"></i></a>
					    <a id="three" href="#"><i class="ion-ios-heart-outline"></i></a>
					    <a id="all" href="#"><i class="ion-ios-star-outline"></i></a> 
					    -->
					    <span class="toggle-btn ion-android-funnel"></span>
					  </div>
					  <div class="clearfix"></div>
					  <div class="bottom">
					    <div class="title">
					      <h3 style="color: #000000;">스터디 일정</h3>
					      <small>자세한 내용은 우측의 버튼을 눌려주세요</small>
					    </div>
					    <ul class="tasks">
					      <%
						      if (studyinfo.getScheduleInfo() != null)
						      {
						      	 for (ScheduleInfo schedule : studyinfo.getScheduleInfo())
						      	 {
						      		 Date scheduleDate = oldDateFormat.parse(schedule.getSchedule_date());
						      		 if (scheduleDate.compareTo(currentTime) < 0)
						      		 {
						      			 continue;
						      		 }
						      		 
						      		 out.println("<li class='one red'>");
						      		 out.println("<span class='task-title'>" + schedule.getSchedule_name() + "</span>");
						      		 out.println("<span class='task-time'>" + newDateFormat.format(scheduleDate) + " " + schedule.getCheckin() + "시 ~ " +schedule.getCheckout() +"시</span>");
						      		 out.println("<span class='task-cat'>" + schedule.getStudyroom_name() + " " + schedule.getRoom_name() + "</span>");
						      		 out.println("</li>");
						      	 }
						      }
						      else
						      {
						    	  out.println("<li class='tow green hang'>");
						    	  out.println("<span class='task-title'>아직 스케쥴이 없네요!</span>");
						    	  out.println("</li>");
						      }
					      %>
					      <!-- 
						      <li class="tow green hang">
						        <span class="task-title">Team Meeting</span>
						        <span class="task-time">2pm</span>
						        <span class="task-cat">Hangouts</span>
						        <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/2.jpg">
						        <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/3.jpg">
						        <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/profile.jpg">
						      </li> 
					      -->
					
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

  

    <script  src="/Graduation_KMS/ScheduleCard/js/index.js"></script>




</body>

</html>
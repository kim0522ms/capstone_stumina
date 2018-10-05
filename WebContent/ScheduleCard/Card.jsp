<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ page import="com.example.study.model.StudyInfo" %>
<%@ page import="com.example.study.model.ScheduleInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">

<head>
  <title>Stumina</title>
  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto'>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'>
	<link rel='stylesheet prefetch' href='https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/ScheduleCard/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Tooltip/pr_tooltip.css">
	
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <style>
	    .ion-android-funnel:before {
		    content: "\f38b";
		    margin-top: 9px;
		}	
		.ion-android-alarm-clock:before {
		    content: "\f35a";
		    margin-top: 8px;
		    margin-left: 2px;
		}
		.ion-android-calendar:before {
		    content: "\f2d1";
		    margin-top: 8px;
		    margin-left: 2px;
		}
		.ion-android-create:before {
    		content: "\f37e";
    		margin-top: 8px;
		    margin-left: 2px;
		}
		.ion-android-person:before {
    		content: "\f3a0";
    		margin-top: 8px;
		}
		.ion-android-checkbox-outline:before {
		    content: "\f373";
		    margin-top: 8.5px;
		    margin-left: 2px;
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
			<h1>���� ���ԵǾ��ִ� ���͵��Դϴ�!</h1>
			<div class="cardwrapper">
			<%
			String path;
			for (StudyInfo studyinfo : studyInfos)
			{		
       			if (studyinfo.getStd_imagepath() != null)
       			{
       				path = studyinfo.getStd_imagepath();
       			}
       			else // �̹��� ��� �������� �⺻ Path
       			{
       				path = "https://www.pagoda21.com/images/upload/2014/09/PGS_201409030639229600.jpg";
       			}
       			%>
				<!-- Card Start -->
					<div class="muck-up">
					  <div class="overlay" style = "background: url(<%=path%>) no-repeat top /contain;"></div>
					  <div class="top">
					    <div class="nav">
					      <p><%=studyinfo.getStd_name()%></p>
					    </div>
					    <div class="user-profile">
					      <img src="https://raw.githubusercontent.com/arjunamgain/FilterMenu/master/images/profile.jpg">
					      <div class="user-details">
					        <h4><%=studyinfo.getStd_theme().length() <= 15 ? studyinfo.getStd_theme() : studyinfo.getStd_theme().substring(0,12) + "..." %></h4>
					        <p>���� : <%=studyinfo.getStd_leader() %></p>
					      </div>
					    </div>
					  </div>
					  <div class="clearfix"></div>
					  <!-- �⼮ üũ ��ư -->
					  <div class="attandance-btn pr_tooltip" title="�⼮ üũ" pr_position="top" pr_color="light" onclick="location.href='${pageContext.request.contextPath}/op/checkAttandance?std_no=<%=studyinfo.getStd_no()%>'">
					    <!-- 
					    <a id="one" href="#"><i class="ion-ios-checkmark-outline"></i></a>
					    <a id="two" href="#"><i class="ion-ios-alarm-outline"></i></a>
					    <a id="three" href="#"><i class="ion-ios-heart-outline"></i></a>
					    <a id="all" href="#"><i class="ion-ios-star-outline"></i></a> 
					    -->
					    <span class="toggle-btn ion-android-checkbox-outline"></span>
					  </div>
					  <!-- �⼮ üũ ��ư END -->
					  <!-- ��� ���� Ȯ�� ��ư -->
					  <div class="viewmember-btn pr_tooltip" title="��� ���� Ȯ��" pr_position="top" pr_color="light" onclick="location.href='${pageContext.request.contextPath}/op/viewStudyMember?std_no=<%=studyinfo.getStd_no()%>'">
					    <!-- 
					    <a id="one" href="#"><i class="ion-ios-checkmark-outline"></i></a>
					    <a id="two" href="#"><i class="ion-ios-alarm-outline"></i></a>
					    <a id="three" href="#"><i class="ion-ios-heart-outline"></i></a>
					    <a id="all" href="#"><i class="ion-ios-star-outline"></i></a> 
					    -->
					    <span class="toggle-btn ion-android-person"></span>
					  </div>
					  <!-- ��� ���� Ȯ�� ��ư END -->
					  <!-- ���͵� ���� ���� ��ư -->
					  <div class="editstudy-btn pr_tooltip" title="���͵� ���� ���� " pr_position="top" pr_color="light" onclick="location.href='${pageContext.request.contextPath}/op/modifyPage?std_no=<%=studyinfo.getStd_no()%>'">
					    <!-- 
					    <a id="one" href="#"><i class="ion-ios-checkmark-outline"></i></a>
					    <a id="two" href="#"><i class="ion-ios-alarm-outline"></i></a>
					    <a id="three" href="#"><i class="ion-ios-heart-outline"></i></a>
					    <a id="all" href="#"><i class="ion-ios-star-outline"></i></a> 
					    -->
					    <span class="toggle-btn ion-android-create"></span>
					  </div>
					  <!-- ���͵� ���� ���� ��ư END -->
					  <!-- ������ Ȯ�� ��ư -->
					  <div class="filter-btn pr_tooltip" title="������ Ȯ��" pr_position="top" pr_color="light" onclick="location.href='${pageContext.request.contextPath}/op/ScheduleCalender?std_no=<%=studyinfo.getStd_no()%>'">
					    <!-- 
					    <a id="one" href="#"><i class="ion-ios-checkmark-outline"></i></a>
					    <a id="two" href="#"><i class="ion-ios-alarm-outline"></i></a>
					    <a id="three" href="#"><i class="ion-ios-heart-outline"></i></a>
					    <a id="all" href="#"><i class="ion-ios-star-outline"></i></a> 
					    -->
					    <span class="toggle-btn ion-android-calendar"></span>
					  </div>
					  <!-- ������ Ȯ�� ��ư END -->
					  <div class="clearfix"></div>
					  <div class="bottom">
					    <div class="title">
					      <h3 style="color: #000000;">���͵� ����</h3>
					      <small>�ڼ��� ������ ������ ��ư�� �����ּ���</small>
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
						      		 out.println("<span class='task-time'>" + newDateFormat.format(scheduleDate) + " " + schedule.getCheckin() + "�� ~ " +schedule.getCheckout() +"��</span>");
						      		 out.println("<span class='task-cat'>" + schedule.getStudyroom_name() + " " + schedule.getRoom_name() + "</span>");
						      		 out.println("</li>");
						      	 }
						      }
						      else
						      {
						    	  out.println("<li class='tow green hang'>");
						    	  out.println("<span class='task-title'>���� �������� ���׿�!</span>");
						    	  out.println("</li>");
						      }
					      %>
					      <!-- 
						      <li class="tow green hang">
						        <span class="task-title">Team Meeting</span>
						        <span class="task-time">2pm</span>
						        <span class="task-cat">Hangouts</span>
						        <img src="https://u.o0bc.com/avatars/no-user-image.gif">
						        <img src="https://u.o0bc.com/avatars/no-user-image.gif">
						        <img src="https://u.o0bc.com/avatars/no-user-image.gif">
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
  <script  src="${pageContext.request.contextPath}/ScheduleCard/js/index.js"></script>
  <script  src="${pageContext.request.contextPath}/Tooltip/pr_tooltip.js"></script>

</body>

</html>
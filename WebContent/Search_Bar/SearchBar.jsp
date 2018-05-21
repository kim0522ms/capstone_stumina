<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.study.model.NotificationInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Stumina</title>
  <meta name="viewport" content="width=device-width">
  <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,900" rel="stylesheet">
  <link rel="stylesheet" href="/Graduation_KMS/Search_Bar/css/style.css">

  
</head>

<body>
	<%
		ArrayList<NotificationInfo> notificationInfos = null;
		if (session.getAttribute("notificationInfos") != null)
		{
			notificationInfos = (ArrayList<NotificationInfo>)session.getAttribute("notificationInfos");
			for (NotificationInfo notification : notificationInfos)
	    	{
	    		if (notification.getNoti_read() == 0)
	    			out.println("<input type='hidden' id='isNotRead' value='true' />");	
	    	}
		}
	%>
  <header>
   <div class="logo">
     <a href="/Graduation_KMS/MainPage.jsp">● Stumina</a>
  </div>
  <nav>
     <div class="search-bar">
       <form class="search" method="do" action="/Graduation_KMS/op/search">
         <input type="search" class="search__input" name="keyword" placeholder="스터디나 세미나를 검색해 보세요!" onload="equalWidth()" required>
         <button class="search__btn">Search</button>
         <i class="ion-ios-search search__icon"></i>
       </form>
       <div class="suggestion">
         <div class="suggestion__content">
           <div class="suggestion__content-left-side">
            <h5>Popular Categories</h5>
            <ul>
              <li>Marketing Material</li>
              <li>Just Sold</li>
              <li>Small Business Card</li>
              <li>Mailing</li>
              <li>Just List</li>
            <ul>  
           </div>
           <div class="suggestion__content-right-side">
            <h5>Popular Keywords</h5>
            <ul>
              <li>Business Card</li>
              <li>Brochures</li>
              <li>Just List Just Sold</li>
              <li>Flyers</li>
            </ul>   
           </div>
         </div>  
       </div>  
    </div>  
  </nav>
  <%
  if (session.getAttribute("user_idx") == null) 
  {
  %>
  	 <div class="signin">
     <a href="/Graduation_KMS/Sign_In/SignIn.jsp">로그인</a>
     </div>
     <div class="signup">
     <a href="/Graduation_KMS/Sign_Up/SignUp.jsp">회원가입</a>
  	 </div>
  <%
  }
  else
  {
  %>
     <div class="notification_container">
	    <div class="notification">
	    <span id="circle" style="display: none;"></span>
	    <a v-on:click="show = !show" class="tooltip-bell">
	      <i class="far fa-2x fa-bell"></i>
	    </a>
	    <transition name="fadeStart" v-cloak>
	      <div v-if="!show" class="tooltip">
	        <div id="heading">
	          <div class="heading-left">
	            <h6 class="heading-title">알림 센터</h6>
	          </div>
	          <div class="heading-right">
	            <!-- <a class="notification-link" href="#">See all</a> -->
	          </div>
	        </div>
	        <ul class="notification-list">
	        <% 
	        if(notificationInfos != null) 
	        {
	        	boolean isNotRead = false;
	        	for (NotificationInfo notification : notificationInfos)
	        	{
	        		String background = "";
	        		if (notification.getNoti_read() == 0)
	        		{
	        			background = "style='background: #e4e4e4;' ";
	        			isNotRead = true;
	        		}
	        		out.println("<li id='" + notification.getNoti_idx() + "' class='notification-item' " + background + "onclick='readNotification(\""+notification.getNoti_idx()+"\");'>");
		        	out.println("<div class='img-left'>");
		        	out.println("<img class='user-photo' src='https://u.o0bc.com/avatars/no-user-image.gif'/>");
		        	out.println("</div>");
		        	out.println("<div class='user-content'>");
		        	out.println("<p class='user-info'><span class='name'></span>" + notification.getNoti_text() +"</p>");
		        	out.println("<p class='time'>" + notification.getNoti_date() + "</p>");
		        	out.println("</div>");
		        	out.println("</li>");
	        	}
	        }
	        else
	        {
	        %>
	        	<li class="notification-item" onclick="test();">
		        	<div class="img-left">
	              		<img class="user-photo" src="https://u.o0bc.com/avatars/no-user-image.gif"/>
	            	</div>
		        	<div class="user-content">
		                <p class="user-info"><span class="name">아직 알림이 없습니다!</span></p>
		                <p class="time">-</p>  
	            	</div>
		        </li>
	        <%
	        }
	        %>
	          <!-- <li class="notification-item" v-for="user of users">
	            <div class="img-left">
	              <img class="user-photo" alt="User Photo" v-bind:src="user.picture.thumbnail" />
	            </div>
	            <div class="user-content">
	              <p class="user-info"><span class="name">{{user.name.first | capitalize}} {{user.name.last | capitalize}}</span> left a comment.</p>
	              <p class="time">1 hour ago</p>
	            </div>
	          </li> -->
	        </ul>
	      </div>
	    </transition>
	  </div>
     </div>
     <div class="logininfo">
     <a href="#"><%=session.getAttribute("user_name")%>님 반갑습니다!</a>
     </div>
     <div class="signout">
     <a href="/Graduation_KMS/op/signOut">로그아웃</a>
  	 </div>
  <%
  }
  %>
  
  <!-- <div class="menu">
    <i class="ion-drag menu__icon"></i>
  </div>   -->
</header>
    
<div class="message" style="display: none">
  <p>Search with suggestion is supported for devices with width larger than 600px. You can click on the search icon to show the search bar.</p>
</div>
  
  

    <!-- <script  src="/Graduation_KMS/Search_Bar/js/index.js"></script> -->
</body>
    <script src='https://unpkg.com/vue'></script>
	<script src='https://unpkg.com/axios/dist/axios.min.js'></script>
	<script src='https://use.fontawesome.com/releases/v5.0.4/js/all.js'></script>
	<script src="/Graduation_KMS/Search_Bar/js/notification.js"></script>
</html>
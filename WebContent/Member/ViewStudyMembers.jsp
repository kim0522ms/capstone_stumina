<%@page import="com.example.study.model.JoinRequestInfo"%>
<%@page import="com.example.study.model.UserInfo"%>
<%@page import="com.example.study.model.ScheduleBoardInfo"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Stumina</title>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
	<link rel='stylesheet prefetch' href='https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
	<link rel="stylesheet" href="/Graduation_KMS/Member/css/style.css">
	
	<style>
		a {
		    color: #FFFFFF;
		}
		h3{
			margin: 0;
			-webkit-margin-after: 0em;
		}
	</style>
</head>

<body>
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header>
	
	<% 
		ArrayList<UserInfo> userInfos = null;
		ArrayList<JoinRequestInfo> requestInfos = null;
		
		if (request.getAttribute("userInfos") != null)
		{
			userInfos = (ArrayList<UserInfo>)request.getAttribute("userInfos");
		}
		else
		{%>
			
		<%}
		if (request.getAttribute("requestInfos") != null)
		{
			requestInfos = (ArrayList<JoinRequestInfo>)request.getAttribute("requestInfos");
		}
	%>
	
	<div class='member'>
	  <div class='member-header'>
	    <div class='member-header__container mdl-layout--fixed-header mdl-layout--fixed-tabs'>
	      <div class='member-header__head'>
	        <div class='member-header__head__back-btn'>
	        </div>
	        <h2 class='member-header__head__title'>스터디 멤버 관리</h2>
	        <div class='member-header__head__search-btn'>
	          <button class='mdl-button mdl-js-button mdl-button--icon'>
	            <i class='material-icons'>search</i>
	          </button>
	        </div>
	        <div class='member-header__head__menu-btn'>
	          <button class='mdl-button mdl-js-button mdl-button--icon'>
	            <i class='material-icons'>more_vert</i>
	          </button>
	        </div>
	      </div>
	      <div class='member-header__nav mdl-tabs mdl-js-tabs mdl-js-ripple-effect'>
	        <div class='mdl-tabs__tab-bar'>
	          <a id='joined_member_tab' class='mdl-tabs__tab mdl-layout__tab is-active' href=''>가입중인 멤버</a>
	          <a id='join_request_tab' class='mdl-tabs__tab mdl-layout__tab' href=''>가입 신청 확인</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  <main class='member-content'>
	    <section class='member-list'>
	    <%
	   		if (userInfos != null)
	   		{
	   			int i = 0;
	   			for (UserInfo user : userInfos)
		    	{
	   				out.println("<div class='member-list__item'>");
		    		out.println("<span class='member-list__item__name' id='"+ i + "' value='"+ user.getUser_idx() +"'>" + user.getUser_name() +"</span>");
		    		if (user.getUser_idx().equals(request.getAttribute("std_leader").toString()))
		    		{
		    			out.println("<span class='member-list__item__admin'>(스터디 리더)</span>");
		    		}
		    		
		    		if (user.getUser_idx().equals(session.getAttribute("user_idx").toString()))
		    		{
		    			out.println("<span class='member-list__item__me' style='margin-left: 10px;'>나</span>");
		    		}
		    		out.println("</div>");
		    		i++;
		    	}
	   		}
	   		else
	   		{
	   			out.println("<div class='member-list__item'>");
	   			out.println("<span class='member-list__item__name'>가입중인 멤버가 없습니다!!</span>");
	   			out.println("</div>");
	   		}
	    %>
	    </section>
	    <section class='member-data'>
	      <h1 class='member-data__name'>정보 확인 탭</h1>
	      <div class='mdl-tabs mdl-js-tabs mdl-js-ripple-effect'>
	        <nav class='member-data__navigation mdl-tabs__tab-bar'>
	          <a class='member-data__navigation__item mdl-tabs__tab mdl-layout__tab is-active' href=''>프로필</a>
	          <a id = 'absentInfo' class='member-data__navigation__item mdl-tabs__tab mdl-layout__tab' href=''>출석 정보</a>
	        </nav>
	      </div>
	      <img src="https://u.o0bc.com/avatars/no-user-image.gif" class="img-responsive" alt="User Picture" style="margin: auto;">
	      <div class='member-data-content'>
	        <img alt='' src=''>
	        <h5 class='member-data__hi'>왼쪽의 멤버를 클릭하시면 정보를 볼 수 있습니다!</h5>
	        <p class='member-data__info'>
	          <!-- Alice was last active on the 23rd of May 2015
	          and made
	          <span class='member-data__info__money'>R450.95</span>
	          in card transactions in the last month. -->
	        </p>
	        <p class='member-data__phone'>
	        </p>
	        <section class='member-data__actions'>
	          <!-- 
	          <div class='member-data__actions__item'>
	            <i class='icon material-icons'>message</i>
	            <span class='text'>SMS</span>
	          </div>
	          <div class='member-data__actions__item'>
	            <i class='icon material-icons'>call</i>
	            <span class='text'>Call</span>
	          </div>
	          <div class='member-data__actions__item'>
	            <i class='icon material-icons'>mode_edit</i>
	            <span class='text'>Edit</span>
	          </div> 
	          -->
	          <div id='actionButtonDiv01' class='member-data__actions__item'>
	            <i id='actionButton01' class='icon material-icons'>message</i>
	            <span id='actionButtonTooltip01' class='text'>메세지 보내기</span>
	          </div>
	          <div id='actionButtonDiv02' class='member-data__actions__item' onclick='Ban_Button_click()'>
	            <i id='actionButton02' class='icon material-icons'>clear</i>
	            <span id='actionButtonTooltip02' class='text'>추방</span>
	          </div>
	        </section>
	      </div>
	    </section>
	  </main>
	</div>
	
	<script src='https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<script  src="/Graduation_KMS/Member/js/index.js"></script>
</body>
	<script defer>
		// 받아온 ArrayList JSTL 사용해서 자바스크립트 변수에 저장함
		
		var std_no = '<%=request.getAttribute("std_no").toString()%>';
		var user_idx = '<%=session.getAttribute("user_idx").toString()%>';
		var std_leader = '<%=request.getAttribute("std_leader").toString()%>';
		console.log('Study number : ' + std_no)
		
		if (user_idx != std_leader)
	    {
		  $("#join_request_tab").hide();
	    }
		
		// 가입중인 회원 정보 저장
		var userInfoList = new Array();
		
		<c:forEach items="${userInfos}" var="user">
			var userInfo = new Array();
			userInfo.push("${user.user_idx}");
			userInfo.push("${user.user_name}");
			userInfo.push("${user.user_sex}");
			userInfo.push("${user.user_id}");
			userInfo.push("${user.user_jobno}");
			userInfo.push("${user.user_belong}");
			userInfo.push("${user.user_area}");
			userInfo.push("${user.join_attcount}");
			userInfo.push("${user.join_date}");
			userInfo.push("${user.user_phone}");
			userInfoList.push(userInfo);
		</c:forEach>
		
		for (var i = 0; i < userInfoList.length; i++)
		{
			console.log('user_idx : ' + userInfoList[i][0]);
			console.log('user_name : ' + userInfoList[i][1]);
			console.log('user_sex : ' + userInfoList[i][2]);
			console.log('user_id : ' + userInfoList[i][3]);
			console.log('user_jobno : ' + userInfoList[i][4]);
			console.log('user_belong : ' + userInfoList[i][5]);
			console.log('user_area : ' + userInfoList[i][6]);
			console.log('join_attcount : ' + userInfoList[i][7]);
			console.log('join_date : ' + userInfoList[i][8]);
			console.log('user_phone : ' + userInfoList[i][9]);
			console.log('----------------------------');
		};
		
		console.log('----------------------------');
		console.log('----------------------------');
		console.log('----------------------------');
		
		
		// 요청 회원 정보 저장
		var requestUserInfoList = new Array();
		<c:forEach items="${requestInfos}" var="requestUser">
			var requestUserInfo = new Array();
			requestUserInfo.push("${requestUser.request_idx}");
			requestUserInfo.push("${requestUser.request_date}");
			requestUserInfo.push("${requestUser.study_idx}");
			requestUserInfo.push("${requestUser.user_idx}");
			requestUserInfo.push("${requestUser.user_name}");
			requestUserInfo.push("${requestUser.user_sex}");
			requestUserInfo.push("${requestUser.user_jobno}");
			requestUserInfo.push("${requestUser.user_belong}");
			requestUserInfo.push("${requestUser.user_area}");
			requestUserInfo.push("${requestUser.user_phone}");
			requestUserInfoList.push(requestUserInfo);
		</c:forEach>
		
		console.log(requestUserInfoList.length);
		for (var i = 0; i < requestUserInfoList.length; i++)
		{
			console.log('request_idx : ' + requestUserInfoList[i][0]);
			console.log('request_date : ' + requestUserInfoList[i][1]);
			console.log('study_idx; : ' + requestUserInfoList[i][2]);
			console.log('user_idx : ' + requestUserInfoList[i][3]);
			console.log('user_name : ' + requestUserInfoList[i][4]);
			console.log('user_sex : ' + requestUserInfoList[i][5]);
			console.log('user_jobno : ' + requestUserInfoList[i][6]);
			console.log('user_belong : ' + requestUserInfoList[i][7]);
			console.log('user_area : ' + requestUserInfoList[i][8]);
			console.log('user_phone : ' + requestUserInfoList[i][9]);
			console.log('----------------------------');
		};
		
		// 처음에 버튼 숨김
		$("#actionButtonDiv01").hide();
		$("#actionButtonDiv02").hide();
		
		var member_list_item = $(".member-list__item");
		console.log(member_list_item);
		var memberData_Name = $(".member-data__name");
		
		// 우측 div 정보 초기에 리스너 등록
		var member_data_content = $(".member-data-content");
		var detail_membername = $(".member-data__hi");
		var selected_idx;
		
		
		
		//console.log(member_actions.children('.icon material-icons').text());
		
		
		
		member_list_item.on("click", function() {
			  var thisitem = $(this);
			  
			  memberData_Name.text(thisitem.text() + ' 님의 정보'); 
			  
			  var sex = userInfoList[$(this).find('span').attr('id')][2] == 'M' ? '남성' : '여성' ;
			  
			  member_data_content.children('.member-data__hi').text(sex + ", " + userInfoList[$(this).find('span').attr('id')][5]);
			  member_data_content.children('.member-data__info').text(userInfoList[$(this).find('span').attr('id')][8].slice(0,10) +'일에 가입')
			  member_data_content.children('.member-data__phone').text(userInfoList[$(this).find('span').attr('id')][9]);
			  
			  member_list_item.removeClass("active");
			  thisitem.addClass("active");
			  
			  console.log('Selected user_idx : ' + $(this).find('span').attr('value'));
			  selected_idx = $(this).find('span').attr('value');
			  
			  $("#actionButtonDiv01").show();
			  if (user_idx == std_leader)
			  {
				  $("#actionButtonDiv02").show();
			  }
			  else
			  {
				  if(user_idx == selected_idx)
				  {
					  $("#actionButton02").text('clear');
					  $("#actionButtonTooltip02").text('탈퇴');
					  $("#actionButtonDiv02").attr('onclick', 'Quit_Button_click();');
					  $("#actionButtonDiv02").show();  
				  }
				  else
				  {
					  $("#actionButtonDiv02").hide();  
				  }
			  }
		});
		

		// 현재 멤버 확인 탭 기능
		document.getElementById("joined_member_tab").onclick = function(){
			$("#absentInfo").show();
			$(".member-list").empty();

			member_data_content.children('.member-data__hi').text('왼쪽의 멤버를 클릭하시면 정보를 볼 수 있습니다!');
		    member_data_content.children('.member-data__info').text('')
		    member_data_content.children('.member-data__phone').text('');
			
			for (var i = 0; i < userInfoList.length; i++)
			{
				if (userInfoList[i][0] == '<%=request.getAttribute("std_leader")%>' && userInfoList[i][0] == user_idx)
	    		{
					$(".member-list").append("<div class='member-list__item'>" + "<span class='member-list__item__name' id='"+ i + "' value='"+ userInfoList[i][0] +"'>" + userInfoList[i][1] +"</span><span class='member-list__item__admin'> (스터디 리더)</span><span class='member-list__item__me' style='margin-left: 10px;'>나</span></div>");
	    		}
				else if (userInfoList[i][0] == '<%=request.getAttribute("std_leader")%>')
				{
					$(".member-list").append("<div class='member-list__item'>" + "<span class='member-list__item__name' id='"+ i + "' value='"+ userInfoList[i][0] +"'>" + userInfoList[i][1] +"</span><span class='member-list__item__admin'> (스터디 리더)</span></div>");
				}
				else{
					$(".member-list").append("<div class='member-list__item'>" + "<span class='member-list__item__name' id='"+ i + "' value='"+ userInfoList[i][0] +"'>" + userInfoList[i][1] +"</span></div>");	
				}
			}
			
			member_list_item = $(".member-list__item");
			
			console.log(member_list_item);
			
			member_list_item.on("click", function() {
				  var thisitem = $(this);
				  
				  memberData_Name.text(thisitem.text() + ' 님의 정보'); 
				  
				  var sex = userInfoList[$(this).find('span').attr('id')][2] == 'M' ? '남성' : '여성' ;
				  
				  member_data_content.children('.member-data__hi').text(sex + ", " + userInfoList[$(this).find('span').attr('id')][5]);
				  member_data_content.children('.member-data__info').text(userInfoList[$(this).find('span').attr('id')][8].slice(0,10) +'일에 가입')
				  member_data_content.children('.member-data__phone').text(userInfoList[$(this).find('span').attr('id')][9]);
				  
				  member_list_item.removeClass("active");
				  thisitem.addClass("active");

				  console.log('Selected user_idx : ' + $(this).find('span').attr('value'));
				  selected_idx = $(this).find('span').attr('value');
				  
				  $("#actionButtonDiv01").show();
				  if (user_idx == std_leader)
				  {
					  $("#actionButtonDiv02").show();
				  }
				  else
				  {
					  if(user_idx == selected_idx)
					  {
						  $("#actionButton02").text('clear');
						  $("#actionButtonTooltip02").text('탈퇴');
						  $("#actionButtonDiv02").attr('onclick', 'Quit_Button_click();');
						  $("#actionButtonDiv02").show();  
					  }
					  else
					  {
						  $("#actionButtonDiv02").hide();  
					  }
				  }
				  
			});
			
			
			// 액션 버튼 변경
			$("#actionButtonDiv01").hide();
			$("#actionButtonDiv02").hide();
			
			$("#actionButton01").text('message');
			$("#actionButtonTooltip01").text('메세지 보내기');
			
			$("#actionButton02").text('clear');
			$("#actionButtonTooltip02").text('추방');
		};
		
		// 가입 신청 확인 탭 기능
		document.getElementById("join_request_tab").onclick = function(){
			//document.getElementById('absentInfo').style.display = "none";
			$("#absentInfo").hide();
			$(".member-list").empty();
			
			member_data_content.children('.member-data__hi').text('왼쪽의 멤버를 클릭하시면 정보를 볼 수 있습니다!');
		    member_data_content.children('.member-data__info').text('')
		    member_data_content.children('.member-data__phone').text('');
			  

			for (var i = 0; i < requestUserInfoList.length; i++)
			{
				$(".member-list").append("<div class='member-list__item'>" + "<span class='member-list__item__name' id='"+ i + "' value='"+ requestUserInfoList[i][3] +"'>" + requestUserInfoList[i][4] +"</span></div>");	
			}
			
			member_list_item = $(".member-list__item");
			
			console.log(member_list_item);
			
			member_list_item.on("click", function() {
				  var thisitem = $(this);
				  
				  memberData_Name.text(thisitem.text() + ' 님의 정보'); 
				  
				  var sex = userInfoList[$(this).find('span').attr('id')][2] == 'M' ? '남성' : '여성' ;
				  
				  member_data_content.children('.member-data__hi').text(sex + ", " + requestUserInfoList[$(this).find('span').attr('id')][7]);
				  member_data_content.children('.member-data__info').text(requestUserInfoList[$(this).find('span').attr('id')][1].slice(0,10) +'일에 가입 요청')
				  member_data_content.children('.member-data__phone').text(requestUserInfoList[$(this).find('span').attr('id')][9]);
				  
				  member_list_item.removeClass("active");
				  thisitem.addClass("active");

				  console.log('Selected user_idx : ' + $(this).find('span').attr('value'));
				  selected_idx = $(this).find('span').attr('value');
				  console.log(selected_idx);
				  
				  $("#actionButtonDiv01").show();
				  if (user_idx == std_leader)
				  {
					  $("#actionButtonDiv02").show();
				  }
				  
				  $("#actionButtonDiv01").attr('onclick', 'location.href=\"/Graduation_KMS/op/acceptMemberJoin?user_idx=' + selected_idx + '&std_no=' + std_no +'\"');
				  $("#actionButtonDiv02").attr('onclick', 'location.href=\"/Graduation_KMS/op/rejectMemberJoin?user_idx=' + selected_idx + '&std_no=' + std_no +'\"');
			});
			
			$("#actionButtonDiv01").hide();
			$("#actionButtonDiv02").hide();
			
			// 액션 버튼 변경
			$("#actionButton01").text('done_outline');
			$("#actionButtonTooltip01").text('가입 승인');
			
			$("#actionButton02").text('pan_tool');
			$("#actionButtonTooltip02").text('거절');
			
		};
		
		function Ban_Button_click(){
			var retVal = confirm("정말로 추방하시겠습니까?");

			if( retVal == true )
			{
				location.href = "/Graduation_KMS/op/banStudyMember?user_idx=" + selected_idx + "&std_no=" + std_no;
			}
			else
			{
				return;
			}
		}
		
		function Quit_Button_click(){
			var retVal = confirm("정말로 탈퇴하시겠습니까?");

			if( retVal == true )
			{
				location.href = "/Graduation_KMS/op/QuitStudyMember?user_idx=" + selected_idx + "&std_no=" + std_no;
			}
			else
			{
				return;
			}
		}
		
	</script>
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

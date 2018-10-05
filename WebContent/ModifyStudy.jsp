<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="com.example.study.model.CategoryInfo" %>
<%@ page import="com.example.study.model.DetailInfo" %>
<%@ page import="com.example.study.model.StudyInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>

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
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
	<link rel="stylesheet prefetch" href="https://fian.my.id/marka/static/marka/css/marka.css">
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>
  <style>
  	.col-md-offset-3 {
    margin-left: 0;
	}
	.col-md-6 {
    width: 100%;
	}
	.container {
    width: 100%;
    background-image: url(${pageContext.request.contextPath}/Schedule/background.jpg);
    -webkit-background-size: cover;
  	-moz-background-size: cover;
  	-o-background-size: cover;
  	background-size: cover;
	}
	.container .board {
    color: #444;
    line-height: 1.6;
    padding: 40px 0;
    margin-top: 1rem;
	}	
  </style>
</head>
<body>
	<header>
	<jsp:include page="/Search_Bar/SearchBar.jsp" />  
	<h1> </h1>
	</header>
	<section class="container" style=”clear:both;”>
	  <div class="content">
		<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
		</div>
		<%
			ArrayList<CategoryInfo> categoryInfos = null;
			ArrayList<DetailInfo> detailInfos = null;
			StudyInfo studyInfo = null;
			if (request.getAttribute("categoryInfos") != null && request.getAttribute("detailInfos") != null && request.getAttribute("studyInfo") != null)
			{
				categoryInfos = (ArrayList<CategoryInfo>)request.getAttribute("categoryInfos");
				detailInfos = (ArrayList<DetailInfo>)request.getAttribute("detailInfos");
				studyInfo = (StudyInfo)request.getAttribute("studyInfo");
			}
			else
			{
				out.print("<script>");
				out.println("alert('세션이 만료되어 메인 페이지로 이동합니다!!');");
				out.println("history.back();");
				out.println("</script>");
			}
			SimpleDateFormat oldDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss.SSS");
			SimpleDateFormat newDateFormat = new SimpleDateFormat("yy/MM/dd");
			String start_date = newDateFormat.format(oldDateFormat.parse(studyInfo.getStd_startDate()));
			String end_date = newDateFormat.format(oldDateFormat.parse(studyInfo.getStd_endDate()));
		%>
		<div class="cardwrapper">
			<div class="row" style="margin:0 auto">
			    <div class="col-md-6 col-md-offset-3">
			        <form id="msform" method="post" action="${pageContext.request.contextPath}/op/modifyStudyInfo">
			        <input type="hidden" name="std_no" value="<%=request.getParameter("std_no") %>">
			            <!-- progressbar -->
			            <ul id="progressbar">
			                <li class="active">스터디 이름 설정</li>
			                <li>카테고리 선택</li>
			                <li>기타 설정</li>
			                <li>스터디 소개 작성</li>
			            </ul>
			            <!-- fieldsets -->
			            <fieldset>
			                <h2 class="fs-title">스터디 이름을 입력해주세요!</h2>
			                <h3 class="fs-subtitle">최대 50자까지 가능합니다.</h3>
			                <input type="text" name="std_name" placeholder="이름 입력" value="<%=studyInfo.getStd_name()%>">
			                <input type="button" name="next" class="next action-button" value="다음"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">스터디의 카테고리를 선택해 주세요!</h2>
			                <h3 class="fs-subtitle">테마는 쉼표로 구분되며, 많이 작성할수록 검색에 쉽게 노출됩니다.</h3>
			                	<!-- 드롭다운 시작 -->
							<div class="button-group">
								<i id="icon"></i>
								<a id="input_Study1" href="">대분류</a>
								  <ul id="dropdown-menu_Study1">
								  <%
								  	for (CategoryInfo category : categoryInfos)
								  	{
								  		out.println("<li><a href='#' value='"+ category.getCategory_idx() +"'>" + category.getCategory_name() +"</a></li>");	
								  	}
								  %>
								  </ul>
								  <input type="hidden" id="area_idx" name="area_idx" value="">
							</div>
							<!-- 드롭다운 끝 -->
							<!-- 드롭다운 시작 -->
							<div class="button-group">
								<i id="icon"></i>
								<a id="input_Study2" href="">소분류</a>
								  <ul id="dropdown-menu_Study2">
							   	  <%
							  	 	for (DetailInfo detail : detailInfos)
							  		{
							  			out.println("<li><a href='#' id='" + detail.getCategory_idx() + "'value='" + detail.getDetail_idx() + "'>" + detail.getDetail_name() +"</a></li>");	
							  		}
								  %>
								  </ul>
								  <input type="hidden" id="detail_idx" name="detail_idx" value="">
							</div>
							<!-- 드롭다운 끝 -->
			                <input type="text" name="std_theme" placeholder="스터디의 테마를 입력해 주세요." value="<%=studyInfo.getStd_theme()%>"/>
			                <!-- 
			                <input type="text" name="gplus" placeholder="Google Plus"/>
			                -->
			                <input type="button" name="previous" class="previous action-button-previous" value="이전"/>
			                <input type="button" name="next" class="next action-button" value="다음"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">기타 설정을 입력해 주세요!</h2>
			                <h3 class="fs-subtitle">스터디 기간, 최대 인원 등을 설정합니다.</h3>
			                <input type="text" name="std_maxmember" placeholder="최대 인원" value="<%=studyInfo.getStd_maxMemberCount() %>" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" style="IME-MODE:disabled;"/>
			                <input type="text" name="std_maxattcount" placeholder="최대 결석 가능 횟수" value="<%=studyInfo.getStd_maxattcnt()%>"onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" style="IME-MODE:disabled;"/>
			                	<!-- 시작날짜 DatePicker Start-->
			                <div class="date-picker">
								<div class="input">
									<div class="result">시작 날짜: <span id="startdate"><%=start_date %></span></div>
									<input type="hidden" id="std_startdate" name="std_startdate" value="<%=start_date %>">
									<button><i class="	fa fa-calendar"></i></button>
								</div>
								<div class="calendar"></div>
							</div>
							<!-- 시작날짜 DatePicker End -->
							<!-- 종료날짜 DatePicker Start-->
			                <div class="date-picker">
								<div class="input">
									<div class="result">종료 날짜: <span id="enddate"><%=end_date %></span></div>
									<input type="hidden" id="std_enddate" name="std_enddate" value="<%=end_date %>">
									<button><i class="	fa fa-calendar"></i></button>
								</div>
								<div class="calendar"></div>
							</div>
							<!-- 종료날짜 DatePicker End -->
			                <input type="button" name="previous" class="previous action-button-previous" value="이전"/>
			                <input type="button" name="next" class="next action-button" value="다음"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">스터디 소개를 입력해 주세요!</h2>
			                <h3 class="fs-subtitle">Fill in your credentials</h3>
			                <textarea rows="5" cols="30" name="std_contents"><%=studyInfo.getStd_contents() %></textarea>
			                <!-- <input type="text" name="email" placeholder="Email"/> -->
			                <input type="button" name="previous" class="previous action-button-previous" value="이전"/>
			                <input type="submit" name="submit" class="submit action-button" value="등록하기!"/>
			            </fieldset>
			        </form>
			        <!-- link to designify.me code snippets -->
			        <div class="dme_link">
			            <p></p>
			        </div>
			        <!-- /.link to designify.me code snippets -->
			    </div>
			</div>
		</div>
	</section>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>
	<script src="https://fian.my.id/marka/static/marka/js/marka.js"></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
  
	<script src="${pageContext.request.contextPath}/js/datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/js/step.js"></script>
    <script src="${pageContext.request.contextPath}/js/dropdown.js"></script>
</body>
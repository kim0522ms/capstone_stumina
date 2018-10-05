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
	<section class="container" style=��clear:both;��>
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
				out.println("alert('������ ����Ǿ� ���� �������� �̵��մϴ�!!');");
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
			                <li class="active">���͵� �̸� ����</li>
			                <li>ī�װ� ����</li>
			                <li>��Ÿ ����</li>
			                <li>���͵� �Ұ� �ۼ�</li>
			            </ul>
			            <!-- fieldsets -->
			            <fieldset>
			                <h2 class="fs-title">���͵� �̸��� �Է����ּ���!</h2>
			                <h3 class="fs-subtitle">�ִ� 50�ڱ��� �����մϴ�.</h3>
			                <input type="text" name="std_name" placeholder="�̸� �Է�" value="<%=studyInfo.getStd_name()%>">
			                <input type="button" name="next" class="next action-button" value="����"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">���͵��� ī�װ��� ������ �ּ���!</h2>
			                <h3 class="fs-subtitle">�׸��� ��ǥ�� ���еǸ�, ���� �ۼ��Ҽ��� �˻��� ���� ����˴ϴ�.</h3>
			                	<!-- ��Ӵٿ� ���� -->
							<div class="button-group">
								<i id="icon"></i>
								<a id="input_Study1" href="">��з�</a>
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
							<!-- ��Ӵٿ� �� -->
							<!-- ��Ӵٿ� ���� -->
							<div class="button-group">
								<i id="icon"></i>
								<a id="input_Study2" href="">�Һз�</a>
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
							<!-- ��Ӵٿ� �� -->
			                <input type="text" name="std_theme" placeholder="���͵��� �׸��� �Է��� �ּ���." value="<%=studyInfo.getStd_theme()%>"/>
			                <!-- 
			                <input type="text" name="gplus" placeholder="Google Plus"/>
			                -->
			                <input type="button" name="previous" class="previous action-button-previous" value="����"/>
			                <input type="button" name="next" class="next action-button" value="����"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">��Ÿ ������ �Է��� �ּ���!</h2>
			                <h3 class="fs-subtitle">���͵� �Ⱓ, �ִ� �ο� ���� �����մϴ�.</h3>
			                <input type="text" name="std_maxmember" placeholder="�ִ� �ο�" value="<%=studyInfo.getStd_maxMemberCount() %>" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" style="IME-MODE:disabled;"/>
			                <input type="text" name="std_maxattcount" placeholder="�ִ� �Ἦ ���� Ƚ��" value="<%=studyInfo.getStd_maxattcnt()%>"onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" style="IME-MODE:disabled;"/>
			                	<!-- ���۳�¥ DatePicker Start-->
			                <div class="date-picker">
								<div class="input">
									<div class="result">���� ��¥: <span id="startdate"><%=start_date %></span></div>
									<input type="hidden" id="std_startdate" name="std_startdate" value="<%=start_date %>">
									<button><i class="	fa fa-calendar"></i></button>
								</div>
								<div class="calendar"></div>
							</div>
							<!-- ���۳�¥ DatePicker End -->
							<!-- ���ᳯ¥ DatePicker Start-->
			                <div class="date-picker">
								<div class="input">
									<div class="result">���� ��¥: <span id="enddate"><%=end_date %></span></div>
									<input type="hidden" id="std_enddate" name="std_enddate" value="<%=end_date %>">
									<button><i class="	fa fa-calendar"></i></button>
								</div>
								<div class="calendar"></div>
							</div>
							<!-- ���ᳯ¥ DatePicker End -->
			                <input type="button" name="previous" class="previous action-button-previous" value="����"/>
			                <input type="button" name="next" class="next action-button" value="����"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">���͵� �Ұ��� �Է��� �ּ���!</h2>
			                <h3 class="fs-subtitle">Fill in your credentials</h3>
			                <textarea rows="5" cols="30" name="std_contents"><%=studyInfo.getStd_contents() %></textarea>
			                <!-- <input type="text" name="email" placeholder="Email"/> -->
			                <input type="button" name="previous" class="previous action-button-previous" value="����"/>
			                <input type="submit" name="submit" class="submit action-button" value="����ϱ�!"/>
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
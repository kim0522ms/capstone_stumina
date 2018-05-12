<%@page import="com.example.study.model.RoomsInfo"%>
<%@page import="com.example.study.model.StudyRoomInfo"%>
<%@page import="com.example.study.model.AreaInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="com.example.study.model.CategoryInfo" %>
<%@ page import="com.example.study.model.DetailInfo" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Stumina</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link rel='stylesheet prefetch' href='http://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.css'>
	<link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.1.8/semantic.css'>
	<link rel="stylesheet" href="/Graduation_KMS/css/style.css">
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
	<link rel="stylesheet prefetch" href="http://fian.my.id/marka/static/marka/css/marka.css">
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
    background-image: url(/Graduation_KMS/Schedule/background.jpg);
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
		<div class="board">
		  <div class="content">
			<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
			</div>
		</div>
		<%
			String std_no = null;
			ArrayList<AreaInfo> areaInfos = null;
			ArrayList<StudyRoomInfo> studyRoomInfos = null;
			ArrayList<RoomsInfo> roomInfos = null;
			if (request.getAttribute("areaInfos") != null && request.getAttribute("studyRoomInfos") != null && request.getAttribute("roomInfos") != null && request.getAttribute("std_no") != null)
			{
				areaInfos = (ArrayList<AreaInfo>)request.getAttribute("areaInfos");
				studyRoomInfos = (ArrayList<StudyRoomInfo>)request.getAttribute("studyRoomInfos");
				roomInfos = (ArrayList<RoomsInfo>)request.getAttribute("roomInfos");
				std_no = request.getAttribute("std_no").toString();
			}
			else
			{
				//TODO: ����ó��
			}
		%>
		<div class="cardwrapper">
			<div class="row" style="margin:0 auto">
			    <div class="col-md-6 col-md-offset-3">
			        <form id="msform" method="post" action="/Graduation_KMS/op/registSchedule">
			        	<input type="hidden" name="std_no" value="<%=std_no%>" />
			            <!-- progressbar -->
			            <ul id="progressbar">
			                <li class="active">������ �̸� ����</li>
			                <li>���͵�� ����</li>
			                <li>��¥ �� ����</li>
			                <li>��Ÿ �޸� �ۼ�</li>
			            </ul>
			            <!-- fieldsets -->
			            <fieldset>
			                <h2 class="fs-title">������ ������ �Է����ּ���!</h2>
			                <h3 class="fs-subtitle">�ִ� 50�ڱ��� �����մϴ�.</h3>
			                <input type="text" name="rsch_name" placeholder="�̸� �Է�"/>
			                <input type="button" name="next" class="next action-button" value="����"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">���͵���� ������ �ּ���!</h2>
			                <h3 class="fs-subtitle"></h3>
			                <!-- ��Ӵٿ� ���� -->
							<div id="ddl_1" class="button-group">
								<i id="icon"></i>
								<a id="input" href="">������ ������ �ּ���!</a>
								  <ul id="dropdown-menu">
								  <%
								  	if (areaInfos != null)
								  	{
									  	for (AreaInfo area : areaInfos)
									  	{
									  		out.println("<li><a href='#' id='"+ area.getArea_idx()+"'>" + area.getArea_name() +"</a></li>");	
									  	}
								  	}
								  %>
								  </ul>
								  <input type="hidden" id="area_idx" name="area_idx" value="">
							</div>
							<!-- ��Ӵٿ� �� -->
							<!-- ��Ӵٿ� ���� -->
							<div id="ddl_2" class="button-group" style="display:none;">
								<i id="icon"></i>
								<a id="input2" href="">���͵���� ������ �ּ���!</a>
								  <ul id="dropdown-menu2">
							   	  <%
 							   	  	if (studyRoomInfos != null)
								  	{
									  	for (StudyRoomInfo studyroom : studyRoomInfos)
									  	{
									  		out.println("<li><a id='" + studyroom.getArea_idx() + "' href='#' value='"+ studyroom.getStudyroom_idx() +"'>" + studyroom.getStudyroom_name() +"</a></li>");	
									  	}
								  	}
							   	  	
								  %>
								  </ul>
								  <input type="hidden" id="sr_idx" name="sr_idx" value="">
							</div>
							<!-- ��Ӵٿ� �� -->
							<!-- ��Ӵٿ� ���� -->
							<div id="ddl_3" class="button-group" style="display:none;">
								<i id="icon"></i>
								<a id="input3" href="">���� ������ �ּ���!</a>
								  <ul id="dropdown-menu3">
							   	  <%
							   	  	if (roomInfos != null)
								  	{
									  	for (RoomsInfo room : roomInfos)
									  	{
									  		out.println("<li><a id='" + room.getStudyroom_idx()+ "' href='#' value='"+ room.getRoom_idx() +"'>" + room.getRoom_name() + "  (" + room.getRoom_maxmember() + "�ν�)</a></li>");
									  		out.println("<input type='hidden' id='"+ room.getRoom_idx() + "' value='" + room.getRoom_pay() +"' />");
									  	}
								  	}
								  %>
								  </ul>
								  <input type="hidden" id="room_idx" name="room_idx" value="">
							</div>
							<!-- ��Ӵٿ� �� -->
							<div style="margin-bottom: 15px;">
								<h2 id="payment" style="display:none;"></h2>
							</div>
			                <!-- <input type="text" name="std_theme" placeholder="���͵��� �׸��� �Է��� �ּ���."/> -->
			                <!-- 
			                <input type="text" name="gplus" placeholder="Google Plus"/>
			                -->
			                <input type="button" name="previous" class="previous action-button-previous" value="����"/>
			                <input type="button" name="next" class="next action-button" value="����"/>
			            </fieldset>
			            <fieldset style="max-width: 700px;">
			                <h2 class="fs-title">��¥�� �ð��� ������ �ּ���!</h2>
			                <h3 class="fs-subtitle">������ 1�� �������� �����մϴ�.</h3>
			                <!-- ���۳�¥ DatePicker Start-->
			                <div class="date-picker">
								<div class="input">
									<div class="result">���� ��¥ : <span id="selectedDate"></span></div>
									<input type="hidden" id="rsch_date" name="rsch_date" value="">
									<button><i class="	fa fa-calendar"></i></button>
								</div>
								<div class="calendar"></div>
							</div>
							<!-- ���۳�¥ DatePicker End -->
			                <%-- 
			                <input type="text" name="std_maxmember" placeholder="�ִ� �ο�" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" style="IME-MODE:disabled;"/>
			                <input type="text" name="std_maxattcount" placeholder="�ִ� �Ἦ ���� Ƚ��" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" style="IME-MODE:disabled;"/>
			                 --%>
			                <div style="width=100%;">
			                <!-- ��Ӵٿ� ���� -->
								<div id="ddl_4" class="button-group" style="max-width: 48%;float: left;">
									<i id="icon" style="z-index: 50;"></i>
									<a id="input4" href="">�Խ� �ð�</a>
									  <ul id="dropdown-menu4">
										  <li><a id = '9' href='#'>���� 9��</a></li>
										  <li><a id = '10' href='#'>���� 10��</a></li>
										  <li><a id = '11' href='#'>���� 11��</a></li>
										  <li><a id = '12' href='#'>���� 12��</a></li>
										  <li><a id = '13' href='#'>���� 1��</a></li>
										  <li><a id = '14' href='#'>���� 2��</a></li>
										  <li><a id = '15' href='#'>���� 3��</a></li>
										  <li><a id = '16' href='#'>���� 4��</a></li>
										  <li><a id = '17' href='#'>���� 5��</a></li>
										  <li><a id = '18' href='#'>���� 6��</a></li>
										  <li><a id = '19' href='#'>���� 7��</a></li>
										  <li><a id = '20' href='#'>���� 8��</a></li>
										  <li><a id = '21' href='#'>���� 9��</a></li>
										  <li><a id = '22' href='#'>���� 10��</a></li>
									  </ul>
									  <input type="hidden" id="rsch_checkin" name="rsch_checkin" value="">
								</div>
								<!-- ��Ӵٿ� �� -->
								<!-- ��Ӵٿ� ���� -->
								<div id="ddl_5" class="button-group" style="max-width: 48%; float: right;">
									<i id="icon" style="z-index: 50;"></i>
									<a id="input5" href="">��� �ð�</a>
									  <ul id="dropdown-menu5">
										  <li><a id = '10' href='#'>���� 10��</a></li>
										  <li><a id = '11' href='#'>���� 11��</a></li>
										  <li><a id = '12' href='#'>���� 12��</a></li>
										  <li><a id = '13' href='#'>���� 1��</a></li>
										  <li><a id = '14' href='#'>���� 2��</a></li>
										  <li><a id = '15' href='#'>���� 3��</a></li>
										  <li><a id = '16' href='#'>���� 4��</a></li>
										  <li><a id = '17' href='#'>���� 5��</a></li>
										  <li><a id = '18' href='#'>���� 6��</a></li>
										  <li><a id = '19' href='#'>���� 7��</a></li>
										  <li><a id = '20' href='#'>���� 8��</a></li>
										  <li><a id = '21' href='#'>���� 9��</a></li>
										  <li><a id = '22' href='#'>���� 10��</a></li>
										  <li><a id = '23' href='#'>���� 11��</a></li>
									  </ul>
									  <input type="hidden" id="rsch_checkout" name="rsch_checkout" value="">
								</div>
								<!-- ��Ӵٿ� �� -->
							</div>
							<div style="margin-bottom: 15px;">
								<h2 id="pay_amount" name="pay_amount" style="display:none;"></h2>
								<input type="hidden" id="rsch_pay" name="rsch_pay" value=""/>
							</div>
			                <input type="button" name="previous" class="previous action-button-previous" value="����"/>
			                <input type="button" name="next" class="next action-button" value="����"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">������ ���������� �Է��� �ּ���!</h2>
			                <h3 class="fs-subtitle">�غ� ���� �ִٸ� ����鿡�� �˷��ּ���.</h3>
			                <textarea rows="5" cols="30" name="rsch_commnet"></textarea>
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
	<script src="http://fian.my.id/marka/static/marka/js/marka.js"></script>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
  
	<script src="/Graduation_KMS/js/datepicker.js"></script>
    <script src="/Graduation_KMS/js/step.js"></script>
    <script src="/Graduation_KMS/js/dropdown.js"></script>
</body>
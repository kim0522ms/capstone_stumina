<%@page import="com.example.study.model.ScheduleBoardInfo"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Stumina</title>
	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto'>
	<link rel="stylesheet" href="/Graduation_KMS/ScheduleThread/css/style.css">
	<style>
		a {
		    color: #FFFFFF;
		}
	</style>
</head>

<body>
	<%
		ArrayList<ScheduleBoardInfo> threadInfos = null;
		String rsch_idx = null;
	
		if (request.getAttribute("threadInfos") != null)
		{
			threadInfos = (ArrayList<ScheduleBoardInfo>)request.getAttribute("threadInfos");
			rsch_idx = threadInfos.get(0).getRsch_idx();
		}
	%>
	<input type="hidden" id="rsch_idx" name="rsch_idx" value="<%=rsch_idx %>" />
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header>
	<div class="container-fluid mainContainer">
	    <div class="header">
	        <ol class="breadcrumb">
	            <li><a href="#" style="color: #575965;" >스터디</a></li>
	            <li><a href="#" style="color: #575965;" >스케줄</a></li>
	            <li class="active"><%=request.getAttribute("rsch_name").toString() %></li>
	        </ol>
	
	        <div class="headline">
	            <div class="row">
	                <div class="col-xs-10">
	                    <h2>[<%=request.getAttribute("rsch_name").toString()%>] 스케쥴의 쓰레드</h2>
	                </div>
	                <div class="col-xs-2 text-right">
	
	                </div>
	            </div>
	        </div>
	        <% 
	        if (threadInfos != null)
	        {
		        for (ScheduleBoardInfo thread : threadInfos)
		        {%>
		        	<section class="post">
		            <div class="row">
		                <div class="col-md-2 text-center">
		                    <img src="https://u.o0bc.com/avatars/no-user-image.gif" class="img-responsive" alt="User Picture"/>
		                    <div class="profile-info">
		                        <p><%=thread.getUser_name() %></p>
		                        <p><%=thread.getUser_belong() %></p>
		                        <p>　</p>
		                        <p><%=thread.getScb_time().split(System.getProperty("line.separator"))[0] %></p>
		                        <p><%=thread.getScb_time().split(System.getProperty("line.separator"))[1] %></p>
		                    </div>
		                </div>
		                <div class="col-md-10">
		                    <h1><%=thread.getScb_title()%></h1>
							<div id="text-1">
							<%
								for(String line : thread.getScb_content().split(System.getProperty("line.separator")))
								{
									out.println("<p>" + line + "</p>");
								}
							%>
							</div>
		                    <div class="signature">
		                        <p>TODO: 첨부파일 기능 추가할것</p>
		                    </div>
		                </div>
		            </div>
		            <div class="answer text-right">
		                <a class="ma-button answer"><i class="fa fa-reply" aria-hidden="true"></i> 답글 달기</a>
		                <!-- <a class="ma-button quote-id" data-quote-id="text-1" data-name="Max Lorem"><i class="fa fa-quote-left" aria-hidden="true"></i> Zitieren</a> -->
		                <a class="ma-button"><i class="fa fa-thumbs-up" aria-hidden="true"></i> 삭제</a>
		            </div>
		        	</section>
		        	<%
		        }
	        }
	        else
	        {%>
	        	<section class="post">
	            <div class="row">
	                <div class="col-md-2 text-center">
	                    <img src="https://u.o0bc.com/avatars/no-user-image.gif" class="img-responsive" alt="User Picture"/>
	                    <div class="profile-info">
	                        <p></p>
	                    </div>
	                </div>
	                <div class="col-md-10">
	                    <h1>아직 쓰레드가 없습니다!</h1>
						<div id="text-1">
							새로운 쓰레드를 등록해주세요!
						</div>
	                    <div class="signature">
	                        <p>TODO: 첨부파일 기능 추가할것</p>
	                    </div>
	                </div>
	            </div>
	            <div class="answer text-right">
	                <a class="ma-button answer"><i class="fa fa-reply" aria-hidden="true"></i> 답글 달기</a>
	                <a class="ma-button quote-id" data-quote-id="text-1" data-name="Max Lorem"><i class="fa fa-quote-left" aria-hidden="true"></i> Zitieren</a>
	                <a class="ma-button"><i class="fa fa-thumbs-up" aria-hidden="true"></i> Danke</a>
	            </div>
	        	</section>
	        <%}
	        %>
	    </div>
	
	    <div class="headline" id="answer">
	        <h2>새 글 쓰기</h2>
	    </div>
	    
	    <!-- 텍스트 에디터 -->
	    <form name="editorForm" action="/test1234">
	    <jsp:include page="/TextEditor/Editor.jsp" />
	    <a id="uploadButton" class="ma-button" ><i class="fa fa-reply" aria-hidden="true"></i> 등록</a>
	    </form>
	    <div class="new-old">
	        <a href="#" class="ma-button"><i class="fa fa-angle-double-left" aria-hidden="true"></i> 이전 쓰레드</a>
	        <a href="#" class="ma-button">다음 쓰레드 <i class="fa fa-angle-double-right" aria-hidden="true"></i> </a>
	    </div>
	
	    <div class="headline">
	        <h2>Ähnliche Themen</h2>
	    </div>
	    <div class="extraContent row">
	        <div class="col-md-3">
	            <div class="similar-thread">
	                <h3><a href="#">Lorem Ipsum</a></h3>
	                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat...</p>
	                <p><a href="#">Mehr lesen..</a></p>
	            </div>
	        </div>
	        <div class="col-md-3">
	            <div class="similar-thread">
	                <h3><a href="#">Lorem Ipsum</a></h3>
	                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat...</p>
	                <p><a href="#">Mehr lesen..</a></p>
	            </div>
	        </div>
	        <div class="col-md-3">
	            <div class="similar-thread">
	                <h3><a href="#">Lorem Ipsum</a></h3>
	                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat...</p>
	                <p><a href="#">Mehr lesen..</a></p>
	            </div>
	        </div>
	        <div class="col-md-3">
	            <div class="similar-thread">
	                <h3><a href="#">Lorem Ipsum</a></h3>
	                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat...</p>
	                <p><a href="#">Mehr lesen..</a></p>
	            </div>
	        </div>
	    </div>
	</div>
	<script>
	    $(function () {
	        $('[data-toggle="tooltip"]').tooltip()
	    })
	</script>
	  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<script  src="/Graduation_KMS/ScheduleThread/js/index.js"></script>
</body>

</html>

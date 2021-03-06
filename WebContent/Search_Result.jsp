<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="com.example.study.model.StudyInfo" %>
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
</head>
<body>
	<header>
	<jsp:include page="/Search_Bar/SearchBar.jsp" />  
	<h1> </h1>
	</header>
	<style>
		.container .board {
		    background-image: url(${pageContext.request.contextPath}/Schedule/background.jpg);
		    background-size: cover;
		    height: 100%;
		}
		.ui.segment {
		    position: relative;
		    box-shadow: rgba(34, 36, 38, 0.15) 0px 1px 2px 0px;
		    margin: 1rem 0em;
		    padding: 1em;
		    border-radius: 0.285714rem;
		    border-width: 1px;
		    border-style: solid;
		    border-color: rgba(34, 36, 38, 0.15);
		    border-image: initial;
		    background: rgba(45, 48, 66, 0.7);
		}		
		.ui.cards > .card, .ui.card {
			background: #2d3042;
		    box-shadow: none;
		}
		.container p {
	    	color: white;
		}
		
	</style>
	<section class="container" style=”clear:both;”>
	  <div class="board">
		  <div class="content">
			<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
		    <h3>
		    <%
		    if (request.getAttribute("keyword") != null)
		    {%>
		    	<%=request.getAttribute("keyword")%>에 대해 검색하신 결과입니다.
		    <%}
		    else
		    {%>
		    	검색 결과가 없습니다!
		    <%}%>
		    </h3>
		    <div class="ui segment">
		    	<div class="ui cards">
		    	<%
			    	if(request.getAttribute("studyInfos") != null)
			    	{
			    		ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>)request.getAttribute("studyInfos");
			    		
			    		String path;
			    		for (StudyInfo studyinfo : studyInfos)
			    		{
			    			%>
			    			<div class="ui card">
				      			<div class="content">
				      				<p class="card_title"><%=studyinfo.getStd_name()%></p>
				      			</div>
				      			<div class="ui slide masked reveal">
				        		<% 
				        			if (studyinfo.getStd_imagepath() != null)
				        			{
				        				path = studyinfo.getStd_imagepath();
				        			}
				        			else // 이미지 등록 안했으면 기본 Path
				        			{
				        				path = "https://www.pagoda21.com/images/upload/2014/09/PGS_201409030639229600.jpg";
				        			}
			        			%>
			        			<img src="<%=path%>" class="visible content"><div class="hidden content">
				        			<p class="p-content"><p>모집 인원 : <%=studyinfo.getStd_maxMemberCount() %>명<br>남은 인원 : <%=studyinfo.getStd_remainMember()%>명</p><button class="ui primary button" onclick="location.href='${pageContext.request.contextPath}/op/viewstudyinfo?std_no=<%=studyinfo.getStd_no()%>'">자세히 보기</button>
				          			</p>
				        			</div>
				      			</div>
				    		</div>
			    			<%
			    		}
			    		
			    	}
			    	else{
			    		%>
			    			<br>
			    			<h3>직접 스터디를 만들어 볼까요?&nbsp<a href='${pageContext.request.contextPath}/op/createStudy'>만들러 가기!</a></h3>
			    			<br>
			    		<%
			    	}
		    	%>
		    	
		    	</div>
		  </div>
	  
		  <div class="content">
			    <h3 style="margin-top: 8%;">이런 스터디는 어떠세요?</h3>
			    
			    <!-- 카드 리스트 시작 -->
			    <div class="ui segment">
			    	<div class="ui cards">
			    	<!-- 카드 시작 -->
			    		<div class="ui card">
					      <div class="content">
					        <p class="card_title">신라대학교<br>토익 850 스터디</p>
					      </div>
					      <div class="ui slide masked reveal">
					        <img src="https://www.pagoda21.com/images/upload/2014/09/PGS_201409030639229600.jpg" class="visible content">
					        <div class="hidden content">
					          <p class="p-content"><p>모집 인원 : 15명<br>남은 인원 : 3명</p><button class="ui primary button" onclick="modalOpen()">자세히 보기</button>
					          </p>
					        </div>
					      </div>
					    </div>
					<!-- 카드 끝 -->
					<!-- 카드 시작 -->
			    		<div class="ui card">
					      <div class="content">
					        <p class="card_title">부경대학교<br>공공경제학 스터디</p>
					      </div>
					      <div class="ui slide masked reveal">
					        <img src="https://cfile5.uf.tistory.com/image/2366523F57D629D028B73E" class="visible content">
					        <div class="hidden content">
					          <p class="p-content"><p>모집 인원 : 15명<br>남은 인원 : 3명</p><button class="ui primary button" onclick="modalOpen()">자세히 보기</button>
					          </p>
					        </div>
					      </div>
					    </div>
					<!-- 카드 끝 -->
					<!-- 카드 시작 -->
			    		<div class="ui card">
					      <div class="content">
					        <p class="card_title">AWS 클라우드 세미나<br>AWSome DAY</p>
					      </div>
					      <div class="ui slide masked reveal">
					        <img src="https://a0.awsstatic.com/main/images/logos/aws_logo_smile_1200x630.png" class="visible content">
					        <div class="hidden content">
					          <p class="p-content"><p>모집 인원 : 15명<br>남은 인원 : 3명</p><button class="ui primary button" onclick="modalOpen()">자세히 보기</button>
					          </p>
					        </div>
					      </div>
					    </div>
					<!-- 카드 끝 -->
			    	</div>
			    </div>    
			    <!-- 카드 리스트 끝 -->
			  </div>
		</div>
	</section>
	<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js'></script>
	<script src="${pageContext.request.contextPath}/js/index.js"></script>  
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="com.example.study.model.StudyInfo" %>
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

  
</head>
<body>
	<header>
	<jsp:include page="/Search_Bar/SearchBar.jsp" />  
	<h1> </h1>
	</header>
	<section class="container" style=”clear:both;”>
	
	  <div class="board">
	  <nav class="side-menu">
		    <h3>테마별 검색</h3>
		    <ul class="Sections">
		      <li><a href="#">최근 등록</a></li>
		      <li><a href="#">가까운 지역</a></li>
		      <li><a href="#">인기 스터디</a></li>
		    </ul>
		
		    <h3>지역별 검색</h3>
		
		    <ul class="Topics">
		      <li><a href="#">서울</a></li>
		      <li><a href="#">경기</a></li>
		      <li><a class="active" href="#">부산</a></li>
		      <li><a href="#">강원</a></li>
		      <li><a href="#">Environment</a></li>
		      <li><a href="#">Family</a></li>
		      <li><a href="#">Instant Karma</a></li>
		      <li><a href="#">Justice</a></li>
		      <li><a href="#">Media</a></li>
		      <li><a href="#">Religion</a></li>
		      <li><a href="#">Science</a></li>
		      <li><a href="#">Business</a></li>
		      <li><a href="#">Faith</a></li>
		      <li><a href="#">Military</a></li>
		      <li><a href="#">Police</a></li>
		    </ul>
		
		    <h3>Qpolitical</h3>
		
		    <ul class="qpolitical">
		      <li><a href="#">Contact</a></li>
		      <li><a href="#">Privacy</a></li>
		      <li><a href="#">Terms</a></li>
		      <li><a href="#">Facebook</a></li>
		    </ul>
		</nav>
		  <div class="content">
		    <h3><%=request.getAttribute("keyword") %>에 대해 검색하신 결과입니다.</h3>
		    <div class="ui segment">
		    	<div class="ui cards">
		    	<%
			    	if(request.getAttribute("studyInfos") != null)
			    	{
			    		ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>)request.getAttribute("studyInfos");
			    		
			    		for (StudyInfo studyinfo : studyInfos)
			    		{
			    			%>
			    			<div class="ui card">
				      			<div class="content">
				      				<p class="card_title"><%=studyinfo.getStd_name()%></p>
				      			</div>
				      			<div class="ui slide masked reveal">
				        			<img src="https://www.pagoda21.com/images/upload/2014/09/PGS_201409030639229600.jpg" class="visible content">
				        			<div class="hidden content">
				        			<p class="p-content"><p>모집 인원 : <%=studyinfo.getStd_maxMemberCount() %>명<br>남은 인원 : <%=studyinfo.getStd_remainMember()%>명</p><button class="ui primary button" onclick="location.href='/Graduation_KMS/op/viewstudyinfo?std_no=<%=studyinfo.getStd_no()%>'">자세히 보기</button>
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
			    			<h3>아직 개설된 세미나가 없네요!</h3>
			    			<br>
			    		<%
			    	}
		    	%>
		    	
		    	</div>
		  </div>
	  
		  <div class="content">
			    <h3>이런 스터디는 어떠세요?</h3>
			    
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
					        <img src="http://cfile5.uf.tistory.com/image/2366523F57D629D028B73E" class="visible content">
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
	<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js'></script>
	<script src="/Graduation_KMS/js/index.js"></script>  
</body>
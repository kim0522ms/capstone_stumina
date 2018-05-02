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
	<section class="container" style=��clear:both;��>
	
	  <div class="board">
	  <nav class="side-menu">
		    <h3>�׸��� �˻�</h3>
		    <ul class="Sections">
		      <li><a href="#">�ֱ� ���</a></li>
		      <li><a href="#">����� ����</a></li>
		      <li><a href="#">�α� ���͵�</a></li>
		    </ul>
		
		    <h3>������ �˻�</h3>
		
		    <ul class="Topics">
		      <li><a href="#">����</a></li>
		      <li><a href="#">���</a></li>
		      <li><a class="active" href="#">�λ�</a></li>
		      <li><a href="#">����</a></li>
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
		    <h3><%=request.getAttribute("keyword") %>�� ���� �˻��Ͻ� ����Դϴ�.</h3>
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
				        			<p class="p-content"><p>���� �ο� : <%=studyinfo.getStd_maxMemberCount() %>��<br>���� �ο� : <%=studyinfo.getStd_remainMember()%>��</p><button class="ui primary button" onclick="location.href='/Graduation_KMS/op/viewstudyinfo?std_no=<%=studyinfo.getStd_no()%>'">�ڼ��� ����</button>
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
			    			<h3>���� ������ ���̳��� ���׿�!</h3>
			    			<br>
			    		<%
			    	}
		    	%>
		    	
		    	</div>
		  </div>
	  
		  <div class="content">
			    <h3>�̷� ���͵�� �����?</h3>
			    
			    <!-- ī�� ����Ʈ ���� -->
			    <div class="ui segment">
			    	<div class="ui cards">
			    	<!-- ī�� ���� -->
			    		<div class="ui card">
					      <div class="content">
					        <p class="card_title">�Ŷ���б�<br>���� 850 ���͵�</p>
					      </div>
					      <div class="ui slide masked reveal">
					        <img src="https://www.pagoda21.com/images/upload/2014/09/PGS_201409030639229600.jpg" class="visible content">
					        <div class="hidden content">
					          <p class="p-content"><p>���� �ο� : 15��<br>���� �ο� : 3��</p><button class="ui primary button" onclick="modalOpen()">�ڼ��� ����</button>
					          </p>
					        </div>
					      </div>
					    </div>
					<!-- ī�� �� -->
					<!-- ī�� ���� -->
			    		<div class="ui card">
					      <div class="content">
					        <p class="card_title">�ΰ���б�<br>���������� ���͵�</p>
					      </div>
					      <div class="ui slide masked reveal">
					        <img src="http://cfile5.uf.tistory.com/image/2366523F57D629D028B73E" class="visible content">
					        <div class="hidden content">
					          <p class="p-content"><p>���� �ο� : 15��<br>���� �ο� : 3��</p><button class="ui primary button" onclick="modalOpen()">�ڼ��� ����</button>
					          </p>
					        </div>
					      </div>
					    </div>
					<!-- ī�� �� -->
					<!-- ī�� ���� -->
			    		<div class="ui card">
					      <div class="content">
					        <p class="card_title">AWS Ŭ���� ���̳�<br>AWSome DAY</p>
					      </div>
					      <div class="ui slide masked reveal">
					        <img src="https://a0.awsstatic.com/main/images/logos/aws_logo_smile_1200x630.png" class="visible content">
					        <div class="hidden content">
					          <p class="p-content"><p>���� �ο� : 15��<br>���� �ο� : 3��</p><button class="ui primary button" onclick="modalOpen()">�ڼ��� ����</button>
					          </p>
					        </div>
					      </div>
					    </div>
					<!-- ī�� �� -->
			    	</div>
			    </div>    
			    <!-- ī�� ����Ʈ �� -->
			  </div>
		</div>
	</section>
	<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js'></script>
	<script src="/Graduation_KMS/js/index.js"></script>  
</body>
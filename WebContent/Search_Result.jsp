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
	<style>
		.container .board {
		    background-image: url(/Graduation_KMS/Schedule/background.jpg);
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
	<section class="container" style=��clear:both;��>
	  <div class="board">
		  <div class="content">
			<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
		    <h3>
		    <%
		    if (request.getAttribute("keyword") != null)
		    {%>
		    	<%=request.getAttribute("keyword")%>�� ���� �˻��Ͻ� ����Դϴ�.
		    <%}
		    else
		    {%>
		    	�˻� ����� �����ϴ�!
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
				        			else // �̹��� ��� �������� �⺻ Path
				        			{
				        				path = "https://www.pagoda21.com/images/upload/2014/09/PGS_201409030639229600.jpg";
				        			}
			        			%>
			        			<img src="<%=path%>" class="visible content"><div class="hidden content">
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
			    			<h3>���� ���͵� ����� �����?&nbsp<a href='/Graduation_KMS/op/createStudy'>���鷯 ����!</a></h3>
			    			<br>
			    		<%
			    	}
		    	%>
		    	
		    	</div>
		  </div>
	  
		  <div class="content">
			    <h3 style="margin-top: 8%;">�̷� ���͵�� �����?</h3>
			    
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
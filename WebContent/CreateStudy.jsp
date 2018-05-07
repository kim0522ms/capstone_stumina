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
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
  <style>
  	.col-md-offset-3 {
    margin-left: 0;
	}
	.col-md-6 {
    width: 100%;
	}
	.container {
    width: 100%;
	}
  </style>
</head>
<body>
	<header>
	<jsp:include page="/Search_Bar/SearchBar.jsp" />  
	<h1> </h1>
	</header>
	<section class="container" style=”clear:both;”>
		<div class="board">
		  <div class="content">
			<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
			</div>
		</div>
		<div class="cardwrapper">
			<div class="row" style="margin:0 auto">
			    <div class="col-md-6 col-md-offset-3">
			        <form id="msform">
			            <!-- progressbar -->
			            <ul id="progressbar">
			                <li class="active">스터디 이름 설정</li>
			                <li>카테고리 선택</li>
			                <li>스터디 소개 작성</li>
			            </ul>
			            <!-- fieldsets -->
			            <fieldset>
			                <h2 class="fs-title">스터디 이름을 입력해주세요!</h2>
			                <h3 class="fs-subtitle">최대 50자까지 가능합니다.</h3>
			                <input type="text" name="fname" placeholder="이름 입력"/>
			                <input type="button" name="next" class="next action-button" value="Next"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">스터디의 카테고리를 선택해 주세요!</h2>
			                <h3 class="fs-subtitle">Your presence on the social network</h3>
			                <input type="text" name="twitter" placeholder="Twitter"/>
			                <input type="text" name="facebook" placeholder="Facebook"/>
			                <input type="text" name="gplus" placeholder="Google Plus"/>
			                <input type="button" name="previous" class="previous action-button-previous" value="Previous"/>
			                <input type="button" name="next" class="next action-button" value="Next"/>
			            </fieldset>
			            <fieldset>
			                <h2 class="fs-title">Create your account</h2>
			                <h3 class="fs-subtitle">Fill in your credentials</h3>
			                <input type="text" name="email" placeholder="Email"/>
			                <input type="password" name="pass" placeholder="Password"/>
			                <input type="password" name="cpass" placeholder="Confirm Password"/>
			                <input type="button" name="previous" class="previous action-button-previous" value="Previous"/>
			                <input type="submit" name="submit" class="submit action-button" value="Submit"/>
			            </fieldset>
			        </form>
			        <!-- link to designify.me code snippets -->
			        <div class="dme_link">
			            <p><a href="http://designify.me/code-snippets-js/" target="_blank">More Code Snippets</a></p>
			        </div>
			        <!-- /.link to designify.me code snippets -->
			    </div>
			</div>
		</div>
	</section>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>

  

    <script  src="js/step.js"></script>
</body>
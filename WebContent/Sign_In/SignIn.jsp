<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>로그인 페이지</title>
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  
  <link rel="stylesheet" href="/Graduation_KMS/Sign_In/css/style_signin.css">

  
</head>

<body>
  <section class="container">
		    <article class="half">
			        <h1>로그인</h1>
			        <div class="tabs">
				            <span class="tab signin active"><a href="#signin">Sign in</a></span>
			        </div>
			        <div class="content">
				            <div class="signin-cont cont">
					                <form action="/Graduation_KMS/op/signIn" method="post">
										<%
											System.out.println("[SignIn.jsp] requestURL : " + request.getParameter("requestURL"));
										%>
										<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL") %>" />
					                    <input type="text" name="id" id="loginid" class="inpt" required="required" placeholder="Your ID">
					                    <label for="text">Login ID</label>
					                    <input type="password" name="passwd" id="loginpw" class="inpt" required="required" placeholder="Your Password">
               						    <label for="password">Your password</label>
					                    <input type="checkbox" id="remember" class="checkbox" checked>
					                    <label for="remember">ID/PW 기억하기</label>
					                    <div class="submit-wrap">
						                        <input type="button" value="회원가입" onclick="window.location.href='/Sogong/SignUp.jsp'" class="submit">
						                        <br>
						                        <input type="submit" value="로그인" class="submit">
						                        <a href="#" class="more">비밀번호를 잊어버리셨습니까?</a>
					                    </div>
        					        </form>
    				        </div>
    				        <div class="signup-cont cont">
                <form action="#" method="post" enctype="multipart/form-data">
						                    <input type="name" name="name" id="name" class="inpt" required="required" placeholder="Your name">
						                    <label for="name">Your name</label>
                    <input type="email" name="email" id="email" class="inpt" required="required" placeholder="Your email">
						                    <label for="email">Your email</label>
						                    <input type="password" name="password" id="password" class="inpt" required="required" placeholder="Your password">
                						    <label for="password">Your password</label>
						                    <div class="submit-wrap">
							                        <input type="submit" value="Sign up" class="submit">
							                        <a href="#" class="more">Terms and conditions</a>
						                    </div>
        					        </form>
            </div>
			        </div>
		    </article>
		    <div class="half bg"></div>
	</section>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="/Graduation_KMS/Sign_in/js/index.js"></script>

</body>
</html>

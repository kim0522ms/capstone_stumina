<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Stumina</title>
  <script src="//use.typekit.net/bzf3ugx.js"></script>
  <script>try{Typekit.load();}catch(e){}</script>
  <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Sign_Up/css/style.css">

  
</head>

<body>

  <div class='container'>
	  <header>
	  <br>
	    <h2>Sign up for Free</h2>
	    <p>간단한 내용을 입력하기만 하면 가입이 완료됩니다!</p>
	  </header>
	  <!-- / START Form -->
	  <div class='form'>
	    <form method='post' action='${pageContext.request.contextPath}/op/signUp'>
	      <div class='field'>
	        <label for='username'>이름</label>
	        <input id='username' name='username' type='text' value='' placeholder="ex) 홍길동" required>
	      </div>
	      <div class='field'>
	        <label for='email'>아이디</label>
	        <input id='userid' name='userid' type='email' value='' placeholder="ex) abc@google.com" required>
	      </div>
	      <div class='field'>
	        <label for='password'>패스워드</label>
	        <input id='userpw' name='password' type='password' value='' placeholder="영문자, 숫자를 포함한 8글자 이상" required>
	      </div>
	      <div class='field' id='ruserpw_div'>
	        <label for='rpassword'>패스워드 확인</label>
	        <input id='ruserpw' name='rpassword' type='password' value='' placeholder="동일한 패스워드를 재입력" required onkeyup='checkValue();'>
	      </div>
	      <div class='field' style='padding-top: 13px;'>
	        <label for='username'>성별</label><br>
	        <label><input id='gender' name='usergender' type="radio" value='M' style='width: 50%;' required/>남성</label>
	        <label><input id='gender' name='usergender' type="radio" value='F' style='width: 50%;' required/>여성</label>
	      </div>
	      <div class='field' >
	        <label for='userphone'>연락처</label><br>
	        <input id='phone' name='phone' type='text' value='' placeholder="휴대폰번호 혹은 전화번호를 입력해 주세요" required>
	      </div>
	      <div class='field'>
	        <label for='username'>지역</label><br><br>
	        <select name='area' style='width: 70%; margin-left: 10%;' required>
			    <option value='' selected>지역을 선택해 주세요</option>
			    <option value='100'>서울특별시</option>
			    <option value='101'>부산광역시</option>
			    <option value='102'>경기도</option>
			    <option value='103'>경상남도</option>
			    <option value='104'>경상북도</option>
			    <option value='105'>강원도</option>
			    <option value='106'>대구광역시</option>
			    <option value='107'>전라남도</option>
			    <option value='108'>전라북도</option>
			    <option value='109'>광주광역시</option>
			    <option value='110'>대전광역시</option>
			    <option value='111'>제주특별시</option>
			</select>
	      </div>
	      <div class='field'>
	        <label for='job'>직업</label><br><br>
	        <select name='job' style='width: 70%; margin-left: 10%;' required>
			    <option value='' selected>직업을 선택해 주세요</option>
			    <option value='1'>대학생</option>
			    <option value='2'>고등학생</option>
			    <option value='3'>중학생</option>
			    <option value='4'>초등학생</option>
			    <option value='5'>직장인</option>
			    <option value='6'>기타</option>
			</select>
	      </div>
	      <div class='field' style='width: 100%;'>
	        <label for='username'>소속</label>
	        <input id='belong' name='belong' type='text' value='' placeholder='대학이나 직장명 등을 입력해 주세요.' required>
	      </div>
	      <div class='checkbox'>
	        <input id='checkbox' name='check_TOS' type='checkbox' required>
	        <label for='checkbox'>
	         	가입 시 <a href='#'>이용 약관</a>에 동의하게 됩니다.
	        </label>
	      </div>
	      <button>가입하기</button>
	    </form>
	  </div>
	  <!-- / END Form -->
	  <footer>
	         세상의 모든 스터디 -
	    <a href='https://andytran.me'>Stumina</a>
	  </footer>
	  <div class="half" style='height: 200px; background-image: url(https://cfile9.uf.tistory.com/image/214AB63856E182E62057D6);'></div>
  </div>
  
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

  

    <script  src="${pageContext.request.contextPath}/Sign_Up/js/index.js"></script>
</body>
</html>

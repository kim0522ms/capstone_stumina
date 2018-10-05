<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Responsive Search Bar with Suggestion  - Daily UI - 03</title>
  <meta name="viewport" content="width=device-width">
<link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,900" rel="stylesheet">
      <link rel="stylesheet" href="/Graduation_KMS/Search_Bar/css/style.css">

  
</head>

<body>

  <header>
   <div class="logo">
     <a href="/Graduation_KMS/MainPage.jsp">● Stumina</a>
  </div>
  <nav>
     <div class="search-bar">
       <form class="search" method="do" action="/Graduation_KMS/op/search">
         <input type="search" class="search__input" name="keyword" placeholder="스터디나 세미나를 검색해 보세요!" onload="equalWidth()" required>
         <button class="search__btn">Search</button>
         <i class="ion-ios-search search__icon"></i>
       </form>
       <div class="suggestion">
         <div class="suggestion__content">
           <div class="suggestion__content-left-side">
            <h5>Popular Categories</h5>
            <ul>
              <li>Marketing Material</li>
              <li>Just Sold</li>
              <li>Small Business Card</li>
              <li>Mailing</li>
              <li>Just List</li>
            <ul>  
           </div>
           <div class="suggestion__content-right-side">
            <h5>Popular Keywords</h5>
            <ul>
              <li>Business Card</li>
              <li>Brochures</li>
              <li>Just List Just Sold</li>
              <li>Flyers</li>
            </ul>   
           </div>
         </div>  
       </div>  
    </div>  
  </nav>
  <%
  if (session.getAttribute("user_idx") == null) 
  {
  %>
  	 <div class="signin">
     <a href="/Graduation_KMS/Sign_In/SignIn.jsp">로그인</a>
     </div>
     <div class="signup">
     <a href="#">회원가입</a>
  	 </div>
  <%
  }
  else
  {
  %>
  	 <div class="logininfo">
     <a href="#"><%=session.getAttribute("user_name")%>님 반갑습니다!</a>
     </div>
     <div class="signout">
     <a href="/Graduation_KMS/op/signOut">로그아웃</a>
  	 </div>
  <%
  }
  %>
  
  <!-- <div class="menu">
    <i class="ion-drag menu__icon"></i>
  </div>   -->
</header>
    
<div class="message" style="display: none">
  <p>Search with suggestion is supported for devices with width larger than 600px. You can click on the search icon to show the search bar.</p>
</div>
  
  

    <!-- <script  src="/Graduation_KMS/Search_Bar/js/index.js"></script> -->




</body>

</html>
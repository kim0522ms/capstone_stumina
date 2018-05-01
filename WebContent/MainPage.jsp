<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
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
<section class="main-slider">
  <div class="item image">
    <span class="loading">Loading...</span>
    <figure>
      <div class="slide-image slide-media" style="background-image:url('https://8percent.github.io/images/tsd-2-1.jpeg');">
        <img data-lazy="https://8percent.github.io/images/tsd-2-1.jpeg" class="image-entity" />
      </div>
      <figcaption class="caption">�Ŷ���б�<br><br>iOS ���� ���͵� ����</figcaption>
    </figure>
  </div>
  </div>
  <div class="item image">
    <figure>
      <div class="slide-image slide-media" style="background-image:url('http://yeonjae.or.kr/files/attach/images/1623/055/002/f_4d241b2a6c77a.jpg');">
        <img data-lazy="http://yeonjae.or.kr/files/attach/images/1623/055/002/f_4d241b2a6c77a.jpg" class="image-entity" />
      </div>
      <figcaption class="caption">�ΰ���б�<br><br>���� ���͵�</figcaption>
    </figure>
  </div>
  
<!--   
  <div class="item vimeo" data-video-start="4">
    <iframe class="embed-player slide-media" src="https://player.vimeo.com/video/217885864?api=1&byline=0&portrait=0&title=0&background=1&mute=1&loop=1&autoplay=0&id=217885864" width="980" height="520" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
    <p class="caption">Vimeo</p>
  </div>
  <div class="item image">
    <figure>
      <div class="slide-image slide-media" style="background-image:url('https://drive.google.com/uc?export=view&id=0B_koKn2rKOkLNXBIcEdOUFVIWmM');">
        <img data-lazy="https://drive.google.com/uc?export=view&id=0B_koKn2rKOkLNXBIcEdOUFVIWmM" class="image-entity" />
      </div>
      <figcaption class="caption">Static Image</figcaption>
    </figure>
  </div>
   -->
  
<!--   <div class="item youtube">
    <iframe class="embed-player slide-media" width="980" height="520" src="https://www.youtube.com/embed/tdwbYGe8pv8?enablejsapi=1&controls=0&fs=0&iv_load_policy=3&rel=0&showinfo=0&loop=1&playlist=tdwbYGe8pv8&start=102" frameborder="0" allowfullscreen></iframe> 
    <p class="caption">YouTube</p>
  </div>
  <div class="item image">
    <figure>
      <div class="slide-image slide-media" style="background-image:url('https://drive.google.com/uc?export=view&id=0B_koKn2rKOkLSlBkWDBsWXJNazQ');">
        <img data-lazy="https://drive.google.com/uc?export=view&id=0B_koKn2rKOkLSlBkWDBsWXJNazQ" class="image-entity" />
      </div>
      <figcaption class="caption">Static Image</figcaption>
    </figure>
  </div>
  <div class="item video">
    <video class="slide-video slide-media" loop muted preload="metadata" poster="https://drive.google.com/uc?export=view&id=0B_koKn2rKOkLSXZCakVGZWhOV00">
      <source src="https://player.vimeo.com/external/138504815.sd.mp4?s=8a71ff38f08ec81efe50d35915afd426765a7526&profile_id=112" type="video/mp4" />
    </video>
    <p class="caption">HTML 5 Video</p>
  </div> -->
</section>
<section class="container">
  <div class="content">
    <h2>���� �α��ִ� ���͵� Ȯ���غ�����!</h2>
    
    
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
</section>
  <script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js'></script>
<script  src="/Graduation_KMS/js/index.js"></script>  

</body>
</html>


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
	
		if (request.getAttribute("threadInfos") != null)
		{
			threadInfos = (ArrayList<ScheduleBoardInfo>)request.getAttribute("threadInfos");
		}
	%>
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header>
	<div class="container-fluid mainContainer">
	    <div class="header">
	        <ol class="breadcrumb">
	            <li><a href="#" style="color: #575965;" >Programmiersprachen</a></li>
	            <li><a href="#" style="color: #575965;" >PHP</a></li>
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
		                <a class="ma-button quote-id" data-quote-id="text-1" data-name="Max Lorem"><i class="fa fa-quote-left" aria-hidden="true"></i> Zitieren</a>
		                <a class="ma-button"><i class="fa fa-thumbs-up" aria-hidden="true"></i> Danke</a>
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
	    <div class="editor-wrapper">
	        <div class="editor" id="editor">
	            <div id="buttons" class="hidden-buttons">
	                <a id="normal" data-toggle="tooltip" data-placement="top" title="Normal"><i class="fa fa-font" aria-hidden="true"></i></a>
	                <a id="header" data-toggle="tooltip" data-placement="top" title="Überschrift"><i class="fa fa-header" aria-hidden="true"></i></a>
	                <a id="bold" data-toggle="tooltip" data-placement="top" title="Fett"><i class="fa fa-bold" aria-hidden="true"></i></a>
	                <a id="italic" data-toggle="tooltip" data-placement="top" title="Kursiv"><i class="fa fa-italic" aria-hidden="true"></i></a>
	                <a id="underline" data-toggle="tooltip" data-placement="top" title="Unterschrift"><i class="fa fa-underline" aria-hidden="true"></i></a>
	                <a id="strikethrough" data-toggle="tooltip" data-placement="top" title="Durchgestrichen"><i class="fa fa-strikethrough" aria-hidden="true"></i></a>
	                <a id="link" data-toggle="tooltip" data-placement="top" title="Link"><i class="fa fa-chain" aria-hidden="true"></i></a>
	                <a id="quote" data-toggle="tooltip" data-placement="top" title="Zitat"><i class="fa fa-quote-right" aria-hidden="true"></i></a>
	                <a id="picture" data-toggle="tooltip" data-placement="top" title="Bild einfügen"><i class="fa fa-picture-o" aria-hidden="true"></i></a>
	                <a id="align-left" data-toggle="tooltip" data-placement="top" title="Linksbündig"><i class="fa fa-align-left" aria-hidden="true"></i></a>
	                <a id="align-center" data-toggle="tooltip" data-placement="top" title="Zentrieren"><i class="fa fa-align-center" aria-hidden="true"></i></a>
	                <a id="align-right" data-toggle="tooltip" data-placement="top" title="Rechtsbündig"><i class="fa fa-align-right" aria-hidden="true"></i></a>
	                <a id="align-justify" data-toggle="tooltip" data-placement="top" title="Justify"><i class="fa fa-align-justify" aria-hidden="true"></i></a>
	                <a id="question" data-toggle="tooltip" data-placement="top" title="Frage"><i class="fa fa-question" aria-hidden="true"></i></a>
	                <a id="more" data-toggle="tooltip" data-placement="top" title="Mehr"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
	            </div>
	            <form>
	                <textarea id="textarea" placeholder="내용을 입력해주세요..." class="textarea-border"></textarea>
	                <div id="keyboard" class="keyboard hidden-keyboard">
	                    <div id="key-message">
	                        <a id="open-keyboard"><i id="key-icon" class="fa fa-keyboard-o fa-2x" aria-hidden="true"></i></a>
	                    </div>
	                    <div id="keys" class="hidden-keys">
	                        <div class="keyboard-container">
	                            <ul>
	                                <li class="key-button" id="btn-1">^</li>
	                                <li class="key-button" id="btn-2">1</li>
	                                <li class="key-button" id="btn-3">2</li>
	                                <li class="key-button" id="btn-4">3</li>
	                                <li class="key-button" id="btn-5">4</li>
	                                <li class="key-button" id="btn-6">5</li>
	                                <li class="key-button" id="btn-7">6</li>
	                                <li class="key-button" id="btn-8">7</li>
	                                <li class="key-button" id="btn-9">8</li>
	                                <li class="key-button" id="btn-10">9</li>
	                                <li class="key-button" id="btn-11">0</li>
	                                <li class="key-button" id="btn-12">ß</li>
	                                <li class="key-button" id="btn-13">#</li>
	                                <li id="btn-14" class="delete last-item key-button">delete</li>
	                                <li id="btn-15" class="tab key-button">tab</li>
	                                <li class="key-button" id="btn-16">q</li>
	                                <li class="key-button" id="btn-17">w</li>
	                                <li class="key-button" id="btn-18">e</li>
	                                <li class="key-button" id="btn-19">r</li>
	                                <li class="key-button" id="btn-20">t</li>
	                                <li class="key-button" id="btn-21">z</li>
	                                <li class="key-button" id="btn-22">u</li>
	                                <li class="key-button" id="btn-23">i</li>
	                                <li class="key-button" id="btn-24">o</li>
	                                <li class="key-button" id="btn-25">p</li>
	                                <li class="key-button" id="btn-26">ü</li>
	                                <li class="key-button" id="btn-27">*</li>
	                                <li id="btn-28" class="last-item key-button">|</li>
	                                <li id="btn-29" class="caps key-button">caps lock</li>
	                                <li class="key-button" id="btn-30">a</li>
	                                <li class="key-button" id="btn-31">s</li>
	                                <li class="key-button" id="btn-32">d</li>
	                                <li class="key-button" id="btn-33">f</li>
	                                <li class="key-button" id="btn-34">g</li>
	                                <li class="key-button" id="btn-35">h</li>
	                                <li class="key-button" id="btn-36">j</li>
	                                <li class="key-button" id="btn-37">k</li>
	                                <li class="key-button" id="btn-38">l</li>
	                                <li class="key-button" id="btn-39">ö</li>
	                                <li class="key-button" id="btn-40">ä</li>
	                                <li id="btn-41" class="enter last-item key-button">enter</li>
	                                <li id="btn-42" class="shift key-button">shift</li>
	                                <li class="key-button" id="btn-43"><</li>
	                                <li class="key-button" id="btn-44">y</li>
	                                <li class="key-button" id="btn-45">x</li>
	                                <li class="key-button" id="btn-46">c</li>
	                                <li class="key-button" id="btn-47">v</li>
	                                <li class="key-button" id="btn-48">b</li>
	                                <li class="key-button" id="btn-49">n</li>
	                                <li class="key-button" id="btn-50">m</li>
	                                <li class="key-button" id="btn-51">,</li>
	                                <li class="key-button" id="btn-52">.</li>
	                                <li class="key-button" id="btn-53">-</li>
	                                <li id="btn-54" class="shift last-item key-button">shift</li>
	                                <li class="key-button" id="btn-55">fn</li>
	                                <li class="key-button" id="btn-56">ctrl</li>
	                                <li class="key-button" id="btn-57">alt</li>
	                                <li id="btn-58" class="space key-button">space</li>
	                                <li class="key-button" id="btn-59">alt gr</li>
	                                <li id="btn-60" class="last-item key-button">ctlr</li>
	                            </ul>
	                        </div>
	                    </div>
	                </div>
	                <!--
	                <div class="text-center">
	                    <button type="button" class="ma-button">Vorschau</button>
	                    <button type="submit" class="ma-button">Abschicken</button>
	                </div>
	                -->
	            </form>
	        </div>
	    </div>
	
	
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

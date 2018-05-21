<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.example.study.model.StudyInfo"%>
<%@page import="com.example.study.model.ScheduleInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Stumina</title>
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css'>
    <link rel="stylesheet" href="/Graduation_KMS/Schedule/css/style.css">
    <style>
    	h6 {
		    margin: 0;
		    padding: 0;
		    border: 0;
		    font-size: 100%;
		    font: inherit;
		    vertical-align: baseline;
		}
    </style>
    <%
	String rsch_no = "";
	%>
</head>

<body>
<div>
	<jsp:include page="/Sidebar/SideBar_Profile.jsp" />
	<header>
		<jsp:include page="/Search_Bar/SearchBar.jsp" />
	</header>  
  <script>
 <!--  // fill the month table with column headings
function day_title(day_name) {
    document.write("<div class='c-cal__col'>" + day_name + "</div>");
  }
  // fills the month table with numbers
function fill_table(month, month_length, indexMonth) {
    day = 1;
    // begin the new month table
    document.write("<div class='c-main c-main-" + indexMonth + "'>");
    //document.write("<b>"+month+" "+year+"</b>")

    // column headings
    document.write("<div class='c-cal__row'>");
    day_title("Sun");
    day_title("Mon");
    day_title("Tue");
    day_title("Wed");
    day_title("Thu");
    day_title("Fri");
    day_title("Sat");
    document.write("</div>");

    // pad cells before first day of month
    document.write("<div class='c-cal__row'>");
    for (var i = 1; i < start_day; i++) {
      if (start_day > 7) {
      } else {
        document.write("<div class='c-cal__cel'></div>");
      }
    }

    // fill the first week of days
    for (var i = start_day; i < 8; i++) {
      document.write(
        "<div data-day='2018-" +
          indexMonth +
          "-0" +
          day +
          "'class='c-cal__cel'><p>" +
          day +
          "</p></div>"
      );
      day++;
    }
    document.write("</div>");

    // fill the remaining weeks
    while (day <= month_length) {
      document.write("<div class='c-cal__row'>");
      for (var i = 1; i <= 7 && day <= month_length; i++) {
        if (day >= 1 && day <= 9) {
          document.write(
            "<div data-day='2018-" +
              indexMonth +
              "-0" +
              day +
              "'class='c-cal__cel'><p>" +
              day +
              "</p></div>"
          );
          day++;
        } else {
          document.write(
            "<div data-day='2018-" +
              indexMonth +
              "-" +
              day +
              "' class='c-cal__cel'><p>" +
              day +
              "</p></div>"
          );
          day++;
        }
      }
      document.write("</div>");
      // the first day of the next month
      start_day = i;
    }
    document.write("</div>");
  }
</script>
	<div class="wrapper">
		<input type="hidden" id="selectedDate" value="" />
		<%
			StudyInfo studyInfo = null;
			if (request.getAttribute("studyInfo") != null)
			{
				studyInfo = (StudyInfo)request.getAttribute("studyInfo");
			}
			
			ArrayList<ScheduleInfo> schedules = null;
			if (request.getAttribute("scheduleInfo") != null)
			{
				schedules = (ArrayList<ScheduleInfo>)request.getAttribute("scheduleInfo");
			}
		%>
		<h1>[${studyInfo.std_name}]의 스케줄을 확인해 볼까요?</h1>
	<headers>
	  <div class="wrapper">
	    <div class="c-monthyear">
	    <div class="c-month">
	        <span id="prev" class="prev fa fa-angle-left" aria-hidden="true" onclick="prev();"></span>
	        <div id="c-paginator">
	          <span class="c-paginator__month">JANUARY</span>
	          <span class="c-paginator__month">FEBRUARY</span>
	          <span class="c-paginator__month">MARCH</span>
	          <span class="c-paginator__month">APRIL</span>
	          <span class="c-paginator__month">MAY</span>
	          <span class="c-paginator__month">JUNE</span>
	          <span class="c-paginator__month">JULY</span>
	          <span class="c-paginator__month">AUGUST</span>
	          <span class="c-paginator__month">SEPTEMBER</span>
	          <span class="c-paginator__month">OCTOBER</span>
	          <span class="c-paginator__month">NOVEMBER</span>
	          <span class="c-paginator__month">DECEMBER</span>
	        </div>
	        <span id="next" class="next fa fa-angle-right" aria-hidden="true" onclick="next();")></span>
	      </div>
	      <span class="c-paginator__year">2018</span>
	    </div>
	    <!-- <div class="c-sort">
	      <a class="o-btn c-today__btn" href="javascript:;">TODAY</a>
	    </div> -->
	  </div>
	</headers>
	  <div class="c-calendar">
	    <div class="c-calendar__style c-aside">
	      <!-- <a class="c-add o-btn js-event__add" href="javascript:;">일정 추가하기 <span class="fa fa-plus"></span></a> -->
	      
	      <!-- 일정 추가하기 버튼 -->
	      <a class="c-add o-btn js-event__add" href="/Graduation_KMS/op/createSchedule?std_no=<%=request.getAttribute("std_no").toString()%>">일정 추가하기 <span class="fa fa-plus"></span></a>
	      <br>
	      <br>
	      
	      <div class="c-aside__day">
	        <span class="c-aside__num"></span> <span class="c-aside__month"></span>
	      </div>
	      <div class="c-aside__eventList">
	      </div>
	    </div>
	    <div class="c-cal__container c-calendar__style">
	      <script>
	      
	      // CAHNGE the below variable to the CURRENT YEAR
	      year = 2018;
	
	      // first day of the week of the new year
	      today = new Date("January 1, " + year);
	      start_day = today.getDay() + 1;
	      fill_table("January", 31, "01");
	      fill_table("February", 28, "02");
	      fill_table("March", 31, "03");
	      fill_table("April", 30, "04");
	      fill_table("May", 31, "05");
	      fill_table("June", 30, "06");
	      fill_table("July", 31, "07");
	      fill_table("August", 31, "08");
	      fill_table("September", 30, "09");
	      fill_table("October", 31, "10");
	      fill_table("November", 30, "11");
	      fill_table("December", 31, "12");
	      </script>
	    </div>
	  </div>
	</div>
  	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.cycle2/2.1.6/jquery.cycle2.core.min.js'></script>
	<script src="/Graduation_KMS/Schedule/js/index.js"></script>
	<script>
	<%
	SimpleDateFormat oldFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
	SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	if (schedules != null)
	{
		for (ScheduleInfo schedule : schedules)
		{
			String dateString = schedule.getSchedule_date();
			out.println("defaultEvents('"+ newFormat.format(oldFormat.parse(schedule.getSchedule_date())) +"','"+schedule.getCheckin()+"시 ~ " + schedule.getCheckout()+ "시<br><br> • 제목<br>', '"+schedule.getSchedule_name()+"<br><br> • 장소<br>" + schedule.getStudyroom_name() + " " +schedule.getRoom_name() + "<br><br> • 상세 주소<br>" + schedule.getStudyroom_location() + "<br><br> • 대여 비용<br>" + schedule.getRoom_pay() + "원<br><br> • 공지사항<br>" + schedule.getSchedule_comment() + "<br><br><br><br><a href=\"/Graduation_KMS/op/viewThread?rsch_idx=" + schedule.getSchedule_idx() +"\" style=\"font-size: 1.6rem;\">스터디 게시판 확인</a>  " +"')");          
		}
	}
	%>
	</script>
</div>
</body>

</html>

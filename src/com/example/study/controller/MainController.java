package com.example.study.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.study.model.CategoryInfo;
import com.example.study.model.DetailInfo;
import com.example.study.model.ScheduleInfo;
import com.example.study.model.StudyInfo;
import com.example.study.model.UserInfo;

/**
 * Servlet implementation class MainController
 */
//@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subPath = request.getPathInfo();
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		System.out.println(subPath);
		if (subPath.equals("/signOut"))
		{
			if (session.getAttribute("userInfo") != null)
			{
				session.setAttribute("user_name", null);
				session.setAttribute("user_idx", null);
				session.setAttribute("userInfo", null);
			}
			
			viewName = "/MainPage.jsp";
		}
		else if (subPath.equals("/myStudies"))
		{
			if (session.getAttribute("userInfo") != null)
			{
				UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
				
				String user_idx = userInfo.getUser_idx();
				System.out.println(user_idx);
				
				
				StudyDBDAO db = new StudyDBDAO();
				
				ArrayList<StudyInfo> studyInfos = db.getMyStudies(user_idx);
				
				if (studyInfos != null)
				{
					request.setAttribute("studyInfos", studyInfos);
					viewName = "/ScheduleCard/Card.jsp";
				}
				else
				{
					viewName = "Error.jsp?value=NoJoinedStudy";
				}
			}
			else
			{
				viewName = "/Sign_In/SignIn.jsp";
			}
			
		}
		
		else if (subPath.equals("/search"))
		{
			String keyword = request.getParameter("keyword");
			System.out.println("Search Keyword : " + keyword);
			
			StudyDBDAO db = new StudyDBDAO();
			ArrayList<StudyInfo> studyInfos = null;
			studyInfos = db.searchStudy(keyword);
			
			if (studyInfos != null)
			{
				request.setAttribute("studyInfos", studyInfos);
				
			}
			request.setAttribute("keyword", keyword);
			
			viewName = "/Search_Result.jsp";
		}
		else if (subPath.equals("/viewstudyinfo"))
		{
			String std_no = request.getParameter("std_no");
			
			StudyDBDAO db = new StudyDBDAO();
			
			StudyInfo studyInfo = db.getStudyInfo(std_no);
			
			if (studyInfo != null)
			{
				request.setAttribute("studyInfo", studyInfo);
				viewName = "/StudyInfo.jsp";
			}
			else
				viewName = "/Error.jsp";
		}
		else if (subPath.equals("/joinStudy"))
		{
			if (session.getAttribute("userInfo") == null)
			{
				viewName = "/Sign_In/SignIn.jsp";
			}
			else
			{
				viewName = "/MainPage.jsp";
			}
		}
		else if (subPath.equals("/getMainList"))
		{
			System.out.println("aaaa");
			StudyDBDAO db = new StudyDBDAO();
			
			ArrayList<StudyInfo> studyInfos = db.mainPage_Study();
			
			response.setContentType("text/plain");
			request.setAttribute("studyInfos", studyInfos);
			//response.getWriter().write(studyInfos.toString());
		}
		else if (subPath.equals("/createStudy"))
		{
			if (session.getAttribute("userInfo") == null)
			{
				viewName = "/Sign_In/SignIn.jsp";
			}
			else
			{
				StudyDBDAO db = new StudyDBDAO();
				
				ArrayList<CategoryInfo> categoryInfos = db.getCategory();
				ArrayList<DetailInfo> detailInfos = db.getDetail();
				
				if (categoryInfos != null && detailInfos != null)
				{
					request.setAttribute("categoryInfos", categoryInfos);
					request.setAttribute("detailInfos", detailInfos);
					viewName = "/CreateStudy.jsp";
				}
				else
				{
					viewName = "/Error.jsp/value=NoCategoryInfo";
				}
			}
		}
		else if (subPath.equals("/ScheduleCalender"))
		{
			String std_no = request.getParameter("std_no");
			
			StudyDBDAO db = new StudyDBDAO();
			
			// 스터디 정보 쿼리
			StudyInfo studyInfo = db.getStudyInfo(std_no);
			if (studyInfo != null)
			{
				request.setAttribute("studyInfo", studyInfo);
			}
			else
			{
				//TODO: 스터디 정보가 없을 경우 예외처리 할 것.
			}
			
			// 스케줄 쿼리
			ArrayList<ScheduleInfo> scheduleInfo = db.getSchedules(std_no);
			
			if (scheduleInfo != null)
			{
				request.setAttribute("scheduleInfo", scheduleInfo);
			}
			viewName ="/Schedule/ViewCalander.jsp";
		}
	
		// 媛� Path 湲곕뒫�쓣 �떎�뻾�븳 �썑 �쉷�뱷�븳 viewName 二쇱냼濡� Forwarding
		if(viewName != null) {
			System.out.println("Forward to " + viewName);
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Post Data has been arrive");
		String subPath = request.getPathInfo();
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (subPath.equals("/signIn"))
		{
			StudyDBDAO db = new StudyDBDAO();
			
			String id = null;
			String passwd = null;
			UserInfo userInfo = null;
			
			try
			{
				id = request.getParameter("id");
				passwd = request.getParameter("passwd");
			}catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			
			if (id != null && passwd != null)
			{
				userInfo = db.signin(id, passwd);
			}
			
			if (userInfo != null)
			{
				session.setAttribute("user_name", userInfo.getUser_name());
				session.setAttribute("user_idx", userInfo.getUser_idx());
				session.setAttribute("userInfo", userInfo);
				viewName = "/MainPage.jsp";
			}
			else
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.println("alert('�븘�씠�뵒 �삉�뒗 鍮꾨�踰덊샇媛� �옒紐삳릺�뿀�뒿�땲�떎!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		else if (subPath.equals("/myStudies"))
		{
			this.doGet(request, response);
		}
		
		else if (subPath.equals("/registStudy"))
		{
			StudyDBDAO db = new StudyDBDAO();
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.KOREA );
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			
			// 문자 형식으로 날아온 소분류 값을 idx형태로 변경해줌
			String detail_idx = db.getDetail_IdxByName(request.getParameter("detail_idx"));
			if (detail_idx == null)
			{
				viewName = "/Error.jsp?Value=DetailNotFound";
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request,response);
				return;
			}
			
			/*
			String area_idx = db.getArea_IDXbyName(request.getParameter("area_name"));
			if (area_idx == null)
			{
				viewName = "/Error.jsp?Value=AreaNotFound";
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request,response);
				return;
			}
			*/
			
			StudyInfo studyInfo = new StudyInfo();
			studyInfo.setStd_no(mTime);
			studyInfo.setDetail_idx(detail_idx);
			studyInfo.setStd_name(request.getParameter("std_name"));
			studyInfo.setStd_contents(request.getParameter("std_contents"));
			studyInfo.setStd_leader(session.getAttribute("user_idx").toString());
			studyInfo.setStd_location("101");
			studyInfo.setStd_maxattcnt(Integer.parseInt(request.getParameter("std_maxattcount")));
			studyInfo.setStd_endflag(0);
			studyInfo.setStd_teacher(null);
			studyInfo.setStd_startDate(request.getParameter("std_startdate"));
			studyInfo.setStd_endDate(request.getParameter("std_enddate"));
			studyInfo.setStd_maxMemberCount(Integer.parseInt(request.getParameter("std_maxmember")));
			studyInfo.setStd_theme(request.getParameter("std_theme"));
			
			System.out.println("Recived Parameters");
			System.out.println("[STD_NO : " + studyInfo.getStd_no() + "]");
			System.out.println("[DETAIL_IDX : " + studyInfo.getDetail_idx() + "]");
			System.out.println("[STD_NAME : " + studyInfo.getStd_name() + "]");
			System.out.println("[STD_CONTENTS : " + studyInfo.getStd_contents() + "]");
			System.out.println("[STD_LEADER : " + studyInfo.getStd_leader() + "]");
			System.out.println("[STD_LOCATION : " + studyInfo.getStd_location() + "]");
			System.out.println("[STD_MAXATTCNT : " + studyInfo.getStd_maxattcnt() + "]");
			System.out.println("[STD_ENDFLAG : " + studyInfo.getStd_endflag() + "]");
			System.out.println("[STD_TEACHER : " + studyInfo.getStd_teacher() + "]");
			System.out.println("[STD_STARTDATE : " + studyInfo.getStd_startDate() + "]");
			System.out.println("[STD_ENDDATE : " + studyInfo.getStd_endDate() + "]");
			System.out.println("[STD_MAXMEMBER : " + studyInfo.getStd_maxMemberCount() + "]");
			System.out.println("[STD_THEME : " + studyInfo.getStd_theme() + "]");
			
			boolean result = db.registerStudy(studyInfo);
			
			if (result)
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('정상적으로 등록되었습니다!');");
				out.println("</script>");

				session.setAttribute("studyInfo", studyInfo);
				viewName = "/op/myStudies";
			}
			else
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록되지 않았습니다!');");
				out.println("</script>");
				viewName = "/Error.jsp?value=RegisterStudyFail";
			}
		}
		else if (subPath.equals("/myStudies"))
		{
			this.doGet(request, response);
		}
				
		// 媛� Path 湲곕뒫�쓣 �떎�뻾�븳 �썑 �쉷�뱷�븳 viewName 二쇱냼濡� Forwarding
		if(viewName != null) {
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

}

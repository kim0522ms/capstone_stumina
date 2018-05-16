package com.example.study.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.study.model.AreaInfo;
import com.example.study.model.CategoryInfo;
import com.example.study.model.DetailInfo;
import com.example.study.model.JoinRequestInfo;
import com.example.study.model.RoomsInfo;
import com.example.study.model.ScheduleBoardInfo;
import com.example.study.model.ScheduleInfo;
import com.example.study.model.StudyInfo;
import com.example.study.model.StudyRoomInfo;
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
		
		System.out.println("[MainController.java] Get Data has been arrive");
		System.out.println("[MainController.java] Arrived SubPath : " + subPath);
		System.out.println();
		
		
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		System.out.println(subPath);
		if (subPath.equals("/signOut"))
		{
			session.setAttribute("user_name", null);
			session.setAttribute("user_idx", null);
			session.setAttribute("userInfo", null);
			
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
					System.out.println("[/myStudies] Error : user_idx(" +user_idx+ ") has not join in any study !!");
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script>");
					out.println("alert('아직 가입한 스터디가 없습니다!');");
					out.println("history.back();");
					out.println("</script>");
				}
			}
			else
			{
				viewName = "/Sign_In/SignIn.jsp?requestURL=" + request.getRequestURI();
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
			
			String img_path = db.getStudyImage(std_no);
			
			if (img_path != null)
			{
				request.setAttribute("img_path", img_path);
			}
			
			if (studyInfo != null)
			{
				request.setAttribute("studyInfo", studyInfo);
				viewName = "/StudyInfo.jsp";
			}
			else
				viewName = "/Error.jsp";
		}
		
		// 스터디 소속 멤버 확인
		else if (subPath.equals("/viewStudyMember"))
		{
			String std_no = request.getParameter("std_no");
			System.out.println("[/viewStudyMember] Parameter std_no : " + std_no);
			System.out.println();
			
			if (std_no != null)
			{
				StudyDBDAO db = new StudyDBDAO();
				
				ArrayList<UserInfo> userInfos = db.getStudyUserInfo(std_no);
				
				// 스터디에 가입된 회원 정보 모두 쿼리해서 request에 저장
				if (userInfos != null)
				{
					request.setAttribute("userInfos", userInfos);
					System.out.println("[/viewStudyMember] Study Member Info added successfully! ");
					System.out.println("[/viewStudyMember] Study Member Count : " + userInfos.size());
					System.out.println();
				}
				else
				{
					System.out.println("[/viewStudyMember] Failed to add Study Member Info... ");
					System.out.println();
					// TODO: 스터디에 가입된 사람이 아예 없을수는 없으므로 에러 처리 해야함. 가입요청은 없을수도 있음
				}
				
				// 스터디에 가입신청한 회원 정보를 모두 쿼리해서 request에 저장
				ArrayList<JoinRequestInfo> requestInfos = db.getJoinRequests(std_no);
				if (requestInfos != null)
				{
					request.setAttribute("requestInfos", requestInfos);
					System.out.println("[/viewStudyMember] Join Request Member Info added successfully! ");
					System.out.println("[/viewStudyMember] Join Request Member Count : " + requestInfos.size());
					System.out.println();
				}
				else
				{
					System.out.println("[/viewStudyMember] Failed to add Join Request Member Info... ");
					System.out.println();
				}
				
				String std_leader = db.getStudyLeader(std_no);
				if (std_leader != null)
				{
					request.setAttribute("std_leader", std_leader);
					System.out.println("[/viewStudyMember] STD_LEADER Info added successfully! ");
					System.out.println();
				}
				else
				{
					System.out.println("[/viewStudyMember] Failed to add STD_LEADER Info... ");
					System.out.println();
				}
				
				request.setAttribute("std_no", std_no);
				
				viewName = "/Member/ViewStudyMembers.jsp";
				
			}
			else
			{
				System.out.println("[/viewStudyMember] Error : Parameter std_no is null !!");
				System.out.println();
			}
		}
		
		// TODO : 스터디 가입하기 완성할것
		else if (subPath.equals("/joinStudy"))
		{
			if (session.getAttribute("userInfo") == null)
			{
				viewName = "/Sign_In/SignIn.jsp";
			}
			else
			{
				String user_idx = session.getAttribute("user_idx").toString();
				String std_no = request.getParameter("std_no");
				
				System.out.println("[/joinStudy] user_idx : " + user_idx);
				System.out.println("[/joinStudy] std_no : " + std_no);
				
				StudyDBDAO db = new StudyDBDAO();
				
				boolean isJoined = db.checkJoined(std_no, user_idx);
				
				if (isJoined == true)
				{
					System.out.println("[/joinStudy] user_idx : " + user_idx + "has already join in std_no : " + std_no + " !!");
					
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script>");
					out.println("alert('이미 이 스터디에 가입되어 있습니다!');");
					out.println("history.back();");
					out.println("</script>");
				}
				else
				{
					boolean isRequested = db.requestJoin(std_no, user_idx);
					
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					
					if (isRequested = true)
					{
						out.print("<script>");
						out.println("alert('스터디에 가입신청을 했습니다! 스터디 리더가 확인 후 승인한 뒤부터 스터디에 참여할 수 있습니다.');");
						out.println("history.back();");
						out.println("</script>");
					}
					else
					{
						out.print("<script>");
						out.println("alert('가입신청에 실패했습니다... 새로고침 후 다시 시도해 주세요.');");
						out.println("history.back();");
						out.println("</script>");
					}
				}
			}
		}
		
		
		// 메인화면에 표시되는 스터디 목록 추가
		else if (subPath.equals("/getMainList"))
		{
			System.out.println("aaaa");
			StudyDBDAO db = new StudyDBDAO();
			
			ArrayList<StudyInfo> studyInfos = db.mainPage_Study();
			
			response.setContentType("text/plain");
			request.setAttribute("studyInfos", studyInfos);
			//response.getWriter().write(studyInfos.toString());
		}
		
		// 스터디 개설
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
			
			request.setAttribute("std_no", std_no);
			viewName ="/Schedule/ViewCalander.jsp";
		}
		// 해당 스케줄 게시판 내용을 request에 담아 전송
		else if (subPath.equals("/viewThread"))
		{
			String rsch_idx = request.getParameter("rsch_idx");
			System.out.println("[/viewThread] rsch_idx : " + rsch_idx);
			
			if (rsch_idx == null)
			{
				System.out.println("[/viewThread] Error : rsch_idx is null !!");
			}
			else
			{
				StudyDBDAO db = new StudyDBDAO();
				
				ArrayList<ScheduleBoardInfo> threadInfos = db.getThreads(rsch_idx);
				
				// Thread가 있을 경우
				if (threadInfos != null)
				{
					request.setAttribute("threadInfos", threadInfos);
					System.out.println("[/viewThread] threadInfos has been added to request.");
				}
				// Thread가 없을 경우
				else
				{
					System.out.println("[/viewThread] threadInfos is null");
				}
				
				request.setAttribute("rsch_name", db.getScheduleName(rsch_idx)); ;

				viewName = "/ScheduleThread/Thread.jsp";
			}
		}
		
		// 스터디 정보 수정 페이지로 이동
		else if (subPath.equals("/modifyPage"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = session.getAttribute("user_idx").toString();
			
			System.out.println("[/modifyPage] std_no :" + std_no);
			System.out.println("[/modifyPage] user_idx :" + user_idx);
			System.out.println();
			
			StudyDBDAO db = new StudyDBDAO();
			
			if (user_idx.equals(db.getStudyLeader(std_no)))
			{
				System.out.println("[/modifyPage] Edit stdno(" + std_no+ ")'s info");
				System.out.println();
				
				
				ArrayList<CategoryInfo> categoryInfos = db.getCategory();
				ArrayList<DetailInfo> detailInfos = db.getDetail();
				StudyInfo studyInfo = db.getStudyInfo(std_no);
				
				if (categoryInfos != null && detailInfos != null && studyInfo != null)
				{
					request.setAttribute("categoryInfos", categoryInfos);
					request.setAttribute("detailInfos", detailInfos);
					request.setAttribute("studyInfo", studyInfo);
					viewName = "/CreateStudy.jsp";
				}
				else
				{
					viewName = "/Error.jsp/value=NoCategoryInfo";
				}
				
				viewName = "/ModifyStudy.jsp";
			}
			else
			{
				System.out.println("[/editStudyInfo] Error : stdno(" + std_no+ ")'s leader idx is incorrect.");
				System.out.println();
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.println("alert('스터디 정보를 수정할 수 있는 권한이 없습니다!!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		// 스터디에서 탈퇴
		else if (subPath.equals("/QuitStudyMember"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = request.getParameter("user_idx");
			
			System.out.println("[/QuitStudyMember] std_no :" + std_no);
			System.out.println("[/QuitStudyMember] user_idx :" + user_idx);
			System.out.println();
			
			StudyDBDAO db = new StudyDBDAO();
			
			boolean isQuited = db.deleteStudyMember(std_no, user_idx);
			
			if (isQuited)
			{
				System.out.println("[/QuitStudyMember] Success to Quit user_idx(" +user_idx+ ") from std_no(" + std_no + ")!" );
			}
			else
			{
				System.out.println("[/QuitStudyMember] Failed to Quit user_idx(" +user_idx+ ") from std_no(" + std_no + ")..." );
			}
			
			viewName = "/MainPage.jsp";
		}
		
		
		// 스터디에서 유저 추방
		else if (subPath.equals("/banStudyMember"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = request.getParameter("user_idx");
			
			System.out.println("[/banStudyMember] std_no :" + std_no);
			System.out.println("[/banStudyMember] user_idx :" + user_idx);
			System.out.println();
			
			StudyDBDAO db = new StudyDBDAO();
			
			boolean isDeleted = db.deleteStudyMember(std_no, user_idx);
			
			if (isDeleted)
			{
				System.out.println("[/banStudyMember] Success to Ban user_idx(" +user_idx+ ") from std_no(" + std_no + ")!" );
			}
			else
			{
				System.out.println("[/banStudyMember] Failed to Ban user_idx(" +user_idx+ ") from std_no(" + std_no + ")..." );
			}
			
			viewName = "/op/viewStudyMember?std_no=" + std_no;
		}
		
		// 대기중인 회원 가입 승인
		else if (subPath.equals("/acceptMemberJoin"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = request.getParameter("user_idx");
			System.out.println("[/acceptMemberJoin] std_no : " + std_no);
			System.out.println("[/acceptMemberJoin] user_idx : " + user_idx);
			
			if (std_no != null && user_idx != null)
			{
				StudyDBDAO db = new StudyDBDAO();
				
				boolean isInserted = db.acceptMemberJoin(std_no, user_idx);
				
				if (isInserted)
				{
					System.out.println("[/acceptMemberJoin] Success to add user to JOININFO!");
				}
				else
				{
					System.out.println("[/acceptMemberJoin] Failed to add user to JOININFO!");
				}
			}
			else
			{
				System.out.println("[/acceptMemberJoin] Error : Recieved Null Parameter...");
			}
			viewName = "/op/viewStudyMember";
		}
		
		else if (subPath.equals("/rejectMemberJoin"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = request.getParameter("user_idx");
			
			StudyDBDAO db = new StudyDBDAO();
			
			boolean isDeleted = db.rejectMemberJoin(std_no, user_idx);
			
			if (isDeleted == true)
			{
				System.out.println("[/rejectMemberJoin] Success to reject Join Request stdno(" + std_no + ")");
				viewName = "/op/viewStudyMember";
			}
			else
			{
				System.out.println("[/rejectMemberJoin] Failed to reject Join Request stdno(" + std_no + ")");
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.println("alert('요청 거절에 실패했습니다! 새로고침 후 다시 시도해 주세요.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		// 쓰레드 삭제
		else if (subPath.equals("/deleteThread"))
		{
			String scb_idx = request.getParameter("scb_idx");
			String rsch_idx = request.getParameter("rsch_idx");
			System.out.println("[/modifyStudyInfo] scb_idx : " + scb_idx);
			System.out.println("[/modifyStudyInfo] rsch_idx : " + rsch_idx);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (scb_idx != null)
			{
				StudyDBDAO db = new StudyDBDAO();
				
				boolean isDeleted = db.deleteThread(scb_idx);
				
				if (isDeleted)
				{
					out.println("<script>");
					out.println("alert('성공적으로 삭제되었습니다!');");
					out.println("document.location.href='/Graduation_KMS/op/viewThread?rsch_idx="+ rsch_idx +"';");
					out.println("</script>");
				}
				else
				{
					out.println("<script>");
					out.println("alert('쓰레드 삭제에 실패했습니다! 새로고침 후 다시 시도해주세요.');");
					out.println("history.back();");
					out.println("</script>");
				}
			}
			else
			{
				out.println("<script>");
				out.println("alert('존재하지 않는 쓰레드입니다!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}

		// 스케줄 추가를 위해 지역 및 스터디룸 정보 쿼리 후 request에 담아 전송
		else if (subPath.equals("/createSchedule"))
		{
			String std_no = request.getParameter("std_no");
			System.out.println("[/createSchedule] std_no :" + std_no);
			
			StudyDBDAO db = new StudyDBDAO();
			
			// 지역 정보 쿼리
			ArrayList<AreaInfo> areaInfos = db.getAllAreaInfo();
			if (areaInfos != null)
			{
				System.out.println("[/createSchedule] : AreaInfo Added.");
				request.setAttribute("areaInfos", areaInfos);
			}
			else
			{
				System.out.println("[/createSchedule] : Error in AreaInfo Add.");
				viewName = "/Error.jsp?value=NoAreaData";
			}
			
			// 스터디룸 정보 쿼리
			ArrayList<StudyRoomInfo> studyRoomInfos = db.getAllStudyRoomInfo();
			if (studyRoomInfos != null)
			{
				System.out.println("[/createSchedule] : StudyRoomInfo Added.");
				request.setAttribute("studyRoomInfos", studyRoomInfos);
			}
			else
			{
				System.out.println("[/createSchedule] : Error in StudyRoomInfo Add.");
				viewName = "/Error.jsp?value=NoStudyRoomData";
			}
			
			// 방 정보 쿼리
			ArrayList<RoomsInfo> roomInfos = db.getAllRoomsInfo();
			if (roomInfos != null)
			{
				System.out.println("[/createSchedule] : RoomsInfo Added.");
				request.setAttribute("roomInfos", roomInfos);
			}
			else
			{
				System.out.println("[/createSchedule] : Error in RoomsInfo Add.");
				viewName ="/Error.jsp?value=NoRoomInfoData";
			}
			
			request.setAttribute("std_no", std_no);
			
			viewName = "/CreateSchedule.jsp";
		}
	
		// 媛� Path 湲곕뒫�쓣 �떎�뻾�븳 �썑 �쉷�뱷�븳 viewName 二쇱냼濡� Forwarding
		if(viewName != null) {
			System.out.println("[MainController.java.doGet()] Forward to " + viewName);
			System.out.println();
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[MainController.java] Get Data has been arrive");
		System.out.println();
		
		String subPath = request.getPathInfo();
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (subPath.equals("/signIn"))
		{
			System.out.println("[/signIn] Request From : " + request.getParameter("requestURL").replace("/Graduation_KMS", ""));
			
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
				
				if (request.getParameter("requestURL").equals(null))
				{
					System.out.println("[/signIn] Redirect URL : " + request.getParameter("requestURL").replace("/Graduation_KMS", ""));
					viewName = request.getParameter("requestURL").replace("/Graduation_KMS", "");
				}
				else
				{
					System.out.println("[/signIn] Redirect URL : /MainPage.jsp");
					viewName = "/MainPage.jsp";
				}
				
				//viewName = "/MainPage.jsp";
			}
			else
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.println("alert('잘못된 아이디 또는 비밀번호입니다!');");
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
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			
			// 문자 형식으로 날아온 소분류 값을 idx형태로 변경해줌
			// String detail_idx = db.getDetail_IdxByName(request.getParameter("detail_idx"));
			String detail_idx = request.getParameter("detail_idx");
			if (detail_idx == null)
			{
				System.out.println("[/registStudy] Failed to Find 'detail_idx'");
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
			
			System.out.println("[/registStudy] Recived Parameters");
			System.out.println("[/registStudy] STD_NO : " + studyInfo.getStd_no() + "]");
			System.out.println("[/registStudy] DETAIL_IDX : " + studyInfo.getDetail_idx() + "]");
			System.out.println("[/registStudy] STD_NAME : " + studyInfo.getStd_name() + "]");
			System.out.println("[/registStudy] STD_CONTENTS : " + studyInfo.getStd_contents() + "]");
			System.out.println("[/registStudy] STD_LEADER : " + studyInfo.getStd_leader() + "]");
			System.out.println("[/registStudy] STD_LOCATION : " + studyInfo.getStd_location() + "]");
			System.out.println("[/registStudy] STD_MAXATTCNT : " + studyInfo.getStd_maxattcnt() + "]");
			System.out.println("[/registStudy] STD_ENDFLAG : " + studyInfo.getStd_endflag() + "]");
			System.out.println("[/registStudy] STD_TEACHER : " + studyInfo.getStd_teacher() + "]");
			System.out.println("[/registStudy] STD_STARTDATE : " + studyInfo.getStd_startDate() + "]");
			System.out.println("[/registStudy] STD_ENDDATE : " + studyInfo.getStd_endDate() + "]");
			System.out.println("[/registStudy] STD_MAXMEMBER : " + studyInfo.getStd_maxMemberCount() + "]");
			System.out.println("[/registStudy] STD_THEME : " + studyInfo.getStd_theme() + "]");
			
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
		
		// 스케쥴 등록
		else if (subPath.equals("/registSchedule"))
		{
			StudyDBDAO db = new StudyDBDAO();
			
			String rsch_date = request.getParameter("rsch_date");	
			String room_idx = request.getParameter("room_idx");
			String std_no = request.getParameter("std_no");
			String rsch_checkin = request.getParameter("rsch_checkin");
			String rsch_checkout = request.getParameter("rsch_checkout");
			String rsch_name = request.getParameter("rsch_name");
			String rsch_commnet = request.getParameter("rsch_commnet");
			String rsch_pay = request.getParameter("rsch_pay"); 
			
			// rsch_idx_head 값 확인 (나중에 지우자) /////////////////////
			SimpleDateFormat oldDateFormat = new SimpleDateFormat("yy/MM/dd", Locale.KOREA);
			SimpleDateFormat newDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
			
			String rsch_idx_head = "";
			Date oldDate;
			try {
				oldDate = oldDateFormat.parse(rsch_date);
				rsch_idx_head = newDateFormat.format(oldDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			System.out.println("[/registSchedule] rsch_idx_head : " + rsch_idx_head);
			///////////////////////////////
			
			
			System.out.println("[/registSchedule] Recived Parameter");
			System.out.println("[/registSchedule] rsch_date : " + rsch_date);
			System.out.println("[/registSchedule] room_idx : " + room_idx);
			System.out.println("[/registSchedule] std_no : " + std_no);
			System.out.println("[/registSchedule] rsch_checkin : " + rsch_checkin);
			System.out.println("[/registSchedule] rsch_checkout : " + rsch_checkout);
			System.out.println("[/registSchedule] rsch_name : " + rsch_name);
			System.out.println("[/registSchedule] rsch_commnet " + rsch_commnet);
			System.out.println("[/registSchedule] rsch_pay : " + rsch_pay);
			System.out.println();
			
			boolean isInserted = db.registSchedule(rsch_date, room_idx, std_no, rsch_checkin, rsch_checkout, rsch_name, rsch_commnet, rsch_pay);
			
			if (isInserted)
			{
				System.out.println("[/registSchedule] Success to Insert Schedule !! ");
				System.out.println();
				viewName = "/op/ScheduleCalender";
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
				return;
			}
			else
			{
				System.out.println("[/registSchedule] Failed to Insert Schedule !! ");
				System.out.println();
				viewName = "/Error.jsp?value=InsertScheduleFailed";
			}
		}
		else if (subPath.equals("/uploadThread"))
		{
			String user_idx;
			String rsch_idx = request.getParameter("rsch_idx");
			String title = "";
			String content = request.getParameter("content");
			
			if (content == null)
			{
				System.out.println("[/uploadThread] Error : content is null ");		
				System.out.println();
			}
			else if (session.getAttribute("user_idx") == null)
			{
				System.out.println("[/uploadThread] Error : user_idx is null ");
				System.out.println();
			}
			else if (rsch_idx == null)
			{
				System.out.println("[/uploadThread] Error : rsch_idx is null ");
				System.out.println();
			}
			else
			{
				user_idx = session.getAttribute("user_idx").toString();
				
				//////// DB에 넣기 위해 값 처리 /////////
				System.out.println("[/registSchedule] Recieved parameter user_idx : " + user_idx);
				System.out.println("[/registSchedule] Recieved parameter content : " + content);
				System.out.println("[/registSchedule] Recieved parameter content : " + rsch_idx);
				System.out.println();
	
				
				// 개행문자 치환
				content = content.replaceAll("<br>", System.getProperty("line.separator"));
				content = content.replaceAll("\\<p(/?[^\\>]+)\\></p>", System.getProperty("line.separator"));
				content = content.replaceAll("\\<br(/?[^\\>]+)\\>", System.getProperty("line.separator"));
				
				// h 태그 제거(h1, h2...)
				content = content.replaceAll("\\<h(/?[^\\>]+)\\>", "");
				
				// HTML 태그 제거
				content = content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
				System.out.println("[/registSchedule] Replaced parameter content : " + content);
				System.out.println();
				
				if (content.length() > 30)
				{
					title = content.substring(0, 30) + "...";
				}
				else
				{
					title = content;
				}
				
				/*
				String replaced_content = content.replace("<div class=\"ps-scrollbar-x-rail\" style=\"left: 0px; bottom: 3px;\"><div class=\"ps-scrollbar-x\" style=\"left: 0px; width: 0px;\"></div></div><div class=\"ps-scrollbar-y-rail\" style=\"top: 0px; right: 3px;\"><div class=\"ps-scrollbar-y\" style=\"top: 0px; height: 0px;\"></div></div>", "");			
				replaced_content = replaced_content.replace("<p style=\"background-color: rgb(255, 255, 255);\">", "");
				replaced_content = replaced_content.replace("</p>", "<div><br></div>");
				System.out.println("[/registSchedule] Replaced parameter content 1 : " + replaced_content);
				
				replaced_content =replaced_content.replace("</div>", "");
				System.out.println("[/registSchedule] Replaced parameter content 2 : " + replaced_content);
				
				// 제목 처리
				for (String str : replaced_content.split("<div>"))
				{
					if (str.equals("") || str.equals("<br>"))
					{
						continue;
					}
					else
					{
						if (str.length() > 30)
						{
							str = str.substring(0, 30);
							str = str + "...";
						}
						title = str;
						break;
					}
				}
				System.out.println("[/registSchedule] Title : " + title);
				
				replaced_content = replaced_content.replace("<div><br>", System.getProperty("line.separator"));
				replaced_content = replaced_content.replace("<div>", System.getProperty("line.separator"));
				
				System.out.println("[/registSchedule] Content : " + System.getProperty("line.separator") + replaced_content);
				
				////////// 문자열 처리 끝 //////////
				*/
				
				
				
				// DB에 입력
				StudyDBDAO db = new StudyDBDAO();
				
				boolean isInserted = db.uploadThread(rsch_idx, title, content, user_idx);
				
				if (isInserted)
				{
					viewName = "/op/viewThread";
				}
				else
				{
					viewName = "Error.jsp?value=FailedToInsertThread";
				}
			}
		}

		// 스터디 정보 갱신
		else if (subPath.equals("/modifyStudyInfo"))
		{
			StudyDBDAO db = new StudyDBDAO();
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			
			// 문자 형식으로 날아온 소분류 값을 idx형태로 변경해줌
			// String detail_idx = db.getDetail_IdxByName(request.getParameter("detail_idx"));
			String detail_idx = request.getParameter("detail_idx");
			if (detail_idx == null)
			{
				System.out.println("[/modifyStudyInfo] Failed to Find 'detail_idx'");
				viewName = "/Error.jsp?Value=DetailNotFound";
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request,response);
				return;
			}
			
			System.out.println("[/modifyStudyInfo] detail_idx : " + detail_idx);
			
			StudyInfo studyInfo = new StudyInfo();
			studyInfo.setStd_no(request.getParameter("std_no"));
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
			
			System.out.println("[/modifyStudyInfo] Recived Parameters");
			System.out.println("[/modifyStudyInfo] STD_NO : " + studyInfo.getStd_no() + "]");
			System.out.println("[/modifyStudyInfo] DETAIL_IDX : " + studyInfo.getDetail_idx() + "]");
			System.out.println("[/modifyStudyInfo] STD_NAME : " + studyInfo.getStd_name() + "]");
			System.out.println("[/modifyStudyInfo] STD_CONTENTS : " + studyInfo.getStd_contents() + "]");
			System.out.println("[/modifyStudyInfo] STD_LEADER : " + studyInfo.getStd_leader() + "]");
			System.out.println("[/modifyStudyInfo] STD_LOCATION : " + studyInfo.getStd_location() + "]");
			System.out.println("[/modifyStudyInfo] STD_MAXATTCNT : " + studyInfo.getStd_maxattcnt() + "]");
			System.out.println("[/modifyStudyInfo] STD_ENDFLAG : " + studyInfo.getStd_endflag() + "]");
			System.out.println("[/modifyStudyInfo] STD_TEACHER : " + studyInfo.getStd_teacher() + "]");
			System.out.println("[/modifyStudyInfo] STD_STARTDATE : " + studyInfo.getStd_startDate() + "]");
			System.out.println("[/modifyStudyInfo] STD_ENDDATE : " + studyInfo.getStd_endDate() + "]");
			System.out.println("[/modifyStudyInfo] STD_MAXMEMBER : " + studyInfo.getStd_maxMemberCount() + "]");
			System.out.println("[/modifyStudyInfo] STD_THEME : " + studyInfo.getStd_theme() + "]");
			System.out.println();
			
			boolean result = db.modifyStudyInfo(studyInfo);
			
			if (result)
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('정상적으로 변경되었습니다!');");
				out.println("location.href = '/Graduation_KMS/op/myStudies'");
				out.println("</script>");
			}
			else
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('스터디 정보가 변경되지 않았습니다!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}

		
		else if (subPath.equals("/viewThread"))
		{
			this.doGet(request, response);
		}		
		else if (subPath.equals("/myStudies"))
		{
			this.doGet(request, response);
		}
		else if (subPath.equals("/ScheduleCalender"))
		{
			this.doGet(request, response);
		}
				
		// Forwarding
		if(viewName != null) {
			System.out.println("[MainController.java.doPost()] Forward to " + viewName);
			System.out.println("[MainController.java.doPost()] request.getPathInfo() : " + request.getPathInfo());
			System.out.println();
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

}

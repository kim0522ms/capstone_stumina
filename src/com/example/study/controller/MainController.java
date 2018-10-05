package com.example.study.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.study.model.AreaInfo;
import com.example.study.model.AttandantInfo;
import com.example.study.model.CategoryInfo;
import com.example.study.model.DetailInfo;
import com.example.study.model.JoinRequestInfo;
import com.example.study.model.NotificationInfo;
import com.example.study.model.RoomsInfo;
import com.example.study.model.ScheduleBoardInfo;
import com.example.study.model.ScheduleInfo;
import com.example.study.model.StudyInfo;
import com.example.study.model.StudyRoomInfo;
import com.example.study.model.UserInfo;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MainController
 */
//@WebServlet("/MainController")
public class MainController extends HttpServlet {
	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
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
		
		System.out.println("["+ dayTime.format(new Date()) + "][MainController.java] Get Data has been arrive");
		System.out.println("["+ dayTime.format(new Date()) + "][MainController.java] Arrived SubPath : " + subPath);
		System.out.println();
		
		
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (subPath.equals("/signOut"))
		{
			session.setAttribute("user_name", null);
			session.setAttribute("user_idx", null);
			session.setAttribute("userInfo", null);
			session.setAttribute("notificationInfos", null);
			
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
					System.out.println("["+ dayTime.format(new Date()) + "][/myStudies] Error : user_idx(" +user_idx+ ") has not join in any study !!");
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
			System.out.println("["+ dayTime.format(new Date()) + "][/search] Search Keyword : " + keyword);
			
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
			System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Parameter std_no : " + std_no);
			System.out.println();
			
			if (std_no != null)
			{
				StudyDBDAO db = new StudyDBDAO();
				
				ArrayList<UserInfo> userInfos = db.getStudyUserInfo(std_no);
				
				// 스터디에 가입된 회원 정보 모두 쿼리해서 request에 저장
				if (userInfos != null)
				{
					request.setAttribute("userInfos", userInfos);
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Study Member Info added successfully! ");
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Study Member Count : " + userInfos.size());
					System.out.println();
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Failed to add Study Member Info... ");
					System.out.println();
					// TODO: 스터디에 가입된 사람이 아예 없을수는 없으므로 에러 처리 해야함. 가입요청은 없을수도 있음
				}
				
				// 스터디에 가입신청한 회원 정보를 모두 쿼리해서 request에 저장
				ArrayList<JoinRequestInfo> requestInfos = db.getJoinRequests(std_no);
				if (requestInfos != null)
				{
					request.setAttribute("requestInfos", requestInfos);
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Join Request Member Info added successfully! ");
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Join Request Member Count : " + requestInfos.size());
					System.out.println();
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Failed to add Join Request Member Info... ");
					System.out.println();
				}
				
				String std_leader = db.getStudyLeader(std_no);
				if (std_leader != null)
				{
					request.setAttribute("std_leader", std_leader);
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] STD_LEADER Info added successfully! ");
					System.out.println();
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Failed to add STD_LEADER Info... ");
					System.out.println();
				}
				
				request.setAttribute("std_no", std_no);
				
				viewName = "/Member/ViewStudyMembers.jsp";
				
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/viewStudyMember] Error : Parameter std_no is null !!");
				System.out.println();
			}
		}
		
		// 스터디 가입 신청
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
				
				System.out.println("["+ dayTime.format(new Date()) + "][/joinStudy] user_idx : " + user_idx);
				System.out.println("["+ dayTime.format(new Date()) + "][/joinStudy] std_no : " + std_no);
				
				StudyDBDAO db = new StudyDBDAO();
				
				boolean isJoined = db.checkJoined(std_no, user_idx);
				
				if (isJoined == true)
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/joinStudy] user_idx : " + user_idx + "has already join in std_no : " + std_no + " !!");
					
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
						new NotificationDAO().requestJoinStudy(user_idx, std_no);
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
			System.out.println("["+ dayTime.format(new Date()) + "][/viewThread] rsch_idx : " + rsch_idx);
			
			if (rsch_idx == null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/viewThread] Error : rsch_idx is null !!");
			}
			else
			{
				StudyDBDAO db = new StudyDBDAO();
				
				ArrayList<ScheduleBoardInfo> threadInfos = db.getThreads(rsch_idx);
				
				// Thread가 있을 경우
				if (threadInfos != null)
				{
					request.setAttribute("threadInfos", threadInfos);
					request.setAttribute("rsch_idx", rsch_idx);
					System.out.println("["+ dayTime.format(new Date()) + "][/viewThread] threadInfos has been added to request.");
				}
				// Thread가 없을 경우
				else
				{
					request.setAttribute("rsch_idx", rsch_idx);
					System.out.println("["+ dayTime.format(new Date()) + "][/viewThread] threadInfos is null");
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
			
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyPage] std_no :" + std_no);
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyPage] user_idx :" + user_idx);
			System.out.println();
			
			StudyDBDAO db = new StudyDBDAO();
			
			if (user_idx.equals(db.getStudyLeader(std_no)))
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/modifyPage] Edit stdno(" + std_no+ ")'s info");
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
				System.out.println("["+ dayTime.format(new Date()) + "][/editStudyInfo] Error : stdno(" + std_no+ ")'s leader idx is incorrect.");
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
			
			System.out.println("["+ dayTime.format(new Date()) + "][/QuitStudyMember] std_no :" + std_no);
			System.out.println("["+ dayTime.format(new Date()) + "][/QuitStudyMember] user_idx :" + user_idx);
			System.out.println();
			
			StudyDBDAO db = new StudyDBDAO();
			
			boolean isQuited = db.deleteStudyMember(std_no, user_idx);
			
			if (isQuited)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/QuitStudyMember] Success to Quit user_idx(" +user_idx+ ") from std_no(" + std_no + ")!" );
				new NotificationDAO().QuitStudyMember(user_idx, std_no);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/QuitStudyMember] Failed to Quit user_idx(" +user_idx+ ") from std_no(" + std_no + ")..." );
			}
			
			
			
			viewName = "/MainPage.jsp";
		}
		
		
		// 스터디에서 유저 추방
		else if (subPath.equals("/banStudyMember"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = request.getParameter("user_idx");
			
			System.out.println("["+ dayTime.format(new Date()) + "][/banStudyMember] std_no :" + std_no);
			System.out.println("["+ dayTime.format(new Date()) + "][/banStudyMember] user_idx :" + user_idx);
			System.out.println();
			
			StudyDBDAO db = new StudyDBDAO();
			
			boolean isDeleted = db.deleteStudyMember(std_no, user_idx);
			
			if (isDeleted)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/banStudyMember] Success to Ban user_idx(" +user_idx+ ") from std_no(" + std_no + ")!" );
				new NotificationDAO().BanStudyMember(user_idx, std_no);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/banStudyMember] Failed to Ban user_idx(" +user_idx+ ") from std_no(" + std_no + ")..." );
			}
			
			viewName = "/op/viewStudyMember?std_no=" + std_no;
		}
		
		// 대기중인 회원 가입 승인
		else if (subPath.equals("/acceptMemberJoin"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = request.getParameter("user_idx");
			System.out.println("["+ dayTime.format(new Date()) + "][/acceptMemberJoin] std_no : " + std_no);
			System.out.println("["+ dayTime.format(new Date()) + "][/acceptMemberJoin] user_idx : " + user_idx);
			
			if (std_no != null && user_idx != null)
			{
				StudyDBDAO db = new StudyDBDAO();
				
				boolean isInserted = db.acceptMemberJoin(std_no, user_idx);
				
				if (isInserted)
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/acceptMemberJoin] Success to add user to JOININFO!");
					new NotificationDAO().acceptStudyMember(user_idx, std_no);
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/acceptMemberJoin] Failed to add user to JOININFO!");
				}
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/acceptMemberJoin] Error : Recieved Null Parameter...");
			}
			
			viewName = "/op/viewStudyMember";
		}
		
		// 출석 체크
		else if (subPath.equals("/checkAttandance"))
		{
			String std_no = request.getParameter("std_no");
			System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Parameter std_no : " + std_no);
			System.out.println();
			
			if (std_no != null)
			{
				StudyDBDAO db = new StudyDBDAO();

				ArrayList<UserInfo> userInfos = db.getStudyUserInfo(std_no);
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				// 스터디에 가입된 회원 정보 모두 쿼리해서 request에 저장
				if (userInfos != null)
				{
					request.setAttribute("userInfos", userInfos);
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Study Member Info added successfully! ");
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Study Member Count : " + userInfos.size());
					System.out.println();
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Failed to add Study Member Info... ");
					System.out.println();
					// TODO: 스터디에 가입된 사람이 아예 없을수는 없으므로 에러 처리 해야함. 가입요청은 없을수도 있음
				}
				
				// 스터디에 가입신청한 회원 정보를 모두 쿼리해서 request에 저장
				ArrayList<JoinRequestInfo> requestInfos = db.getJoinRequests(std_no);
				if (requestInfos != null)
				{
					request.setAttribute("requestInfos", requestInfos);
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Join Request Member Info added successfully! ");
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Join Request Member Count : " + requestInfos.size());
					System.out.println();
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Failed to add Join Request Member Info... ");
					System.out.println();
				}
				
				String std_leader = db.getStudyLeader(std_no);
				if (std_leader != null)
				{
					request.setAttribute("std_leader", std_leader);
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] STD_LEADER Info added successfully! ");
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Failed to add STD_LEADER Info... ");
				}
				
				String std_name = db.getStudyName(std_no);
				if (std_name != null)
				{
					request.setAttribute("std_name", std_name);
				}
				
				request.setAttribute("std_no", std_no);
				
				ArrayList<ScheduleInfo> scheduleInfos = db.getSchedules(std_no);
				
				if (scheduleInfos != null)
				{
					request.setAttribute("scheduleInfos", scheduleInfos);
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] scheduleInfos added successfully! ");
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] scheduleInfos Count : " + scheduleInfos.size());
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Failed to add scheduleInfos... ");
				}
				
				ArrayList<AttandantInfo> attandantInfos = db.getAttendantInfo(std_no);
				
				if (attandantInfos != null)
				{
					request.setAttribute("attandantInfos", attandantInfos);
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] attandantInfos added successfully! ");
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] attandantInfos Count : " + attandantInfos.size());
					viewName = "/Attandance/Attandance.jsp";
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Failed to add attandantInfos... ");
					
					out.println("<script>");
					out.println("alert('아직 개설된 스케쥴이 없습니다!');");
					out.println("history.back();");
					out.println("</script>");
					viewName = null;
				}
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/checkAttandance] Error : Parameter std_no is null !!");
				System.out.println();
			}
		}
		
		// 가입 거절
		else if (subPath.equals("/rejectMemberJoin"))
		{
			String std_no = request.getParameter("std_no");
			String user_idx = request.getParameter("user_idx");
			
			StudyDBDAO db = new StudyDBDAO();
			
			boolean isDeleted = db.rejectMemberJoin(std_no, user_idx);
			
			if (isDeleted == true)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/rejectMemberJoin] Success to reject Join Request stdno(" + std_no + ")");
				viewName = "/op/viewStudyMember";
				
				new NotificationDAO().rejectStudyMember(user_idx, std_no);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/rejectMemberJoin] Failed to reject Join Request stdno(" + std_no + ")");
				
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
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] scb_idx : " + scb_idx);
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] rsch_idx : " + rsch_idx);
			
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
					out.println("location.href='${pageContext.request.contextPath}/op/viewThread?rsch_idx="+ rsch_idx +"';");
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
		
		// 알림을 읽음 상태로 변경
		else if (subPath.equals("/readNofitication"))
		{
			String noti_idx = request.getParameter("noti_idx");
			boolean isReaded = new NotificationDAO().readNotification(noti_idx);

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (isReaded)
			{
				out.println("success");
			}
			else
			{
				out.println("fail");
			}
		}

		// 스케줄 추가를 위해 지역 및 스터디룸 정보 쿼리 후 request에 담아 전송
		else if (subPath.equals("/createSchedule"))
		{
			String std_no = request.getParameter("std_no");
			System.out.println("["+ dayTime.format(new Date()) + "][/createSchedule] std_no :" + std_no);
			
			StudyDBDAO db = new StudyDBDAO();
			
			// 지역 정보 쿼리
			ArrayList<AreaInfo> areaInfos = db.getAllAreaInfo();
			if (areaInfos != null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/createSchedule] : AreaInfo Added.");
				request.setAttribute("areaInfos", areaInfos);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/createSchedule] : Error in AreaInfo Add.");
				viewName = "/Error.jsp?value=NoAreaData";
			}
			
			// 스터디룸 정보 쿼리
			ArrayList<StudyRoomInfo> studyRoomInfos = db.getAllStudyRoomInfo();
			if (studyRoomInfos != null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/createSchedule] : StudyRoomInfo Added.");
				request.setAttribute("studyRoomInfos", studyRoomInfos);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/createSchedule] : Error in StudyRoomInfo Add.");
				viewName = "/Error.jsp?value=NoStudyRoomData";
			}
			
			// 방 정보 쿼리
			ArrayList<RoomsInfo> roomInfos = db.getAllRoomsInfo();
			if (roomInfos != null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/createSchedule] : RoomsInfo Added.");
				request.setAttribute("roomInfos", roomInfos);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/createSchedule] : Error in RoomsInfo Add.");
				viewName ="/Error.jsp?value=NoRoomInfoData";
			}
			
			request.setAttribute("std_no", std_no);
			
			viewName = "/CreateSchedule.jsp";
		}
		
		// 파일 다운로드
		else if (subPath.equals("/downloadFile"))
		{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String file_idx = request.getParameter("file_idx");
			
			StudyDBDAO db = new StudyDBDAO();
			
			String path = db.getFilePath(file_idx);
			
			if (path != null)
			{
				InputStream in = null;
			    OutputStream os = null;
			    File file = null;
			    boolean skip = false;
			    String client = "";
			 
			 
			    try{
			         
			 
			        // 파일을 읽어 스트림에 담기
			        try{
			            file = new File(path);
			            in = new FileInputStream(file);
			        }catch(FileNotFoundException fe){
			            skip = true;
			        }
			 
			 
			 
			         
			        client = request.getHeader("User-Agent");
			 
			        // 파일 다운로드 헤더 지정
			        response.reset() ;
			        response.setContentType("application/octet-stream");
			        response.setHeader("Content-Description", "JSP Generated Data");
			 
			 
			        if(!skip){
			 
			             
			            // IE
			            if(client.indexOf("MSIE") != -1){
			                response.setHeader ("Content-Disposition", "attachment; filename="+new String(file.getName().getBytes("KSC5601"),"ISO8859_1"));
			 
			            }else{
			                // 한글 파일명 처리
			                response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(file.getName().getBytes("utf-8"),"iso-8859-1") + "\"");
			                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			            } 
			             
			            response.setHeader ("Content-Length", ""+file.length() );
			 
			 
			       
			            os = response.getOutputStream();
			            byte b[] = new byte[(int)file.length()];
			            int leng = 0;
			             
			            while( (leng = in.read(b)) > 0 ){
			                os.write(b,0,leng);
			            }
			 
			        }else{
			            response.setContentType("text/html;charset=UTF-8");
			            out.println("<script language='javascript'>alert('파일 다운로드에 실패했습니다! 다시 시도해 주세요...');history.back();</script>");
			 
			        }
			         
			        in.close();
			        os.close();
			 
			    }catch(Exception e){
			      e.printStackTrace();
			    }
			}
			else
			{
				out.println("<script>");
				out.println("alert('해당 파일이 존재하지 않습니다!!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
	
		// 媛� Path 湲곕뒫�쓣 �떎�뻾�븳 �썑 �쉷�뱷�븳 viewName 二쇱냼濡� Forwarding
		if(viewName != null) {
			
			if (session.getAttribute("user_idx") != null)
			{
				String user_idx = session.getAttribute("user_idx").toString();
				ArrayList<NotificationInfo> notificationInfos = new NotificationDAO().getMyNofitication(user_idx);
				
				if (notificationInfos != null)
				{
					session.setAttribute("notificationInfos", notificationInfos);
				}
			}
			
			System.out.println("["+ dayTime.format(new Date()) + "][MainController.java.doGet()] Forward to " + viewName);
			System.out.println();
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("["+ dayTime.format(new Date()) + "][MainController.java] Post Data has been arrive");
		System.out.println();
		
		String subPath = request.getPathInfo();
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (subPath.equals("/signIn"))
		{
			System.out.println("["+ dayTime.format(new Date()) + "][/signIn] Request From : " + request.getParameter("requestURL").replace("${pageContext.request.contextPath}", ""));
			
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
				session.setAttribute("notificationInfos", null);
				
				if (request.getParameter("requestURL").equals(null))
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/signIn] Redirect URL : " + request.getParameter("requestURL").replace("${pageContext.request.contextPath}", ""));
					viewName = request.getParameter("requestURL").replace("${pageContext.request.contextPath}", "");
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][/signIn] Redirect URL : /MainPage.jsp");
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
				System.out.println("["+ dayTime.format(new Date()) + "][/registStudy] Failed to Find 'detail_idx'");
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
			
			System.out.println("["+ dayTime.format(new Date()) + "][/registStudy] Recived Parameters");
			System.out.println("[/registStudy] STD_NO : " + studyInfo.getStd_no() + "]");
			System.out.println("[/registStudy] DETAIL_IDX : " + studyInfo.getDetail_idx() + "]");
			System.out.println("[/registStudy] STD_NAME : " + studyInfo.getStd_name() + "]");
			System.out.println("[/registStudy] STD_CONTENTS : " + studyInfo.getStd_contents() + "]");
			System.out.println("[/registStudy] STD_LEADER : " + studyInfo.getStd_leader() + "]");
			System.out.println("][/registStudy] STD_LOCATION : " + studyInfo.getStd_location() + "]");
			System.out.println("[/registStudy] STD_MAXATTCNT : " + studyInfo.getStd_maxattcnt() + "]");
			System.out.println("[/registStudy] STD_ENDFLAG : " + studyInfo.getStd_endflag() + "]");
			System.out.println("[/registStudy] STD_TEACHER : " + studyInfo.getStd_teacher() + "]");
			System.out.println("[/registStudy] STD_STARTDATE : " + studyInfo.getStd_startDate() + "]");
			System.out.println("[/registStudy] STD_ENDDATE : " + studyInfo.getStd_endDate() + "]");
			System.out.println("[/registStudy] STD_MAXMEMBER : " + studyInfo.getStd_maxMemberCount() + "]");
			System.out.println("[/registStudy] STD_THEME : " + studyInfo.getStd_theme() + "]");
			System.out.println();
			
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
			Date oldDate = null;;
			try {
				oldDate = oldDateFormat.parse(rsch_date);
				rsch_idx_head = newDateFormat.format(oldDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] rsch_idx_head : " + rsch_idx_head);
			///////////////////////////////
			
			
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] Recived Parameter");
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] rsch_date : " + rsch_date);
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] room_idx : " + room_idx);
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] std_no : " + std_no);
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] rsch_checkin : " + rsch_checkin);
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] rsch_checkout : " + rsch_checkout);
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] rsch_name : " + rsch_name);
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] rsch_commnet " + rsch_commnet);
			System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] rsch_pay : " + rsch_pay);
			System.out.println();
			
			boolean isInserted = db.registSchedule(rsch_date, room_idx, std_no, rsch_checkin, rsch_checkout, rsch_name, rsch_commnet, rsch_pay);
			
			if (isInserted)
			{
				new NotificationDAO().registSchedule(std_no, oldDateFormat.format(oldDate));
				
				System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] Success to Insert Schedule !! ");
				System.out.println();
				viewName = "/op/ScheduleCalender";
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
				return;
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] Failed to Insert Schedule !! ");
				System.out.println();
				viewName = "/Error.jsp?value=InsertScheduleFailed";
			}
		}
		
		// 쓰레드 추가
		else if (subPath.equals("/uploadThread"))
		{
			// 첨부파일 처리
			String file_path = request.getServletContext().getRealPath("ScheduleThread/ThreadUploadFile");
			
			System.out.println(file_path);
			
			int size = 100*1024*1024;
			MultipartRequest multi = new MultipartRequest(request, file_path, size, "UTF-8", new FileRenamePolicy());
			
			String user_idx;
			String rsch_idx = multi.getParameter("rsch_idx");
			String title = "";
			String content = multi.getParameter("content");
			
			if (content == null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/uploadThread] Error : content is null ");		
				System.out.println();
			}
			else if (session.getAttribute("user_idx") == null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/uploadThread] Error : user_idx is null ");
				System.out.println();
			}
			else if (rsch_idx == null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][/uploadThread] Error : rsch_idx is null ");
				System.out.println();
			}
			else
			{
				user_idx = session.getAttribute("user_idx").toString();
				
				StudyDBDAO db = new StudyDBDAO();
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				// 파일부터 업로드 처리
				Enumeration files = multi.getFileNames();
				
				String file_hashname = null;
				String filenames = (String)files.nextElement();
				String filename = multi.getFilesystemName(filenames);
				String file_originalName = multi.getParameter("myFileName");
				
				System.out.println("아오 ㅅㅂ! : " + multi.getParameter("myFileName"));
				
				if (filename != null)
				{
					System.out.println(filename);
					
					File saveFile = multi.getFile("myFile");
					//saveFile = new FileRenamePolicy().rename(saveFile);
					System.out.println("경로 : " + file_path + "\\" + filename);
					
					file_hashname = filename + "_" +saveFile.getName().hashCode();
					
					
					boolean isUploaded = db.uploadThreadFile(file_originalName, file_hashname, file_path + "\\" + saveFile.getName(), user_idx);
					
					if (!isUploaded)
					{
						out.println("<script>");
						out.println("alert('파일 업로드에 실패했습니다...');");
						out.println("history.back();");
						out.println("</script>");
						return;
					}
				}
				
				
				
				//////// DB에 넣기 위해 값 처리 /////////
				System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] Recieved parameter user_idx : " + user_idx);
				System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] Recieved parameter content : " + content);
				System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] Recieved parameter rsch_idx : " + rsch_idx);
				System.out.println();
	
				
				// 개행문자 치환
				content = content.replaceAll("<br>", System.getProperty("line.separator"));
				content = content.replaceAll("\\<p(/?[^\\>]+)\\></p>", System.getProperty("line.separator"));
				content = content.replaceAll("\\<br(/?[^\\>]+)\\>", System.getProperty("line.separator"));
				
				// h 태그 제거(h1, h2...)
				content = content.replaceAll("\\<h(/?[^\\>]+)\\>", "");
				
				// HTML 태그 제거
				content = content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
				System.out.println("["+ dayTime.format(new Date()) + "][/registSchedule] Replaced parameter content : " + content);
				System.out.println();
				
				if (title.equals(""))
				{
					out.println("<script>");
					out.println("alert('내용을 입력해 주세요!!'");
					out.println("history.back();");
					out.println("</script>");
				}
				
				if (content.length() > 30)
				{
					title = content.substring(0, 30) + "...";
				}
				else
				{
					title = content;
				}
				
				// DB에 쓰레드(게시글) 입력
				boolean isInserted = db.uploadThread(rsch_idx, title, content, user_idx, file_hashname);
				
				if (isInserted)
				{
					new NotificationDAO().uploadThread(db.getStd_no(rsch_idx), rsch_idx);
					
					out.println("<script>");
					out.println("location.href='${pageContext.request.contextPath}/op/viewThread?rsch_idx="+ rsch_idx +"';");
					out.println("</script>");
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
				System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] Failed to Find 'detail_idx'");
				viewName = "/Error.jsp?Value=DetailNotFound";
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request,response);
				return;
			}
			
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] detail_idx : " + detail_idx);
			
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
			
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] Recived Parameters");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_NO : " + studyInfo.getStd_no() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] DETAIL_IDX : " + studyInfo.getDetail_idx() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_NAME : " + studyInfo.getStd_name() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_CONTENTS : " + studyInfo.getStd_contents() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_LEADER : " + studyInfo.getStd_leader() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_LOCATION : " + studyInfo.getStd_location() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_MAXATTCNT : " + studyInfo.getStd_maxattcnt() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_ENDFLAG : " + studyInfo.getStd_endflag() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_TEACHER : " + studyInfo.getStd_teacher() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_STARTDATE : " + studyInfo.getStd_startDate() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_ENDDATE : " + studyInfo.getStd_endDate() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_MAXMEMBER : " + studyInfo.getStd_maxMemberCount() + "]");
			System.out.println("["+ dayTime.format(new Date()) + "][/modifyStudyInfo] STD_THEME : " + studyInfo.getStd_theme() + "]");
			System.out.println();
			
			boolean result = db.modifyStudyInfo(studyInfo);
			
			if (result)
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('정상적으로 변경되었습니다!');");
				out.println("location.href = '${pageContext.request.contextPath}/op/myStudies'");
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

		// 출석 체크
		else if (subPath.equals("/modifyAttandance"))
		{
			StudyDBDAO db = new StudyDBDAO();
			String[] schedules = request.getParameterValues("rsch_idx");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			for (String rsch_idx : schedules)
			{
				System.out.println("rsch_idx : " + rsch_idx);
				String[] students = request.getParameterValues(rsch_idx);
				
				boolean isUpdated = false;
				if (students != null)
				{
					isUpdated = db.updateAttendantInfo(rsch_idx, students);
				}
				else
				{
					isUpdated = db.updateAttendantInfo(rsch_idx, null);
				}
				if (isUpdated == false)
				{
					out.println("<script>");
					out.println("alert('출석 정보 갱신에 실패했습니다! 새로고침 후 다시 시도해 주세요!');");
					out.println("history.back();");
					out.println("</script>");
					break;
				}
			}
			out.println("<script>");
			out.println("alert('출석 정보를 갱신하였습니다!');");
			out.println("location.href='${pageContext.request.contextPath}/op/myStudies';");
			out.println("</script>");
		}
		
		// 회원가입
		else if (subPath.equals("/signUp"))
		{
			String user_id = request.getParameter("userid");
			String user_pw = request.getParameter("password");
			String user_rpw = request.getParameter("rpassword");
			String user_name = request.getParameter("username");
			String user_sex = request.getParameter("usergender");
			String user_area = request.getParameter("area");
			String user_belong = request.getParameter("belong");
			String user_phone = request.getParameter("phone");
			String user_jobno = request.getParameter("job");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			StudyDBDAO db = new StudyDBDAO();
			
			if (user_id == null || user_pw == null || user_rpw == null || user_sex == null || user_area == null || user_belong == null || user_phone == null || user_jobno == null)
			{
				out.println("<script>");
				out.println("alert('입력되지 않은 값이 있습니다!');");
				out.println("history.back();");
				out.println("</script>");
			}
			
			if (db.checkUserIDExist(user_id))
			{
				out.println("<script>");
				out.println("alert('이미 존재하는 ID입니다! 다른 ID를 입력해 주세요.');");
				out.println("history.back();");
				out.println("</script>");
			}
			
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_id : " + user_id);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_pw : " + user_pw);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_rpw : " + user_rpw);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_name : " + user_name);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_sex : " + user_sex);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_area : " + user_area);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_belong : " + user_belong);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_phone : " + user_phone);
			System.out.println("["+ dayTime.format(new Date()) + "][/signUp] user_jobno : " + user_jobno);
			
			UserInfo newUser = new UserInfo();
			
			newUser.setUser_id(user_id);
			newUser.setUser_pw(user_pw);
			newUser.setUser_name(user_name);
			newUser.setUser_sex(user_sex);
			newUser.setUser_area(user_area);
			newUser.setUser_phone(user_phone);
			newUser.setUser_belong(user_belong);
			newUser.setUser_jobno(user_jobno);
			
			
			boolean isInserted = db.signUp(newUser);
			
			if (isInserted == true)
			{
				out.println("<script>");
				out.println("alert('축하합니다! 가입이 완료되었습니다!');");
				out.println("location.href='${pageContext.request.contextPath}/MainPage.jsp'");
				out.println("</script>");
			}
			else
			{
				out.println("<script>");
				out.println("alert('가입에 실패하였습니다! 새로고침 후 다시 입력해 주세요!');");
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
			
			if (session.getAttribute("user_idx") != null)
			{
				String user_idx = session.getAttribute("user_idx").toString();
				ArrayList<NotificationInfo> notificationInfos = new NotificationDAO().getMyNofitication(user_idx);
				
				if (notificationInfos != null)
				{
					session.setAttribute("notificationInfos", notificationInfos);
				}
			}
			
			System.out.println("["+ dayTime.format(new Date()) + "][MainController.java.doPost()] Forward to " + viewName);
			System.out.println();
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

}

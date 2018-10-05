package com.example.study.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.study.model.AttandantInfo;
import com.example.study.model.NotificationInfo;
import com.example.study.model.ScheduleInfo;
import com.example.study.model.StudyInfo;
import com.example.study.model.UserInfo;

/**
 * Servlet implementation class MobileController
 */
//@WebServlet("/MobileController")
public class MobileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileController() {
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
		
		if (subPath.equals("/initApp"))
		{
			this.doPost(request, response);
		}
		else if (subPath.equals("/registToken"))
		{
			this.doPost(request, response);
		}
		else if (subPath.equals("/androidPushTest"))
		{
			this.doPost(request, response);
		}
		else if (subPath.equals("/updateAbsent"))
		{
			this.doPost(request, response);
		}
		else if (subPath.equals("/checkAttendance"))
		{
			this.doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subPath = request.getPathInfo();
		
		System.out.println("["+ dayTime.format(new Date()) + "][MainController.java] Post Data has been arrive");
		System.out.println("["+ dayTime.format(new Date()) + "][MainController.java] Arrived SubPath : " + subPath);
		System.out.println();
		
		
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (subPath.equals("/initApp"))
		{
			String user_idx = request.getParameter("user_idx");
			
			StudyDBDAO db = new StudyDBDAO();
			
			JSONObject jobj = new JSONObject();
			
			ArrayList<StudyInfo> studyInfos = db.getMyStudies(user_idx);
			JSONArray jArr = new JSONArray();
			
			if (studyInfos != null)
			{
				for (StudyInfo study : studyInfos)
				{
					JSONObject tempObj = new JSONObject();
					
					String imagePath = "https://" + request.getServerName() + db.getStudyImage(study.getStd_no());
					tempObj.put("std_name", study.getStd_name());
					tempObj.put("std_contents", "리더 : " + study.getStd_leader());
					tempObj.put("std_image", imagePath);
					tempObj.put("std_no", study.getStd_no());
					tempObj.put("std_leader", study.getStd_leader_idx());
					
					jArr.add(tempObj);
				}
			}
			if (jArr.size() == 0)
			{
				jobj.put("return", "NODATA");
			}
			else
			{
				jobj.put("return", jArr);
			}
			
			jArr = new JSONArray();
			if (studyInfos != null)
			{
				for (StudyInfo study : studyInfos)
				{
					if (user_idx.equals(study.getStd_leader_idx()))
					{
						JSONObject tempObj = new JSONObject();
						String imagePath = "https://" + request.getServerName() + db.getStudyImage(study.getStd_no());
						tempObj.put("std_name", study.getStd_name());
						tempObj.put("std_contents", "리더 : " + study.getStd_leader());
						tempObj.put("std_image", imagePath);
						tempObj.put("std_no", study.getStd_no());
						jArr.add(tempObj);
					}
				}
			}
			if (jArr.size() == 0)
			{
				jobj.put("leaderStudy", "NODATA");
			}
			else
			{
				jobj.put("leaderStudy", jArr);
			}
			
			NotificationDAO noti_db = new NotificationDAO();
			
			ArrayList<NotificationInfo> notificationInfos = new ArrayList<>();
			notificationInfos = noti_db.getMyNofitication(user_idx);
			
			jArr = new JSONArray();
			
			if (notificationInfos != null)
			{
				for (NotificationInfo noti : notificationInfos)
				{
					JSONObject tempObj = new JSONObject();
					tempObj.put("noti_idx", noti.getNoti_idx());
					tempObj.put("noti_date", noti.getNoti_date());
					tempObj.put("noti_text", noti.getNoti_text());
					tempObj.put("noti_read", noti.getNoti_read());
					
					jArr.add(tempObj);
				}
			}
			if (jArr.size() == 0)
			{
				jobj.put("notification", "NODATA");
			}
			else
			{
				jobj.put("notification", jArr);
			}

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println(jobj.toString());
		}
		
		else if (subPath.equals("/getMyNotification"))
		{
			String user_idx = request.getParameter("user_idx");
			NotificationDAO noti_db = new NotificationDAO();
			
			ArrayList<NotificationInfo> notificationInfos = new ArrayList<>();
			notificationInfos = noti_db.getMyNofitication(user_idx);
			
			JSONObject jobj = new JSONObject();
			
			JSONArray jArr = new JSONArray();
			
			if (notificationInfos != null)
			{
				for (NotificationInfo noti : notificationInfos)
				{
					JSONObject tempObj = new JSONObject();
					tempObj.put("noti_idx", noti.getNoti_idx());
					tempObj.put("noti_date", noti.getNoti_date());
					tempObj.put("noti_text", noti.getNoti_text());
					tempObj.put("noti_read", noti.getNoti_read());
					
					jArr.add(tempObj);
				}
			}
			jobj.put("return", jArr);
		}
		
		else if (subPath.equals("/getAllMyStudies"))
		{
			String user_idx = request.getParameter("user_idx");
			
			StudyDBDAO db = new StudyDBDAO();
			
			ArrayList<StudyInfo> studyInfos = db.getMyStudies(user_idx);
			
			if (studyInfos != null)
			{
				JSONObject jobj = new JSONObject();
				
				JSONArray jArr = new JSONArray();
				
				for (StudyInfo study : studyInfos)
				{
					JSONObject tempObj = new JSONObject();
					
					String imagePath = "https://" + request.getServerName() + db.getStudyImage(study.getStd_no());
					tempObj.put("std_name", study.getStd_name());
					tempObj.put("std_contents", "리더 : " + study.getStd_leader());
					tempObj.put("std_image", imagePath);
					tempObj.put("std_no", study.getStd_no());
					
					jArr.add(tempObj);
				}
				jobj.put("return", jArr);
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println(jobj.toString());
			}
		}
		
		else if (subPath.equals("/updateAbsent"))
		{
			String jsonStr = request.getParameter("json");
			String resultCode = "RES_FAILED";
			
			if (jsonStr != null)
			{
				try {
					JSONParser parser = new JSONParser();

					JSONObject jsonObj = (JSONObject)parser.parse(jsonStr);

					JSONArray jArr = (JSONArray)parser.parse(jsonObj.get("return").toString());
					
					String rsch_idx = request.getParameter("rsch_idx");
					if (jArr.size() > 0)
					{
						List<String> users = new ArrayList<>();
						JSONObject tempObj;
						Iterator i = jArr.iterator();
				        while (i.hasNext()) {
							tempObj = (JSONObject) i.next();

							if (tempObj.get("absent").toString().equals("1"))
							{
								users.add(tempObj.get("user_idx").toString());
							}
						}
						StudyDBDAO db = new StudyDBDAO();
						db.updateAttendantInfo(rsch_idx, users.toArray(new String[users.size()]));
						resultCode = "RES_SUCCESS";
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("recieved json : " + jsonStr);
					out.println("resultCode : " + resultCode);
				}	
			}
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("recieved json : " + jsonStr);
			out.println("resultCode : " + resultCode);
		}
		
		else if (subPath.equals("/getScheduleAttendance"))
		{
			String rsch_idx = request.getParameter("rsch_idx");
			
			StudyDBDAO db = new StudyDBDAO();
			
			ArrayList<AttandantInfo> attendanceInfos = db.getAttendantInfoBy_rsch_idx(rsch_idx);
			
			if (attendanceInfos != null)
			{
				JSONObject jobj = new JSONObject();
				
				JSONArray jArr = new JSONArray();
				
				for (AttandantInfo att : attendanceInfos)
				{
					JSONObject tempObj = new JSONObject();
					
					tempObj.put("user_name", att.getUser_name());
					tempObj.put("absent", att.getAbsent());
					tempObj.put("user_idx", att.getUser_idx());
					tempObj.put("rsch_idx", att.getRsch_idx());
					
					jArr.add(tempObj);
				}
				jobj.put("return", jArr);
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println(jobj.toString());
			}
		}
		
		else if (subPath.equals("/getStudySchedule"))
		{
			String std_no = request.getParameter("std_no");
			
			StudyDBDAO db = new StudyDBDAO();
			
			ArrayList<ScheduleInfo> scheduleInfos = db.getSchedules(std_no);
			
			if (scheduleInfos != null)
			{
				JSONObject jobj = new JSONObject();
				
				JSONArray jArr = new JSONArray();
				
				for (ScheduleInfo schedule : scheduleInfos)
				{
					JSONObject tempObj = new JSONObject();
					
					tempObj.put("rsch_idx", schedule.getSchedule_idx());
					tempObj.put("rsch_name", schedule.getSchedule_name());
					tempObj.put("rsch_date", schedule.getSchedule_date());
					tempObj.put("sr_name", schedule.getStudyroom_name() + " " + schedule.getRoom_name());
					tempObj.put("sr_location", schedule.getStudyroom_location());
					
					jArr.add(tempObj);
				}
				jobj.put("return", jArr);
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println(jobj.toString());
			}
		}
		else if (subPath.equals("/checkAttendance"))
		{
			String user_idx = request.getParameter("user_idx");
			String room_beacon = request.getParameter("room_beacon");
			
			StudyDBDAO db = new StudyDBDAO();
			
			String checkedSchedule = db.checkAttendanceByScanner(user_idx, room_beacon);
			
			JSONObject jobj = new JSONObject();
			
			if (checkedSchedule != null)
			{
				jobj.put("return", "CHECK_SUCCESS");
				jobj.put("rsch_name", checkedSchedule);
			}
			else
				jobj.put("return", "CHECK_FAILED");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println(jobj.toString());
		}
		
		else if (subPath.equals("/login"))
		{
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			
			StudyDBDAO db = new StudyDBDAO();
			
			UserInfo userInfo = db.signin(user_id, user_pw);
			
			JSONObject jobj = new JSONObject();
			
			if (userInfo != null)
			{
				jobj.put("user_idx", userInfo.getUser_idx());
				jobj.put("user_name", userInfo.getUser_name());
				jobj.put("user_area", userInfo.getUser_area());
				jobj.put("user_sex", userInfo.getUser_sex());
				jobj.put("user_jobno", userInfo.getUser_jobno());
				jobj.put("user_phone", userInfo.getUser_phone());
				jobj.put("user_belong", userInfo.getUser_belong());
			}
			else
			{
				jobj.put("return", "NO_MEMBER");
			}
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println(jobj.toString());
		}
		else if (subPath.equals("/registToken"))
		{
			String token = request.getParameter("token");
			String user_idx = request.getParameter("user_idx");
			
			NotificationDAO noti_db = new NotificationDAO();
			
			noti_db.registAndroidUserToken(user_idx, token);
		}
		else if (subPath.equals("/androidPushTest"))
		{
			String user_idx = request.getParameter("user_idx");
			String message = request.getParameter("message");
			
			NotificationDAO noti_db = new NotificationDAO();
			noti_db.sendAndroidPush(user_idx, message);
		}
		else
		{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			out.println("서버가 받은 파라메터 : " + null);
		}
	}

}

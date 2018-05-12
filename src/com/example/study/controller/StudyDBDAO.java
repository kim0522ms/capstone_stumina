package com.example.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.example.study.model.AreaInfo;
import com.example.study.model.CategoryInfo;
import com.example.study.model.DetailInfo;
import com.example.study.model.RoomsInfo;
import com.example.study.model.ScheduleInfo;
import com.example.study.model.StudyInfo;
import com.example.study.model.StudyRoomInfo;
import com.example.study.model.UserInfo;

public class StudyDBDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private boolean recursion = false;
	
	/* 
	// localhost 
	private final String DB_URL = "jdbc:oracle:thin://@localhost:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	*/
	
	/* 
	// MainServer
	private final String DB_URL = "jdbc:oracle:thin://@172.17.14.204:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	*/
	
	// Home
	private final String DB_URL = "jdbc:oracle:thin://@192.168.0.2:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	
	
	public void connect() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
	}
	
	public void disconnect() throws SQLException{
		if (pstmt != null){
			pstmt.close();
			pstmt = null;
		}
		
		if (conn != null){
			conn.close();
			conn = null;
		}
	}
	
	public UserInfo signin(String id, String passwd)
	{
		UserInfo userInfo = null;
		try {
			connect();
			
			String sql = "SELECT * FROM tb_users WHERE user_id = ? AND user_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				userInfo = new UserInfo();
				userInfo.setUser_idx(rs.getString("USER_IDX"));
				userInfo.setUser_name(rs.getString("USER_NAME"));
				userInfo.setUser_area(rs.getString("USER_AREA"));
				userInfo.setUser_belong(rs.getString("USER_BELONG"));
				userInfo.setUser_sex(rs.getString("USER_SEX"));
				userInfo.setUser_jobno(rs.getString("USER_JOBNO"));
				
				System.out.println("[StudyDBDAO.java.signin()] User " + id + " has log in.");
				System.out.println();
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	public ArrayList<StudyInfo> searchStudy(String search)
	{
		ArrayList<StudyInfo> studyInfos = null;
		
		String sql = "SELECT * FROM TB_STUDYINFO NATURAL JOIN TB_CATEGORY_DETAIL WHERE STD_CONTENTS LIKE '%" + search + "%' OR STD_NAME LIKE '%" + search + "%'";
		System.out.println("Created SQL : " + sql);
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				studyInfos = new ArrayList<StudyInfo>();
				StudyInfo studyInfo;
				do {
					if (rs.getInt("STD_ENDFLAG") == 1)
					{
						continue;
					};
					studyInfo = new StudyInfo();
					studyInfo.setStd_category(rs.getString("DETAIL_NAME"));
					studyInfo.setStd_contents(rs.getString("STD_CONTENTS"));
					studyInfo.setStd_endflag(rs.getInt("STD_ENDFLAG"));
					studyInfo.setStd_leader(rs.getString("STD_LEADER"));
					studyInfo.setStd_location(rs.getString("STD_LOCATION"));
					studyInfo.setStd_name(rs.getString("STD_NAME"));
					studyInfo.setStd_no(rs.getString("STD_NO"));
					studyInfo.setStd_startDate(rs.getString("STD_STARTDATE"));
					studyInfo.setStd_endDate(rs.getString("STD_ENDDATE"));
					studyInfo.setStd_teacher(rs.getString("STD_TEACHER"));
					studyInfo.setStd_maxMemberCount(rs.getInt("STD_MAXMEMBER"));
					
					sql = "SELECT COUNT(*) AS REMAIN FROM TB_JOININFO WHERE std_no = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, studyInfo.getStd_no());
					ResultSet rs_cnt = pstmt.executeQuery();
					
					if (rs_cnt.next())
					{
						int remain = studyInfo.getStd_maxMemberCount() - rs_cnt.getInt("REMAIN") ;
						studyInfo.setStd_remainMember(remain);
					}
					
					System.out.println("Param Added.");
					studyInfos.add(studyInfo);
				}while (rs.next());
			}
			disconnect();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studyInfos;
	}
	
	public ArrayList<StudyInfo> mainPage_Study()
	{
		ArrayList<StudyInfo> studyInfos = null;
		
		String sql = "SELECT * FROM TB_STUDYINFO NATURAL JOIN TB_CATEGORY_DETAIL";
		System.out.println("Created SQL : " + sql);
		try {
			connect();
			
			System.out.println("aaaa");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("aaaa");
			if (rs.next())
			{
				studyInfos = new ArrayList<StudyInfo>();
				StudyInfo studyInfo;
				do {
					if (rs.getInt("STD_ENDFLAG") == 1)
					{
						continue;
					};
					
					studyInfo = new StudyInfo();
					studyInfo.setStd_category(rs.getString("DETAIL_NAME"));
					studyInfo.setStd_contents(rs.getString("STD_CONTENTS"));
					studyInfo.setStd_endflag(rs.getInt("STD_ENDFLAG"));
					studyInfo.setStd_leader(rs.getString("STD_LEADER"));
					studyInfo.setStd_location(rs.getString("STD_LOCATION"));
					studyInfo.setStd_name(rs.getString("STD_NAME"));
					studyInfo.setStd_no(rs.getString("STD_NO"));
					studyInfo.setStd_startDate(rs.getString("STD_STARTDATE"));
					studyInfo.setStd_endDate(rs.getString("STD_ENDDATE"));
					studyInfo.setStd_teacher(rs.getString("STD_TEACHER"));
					studyInfo.setStd_maxMemberCount(rs.getInt("STD_MAXMEMBER"));
					
					
					sql = "SELECT COUNT(*) AS REMAIN FROM TB_JOININFO WHERE std_no = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, studyInfo.getStd_no());
					ResultSet rs_cnt = pstmt.executeQuery();
					
					if (rs_cnt.next())
					{
						int remain = studyInfo.getStd_maxMemberCount() - rs_cnt.getInt("REMAIN") ;
						studyInfo.setStd_remainMember(remain);
					}
					
					studyInfos.add(studyInfo);
					System.out.println("MainPage's Study List Added");
				}while (rs.next());
			}
			disconnect();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studyInfos;
	}
	
	public ArrayList<StudyInfo> getMyStudies(String user_idx)
	{
		ArrayList<StudyInfo> studyInfos = null;
		
		String sql = "SELECT * FROM tb_joininfo NATURAL JOIN (SELECT * FROM TB_STUDYINFO NATURAL JOIN TB_CATEGORY_DETAIL) WHERE USER_IDX = ?";
		System.out.println("Created SQL : " + sql);
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_idx);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				studyInfos = new ArrayList<StudyInfo>();
				StudyInfo studyInfo;
				do {
					if (rs.getInt("STD_ENDFLAG") == 1)
					{
						continue;
					};
					System.out.println("aaaa");
					studyInfo = new StudyInfo();
					studyInfo.setStd_category(rs.getString("DETAIL_NAME"));
					studyInfo.setStd_contents(rs.getString("STD_CONTENTS"));
					studyInfo.setStd_endflag(rs.getInt("STD_ENDFLAG"));
					studyInfo.setStd_leader(rs.getString("STD_LEADER"));
					studyInfo.setStd_location(rs.getString("STD_LOCATION"));
					studyInfo.setStd_name(rs.getString("STD_NAME"));
					studyInfo.setStd_no(rs.getString("STD_NO"));
					studyInfo.setStd_startDate(rs.getString("STD_STARTDATE"));
					studyInfo.setStd_endDate(rs.getString("STD_ENDDATE"));
					studyInfo.setStd_teacher(rs.getString("STD_TEACHER"));
					studyInfo.setStd_maxMemberCount(rs.getInt("STD_MAXMEMBER"));
					studyInfo.setStd_theme(rs.getString("STD_THEME"));

					sql = "SELECT user_name FROM TB_USERS where user_idx = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, rs.getString("STD_LEADER"));
					ResultSet rs2 = pstmt.executeQuery();
					
					if (rs2.next())
					{
						studyInfo.setStd_leader(rs2.getString("USER_NAME"));
					}
					
					// 각 스터디의 스케쥴 쿼리 후 저장
					
					recursion = true;
					studyInfo.setScheduleInfo(this.getSchedules(studyInfo.getStd_no()));
					recursion = false;
					
					System.out.println("StudyCard Added.");
					studyInfos.add(studyInfo);
				}while (rs.next());
			}
			else {
				System.out.println("쿼리 실행 결과 없음");
			}
			disconnect();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studyInfos;
	}
	
	public ArrayList<CategoryInfo> getCategory()
	{
		String sql = "SELECT * FROM TB_CATEGORY";
		
		ArrayList<CategoryInfo> categoryInfos = null;
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				categoryInfos = new ArrayList<CategoryInfo>();
				do {
					CategoryInfo category = new CategoryInfo();
					category.setCategory_idx(rs.getString("CG_IDX"));
					category.setCategory_name(rs.getString("CG_NAME"));
					
					categoryInfos.add(category);
				}while (rs.next());
			}
			
			else
			{
				System.out.println("쿼리 실행결과가 없음");
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryInfos;
	}
	
	public ArrayList<DetailInfo> getDetail()
	{
		String sql = "SELECT * FROM TB_CATEGORY_DETAIL";
		
		ArrayList<DetailInfo> detailInfos = null;
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				detailInfos = new ArrayList<DetailInfo>();
				
				do {
					DetailInfo detail = new DetailInfo();
					detail.setDetail_idx(rs.getString("DETAIL_IDX"));
					detail.setCategory_idx(rs.getString("CG_IDX"));
					detail.setDetail_name(rs.getString("DETAIL_NAME"));
					
					detailInfos.add(detail);
				}
				while(rs.next());
			}
			else
			{
				System.out.println("Detail 쿼리 실행결과가 없음");
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detailInfos;
	}
	
	public boolean registerStudy(StudyInfo studyInfo)
	{
		boolean isInserted = false;
		
		String sql = "INSERT INTO TB_STUDYINFO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'yy/mm/dd'), TO_DATE(?, 'yy/mm/dd'), ?, ?)";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studyInfo.getStd_no());
			pstmt.setString(2, studyInfo.getDetail_idx());
			pstmt.setString(3, studyInfo.getStd_name());
			pstmt.setString(4, studyInfo.getStd_contents());
			pstmt.setString(5, studyInfo.getStd_leader());
			pstmt.setString(6, studyInfo.getStd_location());
			pstmt.setInt(7, studyInfo.getStd_maxattcnt());
			pstmt.setInt(8, studyInfo.getStd_endflag());
			pstmt.setString(9, studyInfo.getStd_teacher());
			pstmt.setString(10, studyInfo.getStd_startDate());
			pstmt.setString(11, studyInfo.getStd_endDate());
			pstmt.setInt(12, studyInfo.getStd_maxMemberCount());
			pstmt.setString(13, studyInfo.getStd_theme());
			
			int result = pstmt.executeUpdate();
			
			System.out.println("1 StudyInfo has successfully inserted.");
			if (result > 0)
			{
				isInserted = true;
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isInserted;
	}
	
	public String getDetail_IdxByName(String detail_name)
	{
		String result = null;
		String sql = "SELECT * FROM TB_CATEGORY_DETAIL WHERE detail_name = ?";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, detail_name);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				result = rs.getString("DETAIL_IDX");
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<AreaInfo> getAllAreaInfo()
	{
		String sql = "SELECT * FROM TB_AREA";
		
		ArrayList<AreaInfo> areaInfos = null;
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				areaInfos = new ArrayList<AreaInfo>();
				AreaInfo area = null;
				
				do {
					area = new AreaInfo();
					area.setArea_idx(rs.getString("AREA_IDX"));
					area.setArea_name(rs.getString("AREA_NAME"));
					
					areaInfos.add(area);
				}while(rs.next());
			}
			else
			{
				System.out.println("Error : No Area Data Founded.");
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return areaInfos;
	}
	
	public ArrayList<StudyRoomInfo> getAllStudyRoomInfo()
	{
		String sql = "SELECT * FROM TB_STUDYROOMS";
		
		ArrayList<StudyRoomInfo> studyRoomInfos = null;
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				studyRoomInfos = new ArrayList<StudyRoomInfo>();
				StudyRoomInfo studyRoomInfo = null;
				
				do {
					studyRoomInfo = new StudyRoomInfo();
					
					studyRoomInfo.setStudyroom_idx(rs.getString("SR_IDX"));
					studyRoomInfo.setStudyroom_location(rs.getString("SR_LOCATION"));
					studyRoomInfo.setArea_idx(rs.getString("AREA_IDX"));
					studyRoomInfo.setStudyroom_name(rs.getString("SR_NAME"));
					
					studyRoomInfos.add(studyRoomInfo);
				}while(rs.next());
			}
			else
			{
				System.out.println("Error : No Area Data Founded.");
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studyRoomInfos;
	}
	
	public ArrayList<RoomsInfo> getAllRoomsInfo()
	{
		String sql = "SELECT * FROM TB_ROOMINFO";
		
		ArrayList<RoomsInfo> roomInfos = null;
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				roomInfos = new ArrayList<RoomsInfo>();
				RoomsInfo roomInfo = null;
				
				do {
					roomInfo = new RoomsInfo();
					
					roomInfo.setStudyroom_idx(rs.getString("SR_IDX"));
					roomInfo.setRoom_idx(rs.getString("ROOM_IDX"));
					roomInfo.setRoom_pay(rs.getString("ROOM_PAY"));
					roomInfo.setRoom_name(rs.getString("ROOM_NAME"));
					roomInfo.setRoom_maxmember(rs.getString("ROOM_MAXMEMBER"));
					
					roomInfos.add(roomInfo);
				}while(rs.next());
			}
			else
			{
				System.out.println("Error : No Area Data Founded.");
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roomInfos;
	}
	
	public boolean registSchedule(String rsch_date, String room_idx, String std_no, String rsch_checkin, String rsch_checkout, String rsch_name, String rsch_commnet, String rsch_pay)
	{
		boolean isRegist = false;
		
		SimpleDateFormat oldDateFormat = new SimpleDateFormat("yy/MM/dd", Locale.KOREA);
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
		
		String rsch_idx_head = "";
		Date oldDate;
		try {
			oldDate = oldDateFormat.parse(rsch_date);
			rsch_idx_head = newDateFormat.format(oldDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 해당 일자의 시퀀스 획득
		String sql = "SELECT LPAD( (COUNT(*) + 1), 4, '0') AS COUNT FROM TB_ROOM_SCHEDULE WHERE RSCH_IDX LIKE '%" + rsch_idx_head +"%'";

		System.out.println("[StudyDBDAO/registSchedule()] rsch_idx_head : " + rsch_idx_head);
		System.out.println("[StudyDBDAO/registSchedule()] Created SQL : " + sql);
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				System.out.println("[StudyDBDAO/registSchedule()] rs.getString(\"COUNT\") : " + rs.getString("COUNT"));
				System.out.println("[StudyDBDAO/registSchedule()] RSCH_IDX : " + rsch_idx_head + "_" + rs.getString("COUNT"));
				System.out.println();
				
				sql = "INSERT INTO TB_ROOM_SCHEDULE VALUES (TO_DATE(?, 'YY/MM/DD'), ?, ?, ?, ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rsch_date);
				pstmt.setString(2, rsch_idx_head + "_" + rs.getString("COUNT"));
				pstmt.setString(3, room_idx);
				pstmt.setString(4, std_no);
				pstmt.setInt(5, Integer.parseInt(rsch_checkin));
				pstmt.setInt(6, Integer.parseInt(rsch_checkout));
				pstmt.setString(7, rsch_name);
				pstmt.setString(8, rsch_commnet);
				pstmt.setInt(9, Integer.parseInt(rsch_pay));
				
				int result = pstmt.executeUpdate();
				
				System.out.println("[StudyDBDAO/registSchedule()] Insert Result Value : " + result);
				
				if (result > 0)
					isRegist = true;
				else
					isRegist = false;
			}
			else
			{
				System.out.println("[StudyDBDAO/registSchedule()] Failed to get PK Sequence!!");
				
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isRegist;
	}
	
	public String getArea_IDXbyName(String area_name)
	{
		String result = null;
		String sql = "SELECT * FROM TB_AREA WHERE AREA_NAME = ?";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, area_name);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				result = rs.getString("AREA_IDX");
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public ArrayList<ScheduleInfo> getSchedules(String std_no)
	{
		ArrayList<ScheduleInfo> scheduleInfos = null;
		String sql = "SELECT  * FROM TB_ROOM_SCHEDULE NATURAL JOIN TB_STUDYINFO WHERE STD_NO = ?";
		
		sql = "SELECT  * FROM TB_STUDYINFO NATURAL JOIN "
				+ "(SELECT * FROM TB_ROOM_SCHEDULE NATURAL JOIN "
					+ "(SELECT * FROM TB_ROOMINFO NATURAL JOIN TB_STUDYROOMS)"
				+ ") "
			+ "WHERE STD_NO = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				scheduleInfos = new ArrayList<ScheduleInfo>();
				ScheduleInfo schedule = null;
				do {
					schedule = new ScheduleInfo();
					schedule.setSchedule_idx(rs.getString("RSCH_IDX"));
					schedule.setSchedule_date(rs.getString("RSCH_DATE"));
					schedule.setCheckin(rs.getString("RSCH_CHECKIN"));
					schedule.setCheckout(rs.getString("RSCH_CHECKOUT"));
					schedule.setStd_no(rs.getString("STD_NO"));
					schedule.setRoom_idx(rs.getString("ROOM_IDX"));
					schedule.setStd_name(rs.getString("STD_NAME"));
					schedule.setSchedule_name(rs.getString("RSCH_NAME"));
					schedule.setStudyroom_name(rs.getString("SR_NAME"));
					schedule.setStudyroom_location(rs.getString("SR_LOCATION"));
					schedule.setRoom_name(rs.getString("ROOM_NAME"));
					schedule.setSchedule_comment(rs.getString("RSCH_COMMENT"));
					schedule.setRoom_pay(rs.getString("RSCH_PAY"));
					
					scheduleInfos.add(schedule);
				}while(rs.next());
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scheduleInfos;
	}
	
	
	public StudyInfo getStudyInfo(String std_no)
	{

		String sql = "SELECT * FROM TB_STUDYINFO NATURAL JOIN TB_CATEGORY_DETAIL WHERE std_no = ?";
		StudyInfo studyInfo = null;
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				studyInfo = new StudyInfo();
				studyInfo.setStd_category(rs.getString("DETAIL_NAME"));
				studyInfo.setStd_contents(rs.getString("STD_CONTENTS"));
				studyInfo.setStd_endflag(rs.getInt("STD_ENDFLAG"));
				studyInfo.setStd_location(rs.getString("STD_LOCATION"));
				studyInfo.setStd_name(rs.getString("STD_NAME"));
				studyInfo.setStd_no(rs.getString("STD_NO"));
				studyInfo.setStd_startDate(rs.getString("STD_STARTDATE"));
				studyInfo.setStd_endDate(rs.getString("STD_ENDDATE"));
				studyInfo.setStd_teacher(rs.getString("STD_TEACHER"));
				studyInfo.setStd_maxMemberCount(rs.getInt("STD_MAXMEMBER"));
				studyInfo.setStd_theme(rs.getString("STD_THEME"));
				
				// 由щ뜑 �씠由� �쉷�뱷
				sql = "SELECT user_name FROM TB_USERS where user_idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rs.getString("STD_LEADER"));
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					studyInfo.setStd_leader(rs.getString("USER_NAME"));
				}
				
				// 李몄뿬 �씤�썝 怨꾩궛
				sql = "SELECT COUNT(*) AS REMAIN FROM TB_JOININFO WHERE std_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, studyInfo.getStd_no());
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					int remain = studyInfo.getStd_maxMemberCount() - rs.getInt("REMAIN") ;
					studyInfo.setStd_remainMember(remain);
				}
				
				System.out.println("success to add studyInfo about " + std_no +"!");
			}
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studyInfo;
	}
}

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
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import com.example.study.model.AreaInfo;
import com.example.study.model.AttandantInfo;
import com.example.study.model.CategoryInfo;
import com.example.study.model.DetailInfo;
import com.example.study.model.FileInfo;
import com.example.study.model.JoinRequestInfo;
import com.example.study.model.RoomsInfo;
import com.example.study.model.ScheduleBoardInfo;
import com.example.study.model.ScheduleInfo;
import com.example.study.model.StudyInfo;
import com.example.study.model.StudyRoomInfo;
import com.example.study.model.UserInfo;

public class StudyDBDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private boolean recursion = false;
	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
	
	// localhost 
	private final String DB_URL = "jdbc:oracle:thin://@localhost:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	
	
	/*
	// Azure Server
	private final String DB_URL = "jdbc:oracle:thin://@40.74.80.225:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	*/
	
	/*
	// MainServer
	private final String DB_URL = "jdbc:oracle:thin://@172.17.14.204:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	*/
	
	/*
	// Home
	private final String DB_URL = "jdbc:oracle:thin://@192.168.0.2:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	*/
	
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
	
	public int getFile_SEQ()
	{
		int seq = 0;
		
		String sql = "SELECT FILE_SEQ.NEXTVAL FROM DUAL";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			seq = rs.getInt("NEXTVAL");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seq;
	}
	
	public String getStd_no(String rsch_idx)
	{
		String std_no = null;
		
		String sql = "SELECT * FROM TB_ROOM_SCHEDULE WHERE RSCH_IDX = ?";
		
		try {
			if(!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsch_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				std_no = rs.getString("std_no");
			}
			
			if(!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return std_no;
	}
	
	// 파일 정보 획득
	public FileInfo getFileInfo(String file_idx)
	{
		FileInfo fileInfo = null;
		
		String sql = "SELECT * FROM TB_UPLOAD_FILE WHERE FILE_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				fileInfo = new FileInfo();
				
				fileInfo.setFile_idx(rs.getString("file_idx"));
				fileInfo.setFile_originalname(rs.getString("file_originalname"));
				fileInfo.setFile_path(rs.getString("file_path"));
				fileInfo.setFile_uploader(rs.getString("user_idx"));
				
				System.out.println("으악 : " + fileInfo.getFile_idx());
				System.out.println("으악 : " + fileInfo.getFile_originalname());
				System.out.println("으악 : " + fileInfo.getFile_path());
				System.out.println("으악 : " + fileInfo.getFile_uploader());
				
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileInfo;
	}
	
	// 파일 경로 획득
	public String getFilePath(String file_idx)
	{
		String path = null;
		
		String sql = "SELECT * FROM TB_UPLOAD_FILE WHERE FILE_IDX = ?";
		
		try {
			if(!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				path = rs.getString("FILE_PATH");
			}
			
			if(!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
	// 파일 업로드
	public boolean uploadThreadFile(String file_originalname, String file_hashname, String file_path, String user_idx)
	{
		boolean isUploaded = false;
		
		String sql = "INSERT INTO TB_UPLOAD_FILE VALUES (FILE_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file_originalname);
			pstmt.setString(2, file_hashname);
			pstmt.setString(3, file_path);
			pstmt.setString(4, user_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isUploaded = true;
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.uploadThreadFile()] Success to upload New File!");
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.uploadThreadFile()] Failed to upload New File!");
			}
			
			System.out.println();
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isUploaded;
	}
	
	// 신규 회원 가입
	public boolean signUp(UserInfo newUser)
	{
		boolean isSuccess = false;
		
		String sql = "INSERT INTO TB_USERS VALUES(NEWUSER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newUser.getUser_name());
			pstmt.setString(2, newUser.getUser_sex());
			pstmt.setString(3, newUser.getUser_id());
			pstmt.setString(4, newUser.getUser_pw());
			pstmt.setString(5, newUser.getUser_jobno());
			pstmt.setString(6, newUser.getUser_belong());
			pstmt.setString(7, newUser.getUser_area());
			pstmt.setString(8, newUser.getUser_phone());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isSuccess = true;
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.signUp()] Success to add New User Info!");
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.signUp()] Failed to add New User Info..");
			}
			System.out.println();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	public boolean checkUserIDExist(String user_id) {
		boolean isExist = false;
		
		String sql = "SELECT * FROM TB_USERS WHERE USER_ID = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				isExist = true;
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isExist;
	}
	
	// 로그인
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
				userInfo.setUser_phone(rs.getString("USER_PHONE"));
				
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.signin()] User " + id + " has log in.");
				System.out.println();
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	public String getStudyName(String std_no) {
		String std_name = null;
		
		String sql = "SELECT * FROM TB_STUDYINFO WHERE STD_NO = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				std_name = rs.getString("STD_NAME");
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return std_name;
	}
	
	public ArrayList<StudyInfo> searchStudy(String search)
	{
		ArrayList<StudyInfo> studyInfos = null;
		
		String sql = "SELECT * FROM TB_STUDYINFO NATURAL JOIN TB_CATEGORY_DETAIL WHERE STD_CONTENTS LIKE '%" + search + "%' OR STD_NAME LIKE '%" + search + "%'";
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
					
					String path = new StudyDBDAO().getStudyImage(studyInfo.getStd_no());
					
					if (path != null)
					{
						studyInfo.setStd_imagepath(path);
					}
					else
					{
						System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.searchStudy()] Warning : Study Iamge doesn't exist.");
					}
					studyInfos.add(studyInfo);
				}while (rs.next());
				
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.searchStudy()] Success to add all Study Info.");
				System.out.println();
			}
			disconnect();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studyInfos;
	}
	
	// 회원명 구하기
	public String getUserName(String user_idx)
	{
		String user_name = null;
		
		String sql = "SELECT USER_NAME FROM TB_USERS WHERE USER_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_idx);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				user_name = rs.getString("USER_NAME");
			}
			
			if (!recursion)
				disconnect();	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user_name;
	}
	
	// 스터디 리더 이름 구하기
	public String getLeaderName(String std_no)
	{
		String leader_name = null;
		
		String sql = "SELECT USER_NAME FROM TB_USERS WHERE USER_IDX = (SELECT STD_LEADER FROM TB_STUDYINFO WHERE STD_NO = ?)";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				leader_name = rs.getString("USER_NAME");
			}
			
			if (!recursion)
				disconnect();	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leader_name;
	}
	
	
	// 스터디 리더 구하기, std_leader 구하기
	public String getStudyLeader(String std_no)
	{
		String std_reader = null;
		
		String sql = "SELECT STD_LEADER FROM TB_STUDYINFO WHERE STD_NO = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				std_reader = rs.getString("STD_LEADER");
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getStudyLeader()] STD_NO (" + std_no +")'s STD_LEADER : " + std_reader);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getStudyLeader()] Failed to get STD_NO (" + std_no +")'s STD_LEADER");
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return std_reader;
	}
	
	public ArrayList<StudyInfo> mainPage_Study()
	{
		ArrayList<StudyInfo> studyInfos = null;
		
		String sql = "SELECT * FROM TB_STUDYINFO NATURAL JOIN TB_CATEGORY_DETAIL";
		System.out.println("Created SQL : " + sql);
		try {
			connect();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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
					
					
					String path = new StudyDBDAO().getStudyImage(studyInfo.getStd_no());
					
					if (path != null)
					{
						studyInfo.setStd_imagepath(path);
					}
					else
					{
						System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.mainPage_Study()] Warning : Study Iamge doesn't exist.");
					}
					
					
					studyInfos.add(studyInfo);
				}while (rs.next());
			}
			disconnect();
			System.out.println();
			
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
					studyInfo.setStd_leader_idx(rs.getString("STD_LEADER"));

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
					studyInfo.setScheduleInfo(new StudyDBDAO().getSchedules(studyInfo.getStd_no()));
					recursion = false;
					
					String path = new StudyDBDAO().getStudyImage(studyInfo.getStd_no());
					
					if (path != null)
					{
						studyInfo.setStd_imagepath(path);
					}
					else
					{
						System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.searchStudy()] Warning : Study Image doesn't exist.");
						System.out.println();
					}
					
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
	
	
	// 가입 요청 하기
	public boolean requestJoin(String std_no, String user_idx)
	{
		boolean isRequested = false;
		
		String sql = "INSERT INTO TB_JOIN_REQUEST VALUES ('REQ_' || JOIN_SEQ.NEXTVAL, ?, SYSDATE, ? )";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_idx);
			pstmt.setString(2, std_no);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isRequested = true;
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.requestJoin()] Error : Failed to Insert in TB_JOIN_REQUEST");
				System.out.println();
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return isRequested;
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
	
	// 쓰레드 삭제
	public boolean deleteThread(String scb_idx)
	{
		boolean isDeleted = false;
		
		String sql = "DELETE FROM TB_SCHEDULE_BOARD WHERE SCB_IDX = ?";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scb_idx);
			int result = pstmt.executeUpdate();
			
			if (result > 0){
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.deleteThread()] SCB_IDX("+ scb_idx + ") thread has successfully deleted!");
				isDeleted = true;
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.deleteThread()] Failed to delete SCB_IDX("+ scb_idx + ") thread..");
			}
			System.out.println();
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isDeleted;
	}
	
	
	// study update
	public boolean modifyStudyInfo(StudyInfo studyInfo)
	{
		boolean isUpdated = false;
		
		String sql = "UPDATE TB_STUDYINFO SET "
				+ "DETAIL_IDX = ?,"								// String
				+ "STD_NAME = ?,"								// String
				+ "STD_CONTENTS = ?,"							// String
				+ "STD_LOCATION = ?,"							// String
				+ "STD_MAXATTCNT = ?,"							// Int
				+ "STD_STARTDATE = TO_DATE(?, 'yy/MM/dd'),"		// String
				+ "STD_ENDDATE = TO_DATE(?, 'yy/MM/dd'),"		// String					
				+ "STD_MAXMEMBER = ?,"							// Int
				+ "STD_THEME = ? "								// String
				+ "WHERE STD_NO = ?";
				
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studyInfo.getDetail_idx());
			pstmt.setString(2, studyInfo.getStd_name());
			pstmt.setString(3, studyInfo.getStd_contents());
			pstmt.setString(4, studyInfo.getStd_location());
			pstmt.setInt(5, studyInfo.getStd_maxattcnt());
			pstmt.setString(6, studyInfo.getStd_startDate());
			pstmt.setString(7, studyInfo.getStd_endDate());
			pstmt.setInt(8, studyInfo.getStd_maxMemberCount());
			pstmt.setString(9, studyInfo.getStd_theme());
			pstmt.setString(10, studyInfo.getStd_no());
	
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.modifyStudyInfo()] 1 StudyInfo has successfully updated.");
				isUpdated = true;
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.modifyStudyInfo()] Failed to update StudyInfo.");
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isUpdated;
	}
	
	// study insert
	public boolean registerStudy(StudyInfo studyInfo)
	{
		boolean isInserted = false;
		
		String sql = "INSERT INTO TB_STUDYINFO VALUES (? || STUDY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'yy/mm/dd'), TO_DATE(?, 'yy/mm/dd'), ?, ?)";
		
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
	
	// 스터디에 속해있는 유저 정보 전부 획득
	public ArrayList<UserInfo> getStudyUserInfo(String std_no)
	{
		String sql = "SELECT * FROM TB_JOININFO NATURAL JOIN TB_USERS WHERE STD_NO = ? ORDER BY USER_IDX";
		
		ArrayList<UserInfo> userInfos = null;
		
		
			try {
				if (!recursion)
					connect();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, std_no);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					userInfos = new ArrayList<UserInfo>();
					UserInfo user;
					
					do {
						user = new UserInfo();
						
						user.setUser_id(rs.getString("USER_ID"));
						user.setUser_name(rs.getString("USER_NAME"));
						user.setUser_sex(rs.getString("USER_SEX"));
						user.setUser_idx(rs.getString("USER_IDX"));
						user.setUser_jobno(rs.getString("USER_JOBNO"));
						user.setUser_area(rs.getString("USER_AREA"));
						user.setUser_belong(rs.getString("USER_BELONG"));
						user.setJoin_attcount(rs.getString("JOIN_ATTCOUNT"));
						user.setJoin_date(rs.getString("JOIN_DATE"));
						user.setUser_phone(rs.getString("USER_PHONE"));
						
						userInfos.add(user);
					}while(rs.next());
				}
				else
				{
					System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getStudyUserInfo()] Error : Join Info not found.");
				}
				
				if (!recursion)
					disconnect();
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return userInfos;
	}
	
	// 스터디룸 주소
	public String getStudyroomLocation(String sr_idx)
	{
		String sr_loc = null;
		
		String sql = "SELECT * FROM TB_STUDYROOMS WHERE SR_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sr_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				sr_loc = rs.getString("SR_LOCATION");
			}

			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sr_loc;
	}
	
	// 스터디룸 소개 이미지
	public String getStudyroomImage(String sr_idx)
	{
		String sr_img = null;
		
		String sql = "SELECT * FROM TB_STUDYROOMS NATURAL JOIN (SELECT * FROM TB_UPLOAD_FILE NATURAL JOIN TB_STUDYROOM_PHOTO) WHERE SR_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sr_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				sr_img = rs.getString("FILE_PATH");
			}

			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sr_img;
	}
	
	// 스터디룸 예약 가능 시간 구하기
	public boolean[] getRoomAvailableTime(String sr_idx, String rsch_date, int room_idx)
	{
		boolean[] timeTable = null;
		
		String sql = "SELECT * FROM TB_STUDYROOMS WHERE SR_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sr_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				int sr_open = rs.getInt("SR_OPEN");
				int sr_close = rs.getInt("SR_CLOSE");
				
				timeTable = new boolean[24];
				Arrays.fill(timeTable, true);
				
				for (int i = 0; i < sr_open; i++)
					timeTable[i] = false;
				
				for (int i = sr_close; i < 24; i++)
					timeTable[i] = false;
				
				sql = "SELECT * FROM TB_ROOM_SCHEDULE WHERE ROOM_IDX = ? AND RSCH_DATE = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, room_idx);
				pstmt.setString(2, rsch_date);
				
				ResultSet rs_time = pstmt.executeQuery();
				
				
				if (rs_time.next())
				{
					do
					{
						int checkin = rs_time.getInt("RSCH_CHECKIN");
						int checkout = rs_time.getInt("RSCH_CHECKOUT");
						
						System.out.println(checkin + " " + checkout);
						
						for (int i = checkin; i < checkout; i++)
						{
							timeTable[i] = false;
						}
					}while(rs.next());
				}
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return timeTable;
	}
	
	// 스터디룸 영업 시간
	public String getStudyroomTime(String sr_idx)
	{
		String sr_time = null;
		String sr_open = null;
		String sr_close = null;
		
		String sql = "SELECT * FROM TB_STUDYROOMS WHERE SR_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sr_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				sr_open = rs.getString("SR_OPEN");
				sr_close = rs.getString("SR_CLOSE");
				
				sr_time = sr_open + "시 ~ " + sr_close + "시";
			}

			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sr_time;
	}
	
	// 스터디룸 소개말 획득
	public String getStudyroomInfo(String sr_idx) {
		String sr_info = null;
		
		String sql = "SELECT * FROM TB_STUDYROOMS WHERE SR_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sr_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				sr_info = rs.getString("SR_INFO");
			}

			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sr_info;
	}
	 
	// 모든 스터디룸 정보(모든값) 획득
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
					studyRoomInfo.setStudyroom_info(rs.getString("SR_INFO"));
					
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
		try {
			connect();

			String sql = "INSERT INTO TB_ROOM_SCHEDULE VALUES (TO_DATE(?, 'YY/MM/DD'), ? || '_' || SCHEDULE_SEQ.NEXTVAL , ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsch_date);
			pstmt.setString(2, rsch_idx_head);
			pstmt.setString(3, room_idx);
			pstmt.setString(4, std_no);
			pstmt.setInt(5, Integer.parseInt(rsch_checkin));
			pstmt.setInt(6, Integer.parseInt(rsch_checkout));
			pstmt.setString(7, rsch_name);
			pstmt.setString(8, rsch_commnet);
			pstmt.setInt(9, Integer.parseInt(rsch_pay));
			
			int result = pstmt.executeUpdate();
			
			System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO/registSchedule()] Insert Result Value : " + result);
			
			if (result > 0)
				isRegist = true;
			else
				isRegist = false;
			
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
	
	// 스터디에 가입을 요청한 회원 정보를 모두 획득
	public ArrayList<JoinRequestInfo> getJoinRequests(String std_no)
	{
		ArrayList<JoinRequestInfo> requestInfos = null;
		
		String sql = "SELECT * FROM TB_JOIN_REQUEST NATURAL JOIN TB_USERS WHERE STD_NO = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				requestInfos = new ArrayList<JoinRequestInfo>();
				JoinRequestInfo request;
				
				do {
					request = new JoinRequestInfo();
					
					request.setRequest_idx(rs.getString("REQUEST_IDX"));
					request.setRequest_date(rs.getString("REQUEST_DATE"));
					request.setStudy_idx(rs.getString("STD_NO"));
					request.setUser_area(rs.getString("USER_AREA"));
					request.setUser_belong(rs.getString("USER_BELONG"));
					request.setUser_idx(rs.getString("USER_IDX"));
					request.setUser_jobno(rs.getString("USER_JOBNO"));
					request.setUser_name(rs.getString("USER_NAME"));
					request.setUser_sex(rs.getString("USER_SEX"));
					request.setUser_phone(rs.getString("USER_PHONE"));
					
					requestInfos.add(request);
					
				}while(rs.next());
			}
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requestInfos;
	}
	
	// 유저 삭제
	public boolean deleteStudyMember(String std_no, String user_idx)
	{
		boolean isDeleted = false;
		
		String sql = "DELETE FROM TB_JOININFO WHERE STD_NO = ? AND USER_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			pstmt.setString(2, user_idx);
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isDeleted = true;
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.deleteStudyMember()] Failed to delete TB_JOININFO WHERE std_no = " + std_no +"AND user_idx = "+ user_idx +"  !");
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isDeleted;
	}
	
	public boolean checkJoined(String std_no, String user_idx)
	{
		boolean isJoined = false;
		
		String sql = "SELECT * FROM TB_JOININFO WHERE std_no = ? AND user_idx = ?";
		
		try {
			if(!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			pstmt.setString(2, user_idx);
			ResultSet rs = pstmt.executeQuery();
			
			// rs.next()가 있을 경우 이미 가압되어있음
			if (rs.next())
			{
				isJoined = true;
			}
			
			if(!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isJoined;
	}
	
	public String getStudyImage(String std_no)
	{
		String path = null;
		
		String sql = "SELECT * FROM TB_STUDY_IMAGE WHERE STD_NO = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				path = rs.getString("SIMG_PATH");
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getStudyImage()] STD_NO(" + std_no +")'s image path : " + rs.getString("SIMG_PATH"));
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getStudyImage()] Error : Failed to get STD_NO(" + std_no +")'s image path.");
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
	// 스터디 가입 요청 거절
	public boolean rejectMemberJoin(String std_no, String user_idx)
	{
		boolean isDeleted = false;
		
		String sql = "DELETE FROM TB_JOIN_REQUEST WHERE STD_NO = ? AND USER_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			pstmt.setString(2, user_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isDeleted = true;
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.rejectMemberJoin] Success to delete TB_JOIN_REQUEST WHERE STD_NO : " + std_no + "AND USER_IDX : " + user_idx);
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.rejectMemberJoin] Failed to delete TB_JOIN_REQUEST WHERE STD_NO : " + std_no + "AND USER_IDX : " + user_idx);
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return isDeleted;
	}
	
	// 스터디에 새로운 멤버 추가
	public boolean acceptMemberJoin(String std_no, String user_idx)
	{
		boolean isInserted = false;
		
		String sql = "INSERT INTO TB_JOININFO VALUES(?, ?, 0, SYSDATE)";
		
		try {
			if(!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			pstmt.setString(2, user_idx);
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isInserted = true;
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.acceptMemberJoin()] Failed to insert std_no : " + std_no + " AND user_idx : " + user_idx + "to JOININFO...");
			}
			
			if(!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return isInserted;
	}
	
	// 출석 정보 추가
	public boolean addAttandance(String rsch_idx, String std_no)
	{
		boolean isInserted = false;
		
		ArrayList<UserInfo> userInfos = this.getStudyUserInfo(std_no);
		
		String sql = "INSERT INTO TB_ATTANDANCE VALUES (?, ?, 0)";
		if (userInfos != null)
		{
			try {
				connect();
				
				for (UserInfo user : userInfos)
				{
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user.getUser_idx());
					pstmt.setString(2, rsch_idx);
					
					int result = pstmt.executeUpdate();
					
					if (result <= 0)
						return isInserted;
				}
				disconnect();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isInserted = true;
		}
		else
		{
			return isInserted;
		}
		
		return isInserted;
	}
	
	// 출석 정보 수정
	public boolean updateAttendantInfo(String rsch_idx, String[] students)
	{
		boolean isUpdated = false;
		
		String sql = "UPDATE TB_ATTANDANCE SET ABSENT = 0 WHERE RSCH_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsch_idx);
			
			int reset = pstmt.executeUpdate();
			
			if (reset > 0)
			{
				if (students == null)
				{
					System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.updateAttendantInfo()] Success to initialize TB_ATTANDANCE");
					return true;
				}
				
				sql = "UPDATE TB_ATTANDANCE SET ABSENT = 1 WHERE RSCH_IDX = ? AND USER_IDX = ?";
				pstmt = conn.prepareStatement(sql);
				
				for (String student : students)
				{
					pstmt.setString(1, rsch_idx);
					pstmt.setString(2, student);

					int result = pstmt.executeUpdate();

					if (result <= 0)
					{
						System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.updateAttendantInfo()] Failed to update TB_ATTANDANCE");
						return isUpdated;
					}
				}
				isUpdated = true;
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.updateAttendantInfo()] Failed to reset TB_ATTANDANCE");
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isUpdated;
	}
	
	public ArrayList<AttandantInfo> getAttendantInfoBy_rsch_idx(String rsch_idx)
	{
		ArrayList<AttandantInfo> attandantInfos = null;
		
		String sql = "SELECT * FROM TB_ATTANDANCE NATURAL JOIN TB_USERS WHERE rsch_idx = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsch_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				attandantInfos = new ArrayList<AttandantInfo>();
				AttandantInfo attandant = null;
				
				do
				{
					attandant = new AttandantInfo();
					attandant.setUser_idx(rs.getString("user_idx"));
					attandant.setUser_name(rs.getString("user_name"));
					attandant.setRsch_idx(rs.getString("rsch_idx"));
					attandant.setAbsent(rs.getInt("absent"));
					
					attandantInfos.add(attandant);
				}while(rs.next());
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return attandantInfos;
	}
	
	// 출석 정보 얻기
	public ArrayList<AttandantInfo> getAttendantInfo(String std_no)
	{
		ArrayList<AttandantInfo> attandantInfos = null;
		
		String sql = "SELECT * FROM TB_ATTANDANCE NATURAL JOIN TB_ROOM_SCHEDULE WHERE STD_NO = ? ORDER BY RSCH_DATE, USER_IDX";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				attandantInfos = new ArrayList<AttandantInfo>();
				AttandantInfo attandant = null;
				
				do
				{
					attandant = new AttandantInfo();
					attandant.setUser_idx(rs.getString("user_idx"));
					attandant.setRsch_idx(rs.getString("rsch_idx"));
					attandant.setAbsent(rs.getInt("absent"));
					
					attandantInfos.add(attandant);
				}while(rs.next());
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return attandantInfos;
	}
	
	// 출석 체크 정보 수정
	public boolean modifyAttandance(String rsch_idx, String[] users)
	{
		boolean isUpdated = false;
		
		String sql = "UPDATE TB_ATTANDANCE SET ABSENT = 1 WEHRE USER_IDX = ? AND RSCH_IDX = ?";
		
		try {
			if(!recursion)
				connect();
			
			for (String user_idx : users)
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_idx);
				pstmt.setString(2, rsch_idx);
				
				int result = pstmt.executeUpdate();
				
				if (result <= 0)
				{
					return isUpdated;
				}
			}
			
			if(!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdated;
	}
	
	
	// 해당 스터디의 스케쥴 획득
	public ArrayList<ScheduleInfo> getSchedules(String std_no)
	{
		ArrayList<ScheduleInfo> scheduleInfos = null;
		String sql = "SELECT  * FROM TB_ROOM_SCHEDULE NATURAL JOIN TB_STUDYINFO WHERE STD_NO = ?";
		
		sql = "SELECT  * FROM TB_STUDYINFO NATURAL JOIN "
				+ "(SELECT * FROM TB_ROOM_SCHEDULE NATURAL JOIN "
					+ "(SELECT * FROM TB_ROOMINFO NATURAL JOIN TB_STUDYROOMS)"
				+ ") "
			+ "WHERE STD_NO = ? ORDER BY RSCH_DATE";
		
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
	
	public String getScheduleName(String rsch_idx)
	{
		String scheduleName = "";
		
		String sql = "SELECT RSCH_NAME FROM TB_ROOM_SCHEDULE WHERE rsch_idx = ?";

		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsch_idx);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				scheduleName = rs.getString("RSCH_NAME");
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getScheduleName()] Schedule Name not found !");
			}
			
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scheduleName;
	}
	
	// 게시글 (쓰레드) 업로드
	public boolean uploadThread(String rsch_idx, String scb_title, String scb_content, String user_idx, String file_hashname)
	{
		boolean isUploaded = false;
		
		String sql = "SELECT * FROM TB_UPLOAD_FILE WHERE FILE_HASHNAME = ?";
		String file_idx = null;
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file_hashname);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				file_idx = rs.getString("FILE_IDX");
			}
			System.out.println("8888888888888888");
			sql = "INSERT INTO TB_SCHEDULE_BOARD VALUES ('SCB_' || SCB_SEQ.NEXTVAL , ?, ?, ?, ?, SYSDATE, ?)";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsch_idx);
			pstmt.setString(2, scb_title);
			pstmt.setString(3, scb_content);
			pstmt.setString(4, user_idx);
			pstmt.setString(5, file_idx);
			System.out.println("8888888888888888");
			int result = pstmt.executeUpdate();
			System.out.println("8888888888888888");
			if (result > 0)
			{
				isUploaded = true;
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.uploadThread()] Insert Thread Success !");
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.uploadThread()] Insert Thread Failed...");
			}

			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return isUploaded;
	}
	
	public String checkAttendanceByScanner(String user_idx, String room_beacon)
	{
		String checkedSchedule = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd", Locale.KOREA);
		Date currentDate = new Date();
		String date = dateFormat.format(currentDate);
		
		System.out.println("아 좀.. 0 / " + date);
		
		String sql = "SELECT * FROM TB_JOININFO NATURAL JOIN (SELECT * FROM TB_ROOM_SCHEDULE NATURAL JOIN TB_ROOMINFO) WHERE USER_IDX = ? AND RSCH_DATE = ? AND ROOM_BEACON= ?";
		
		try {
			
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_idx);
			pstmt.setString(2, date);
			pstmt.setString(3, room_beacon);
			
			System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.checkAttendance()] user_idx : " + user_idx + ", room_beacon : " + room_beacon);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				String rsch_idx = rs.getString("RSCH_IDX");
				String rsch_name = rs.getString("RSCH_NAME");
				
				System.out.println("아 좀.. 1 / " + rsch_idx + " / " + rsch_name);
				
				sql = "UPDATE TB_ATTANDANCE SET ABSENT = 1 WHERE RSCH_IDX = ? AND USER_IDX = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rsch_idx);
				pstmt.setString(2, user_idx);
				
				int result = pstmt.executeUpdate();
				
				System.out.println("아 좀.. 2 / " + result);
				
				if (result > 0)
					checkedSchedule = rsch_name;
			}
			
			if (!recursion)
				disconnect();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return checkedSchedule;
	}
	
	// 스케쥴 게시판(Thread) 조회
	public ArrayList<ScheduleBoardInfo> getThreads(String rsch_idx)
	{
		String sql = "SELECT * FROM TB_SCHEDULE_BOARD NATURAL JOIN TB_USERS WHERE RSCH_IDX = ? ORDER BY SCB_TIME DESC";
		
		ArrayList<ScheduleBoardInfo> threadInfos = null;
		
		try {
			// Connect
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsch_idx);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				SimpleDateFormat oldDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss.SSS");
				SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일"+ System.getProperty("line.separator") + "a hh시 mm분 ss초");
				
				threadInfos = new ArrayList<ScheduleBoardInfo>();
				ScheduleBoardInfo thread = null;
				do
				{
					thread = new ScheduleBoardInfo();
					
					// Date 포맷 변경
					Date date = oldDateFormat.parse(rs.getString("SCB_TIME"));
					String rsch_time = newDateFormat.format(date);
					System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getThreads()] Changed DateFormat : " + rsch_time);
					
					thread.setScb_time(rsch_time);
					
					
					thread.setRsch_idx(rs.getString("RSCH_IDX"));
					thread.setProfile_pic_idx(null);
					thread.setScb_idx(rs.getString("SCB_IDX"));
					thread.setScb_title(rs.getString("SCB_TITLE"));
					thread.setScb_content(rs.getString("SCB_CONTENT"));
					thread.setUser_idx(rs.getString("USER_IDX"));
					thread.setUser_name(rs.getString("USER_NAME"));
					thread.setUser_belong(rs.getString("USER_BELONG"));
					thread.setFileInfo(this.getFileInfo(rs.getString("FILE_IDX")));
					
					threadInfos.add(thread);
					System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getThreads()] Thread inserted successfuly !");
				}while(rs.next());
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getThreads()] All Threads inserted successfuly !");
			}
			else
			{
				System.out.println("["+ dayTime.format(new Date()) + "][StudyDBDAO.java.getThreads()] Thread not found !");
			}
			
			// Disconnect
			if (!recursion)
				disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return threadInfos;
	}
}

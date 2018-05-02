package com.example.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.study.model.StudyInfo;
import com.example.study.model.UserInfo;

public class StudyDBDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private final String DB_URL = "jdbc:oracle:thin://@localhost:1521/xe";
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
				
				System.out.println("User " + id + " has log in.");
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
				
				// 리더 이름 획득
				sql = "SELECT user_name FROM TB_USERS where user_idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rs.getString("STD_LEADER"));
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					studyInfo.setStd_leader(rs.getString("USER_NAME"));
				}
				
				// 참여 인원 계산
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

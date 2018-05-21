package com.example.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.study.model.NotificationInfo;

public class NotificationDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private boolean recursion = false;
	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
	// localhost 
	private final String DB_URL = "jdbc:oracle:thin://@localhost:1521/xe";
	private final String DB_USER = "mskim";
	private final String DB_PW = "4321";
	
	
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
	
	public boolean readNotification(String noti_idx)
	{
		boolean isReaded = false;
		System.out.println("test : " + noti_idx);
		String sql = "UPDATE TB_NOTIFICATION SET NOTI_READ = 1 WHERE NOTI_IDX = ?";
		
		try {
			if (!recursion)
				connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noti_idx);
			int result = pstmt.executeUpdate();
			
			if (result > 0)
			{
				isReaded = true;
			}
			
			if (!recursion)
				disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isReaded;
	}
	
	public ArrayList<NotificationInfo> getMyNofitication(String user_idx)
	{
		ArrayList<NotificationInfo> notificationInfos = null;
		
		String sql = "SELECT * FROM TB_NOTIFICATION WHERE USER_IDX = ? ORDER BY NOTI_READ, NOTI_DATE DESC";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_idx);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				notificationInfos = new ArrayList<NotificationInfo>();
				NotificationInfo notification;
				do {
					notification = new NotificationInfo();
					
					notification.setNoti_idx(rs.getString("noti_idx"));
					notification.setNoti_text(rs.getString("noti_text"));
					notification.setNoti_date(rs.getString("noti_date"));
					notification.setNoti_read(rs.getInt("noti_read"));
					notification.setUser_idx(rs.getString("user_idx"));
				
					notificationInfos.add(notification);
				}while(rs.next());
			}
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notificationInfos;
	}
	
	// 회원 가입
	public void signUp(String user_idx)
	{
		StudyDBDAO db = new StudyDBDAO();
		
		String user_name = db.getUserName(user_idx);
		if (user_name == null)
		{
			System.out.println("[NotificationDAO.java.requestJoinStudy()] Error : leader user_name is null");
			System.out.println();
			return;
		}
		
		String sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
		String notification_text = user_name +"님의 가입을 축하드립니다! Stumina에서 나에게 꼭 맞는 스터디를 찾아봅시다!";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notification_text);
			pstmt.setString(2, user_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.signUp()] Success to add Notification");
			else
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.signUp()] Failed to add Notification");
			System.out.println();
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 스터디 가입 신청
	public void requestJoinStudy(String user_idx, String std_no)
	{
		StudyDBDAO db = new StudyDBDAO();
		
		String std_name = db.getStudyName(std_no);
		if (std_name == null)
		{
			System.out.println("[NotificationDAO.java.requestJoinStudy()] Error : std_name is null");
			System.out.println();
			return;
		}
		
		String user_name = db.getUserName(user_idx);
		if (user_name == null)
		{
			System.out.println("[NotificationDAO.java.requestJoinStudy()] Error : leader user_name is null");
			System.out.println();
			return;
		}
		
		String leader_idx = db.getStudyLeader(std_no);
		if (leader_idx == null)
		{
			System.out.println("[NotificationDAO.java.requestJoinStudy()] Error : leader user_idx is null");
			System.out.println();
			return;
		}

		String sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
		String notification_text = "[" + std_name + "] 스터디에  "+ user_name +"님이 가입 신청을 하셨습니다!";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notification_text);
			pstmt.setString(2, leader_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.requestJoinStudy()] Success to add Notification");
			else
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.requestJoinStudy()] Failed to add Notification");
			System.out.println();
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 스터디 가입 거절 메세지
	public void rejectStudyMember(String user_idx, String std_no)
	{
		String std_name = new StudyDBDAO().getStudyName(std_no);
		if (std_name == null)
		{
			System.out.println("[NotificationDAO.java.rejectStudyMember()] Error : std_name is null");
			System.out.println();
			return;
		}

		String sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
		String notification_text = "[" + std_name + "] 스터디의 가입이 거절되였습니다...";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notification_text);
			pstmt.setString(2, user_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.rejectStudyMember()] Success to add Notification");
			else
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.rejectStudyMember()] Failed to add Notification");
			System.out.println();
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 스케쥴 등록 메세지
	public void registSchedule(String std_no, String date)
	{
		String std_name = new StudyDBDAO().getStudyName(std_no);
		if (std_name == null)
		{
			System.out.println("[NotificationDAO.java.registSchedule()] Error : std_name is null");
			System.out.println();
			return;
		}
		
		String sql = "SELECT * FROM TB_JOININFO WHERE STD_NO = ?";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
				String notification_text = "[" + std_name + "] 스터디에  " + date + "일의 스케쥴이 새롭게 등록되였습니다! 확인해 주세요.";
				
				do {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, notification_text);
					pstmt.setString(2, rs.getString("USER_IDX"));
					
					int result = pstmt.executeUpdate();
					
					if (result > 0)
						System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.registSchedule()] Success to add Notification");
					else
						System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.registSchedule()] Failed to add Notification");
					System.out.println();
				}while(rs.next());
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void uploadThread(String std_no, String rsch_idx)
	{
		StudyDBDAO db = new StudyDBDAO();
		String std_name = db.getStudyName(std_no);
		if (std_name == null)
		{
			System.out.println("[NotificationDAO.java.uploadThread()] Error : std_name is null");
			System.out.println();
			return;
		}
		
		String rsch_name = db.getScheduleName(rsch_idx);
		if (rsch_name == null)
		{
			System.out.println("[NotificationDAO.java.uploadThread()] Error : rsch_name is null");
			System.out.println();
			return;
		}
		
		
		String sql = "SELECT * FROM TB_JOININFO WHERE STD_NO = ?";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
				String notification_text = "[" + std_name + "] 스터디의 [" + rsch_name +"] 스케쥴 게시판에 새로운 글이 등록되였습니다! 확인해 주세요.";
				
				do {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, notification_text);
					pstmt.setString(2, rs.getString("USER_IDX"));
					
					int result = pstmt.executeUpdate();
					
					if (result > 0)
						System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.uploadThread()] Success to add Notification");
					else
						System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.uploadThread()] Failed to add Notification");
					System.out.println();
				}while(rs.next());
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	// 추방(강퇴) 메세지
	public void BanStudyMember(String user_idx, String std_no)
	{
		String std_name = new StudyDBDAO().getStudyName(std_no);
		if (std_name == null)
		{
			System.out.println("[NotificationDAO.java.BanStudyMember()] Error : std_name is null");
			System.out.println();
			return;
		}

		String sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
		String notification_text = "[" + std_name + "] 스터디에서 강퇴되였습니다.";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notification_text);
			pstmt.setString(2, user_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.BanStudyMember()] Success to add Notification");
			else
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.BanStudyMember()] Failed to add Notification");
			System.out.println();
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 승인(승낙) 메세지
	public void acceptStudyMember(String user_idx, String std_no)
	{
		String std_name = new StudyDBDAO().getStudyName(std_no);
		if (std_name == null)
		{
			System.out.println("[NotificationDAO.java.acceptStudyMember()] Error : std_name is null");
			System.out.println();
			return;
		}

		String sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
		String notification_text = "[" + std_name + "] 스터디의 가입이 승인되었습니다! 성실하게 활동해 주세요!";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notification_text);
			pstmt.setString(2, user_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.acceptStudyMember()] Success to add Notification");
			else
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.acceptStudyMember()] Failed to add Notification");
			System.out.println();
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void QuitStudyMember(String user_idx, String std_no)
	{
		
		String std_name = new StudyDBDAO().getStudyName(std_no);
		if (std_name == null)
		{
			System.out.println("[NotificationDAO.java.QuitStudyMember()] Error : std_name is null");
			System.out.println();
			return;
		}

		String sql = "INSERT INTO TB_NOTIFICATION VALUES ('NOTI_' || TO_CHAR(NOTI_SEQ.NEXTVAL), ?, ?, SYSDATE, 0)";
		String notification_text = "[" + std_name + "] 스터디를 탈퇴하였습니다.";
		
		try {
			connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notification_text);
			pstmt.setString(2, user_idx);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.QuitStudyMember()] Success to add Notification");
			else
				System.out.println("["+ dayTime.format(new Date()) + "][NotificationDAO.java.QuitStudyMember()] Failed to add Notification");
			System.out.println();
			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

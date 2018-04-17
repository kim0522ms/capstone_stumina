package com.example.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

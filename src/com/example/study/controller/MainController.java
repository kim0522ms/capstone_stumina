package com.example.study.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		// 로그아웃
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
	
		// 각 Path 기능을 실행한 후 획득한 viewName 주소로 Forwarding
		if(viewName != null) {
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subPath = request.getPathInfo();
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		// 로그인
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
				viewName = "/LoginError.jsp";
			}
		}
				
		// 각 Path 기능을 실행한 후 획득한 viewName 주소로 Forwarding
		if(viewName != null) {
			RequestDispatcher view = request.getRequestDispatcher(viewName);
			view.forward(request,response);
		}
	}

}

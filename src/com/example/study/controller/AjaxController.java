package com.example.study.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AjaxController
 */
//@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String subPath = request.getPathInfo();
		
		System.out.println("["+ dayTime.format(new Date()) + "][AjaxController.java] Get Data has been arrive");
		System.out.println("["+ dayTime.format(new Date()) + "][AjaxController.java] Arrived SubPath : " + subPath);
		System.out.println();
		
		
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (subPath.equals("/getStudyroomInfo")) 
		{
			String sr_idx = request.getParameter("sr_idx");
			StudyDBDAO db = new StudyDBDAO();
			
			String sr_info = db.getStudyroomInfo(sr_idx);
			
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (sr_info != null)
			{
				out.println(sr_info);
			}
			else
			{
				out.print("정보가 없습니다.");
			}
		}
		
		else if (subPath.equals("/getStudyroomTime")) 
		{
			String sr_idx = request.getParameter("sr_idx");
			StudyDBDAO db = new StudyDBDAO();
			
			String sr_time = db.getStudyroomTime(sr_idx);
			
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (sr_time != null)
			{
				out.println(sr_time);
			}
			else
			{
				out.print("정보가 없습니다.");
			}
		}
		else if (subPath.equals("/getStudyroomImage"))
		{
			String sr_idx = request.getParameter("sr_idx");
			StudyDBDAO db = new StudyDBDAO();
			
			String sr_img =  db.getStudyroomImage(sr_idx);
			
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (sr_img != null)
			{
				System.out.println("["+ dayTime.format(new Date()) + "][AjaxController.java/getStudyroomImage] Image Path : " + request.getContextPath() + sr_img );
				out.println(request.getContextPath() + sr_img);
			}
			else
			{
				out.print("null");
			}
		}
		else if (subPath.equals("/getStudyroomLocation"))
		{
			String sr_idx = request.getParameter("sr_idx");
			StudyDBDAO db = new StudyDBDAO();
			
			String sr_loc =  db.getStudyroomLocation(sr_idx);
			
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (sr_loc != null)
			{
				out.println(sr_loc);
			}
			else
			{
				out.print("정보가 없습니다.");
			}
		}
		
		else if (subPath.equals("/getRoomAvailableTime"))
		{
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String sr_idx = request.getParameter("sr_idx");
			String rsch_date = request.getParameter("rsch_date");
			String room_idx_str = request.getParameter("room_idx");
			
			
			if (sr_idx == null || rsch_date == null || room_idx_str == null)
			{
				out.println("good");
				return;
			}
			
			int room_idx = Integer.parseInt(request.getParameter("room_idx"));
			
			System.out.println(sr_idx + " " + rsch_date + " " + room_idx);
			
			StudyDBDAO db = new StudyDBDAO();
			
			boolean[] availableTime = db.getRoomAvailableTime(sr_idx, rsch_date, room_idx);
			
			if (availableTime != null) {
				int i = 0;
				for (boolean temp : availableTime)
				{
					System.out.println(i + "시 : " + temp);
					i++;
					out.println(temp);
				}
			}
			else
			{
				out.println("error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

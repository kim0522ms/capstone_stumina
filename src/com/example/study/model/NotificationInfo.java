package com.example.study.model;

public class NotificationInfo {
	private String noti_idx;
	private String noti_text;
	private String noti_date;
	private int noti_read;
	private String user_idx;
	
	
	public int getNoti_read() {
		return noti_read;
	}
	public void setNoti_read(int noti_read) {
		this.noti_read = noti_read;
	}
	public String getNoti_idx() {
		return noti_idx;
	}
	public void setNoti_idx(String noti_idx) {
		this.noti_idx = noti_idx;
	}
	public String getNoti_text() {
		return noti_text;
	}
	public void setNoti_text(String noti_text) {
		this.noti_text = noti_text;
	}
	public String getNoti_date() {
		return noti_date;
	}
	public void setNoti_date(String noti_date) {
		this.noti_date = noti_date;
	}
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
}

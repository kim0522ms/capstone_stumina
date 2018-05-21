package com.example.study.model;

public class ScheduleBoardInfo {
	private String scb_idx;
	private String scb_title;
	private String scb_content;
	private String user_idx;
	private String user_name;
	private String user_belong;
	private String rsch_idx;
	private String profile_pic_idx;
	private String scb_time;
	private FileInfo fileInfo;
	
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public String getScb_time() {
		return scb_time;
	}
	public void setScb_time(String scb_time) {
		this.scb_time = scb_time;
	}
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getScb_idx() {
		return scb_idx;
	}
	public void setScb_idx(String scb_idx) {
		this.scb_idx = scb_idx;
	}
	public String getScb_title() {
		return scb_title;
	}
	public void setScb_title(String scb_title) {
		this.scb_title = scb_title;
	}
	public String getScb_content() {
		return scb_content;
	}
	public void setScb_content(String scb_content) {
		this.scb_content = scb_content;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_belong() {
		return user_belong;
	}
	public void setUser_belong(String user_belong) {
		this.user_belong = user_belong;
	}
	public String getRsch_idx() {
		return rsch_idx;
	}
	public void setRsch_idx(String rsch_idx) {
		this.rsch_idx = rsch_idx;
	}
	public String getProfile_pic_idx() {
		return profile_pic_idx;
	}
	public void setProfile_pic_idx(String profile_pic_idx) {
		this.profile_pic_idx = profile_pic_idx;
	}
}

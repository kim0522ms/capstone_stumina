package com.example.study.model;

public class AttandantInfo {
	private String user_idx;
	private String rsch_idx;
	private String user_name;
	private int absent;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getRsch_idx() {
		return rsch_idx;
	}
	public void setRsch_idx(String rsch_idx) {
		this.rsch_idx = rsch_idx;
	}
	public int getAbsent() {
		return absent;
	}
	public void setAbsent(int absent) {
		this.absent = absent;
	}
}

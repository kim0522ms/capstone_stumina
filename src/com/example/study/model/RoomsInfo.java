package com.example.study.model;

public class RoomsInfo {
	private String room_idx;
	private String studyroom_idx;
	private String room_pay;
	private String room_name;
	private String room_maxmember;
	
	public String getRoom_maxmember() {
		return room_maxmember;
	}
	public void setRoom_maxmember(String room_maxmember) {
		this.room_maxmember = room_maxmember;
	}
	public String getRoom_idx() {
		return room_idx;
	}
	public void setRoom_idx(String room_idx) {
		this.room_idx = room_idx;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getStudyroom_idx() {
		return studyroom_idx;
	}
	public void setStudyroom_idx(String studyroom_idx) {
		this.studyroom_idx = studyroom_idx;
	}
	public String getRoom_pay() {
		return room_pay;
	}
	public void setRoom_pay(String room_pay) {
		this.room_pay = room_pay;
	}
}

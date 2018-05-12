package com.example.study.model;

public class ScheduleInfo {
	private String schedule_idx;
	private String schedule_date;
	private String room_idx;
	private String room_name;
	private String room_pay;
	private String std_no;
	private String checkin;
	private String checkout;
	private String std_name;
	private String schedule_name;
	private String studyroom_name;
	private String studyroom_location;
	private String schedule_comment;
	
	public String getRoom_pay() {
		return room_pay;
	}
	public void setRoom_pay(String room_pay) {
		this.room_pay = room_pay;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getSchedule_comment() {
		return schedule_comment;
	}
	public void setSchedule_comment(String schedule_comment) {
		this.schedule_comment = schedule_comment;
	}
	public String getStudyroom_name() {
		return studyroom_name;
	}
	public void setStudyroom_name(String studyroom_name) {
		this.studyroom_name = studyroom_name;
	}
	public String getStudyroom_location() {
		return studyroom_location;
	}
	public void setStudyroom_location(String studyroom_location) {
		this.studyroom_location = studyroom_location;
	}
	public String getSchedule_name() {
		return schedule_name;
	}
	public void setSchedule_name(String schedule_name) {
		this.schedule_name = schedule_name;
	}
	public String getSchedule_idx() {
		return schedule_idx;
	}
	public void setSchedule_idx(String schedule_idx) {
		this.schedule_idx = schedule_idx;
	}
	public String getSchedule_date() {
		return schedule_date;
	}
	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}
	public String getRoom_idx() {
		return room_idx;
	}
	public void setRoom_idx(String room_idx) {
		this.room_idx = room_idx;
	}
	public String getStd_no() {
		return std_no;
	}
	public void setStd_no(String std_no) {
		this.std_no = std_no;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getStd_name() {
		return std_name;
	}
	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}
	
}

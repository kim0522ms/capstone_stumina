package com.example.study.model;

public class ScheduleInfo {
	private String schedule_idx;
	private String schedule_date;
	private String room_no;
	private String std_no;
	private String checkin;
	private String checkout;
	private String std_name;
	private String schedule_name;
		
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
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
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

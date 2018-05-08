package com.example.study.model;

public class StudyInfo {
	private String std_no;
	private String std_category;
	private String detail_idx;
	private String std_name;
	private String std_contents;
	private String std_leader;
	private String std_location;
	private String std_startDate;
	private String std_endDate;
	private String std_teacher;
	private String std_theme;
	public String getDetail_idx() {
		return detail_idx;
	}
	public void setDetail_idx(String detail_idx) {
		this.detail_idx = detail_idx;
	}
	private int std_maxMemberCount;
	private int std_remainMember;
	private int std_maxattcnt;
	private int std_endflag;
	
	public String getStd_theme() {
		return std_theme;
	}
	public void setStd_theme(String std_theme) {
		this.std_theme = std_theme;
	}	
	public int getStd_remainMember() {
		return std_remainMember;
	}
	public void setStd_remainMember(int std_remainMember) {
		this.std_remainMember = std_remainMember;
	}
	public int getStd_maxMemberCount() {
		return std_maxMemberCount;
	}
	public void setStd_maxMemberCount(int std_maxMemberCount) {
		this.std_maxMemberCount = std_maxMemberCount;
	}
	public String getStd_startDate() {
		return std_startDate;
	}
	public void setStd_startDate(String std_startDate) {
		this.std_startDate = std_startDate;
	}
	public String getStd_endDate() {
		return std_endDate;
	}
	public void setStd_endDate(String std_endDate) {
		this.std_endDate = std_endDate;
	}
	public int getStd_maxattcnt() {
		return std_maxattcnt;
	}
	public void setStd_maxattcnt(int std_maxattcnt) {
		this.std_maxattcnt = std_maxattcnt;
	}
	public int getStd_endflag() {
		return std_endflag;
	}
	public void setStd_endflag(int std_endflag) {
		this.std_endflag = std_endflag;
	}
	public String getStd_no() {
		return std_no;
	}
	public void setStd_no(String std_no) {
		this.std_no = std_no;
	}
	public String getStd_category() {
		return std_category;
	}
	public void setStd_category(String std_category) {
		this.std_category = std_category;
	}
	public String getStd_name() {
		return std_name;
	}
	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}
	public String getStd_contents() {
		return std_contents;
	}
	public void setStd_contents(String std_contents) {
		this.std_contents = std_contents;
	}
	public String getStd_leader() {
		return std_leader;
	}
	public void setStd_leader(String std_leader) {
		this.std_leader = std_leader;
	}
	public String getStd_location() {
		return std_location;
	}
	public void setStd_location(String std_location) {
		this.std_location = std_location;
	}
	public String getStd_teacher() {
		return std_teacher;
	}
	public void setStd_teacher(String std_teacher) {
		this.std_teacher = std_teacher;
	}
}

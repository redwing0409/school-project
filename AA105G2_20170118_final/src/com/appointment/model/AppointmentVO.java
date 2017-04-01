package com.appointment.model;
import java.sql.Date;

public class AppointmentVO implements java.io.Serializable{
	private String app_no;
	private String place_no;
	private String member_no;
	private Date app_date;
	private Date app_place_date;
	private Integer app_status;

	
	
	
	public String getApp_no() {
		return app_no;
	}
	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}
	public String getPlace_no() {
		return place_no;
	}
	public void setPlace_no(String place_no) {
		this.place_no = place_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public Date getApp_date() {
		return app_date;
	}
	public void setApp_date(Date app_date) {
		this.app_date = app_date;
	}
	public Date getApp_place_date() {
		return app_place_date;
	}
	public void setApp_place_date(Date app_place_date) {
		this.app_place_date = app_place_date;
	}
	public Integer getApp_status() {
		return app_status;
	}
	public void setApp_status(Integer app_status) {
		this.app_status = app_status;
	}
	
}
	
	
	

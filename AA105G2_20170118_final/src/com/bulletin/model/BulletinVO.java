package com.bulletin.model;

import java.sql.*;

public class BulletinVO implements java.io.Serializable  {
	private String bulletin_no;
	private String groups_no;
	private String member_no;
	private String bulletin_content;
	private Timestamp bulletin_time;
	private Integer bulletin_status;
	
	public String getBulletin_no() {
		return bulletin_no;
	}
	public void setBulletin_no(String bulletin_no) {
		this.bulletin_no = bulletin_no;
	}
	public String getGroups_no() {
		return groups_no;
	}
	public void setGroups_no(String groups_no) {
		this.groups_no = groups_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getBulletin_content() {
		return bulletin_content;
	}
	public void setBulletin_content(String bulletin_content) {
		this.bulletin_content = bulletin_content;
	}
	public Timestamp getBulletin_time() {
		return bulletin_time;
	}
	public void setBulletin_time(Timestamp bulletin_time) {
		this.bulletin_time = bulletin_time;
	}
	public Integer getBulletin_status() {
		return bulletin_status;
	}
	public void setBulletin_status(Integer bulletin_status) {
		this.bulletin_status = bulletin_status;
	}
	
	
}

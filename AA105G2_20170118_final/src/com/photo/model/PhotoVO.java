package com.photo.model;

import java.sql.*;

public class PhotoVO implements java.io.Serializable {
	private String photo_no;
	private String member_no;
	private String groups_no;
	private String photo_title;
	private byte[] photo_file;
	private Timestamp photo_createdate;
	private Integer photo_status;
	
	public String getPhoto_no() {
		return photo_no;
	}
	public void setPhoto_no(String photo_no) {
		this.photo_no = photo_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getGroups_no() {
		return groups_no;
	}
	public void setGroups_no(String groups_no) {
		this.groups_no = groups_no;
	}
	public String getPhoto_title() {
		return photo_title;
	}
	public void setPhoto_title(String photo_title) {
		this.photo_title = photo_title;
	}
	public byte[] getPhoto_file() {
		return photo_file;
	}
	public void setPhoto_file(byte[] photo_file) {
		this.photo_file = photo_file;
	}
	public Timestamp getPhoto_createdate() {
		return photo_createdate;
	}
	public void setPhoto_createdate(Timestamp photo_createdate) {
		this.photo_createdate = photo_createdate;
	}
	public Integer getPhoto_status() {
		return photo_status;
	}
	public void setPhoto_status(Integer photo_status) {
		this.photo_status = photo_status;
	}
	
}

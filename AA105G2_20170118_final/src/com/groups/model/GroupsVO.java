package com.groups.model;

import java.sql.Date;

public class GroupsVO implements java.io.Serializable   {
	private String groups_no;
	private String groups_owner;
	private String groups_title;
	private Date groups_time;
	private Integer groups_status;
	
	public String getGroups_no() {
		return groups_no;
	}
	public void setGroups_no(String groups_no) {
		this.groups_no = groups_no;
	}
	public String getGroups_owner() {
		return groups_owner;
	}
	public void setGroups_owner(String groups_owner) {
		this.groups_owner = groups_owner;
	}
	public String getGroups_title() {
		return groups_title;
	}
	public void setGroups_title(String groups_title) {
		this.groups_title = groups_title;
	}
	public Date getGroups_time() {
		return groups_time;
	}
	public void setGroups_time(Date groups_time) {
		this.groups_time = groups_time;
	}
	public Integer getGroups_status() {
		return groups_status;
	}
	public void setGroups_status(Integer groups_status) {
		this.groups_status = groups_status;
	}
	
}

package com.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable  {
	private String member_no;
	private String member_acc;
	private String member_pw;
	private String member_name;
	private String member_addr;
	private String member_email;
	private String member_mobile;
	private Integer member_sex;
	private Date member_birthday;
	private Date enroll_time;
	private byte[] member_pic;
	
	
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getMember_acc() {
		return member_acc;
	}
	public void setMember_acc(String member_acc) {
		this.member_acc = member_acc;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_addr() {
		return member_addr;
	}
	public void setMember_addr(String member_addr) {
		this.member_addr = member_addr;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_mobile() {
		return member_mobile;
	}
	public void setMember_mobile(String member_mobile) {
		this.member_mobile = member_mobile;
	}
	public Integer getMember_sex() {
		return member_sex;
	}
	public void setMember_sex(Integer member_sex) {
		this.member_sex = member_sex;
	}
	public Date getMember_birthday() {
		return member_birthday;
	}
	public void setMember_birthday(Date member_birthday) {
		this.member_birthday = member_birthday;
	}
	public Date getEnroll_time() {
		return enroll_time;
	}
	public void setEnroll_time(Date enroll_time) {
		this.enroll_time = enroll_time;
	}
	public byte[] getMember_pic() {
		return member_pic;
	}
	public void setMember_pic(byte[] member_pic) {
		this.member_pic = member_pic;
	}
	
}

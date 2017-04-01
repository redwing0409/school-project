package com.purchase_order.model;

import java.sql.Timestamp;

public class PurVO implements java.io.Serializable{
	private String pur_no;
	private String member_no;
	private Timestamp pur_date;
	private Timestamp pur_money;
	private Timestamp pur_product;
	private Timestamp pur_close;
	private Integer pur_status;
	private Integer pur_sum;
	private String pur_name;
	private String pur_add;
	private String pur_tel;
	private String pur_memo;
	
	public String getPur_no() {
		return pur_no;
	}
	public void setPur_no(String pur_no) {
		this.pur_no = pur_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public Timestamp getPur_date() {
		return pur_date;
	}
	public void setPur_date(Timestamp pur_date) {
		this.pur_date = pur_date;
	}
	public Timestamp getPur_money() {
		return pur_money;
	}
	public void setPur_money(Timestamp pur_money) {
		this.pur_money = pur_money;
	}
	public Timestamp getPur_product() {
		return pur_product;
	}
	public void setPur_product(Timestamp pur_product) {
		this.pur_product = pur_product;
	}
	public Timestamp getPur_close() {
		return pur_close;
	}
	public void setPur_close(Timestamp pur_close) {
		this.pur_close = pur_close;
	}
	public Integer getPur_status() {
		return pur_status;
	}
	public void setPur_status(Integer pur_status) {
		this.pur_status = pur_status;
	}
	public Integer getPur_sum() {
		return pur_sum;
	}
	public void setPur_sum(Integer pur_sum) {
		this.pur_sum = pur_sum;
	}
	public String getPur_name() {
		return pur_name;
	}
	public void setPur_name(String pur_name) {
		this.pur_name = pur_name;
	}
	public String getPur_add() {
		return pur_add;
	}
	public void setPur_add(String pur_add) {
		this.pur_add = pur_add;
	}
	public String getPur_tel() {
		return pur_tel;
	}
	public void setPur_tel(String pur_tel) {
		this.pur_tel = pur_tel;
	}
	public String getPur_memo() {
		return pur_memo;
	}
	public void setPur_memo(String pur_memo) {
		this.pur_memo = pur_memo;
	}

}

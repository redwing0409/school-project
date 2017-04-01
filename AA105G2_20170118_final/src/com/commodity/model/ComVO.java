package com.commodity.model;

import java.sql.Timestamp;

public class ComVO implements java.io.Serializable{
	private String com_no;
	private String sup_no;
	private Integer pcm_no;
	private String com_name;
	private String com_desc;
	private Integer com_price;
	private Integer com_status;
	private Timestamp com_shelf_date;
	private Timestamp com_off_date;
	private String com_note;
    private byte[] com_pic;
	
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public String getSup_no() {
		return sup_no;
	}
	public void setSup_no(String sup_no) {
		this.sup_no = sup_no;
	}
	public Integer getPcm_no() {
		return pcm_no;
	}
	public void setPcm_no(Integer pcm_no) {
		this.pcm_no = pcm_no;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_desc() {
		return com_desc;
	}
	public void setCom_desc(String com_desc) {
		this.com_desc = com_desc;
	}
	public Integer getCom_price() {
		return com_price;
	}
	public void setCom_price(Integer com_price) {
		this.com_price = com_price;
	}
	public Integer getCom_status() {
		return com_status;
	}
	public void setCom_status(Integer com_status) {
		this.com_status = com_status;
	}
	public Timestamp getCom_shelf_date() {
		return com_shelf_date;
	}
	public void setCom_shelf_date(Timestamp com_shelf_date) {
		this.com_shelf_date = com_shelf_date;
	}
	public Timestamp getCom_off_date() {
		return com_off_date;
	}
	public void setCom_off_date(Timestamp com_off_date) {
		this.com_off_date = com_off_date;
	}
	public String getCom_note() {
		return com_note;
	}
	public void setCom_note(String com_note) {
		this.com_note = com_note;
	}
	public byte[] getCom_pic() {
		return com_pic;
	}
	public void setCom_pic(byte[] com_pic) {
		this.com_pic = com_pic;
	}

}


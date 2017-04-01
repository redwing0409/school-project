package com.adv.model;

public class AdvVO implements java.io.Serializable{
	private String adv_no;
	private String sup_no;
	private byte[]adv_pic;
	private String adv_note;
	public String getAdv_no() {
		return adv_no;
	}
	public void setAdv_no(String adv_no) {
		this.adv_no = adv_no;
	}
	public String getSup_no() {
		return sup_no;
	}
	public void setSup_no(String sup_no) {
		this.sup_no = sup_no;
	}
	public byte[] getAdv_pic() {
		return adv_pic;
	}
	public void setAdv_pic(byte[] adv_pic) {
		this.adv_pic = adv_pic;
	}
	public String getAdv_note() {
		return adv_note;
	}
	public void setAdv_note(String adv_note) {
		this.adv_note = adv_note;
	}
	
	
	   
}
package com.menu.model;
import java.sql.Date;

public class MenuVO implements java.io.Serializable{
	private String menu_no;
	private String place_no;
	private String menu_name;
	private String menu_note;
	private byte[] menu_pic;
	private Integer menu_price;
	public String getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(String menu_no) {
		this.menu_no = menu_no;
	}
	public String getPlace_no() {
		return place_no;
	}
	public void setPlace_no(String place_no) {
		this.place_no = place_no;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_note() {
		return menu_note;
	}
	public void setMenu_note(String menu_note) {
		this.menu_note = menu_note;
	}
	public byte[] getMenu_pic() {
		return menu_pic;
	}
	public void setMenu_pic(byte[] menu_pic) {
		this.menu_pic = menu_pic;
	}
	public Integer getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(Integer menu_price) {
		this.menu_price = menu_price;
	}
	
	
}
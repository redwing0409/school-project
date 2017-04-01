package com.order_item.model;

public class OrdVO {
	private String pur_no;
	private String com_no;
	private Integer ord_price;
	private Integer ord_qty;
	private Integer return_qty;
	private Integer ship_status;
	
	public String getPur_no() {
		return pur_no;
	}
	public void setPur_no(String pur_no) {
		this.pur_no = pur_no;
	}
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public Integer getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(Integer ord_price) {
		this.ord_price = ord_price;
	}
	public Integer getOrd_qty() {
		return ord_qty;
	}
	public void setOrd_qty(Integer ord_qty) {
		this.ord_qty = ord_qty;
	}
	public Integer getReturn_qty() {
		return return_qty;
	}
	public void setReturn_qty(Integer return_qty) {
		this.return_qty = return_qty;
	}
	public Integer getShip_status() {
		return ship_status;
	}
	public void setShip_status(Integer ship_status) {
		this.ship_status = ship_status;
	}
	
}

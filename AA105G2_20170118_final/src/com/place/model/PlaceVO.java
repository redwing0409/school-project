package com.place.model;
import java.sql.Date;

public class PlaceVO implements java.io.Serializable{
	private String place_no;
	private String sup_no;
	private Integer place_type;
	private String place_name;
	private String place_area;
	private String place_adds;
	private byte[] place_pic;
	private String place_note;
	private Integer place_status;
	
	
	public String getPlace_no() {
		return place_no;
	}
	public void setPlace_no(String place_no) {
		this.place_no = place_no;
	}
	public String getSup_no() {
		return sup_no;
	}
	public void setSup_no(String sup_no) {
		this.sup_no = sup_no;
	}
	public Integer getPlace_type() {
		return place_type;
	}
	public void setPlace_type(Integer place_type) {
		this.place_type = place_type;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getPlace_area() {
		return place_area;
	}
	public void setPlace_area(String place_area) {
		this.place_area = place_area;
	}
	public String getPlace_adds() {
		return place_adds;
	}
	public void setPlace_adds(String place_adds) {
		this.place_adds = place_adds;
	}
	public byte[] getPlace_pic() {
		return place_pic;
	}
	public void setPlace_pic(byte[] place_pic) {
		this.place_pic = place_pic;
	}
	public String getPlace_note() {
		return place_note;
	}
	public void setPlace_note(String place_note) {
		this.place_note = place_note;
	}
	public Integer getPlace_status() {
		return place_status;
	}
	public void setPlace_status(Integer place_status) {
		this.place_status = place_status;
	}
    public boolean equals(Object obj) {
    	if (this == obj) return true;                     //●1. 仍要使用==來判斷,因為(也許)要比較的"物件參考變數",其實指的是同一個實體(指向同一個記憶體空間)
    	if(obj != null && getClass() == obj.getClass()) { //●2. 用(Object類別的)getClass()來確定是否隸屬於同一個class的物件
    		if(obj instanceof PlaceVO) {
    			PlaceVO e = (PlaceVO)obj;
                if (place_no.equals(e.place_no)) {  //●3. 選擇該類別的必要成員變數(實體變數)來加以判斷是否有相等(相同)
                    return true;
                }
        }
    	}	    	

    	return false;
    }
    
    public int hashCode(){
        return this.place_no.hashCode();               //●借用一下 String 類別現有的hashCode運算法則
   	  //return new Integer(this.empno).hashCode();  //●或借用一下 Integer 類別現有的hashCode運算法則
    	
    }
	
	
	
	
	

	
	
	
	
}
	
	
	

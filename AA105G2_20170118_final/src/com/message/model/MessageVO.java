package com.message.model;
import java.util.Date;

import java.sql.Timestamp;

public class MessageVO {
	private String message_no;
	private String sender_no;
	private String recipient_no;
	private Timestamp   message_time;
	private String message_extname;
	private String message_content;
	private Integer message_visibility;
	
	
	public String getMessage_no() {
		return message_no;
	}
	public void setMessage_no(String message_no) {
		this.message_no = message_no;
	}
	public String getSender_no() {
		return sender_no;
	}
	public void setSender_no(String sender_no) {
		this.sender_no = sender_no;
	}
	public String getRecipient_no() {
		return recipient_no;
	}
	public void setRecipient_no(String recipient_no) {
		this.recipient_no = recipient_no;
	}
	public Timestamp getMessage_time() {
		return message_time;
	}
	public void setMessage_time(Timestamp message_time) {
		this.message_time = message_time;
	}
	public String getMessage_extname() {
		return message_extname;
	}
	public void setMessage_extname(String message_extname) {
		this.message_extname = message_extname;
	}
	
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public Integer getMessage_visibility() {
		return message_visibility;
	}
	public void setMessage_visibility(Integer message_visibility) {
		this.message_visibility = message_visibility;
	}
	
}

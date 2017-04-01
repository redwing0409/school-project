package com.response.model;

import java.sql.Timestamp;

public class ResponseVO {
	private String response_no;
	private String article_no;
	private String member_no;
	private String response_content;
	private Timestamp response_time;
	
	
	public String getResponse_no() {
		return response_no;
	}
	public void setResponse_no(String response_no) {
		this.response_no = response_no;
	}
	public String getArticle_no() {
		return article_no;
	}
	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getResponse_content() {
		return response_content;
	}
	public void setResponse_content(String response_content) {
		this.response_content = response_content;
	}
	public Timestamp getResponse_time() {
		return response_time;
	}
	public void setResponse_time(Timestamp response_time) {
		this.response_time = response_time;
	}


}

package com.article.model;

import java.sql.Timestamp;


public class ArticleVO implements java.io.Serializable{
	private String article_no;
	private String member_no;
	private String article_title;
	private String article_content;
	private Integer article_views;
	private Timestamp article_time;
	private Integer article_status;
	
	
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
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public Integer getArticle_views() {
		return article_views;
	}
	public void setArticle_views(Integer article_views) {
		this.article_views = article_views;
	}
	public Timestamp getArticle_time() {
		return article_time;
	}
	public void setArticle_time(Timestamp article_time) {
		this.article_time = article_time;
	}
	public Integer getArticle_status() {
		return article_status;
	}
	public void setArticle_status(Integer article_status) {
		this.article_status = article_status;
	}
	
}

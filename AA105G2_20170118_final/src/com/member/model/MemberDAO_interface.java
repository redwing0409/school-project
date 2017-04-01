package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public MemberVO findByPrimaryKey(String member_no);
	public List<MemberVO> getAll();
	//for APP
	public String findByACCOUNT_NAME(String member_acc, String member_pw);
	
	public MemberVO findByPrimaryKeyForPicture(String member_no);
	
	public MemberVO findByAccountPassword(String member_acc, String member_pw);
	
}

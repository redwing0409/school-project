package com.member.model;

import java.io.*;
import java.sql.Date;
import java.util.*;


public class MemberService {
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMember(String member_acc, String member_pw, String member_name, String member_addr, 
			String member_email, String member_mobile, Integer member_sex, Date member_birthday, Date enroll_time,
			byte[] member_pic){

		
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_acc(member_acc);
		memberVO.setMember_pw(member_pw);
		memberVO.setMember_name(member_name);
		memberVO.setMember_addr(member_addr);
		memberVO.setMember_email(member_email);
		memberVO.setMember_mobile(member_mobile);
		memberVO.setMember_sex(member_sex);
		memberVO.setMember_birthday(member_birthday);
		memberVO.setEnroll_time(enroll_time);
		memberVO.setMember_pic(member_pic);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(String member_no, String member_acc, String member_pw, String member_name, String member_addr, 
			String member_email, String member_mobile, Integer member_sex, Date member_birthday, Date enroll_time,
			byte[] member_pic) {
		

		MemberVO memberVO = new MemberVO();
		memberVO.setMember_no(member_no);
		memberVO.setMember_acc(member_acc);
		memberVO.setMember_pw(member_pw);
		memberVO.setMember_name(member_name);
		memberVO.setMember_addr(member_addr);
		memberVO.setMember_email(member_email);
		memberVO.setMember_mobile(member_mobile);
		memberVO.setMember_sex(member_sex);
		memberVO.setMember_birthday(member_birthday);
		memberVO.setEnroll_time(enroll_time);
		memberVO.setMember_pic(member_pic);
		dao.update(memberVO);
		
		return memberVO;
	}

	

	public MemberVO getOneMember(String member_no) {
		return dao.findByPrimaryKey(member_no);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}

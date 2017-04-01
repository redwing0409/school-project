package com.member.controller;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;


@WebServlet("/member/ShowMember_Pic.do")
public class ShowMember_Pic extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try {
			String member_no = req.getParameter("member_no");
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(member_no);
				
			byte[] buffer = memberVO.getMember_pic();
			out.write(buffer);
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/nodata/nopic.gif");
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			in.close();
		} 
	}

}

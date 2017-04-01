package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.groups.model.GroupsService;
import com.member.model.*;

@WebServlet("/member/memberServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer member_no = null;
				try {
					member_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(str);
				if (memberVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("listOneMember", memberVO); // ��Ʈw���X��memberVO����,�s�Jreq
				String url = "/front_end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMember.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllMember.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String member_no = req.getParameter("member_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(member_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("update_member_input", memberVO);         // ��Ʈw���X��memberVO����,�s�Jreq
				String url = "/front_end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_member_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//getOne_For_Update
		
		
		if ("update".equals(action)) { // �Ӧ�update_member_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsFromUpdate", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String member_no = req.getParameter("member_no");
				String member_acc = req.getParameter("member_acc");
				String member_pw = req.getParameter("member_pw");
				String member_name = req.getParameter("member_name");
				String member_addr = req.getParameter("member_addr");
				String member_email = req.getParameter("member_email");
				String member_mobile = req.getParameter("member_mobile");
				Integer member_sex = new Integer(req.getParameter("member_sex"));
				
				MemberService memberSvc = new MemberService();
				
				Part part = req.getPart("member_pic");
				byte[] member_pic = null;
				
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {	
					InputStream in = part.getInputStream();
					member_pic = new byte[in.available()];
					in.read(member_pic);
					in.close();
				} else {
					member_pic = memberSvc.getOneMember(member_no).getMember_pic();
				}	
				
				java.sql.Date member_birthday = null;
				try {
					member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
				} catch (IllegalArgumentException e) {
					member_birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				java.sql.Date enroll_time = null;
				try {
					enroll_time = java.sql.Date.valueOf(req.getParameter("enroll_time").trim());
				} catch (IllegalArgumentException e) {
					enroll_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("/front_end/updateMemberInfo.jsp", memberVO); // �t����J�榡���~��memberVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/updateMemberInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				memberVO = memberSvc.updateMember(member_no, member_acc, member_pw, member_name, member_addr, 
						member_email, member_mobile, member_sex, member_birthday, enroll_time,
						member_pic);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("/front_end/frontIndex.jsp", memberVO); // ��Ʈwupdate���\��,���T����memberVO����,�s�Jreq
				if(requestURL.equals("/front_end/updateMemberInfo.jsp")){
					requestURL = "/front_end/frontIndex.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/updateMemberInfo.jsp");
				failureView.forward(req, res);
			} 
		}//Update
		
		if ("insert".equals(action)) { // �Ӧ�addMember.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsFromInsert", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				MemberService memberSvc = new MemberService();
				List<MemberVO> list1 = memberSvc.getAll();
				
				String member_acc = req.getParameter("member_acc");
				if (member_acc == null || (member_acc.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���b��");
				} 
				for(MemberVO m:list1){
					 if(member_acc.equals(m.getMember_acc())){
						 errorMsgs.add("�b���w�s�b");
					 }
				}
				String member_pw = req.getParameter("member_pw");
				if (member_pw == null || (member_pw.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���K�X");
				}
				String member_name = req.getParameter("member_name");
				if (member_name == null || (member_name.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���W��");
				} else if (!member_name.matches("[^0-9]+")){
					errorMsgs.add("�m�W�ФŨϥμƦr");
				}
				String member_addr = req.getParameter("member_addr");
				if (member_addr == null || (member_addr.trim()).length() == 0) {
					errorMsgs.add("�п�J��}");
				}
				String member_email = req.getParameter("member_email");
				if (member_email == null || (member_email.trim()).length() == 0) {
					errorMsgs.add("�п�JE-mail");
				} else if (!member_email.matches("(.+\\@.+)")){
					errorMsgs.add("E-mail�榡���ŦX");
				}
				String member_mobile = req.getParameter("member_mobile");
				if (member_mobile == null || (member_mobile.trim()).length() == 0) {
					errorMsgs.add("�п�J�q�ܸ��X");
				} else if (!member_mobile.matches("[0-9]*")){
					errorMsgs.add("�q�ܸ��X�п�J�Ʀr");
				}
				Integer member_sex = new Integer(req.getParameter("member_sex"));
				if (member_sex.equals(null)) {
					errorMsgs.add("�п�ܩʧO");
				}
//				byte[] member_pic = null;
//				Collection<Part> parts = req.getParts();
//				for (Part part : parts) {
//					if(part.getContentType() != null){
//						InputStream in = part.getInputStream();
//						member_pic = new byte[in.available()];
//						in.read(member_pic);
//					}
//				}
				
				byte[] member_pic = null;
				Part part = req.getPart("member_pic");
				InputStream in = part.getInputStream();
				member_pic = new byte[in.available()];
				in.read(member_pic);
				in.close();
				
				java.sql.Date member_birthday = null;
				try {
					member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
				} catch (IllegalArgumentException e) {
					member_birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�ͤ�!");
				}
				
				java.sql.Date enroll_time = null;
				try {
					enroll_time = java.sql.Date.valueOf(req.getParameter("enroll_time").trim());
				} catch (IllegalArgumentException e) {
					enroll_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("/front_end/frontIndex.jsp", memberVO); // �t����J�榡���~��memberVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member_keyin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				
				memberVO = memberSvc.addMember(member_acc, member_pw, member_name, member_addr, 
						member_email, member_mobile, member_sex, member_birthday, enroll_time,
						member_pic);
				
				List<MemberVO> list2 = new MemberService().getAll();
				MemberVO vo = list2.get(list2.size()-1);
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/front_end/member/listAllMember.jsp?whichPage=50&member_no="+vo.getMember_no();
				if(requestURL.equals("/front_end/member_keyin.jsp")){
					requestURL = "/front_end/frontIndex.jsp";
					req.getSession().setAttribute("account", vo);
					req.getSession().setAttribute("addViews", true);
				}
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �s�W���\�����listAllMember.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/member_keyin.jsp");
				failureView.forward(req, res);
			} 
		}//insert
		
		if ("logOut".equals(action)) {
			req.getSession().removeAttribute("account");
			//String requestURL = req.getParameter("requestURL");
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/frontIndex.jsp"); 
			successView.forward(req, res);				
		}
		
	}//doPost
	
	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}

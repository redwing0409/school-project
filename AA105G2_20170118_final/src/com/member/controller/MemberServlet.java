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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer member_no = null;
				try {
					member_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(str);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listOneMember", memberVO); // 資料庫取出的memberVO物件,存入req
				String url = "/front_end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				String member_no = req.getParameter("member_no");
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(member_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("update_member_input", memberVO);         // 資料庫取出的memberVO物件,存入req
				String url = "/front_end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//getOne_For_Update
		
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsFromUpdate", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
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
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date enroll_time = null;
				try {
					enroll_time = java.sql.Date.valueOf(req.getParameter("enroll_time").trim());
				} catch (IllegalArgumentException e) {
					enroll_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("/front_end/updateMemberInfo.jsp", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/updateMemberInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				memberVO = memberSvc.updateMember(member_no, member_acc, member_pw, member_name, member_addr, 
						member_email, member_mobile, member_sex, member_birthday, enroll_time,
						member_pic);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("/front_end/frontIndex.jsp", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				if(requestURL.equals("/front_end/updateMemberInfo.jsp")){
					requestURL = "/front_end/frontIndex.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/updateMemberInfo.jsp");
				failureView.forward(req, res);
			} 
		}//Update
		
		if ("insert".equals(action)) { // 來自addMember.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsFromInsert", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				MemberService memberSvc = new MemberService();
				List<MemberVO> list1 = memberSvc.getAll();
				
				String member_acc = req.getParameter("member_acc");
				if (member_acc == null || (member_acc.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號");
				} 
				for(MemberVO m:list1){
					 if(member_acc.equals(m.getMember_acc())){
						 errorMsgs.add("帳號已存在");
					 }
				}
				String member_pw = req.getParameter("member_pw");
				if (member_pw == null || (member_pw.trim()).length() == 0) {
					errorMsgs.add("請輸入會員密碼");
				}
				String member_name = req.getParameter("member_name");
				if (member_name == null || (member_name.trim()).length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				} else if (!member_name.matches("[^0-9]+")){
					errorMsgs.add("姓名請勿使用數字");
				}
				String member_addr = req.getParameter("member_addr");
				if (member_addr == null || (member_addr.trim()).length() == 0) {
					errorMsgs.add("請輸入住址");
				}
				String member_email = req.getParameter("member_email");
				if (member_email == null || (member_email.trim()).length() == 0) {
					errorMsgs.add("請輸入E-mail");
				} else if (!member_email.matches("(.+\\@.+)")){
					errorMsgs.add("E-mail格式不符合");
				}
				String member_mobile = req.getParameter("member_mobile");
				if (member_mobile == null || (member_mobile.trim()).length() == 0) {
					errorMsgs.add("請輸入電話號碼");
				} else if (!member_mobile.matches("[0-9]*")){
					errorMsgs.add("電話號碼請輸入數字");
				}
				Integer member_sex = new Integer(req.getParameter("member_sex"));
				if (member_sex.equals(null)) {
					errorMsgs.add("請選擇性別");
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
					errorMsgs.add("請輸入生日!");
				}
				
				java.sql.Date enroll_time = null;
				try {
					enroll_time = java.sql.Date.valueOf(req.getParameter("enroll_time").trim());
				} catch (IllegalArgumentException e) {
					enroll_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("/front_end/frontIndex.jsp", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member_keyin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				memberVO = memberSvc.addMember(member_acc, member_pw, member_name, member_addr, 
						member_email, member_mobile, member_sex, member_birthday, enroll_time,
						member_pic);
				
				List<MemberVO> list2 = new MemberService().getAll();
				MemberVO vo = list2.get(list2.size()-1);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front_end/member/listAllMember.jsp?whichPage=50&member_no="+vo.getMember_no();
				if(requestURL.equals("/front_end/member_keyin.jsp")){
					requestURL = "/front_end/frontIndex.jsp";
					req.getSession().setAttribute("account", vo);
					req.getSession().setAttribute("addViews", true);
				}
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
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
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}

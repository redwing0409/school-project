package com.friend.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.friend.model.*;
import com.member.model.*;


public class FriendServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
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
							.getRequestDispatcher("/front_end/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String member_no = null;
				try {
					new Integer(str);
					member_no=str;
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FriendService friendSvc = new FriendService();
				List<FriendVO> list = friendSvc.getOneFriend(member_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/friend/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的list物件,存入req
				String url = "/front_end/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFriend.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/friend/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		

        if ("insert".equals(action)) { // 來自addFriend.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				MemberService memSvc=new MemberService();
				List<MemberVO> list = memSvc.getAll();
				MemberVO memberVO=null;
				
				String member_no = req.getParameter("member_no");//登入的會員帳號
				String member_acc = req.getParameter("member_acc");	
				
				
				boolean self=false;
				if(member_acc==null) errorMsgs.add("帳號輸入錯誤");
				else{
					for(MemberVO mem:list){						
							if(mem.getMember_acc().equals(member_acc)&&!(self=mem.getMember_no().equals(member_no))){
								memberVO=mem;//要新增的好友會員
							}
					}
					if(memberVO==null){
						if(self){
							errorMsgs.add("不能加自己為好友");
						}
						else errorMsgs.add("查無此帳號");
					}
				}
				MemberVO memberVOer = new MemberVO();
				memberVOer.setMember_acc(member_acc);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVOer); // 含有輸入格式錯誤的friendVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FriendService friendSvc = new FriendService();
				int updateCount = friendSvc.addFriend(member_no,memberVO.getMember_no());
				List<FriendVO> Friendlist = friendSvc.getOneFriend(member_no);
				req.setAttribute("list", Friendlist);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllArticle.jsp

			
			//不能刪除文章
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數***************************************/
				String member_no = req.getParameter("member_no");
				String friend_no = req.getParameter("friend_no");
				
				/***************************2.開始刪除資料***************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(member_no,friend_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		
	}

}

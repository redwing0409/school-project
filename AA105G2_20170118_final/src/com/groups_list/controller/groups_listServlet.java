package com.groups_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.groups.model.*;
import com.groups_list.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/groups_list/groups_listServlet.do")
public class groups_listServlet extends HttpServlet {
       

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
			
			GroupsService groupsSvc = new GroupsService();
			List<GroupsVO> gList = groupsSvc.getAllBack();
			
			Groups_listService groups_listSvc = new Groups_listService();
			String url = null;
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				/***************************2.開始查詢資料*****************************************/
				String groups_no = req.getParameter("groups_no");
				String member_no = req.getParameter("member_no");
				if (member_no == null) {
//					for(GroupsVO g:gList){
//						if(g.getGroups_no().equals(groups_no) && g.getGroups_status().equals(0)){
//							errorMsgs.add("---查無資料---");
//						}
//					}
					List<Groups_ListVO> listByGroups = groups_listSvc.getOneByGroupsNo(groups_no);
					if (listByGroups.size() == 0) {
						errorMsgs.add("查無資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					req.setAttribute("listOneByGroupsNo", listByGroups); // 資料庫取出的List<Groups_ListVO>物件,存入req
					url = "/front_end/groups_list/listOneByGroupsNo.jsp";
				}
				if (groups_no == null) {
					List<Groups_ListVO> listByMember = groups_listSvc.getOneByMemberNo(member_no);
					if (listByMember.size() == 0) {
						errorMsgs.add("查無資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					req.setAttribute("listOneByMemberNo", listByMember); // 資料庫取出的List<Groups_ListVO>物件,存入req
					url = "/front_end/groups_list/listOneByMemberNo.jsp";
				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneByGroupsNo.jsp或listOneByMemberNo.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		if ("insert".equals(action)) { // 來自addGroups_list.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String groups_no = req.getParameter("groups_no").trim();
				String member_no = req.getParameter("member_no").trim();
				String requestURL = req.getParameter("requestURL");
				System.out.println(requestURL);
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				Groups_ListVO groups_ListVO = new Groups_ListVO();
				groups_ListVO.setGroups_no(groups_no);
				groups_ListVO.setMember_no(member_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groups_ListVO", groups_ListVO); // 含有輸入格式錯誤的groups_ListVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups_list/addGroups_list.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Groups_listService groups_listSvc = new Groups_listService();
				groups_ListVO = groups_listSvc.addGroups_list(groups_no, member_no);
//				req.setAttribute("listOneByGroupsNo",groups_listSvc.getOneByGroupsNo(groups_no));
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front_end/groups_list/listOneByGroupsNo.jsp?member_no=" + member_no;
//				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 新增成功後轉交listAllGroups_list_Front.jsp
//				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups_list/addGroups_list.jsp");
				failureView.forward(req, res);
			}
		}//insert
		
		if ("delete".equals(action)) { // 來自/groups/listAllGroups_Front.jsp 或  /groups/listAllGroups_Front.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為/groups/listAllGroups_Front.jsp 或  /groups/listAllGroups_Front.jsp
//			System.out.println(requestURL);
			try {
				/***************************1.接收請求參數***************************************/
				String groups_no = req.getParameter("groups_no");
				String member_no = req.getParameter("member_no");
				/***************************2.開始刪除資料***************************************/
				Groups_listService groups_listSvc = new Groups_listService();
				GroupsService groupsSvc = new GroupsService();
				groups_listSvc.deleteGroups_list(groups_no,member_no);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				if(requestURL.equals("/front_end/groups/listAllGroups_Front.jsp")||requestURL.equals("/front_end/groups_list/listOneByGroupsNo.jsp")){
//					req.setAttribute("listOneByGroupsNo",groups_listSvc.getOneByGroupsNo(groups_no));
//				}
				req.setAttribute("listOneByGroupsNo",groups_listSvc.getOneByGroupsNo(groups_no));
				req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				
				if(req.getParameter("leaveGroupsButton") != null){
					requestURL = "/front_end/groups/groupsIndex.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//delete
		
		if ("findMemberNOByGroupsNO".equals(action)) { // 來自addGroups_list.jsp的請求
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			GroupsService groupsSvc = new GroupsService();
			List<GroupsVO> list = groupsSvc.getAllBack();
			
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String groups_no = req.getParameter("groups_no");
				
				for(GroupsVO g:list){
					if(g.getGroups_no().equals(groups_no) && g.getGroups_status().equals(0)){
						errorMsgs.add("---查無資料---");
					}
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					out.println("<font color='red'>請修正以下錯誤:");
					out.println("<ul>");
					for(String message:errorMsgs){
						out.println("<li>" + message + "</li>");
					}
					out.println("</ul>");
					out.println("</font>");
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Groups_listService groups_listSvc = new Groups_listService();
				List<Groups_ListVO> list1 = groups_listSvc.getOneByGroupsNo(groups_no); //成員
				GroupsVO groupsVO = groupsSvc.getOneGroups(groups_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				MemberService memberSvc = new MemberService();
				List<MemberVO> memberVO_list = memberSvc.getAll();
				List<MemberVO> memberVO_list1 = new ArrayList<MemberVO>();
				JSONObject jsonObj = new JSONObject();
				
				for(MemberVO g:memberVO_list){
					boolean isSame = true;
					if(list1.size() == 0){ 
						if(groupsVO.getGroups_owner().equals(g.getMember_no())){
							isSame = false;
						}
					} else {
						for(Groups_ListVO g_list:list1){
							if(g_list.getMember_no().equals(g.getMember_no())||groupsVO.getGroups_owner().equals(g.getMember_no())){
								isSame = false;
							}
						}
					}
					if(isSame){
						memberVO_list1.add(g);
						jsonObj.put(g.getMember_no(), g.getMember_name());
					}
				}
				out.println(jsonObj);
				out.flush();
				out.close();
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}//findMemberNOByGroupsNO
	}

}

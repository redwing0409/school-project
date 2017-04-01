package com.groups.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bulletin.model.BulletinService;
import com.bulletin.model.BulletinVO;
import com.groups.model.*;
import com.groups_list.model.*;
import com.member.model.*;

@WebServlet("/groups/groupsServlet.do")
public class GroupsServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = null;
			System.out.println("test");
			if(req.getParameter("requestURL") != null){
				requestURL = req.getParameter("requestURL");
			}
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("groups_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入社群編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer groups_no = null;
				try {
					groups_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("社群編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				GroupsService groupsSvc = new GroupsService();
				GroupsVO groupsVO = groupsSvc.getOneGroups(str);
				if (groupsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listOneGroups_Front", groupsVO); // 資料庫取出的groupsVO物件,存入req
//				String url = "/front_end/groups/listOneGroups_Front.jsp";
				String url = "/front_end/groups/groupsContent.jsp";
				
//				if(requestURL!=null && requestURL.equals("/front_end/groups/GroupsIndex.jsp")){
//					url = "/front_end/groups/GroupsIndex.jsp";
//				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroups_Front.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllGroups.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				String groups_no = req.getParameter("groups_no");
				
				/***************************2.開始查詢資料****************************************/
				GroupsService groupsSvc = new GroupsService();
				GroupsVO groupsVO = groupsSvc.getOneGroups(groups_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("update_groups_input", groupsVO);         // 資料庫取出的groupsVO物件,存入req
				String url = "/front_end/groups/update_groups_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_groups_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//getOne_For_Update
		
		if ("update".equals(action)) { // 來自update_groups_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String groups_no = req.getParameter("groups_no");
				String groups_owner = req.getParameter("groups_owner");
				String groups_title = req.getParameter("groups_title");
				java.sql.Date groups_time = java.sql.Date.valueOf(req.getParameter("groups_time").trim());
				
				GroupsVO groupsVO = new GroupsVO();
				groupsVO.setGroups_no(groups_no);
				groupsVO.setGroups_owner(groups_owner);
				groupsVO.setGroups_title(groups_title);
				groupsVO.setGroups_time(groups_time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("update_groups_input", groupsVO); // 含有輸入格式錯誤的groupsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/update_groups_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				GroupsService groupsSvc = new GroupsService();
				BulletinService bulletinSvc = new BulletinService();
				groupsVO = groupsSvc.updateGroups(groups_no, groups_owner, groups_title, groups_time);
				List<BulletinVO> list = bulletinSvc.getGroupsBulletin(groups_no);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listOneGroups_Front", groupsVO); // 資料庫update成功後,正確的的groupsVO物件,存入req
				req.setAttribute("listBulletinByGroups", list);
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
			catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups/update_groups_input.jsp");
				failureView.forward(req, res);
			} 
		}//Update
		
		if ("insert".equals(action)) { // 來自addGroups.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String groups_owner = req.getParameter("groups_owner").trim();
				String groups_title = req.getParameter("groups_title").trim();
				if (groups_title == null || (groups_title.trim()).length() == 0) {
					errorMsgs.add("請輸入社團名稱");
				} 
				
				java.sql.Date groups_time = java.sql.Date.valueOf(req.getParameter("groups_time").trim());
				
				GroupsVO groupsVO = new GroupsVO();
				groupsVO.setGroups_owner(groups_owner);
				groupsVO.setGroups_title(groups_title);
				groupsVO.setGroups_time(groups_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addGroups", groupsVO); // 含有輸入格式錯誤的groupsVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/groups/addGroups.jsp");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/groups/groupsIndex.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GroupsService groupsSvc = new GroupsService();
				groupsVO = groupsSvc.addGroups(groups_owner, groups_title, groups_time);
				
				List<GroupsVO> list = new GroupsService().getAllFront();
				GroupsVO vo = list.get(list.size()-1);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front_end/groups/listAllGroups_Front.jsp?whichPage=50&groups_no="+vo.getGroups_no();
				String url = "/front_end/groups/groupsIndex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGroups_Front.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/groups/groupsIndex.jsp");
				failureView.forward(req, res);
			}
		}//insert
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				String groups_no = req.getParameter("groups_no");
				
				/***************************2.開始刪除資料***************************************/
				GroupsService groupsSvc = new GroupsService();
				groupsSvc.deleteGroups(groups_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/groups/groupsIndex.jsp";
//				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//delete
		
		if ("listMembers_ByGroups_no".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String groups_no = req.getParameter("groups_no");
				String requestURL = req.getParameter("requestURL");
				/*************************** 2.開始查詢資料 ****************************************/
				
				Groups_listService groups_listSvc = new Groups_listService();
				List<Groups_ListVO> list = groups_listSvc.getOneByGroupsNo(groups_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				if(requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front", new GroupsService().getOneGroups(groups_no));
				}
				req.setAttribute("listOneByGroupsNo", list);    // 資料庫取出的list物件,存入request
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

	}//doPost

}

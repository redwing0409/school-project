package com.bulletin.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bulletin.model.*;
import com.groups.model.GroupsService;
import com.groups.model.GroupsVO;

@WebServlet("/bulletin/bulletinServlet.do")
public class BulletinServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String groups_no = req.getParameter("groups_no");
				if (groups_no == null || (groups_no.trim()).length() == 0) {
					errorMsgs.add("請輸入社群編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BulletinService bulletinSvc = new BulletinService();
				List<BulletinVO> list = bulletinSvc.getGroupsBulletin(groups_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				GroupsService groupsSvc = new GroupsService();
				String url = "/front_end/bulletin/listBulletinByGroups.jsp";
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
					url = requestURL;
				} else if(requestURL.equals("/front_end/groups/groupsIndex.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
					url = "/front_end/groups/groupsContent.jsp";
				}
				
				req.setAttribute("listBulletinByGroups", list); // 資料庫取出的list物件,存入req
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listBulletinByGroups.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/bulletin/select_page.jsp");
				failureView.forward(req, res);
			}
		}//getOne_For_Display
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllBulletin_Front.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				String bulletin_no = req.getParameter("bulletin_no");
				
				/***************************2.開始查詢資料****************************************/
				BulletinService bulletinSvc = new BulletinService();
				BulletinVO bulletinVO = bulletinSvc.getOneBulletin(bulletin_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("update_bulletin_input", bulletinVO);         // 資料庫取出的bulletinVO物件,存入req
				String url = "/front_end/bulletin/update_bulletin_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_bulletin_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//getOne_For_Update
		
		if ("update".equals(action)) { // 來自update_bulletin_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String bulletin_no = req.getParameter("bulletin_no");
				String groups_no = req.getParameter("groups_no");
				String member_no = req.getParameter("member_no");
				String bulletin_content = req.getParameter("bulletin_content");
				java.sql.Timestamp bulletin_time = java.sql.Timestamp.valueOf(req.getParameter("bulletin_time").trim());
				
				BulletinVO bulletinVO = new BulletinVO();
				bulletinVO.setBulletin_no(bulletin_no);
				bulletinVO.setGroups_no(groups_no);
				bulletinVO.setMember_no(member_no);
				bulletinVO.setBulletin_content(bulletin_content);
				bulletinVO.setBulletin_time(bulletin_time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("update_bulletin_input", bulletinVO); // 含有輸入格式錯誤的groupsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/update_bulletin_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				BulletinService bulletinSvc = new BulletinService();
				GroupsService groupsSvc = new GroupsService();
				bulletinVO = bulletinSvc.updateBulletin(bulletin_no, groups_no, member_no, bulletin_content, bulletin_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("update_bulletin_input", bulletinVO); // 資料庫update成功後,正確的的bulletinVO物件,存入req
				req.setAttribute("listBulletinByGroups",bulletinSvc.getGroupsBulletin(groups_no));
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
			catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/bulletin/update_bulletin_input.jsp");
				failureView.forward(req, res);
			} 
		}//Update
		
		if ("insert".equals(action)) { // 來自addBulletin.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String groups_no = req.getParameter("groups_no").trim();
				String member_no = req.getParameter("member_no").trim();
				String bulletin_content = req.getParameter("bulletin_content").trim();
				if (bulletin_content == null || (bulletin_content.trim()).length() == 0) {
					errorMsgs.add("請輸入內容");
				} 
				
				java.sql.Timestamp bulletin_time = java.sql.Timestamp.valueOf(req.getParameter("bulletin_time").trim());
				
				BulletinVO bulletinVO = new BulletinVO();
				bulletinVO.setGroups_no(groups_no);
				bulletinVO.setMember_no(member_no);
				bulletinVO.setBulletin_content(bulletin_content);
				bulletinVO.setBulletin_time(bulletin_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addBulletin", bulletinVO); // 含有輸入格式錯誤的bulletinVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/bulletin/addBulletin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BulletinService bulletinSvc = new BulletinService();
				GroupsService groupsSvc = new GroupsService();
				bulletinVO = bulletinSvc.addBulletin(groups_no, member_no, bulletin_content, bulletin_time);
				
//				List<BulletinVO> list = bulletinSvc.getAllFront();
//				BulletinVO vo = list.get(list.size()-1);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("listBulletinByGroups",bulletinSvc.getGroupsBulletin(groups_no));
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				
//				String url = "/front_end/bulletin/listAllBulletin_Front.jsp?whichPage=50&bulletin_no="+vo.getBulletin_no();
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 新增成功後轉交listAllGroups_Front.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/bulletin/addBulletin.jsp");
				failureView.forward(req, res);
			}
		}//insert
		
		if ("delete".equals(action)) { // 來自listAllBulletin_Front.jsp 或  listBulletinByGroups.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			try {
				/***************************1.接收請求參數***************************************/
				String bulletin_no = req.getParameter("bulletin_no");
				String groups_no = req.getParameter("groups_no");
				
				/***************************2.開始刪除資料***************************************/
				BulletinService bulletinSvc = new BulletinService();
				GroupsService groupsSvc = new GroupsService();
				bulletinSvc.deleteBulletin(bulletin_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = requestURL;
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				
				req.setAttribute("listBulletinByGroups",bulletinSvc.getGroupsBulletin(groups_no));
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
		
	}//doPost

}

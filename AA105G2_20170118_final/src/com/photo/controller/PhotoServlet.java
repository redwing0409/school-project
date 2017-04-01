package com.photo.controller;

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

import com.bulletin.model.BulletinService;
import com.bulletin.model.BulletinVO;
import com.groups.model.GroupsService;
import com.member.model.*;
import com.photo.model.*;

@WebServlet("/photo/photoServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class PhotoServlet extends HttpServlet {
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
			String requestURL = req.getParameter("requestURL");

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String groups_no = req.getParameter("groups_no");
				if (groups_no == null || (groups_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PhotoService photoSvc = new PhotoService();
				List<PhotoVO> list = photoSvc.getGroupsPhoto(groups_no);
//				System.out.println(requestURL);
//				System.out.println(groups_no);
//				System.out.println(list==null);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				GroupsService groupsSvc = new GroupsService();
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				req.setAttribute("listPhotoByGroups", list); // 資料庫取出的list物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交 回來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage() + " 此社群無照片");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/photo/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}//getOne_For_Display
		
		
		if ("insert".equals(action)) { // 來自addPhoto.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String member_no = req.getParameter("member_no");
				String groups_no = req.getParameter("groups_no");
				String photo_title = req.getParameter("photo_title");
				
//				byte[] member_pic = null;
//				Collection<Part> parts = req.getParts();
//				for (Part part : parts) {
//					if(part.getContentType() != null){
//						InputStream in = part.getInputStream();
//						member_pic = new byte[in.available()];
//						in.read(member_pic);
//					}
//				}
				byte[] photo_file = null;
				Part part = req.getPart("photo_file");
				InputStream in = part.getInputStream();
				photo_file = new byte[in.available()];
				in.read(photo_file);
				in.close();
				
				java.sql.Timestamp photo_createdate = java.sql.Timestamp.valueOf(req.getParameter("photo_createdate").trim());
				if (photo_title == null || (photo_title.trim()).length() == 0) {
					photo_title = req.getParameter("photo_createdate");
				} 
				PhotoVO photoVO = new PhotoVO();
				photoVO.setMember_no(member_no);
				photoVO.setGroups_no(groups_no);
				photoVO.setPhoto_title(photo_title);
				photoVO.setPhoto_file(photo_file);
				photoVO.setPhoto_createdate(photo_createdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addPhoto", photoVO); // 含有輸入格式錯誤的photoVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/photo/addPhoto.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				PhotoService photoSvc = new PhotoService();
				GroupsService groupsSvc = new GroupsService();
				photoVO = photoSvc.addPhoto(member_no, groups_no, photo_title, photo_file, photo_createdate);
				List<PhotoVO> list = photoSvc.getGroupsPhoto(groups_no);
				
//				List<PhotoVO> list = photoSvc.getAllFront();
//				PhotoVO vo = list.get(list.size()-1);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front_end/photo/listAllPhoto_Front.jsp?whichPage=50&photo_no="+vo.getPhoto_no();
				req.setAttribute("listPhotoByGroups", list);
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
				}
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/photo/addPhoto.jsp");
				failureView.forward(req, res);
			} 
		}//insert
		
		if ("delete".equals(action)) { // 來自listAllPhoto_Front.jsp 或  的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); 
			try {
				/***************************1.接收請求參數***************************************/
				String photo_no = req.getParameter("photo_no");
				String groups_no = req.getParameter("groups_no");
				
				/***************************2.開始刪除資料***************************************/
				PhotoService photoSvc = new PhotoService();
				GroupsService groupsSvc = new GroupsService();
				photoSvc.deletePhoto(photo_no);
				List<PhotoVO> list = photoSvc.getGroupsPhoto(groups_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				if(requestURL.equals("/front_end/photo/listPhotoByGroups.jsp")){
//					req.setAttribute("listPhotoByGroups",photoSvc.getGroupsBulletin(photo_no));
//				}
				req.setAttribute("listPhotoByGroups", list);
				
				if(requestURL.equals("/front_end/groups/listOneGroups_Front.jsp")||requestURL.equals("/front_end/groups/groupsContent.jsp")){
					req.setAttribute("listOneGroups_Front",groupsSvc.getOneGroups(groups_no));
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

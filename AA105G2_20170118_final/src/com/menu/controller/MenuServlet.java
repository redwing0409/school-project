package com.menu.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.adv.model.*;
import com.menu.model.MenuService;
import com.menu.model.MenuVO;
import com.place.model.*;
import com.sup.model.SupService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MenuServlet extends HttpServlet {

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
				String str = req.getParameter("menu_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入菜色編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String menu_no = null;
				try {
					menu_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("菜色編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MenuService MenuSvc = new MenuService();
				MenuVO MenuVO = MenuSvc.getOneMenu(menu_no);
				if (MenuVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("MenuVO", MenuVO); // 資料庫取出的MenuVO物件,存入req
				String url = "/supplier_end/menu/listOneMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMenu.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMenu.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
			
			try {
				/***************************1.接收請求參數****************************************/
				String menu_no = new String(req.getParameter("menu_no"));
				
				/***************************2.開始查詢資料****************************************/
				MenuService MenuSvc = new MenuService();
				MenuVO MenuVO = MenuSvc.getOneMenu(menu_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("MenuVO", MenuVO);         // 資料庫取出的MenuVO物件,存入req
				String url = "/supplier_end/menu/update_menu_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_menu_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} 
				catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/listAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_menu_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String menu_no = new String (req.getParameter("menu_no").trim());
				String place_no = new String (req.getParameter("place_no").trim());
				String menu_name = req.getParameter("menu_name").trim();
				if( menu_name == null || (menu_name.trim()).length()==0){
			    	errorMsgs.add("請輸入菜色名稱");
			    }
				
				String menu_note = req.getParameter("menu_note").trim();	
				if( menu_note == null || (menu_note.trim()).length()==0){
			    	errorMsgs.add("請輸入菜色介紹");
			    }
				
                MenuService MenuSvc = new MenuService();
				
				Part part = req.getPart("menu_pic");
				byte[] menu_pic = null;
				
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {	
					InputStream in = part.getInputStream();
					menu_pic = new byte[in.available()];
					in.read(menu_pic);
					in.close();
				} else {
					menu_pic = MenuSvc.getOneMenu(menu_no).getMenu_pic();
				}	
				
				Integer menu_price = null;
				try{
					menu_price = new Integer(req.getParameter("menu_price").trim());
				} catch (NumberFormatException e){
					errorMsgs.add("請填入菜色價格");
				}
				
				MenuVO MenuVO = new MenuVO();
				MenuVO.setMenu_no(menu_no);
				MenuVO.setPlace_no(place_no);
				MenuVO.setMenu_name(menu_name);
				MenuVO.setMenu_note(menu_note);
				MenuVO.setMenu_pic(menu_pic);
				MenuVO.setMenu_price(menu_price);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MenuVO", MenuVO); // 含有輸入格式錯誤的SupVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/update_menu_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MenuVO = MenuSvc.updateMenu(menu_no, place_no, menu_name, menu_note, menu_pic, menu_price );
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				PlaceService placeSvc = new PlaceService();
				if(requestURL.equals("/supplier_end/menu/listSupMenu.jsp") || requestURL.equals("/supplier_end/menu/listAllMenu.jsp"))
					req.setAttribute("listMenus_ByPlace_no",placeSvc.getMenusByPlace_no(place_no)); // 資料庫取出的list物件,存入request
	
//				req.setAttribute("MenuVO", MenuVO); // 資料庫update成功後,正確的的MenuVO物件,存入req
//				String url = "/supplier_end/menu/listAllMenu.jsp";
				String url = requestURL ;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
		      catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/update_menu_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addSup.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String place_no = new String(req.getParameter("place_no").trim());
				String menu_name = req.getParameter("menu_name");
				if( menu_name == null || (menu_name.trim()).length()==0){
			    	errorMsgs.add("請輸入菜色名稱");
			    }
				String menu_note = req.getParameter("menu_note").trim();	
				if( menu_note == null || (menu_note.trim()).length()==0){
			    	errorMsgs.add("請輸入菜色介紹");
			    }
				
				byte[] menu_pic = null;
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if(part.getContentType() != null){
						InputStream in = part.getInputStream();
						menu_pic = new byte[in.available()];
						in.read(menu_pic);
					}
				}
				
				Integer menu_price = null;
				try{
					menu_price = new Integer(req.getParameter("menu_price").trim());
				} catch (NumberFormatException e){
					errorMsgs.add("請輸入價格");
				}
	
				MenuVO MenuVO = new MenuVO();
				MenuVO.setPlace_no(place_no);
				MenuVO.setMenu_name(menu_name);
				MenuVO.setMenu_note(menu_note);
				MenuVO.setMenu_pic(menu_pic);
				MenuVO.setMenu_price(menu_price);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MenuVO", MenuVO); // 含有輸入格式錯誤的SupVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/addMenu.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MenuService MenuSvc = new MenuService();
				MenuVO = MenuSvc.addMenu(place_no, menu_name, menu_note, menu_pic, menu_price);
				
				List<MenuVO> list = MenuSvc.getAll();
				MenuVO vo = list.get(list.size()-1);
				PlaceService placeSvc = new PlaceService();
				Set<MenuVO> set = placeSvc.getMenusByPlace_no(place_no);
				req.setAttribute("listMenus_ByPlace_no", set);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/supplier_end/menu/listSupMenus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMenu.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
                 catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/addMenu.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllSup.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String menu_no = new String(req.getParameter("menu_no"));
				
				/***************************2.開始刪除資料***************************************/
				MenuService MenuSvc = new MenuService();
				MenuSvc.deleteMenu(menu_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/supplier_end/menu/listAllMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/listAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
	}
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}


package com.place.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.sup.model.SupService;
import com.place.model.PlaceService;
import com.place.model.*;
import com.menu.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class PlaceServlet extends HttpServlet {

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
				String str = req.getParameter("place_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場地編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String place_no = null;
				try {
					place_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("場地編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO = placeSvc.getOnePlace(place_no);
				if (placeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("placeVO", placeVO); // 資料庫取出的placeVO物件,存入req
				String url = "/front_end/Place/listOnePlace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePLACE.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getType_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("place_type");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場地編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer	place_type = Integer.valueOf(str);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PlaceService placeSvc = new PlaceService();
				List<PlaceVO> list = placeSvc.getOnePlaceType(place_type);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listType", list); // 資料庫取出的placeVO物件,存入req
				HttpSession session=req.getSession();
				session.setAttribute("listType", list);
//				String url = "/front_end/Place/listPlaceType.jsp";
				String url = "/front_end/place/place.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listPlaceType.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("getArea_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String place_area = req.getParameter("place_area");
			
				if (place_area == null || (place_area.trim()).length() == 0) {
					errorMsgs.add("請輸入場地編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PlaceService placeSvc = new PlaceService();
				List<PlaceVO> list = placeSvc.getOnePlaceArea(place_area);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listArea", list); // 資料庫取出的placeVO物件,存入req
				HttpSession session=req.getSession();
				session.setAttribute("listArea", list);
//				String url = "/front_end/Place/listPlaceType.jsp";
				String url = "/front_end/place/place.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listPlaceType.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllplace.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				String place_no = new String(req.getParameter("place_no"));
				
				/***************************2.開始查詢資料****************************************/
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO = placeSvc.getOnePlace(place_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("placeVO", placeVO);         // 資料庫取出的placeVO物件,存入req
				String url = "/supplier_end/Place/update_Place_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PLACE_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_PLACE_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
			try {

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String place_no = req.getParameter("place_no").trim();
				String sup_no = req.getParameter("sup_no").trim();
				Integer place_type =new Integer(req.getParameter("place_type").trim());	
				String place_name = req.getParameter("place_name").trim();
				if(place_name == null || place_name.trim().length()==0){
					errorMsgs.add("請輸入場地名稱!");
				}
				
				String place_area = req.getParameter("place_area").trim();
				String place_adds = req.getParameter("place_adds").trim(); 
				if(place_adds == null || place_adds.trim().length()==0){
					errorMsgs.add("請輸入場地地址!");
				}
				
				String place_note = req.getParameter("place_note").trim();
				if(place_note == null || place_note.trim().length()==0){
					errorMsgs.add("請輸入場地介紹!");
				}
				Integer place_status = new Integer(req.getParameter("place_status").trim());
		        
				PlaceService placeSvc = new PlaceService();
				Part part = req.getPart("place_pic");
                byte[] place_pic = null;

				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					place_pic = new byte[in.available()];
					in.read(place_pic);
					in.close();
				}else{
					place_pic = placeSvc.getOnePlace(place_no).getPlace_pic();
				}


				PlaceVO placeVO = new PlaceVO();
				placeVO.setPlace_no(place_no);
				placeVO.setSup_no(sup_no);
				placeVO.setPlace_type(place_type);
				placeVO.setPlace_name(place_name);
				placeVO.setPlace_area(place_area);
				placeVO.setPlace_adds(place_adds);
				placeVO.setPlace_pic(place_pic);
				placeVO.setPlace_note(place_note);
				placeVO.setPlace_status(place_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("placeVO", placeVO); // 含有輸入格式錯誤的placeVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/Place/update_Place_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				placeVO = placeSvc.updatePlace(place_no, sup_no, place_type, place_name, place_area, place_adds, place_pic, place_note, place_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//前後端整合差異
//				req.setAttribute("placeVO", placeVO); // 資料庫update成功後,正確的的placeVO物件,存入req
//				String url = "/front_end/Place/listAllPlace.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
				SupService SupSvc = new SupService();
				if(requestURL.equals("/supplier_end/sup/listPlace_BySup_no.jsp") || requestURL.equals("/Place/listAllPlace.jsp"))
					req.setAttribute("listPlace_BySup_no",SupSvc.getPlaceBySup_no(sup_no)); // 資料庫取出的list物件,存入request
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/update_Place_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPlace.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String sup_no = req.getParameter("sup_no").trim();
				Integer place_type =new Integer(req.getParameter("place_type").trim());	
				String place_name = req.getParameter("place_name").trim();
				if(place_name == null || place_name.trim().length()==0){
					errorMsgs.add("請輸入場地名稱!");
				}
				
				String place_area = req.getParameter("place_area").trim();
				String place_adds = req.getParameter("place_adds").trim();
				if(place_adds == null || place_adds.trim().length()==0){
					errorMsgs.add("請輸入場地地址!");
				}
				
				String place_note = req.getParameter("place_note").trim();
				if(place_note == null || place_note.trim().length()==0){
					errorMsgs.add("請輸入場地介紹!");
				}
				
				Integer place_status = new Integer(req.getParameter("place_status").trim());
				
				byte[] place_pic = null;
				Part part = req.getPart("place_pic");
				InputStream in = part.getInputStream();
				place_pic = new byte[in.available()];
				in.read(place_pic);
				in.close();
				


				PlaceVO placeVO = new PlaceVO();
				
				placeVO.setSup_no(sup_no);
				placeVO.setPlace_type(place_type);
				placeVO.setPlace_name(place_name);
				placeVO.setPlace_area(place_area);
				placeVO.setPlace_adds(place_adds);
				placeVO.setPlace_pic(place_pic);
				placeVO.setPlace_note(place_note);
				placeVO.setPlace_status(place_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("placeVO", placeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/Place/addPlace.jsp");
					failureView.forward(req, res);
					return;
				}

				
//前後端整合差異				
//				/***************************2.開始新增資料***************************************/
//				PlaceService placeSvc = new PlaceService();
//				placeVO = placeSvc.addPlace(sup_no, place_type, place_name, place_area, place_adds, place_pic, place_note, place_status);
//				
//				List<PlaceVO> list = placeSvc.getAll();
//				PlaceVO vo = list.get(list.size()-1);
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front_end/Place/listAllPlace.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPlace.jsp
//				successView.forward(req, res);				
//				
				/***************************2.開始新增資料***************************************/
				PlaceService placeSvc = new PlaceService();
				placeVO = placeSvc.addPlace(sup_no, place_type, place_name, place_area, place_adds, place_pic, place_note, place_status);
				
				List<PlaceVO> list = placeSvc.getAll();
				PlaceVO vo = list.get(list.size()-1);
				SupService SupSvc = new SupService();
				Set<PlaceVO> set = SupSvc.getPlaceBySup_no(sup_no);
				req.setAttribute("listPlace_BySup_no", set);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/				
				String url = "/supplier_end/sup/listPlace_BySup_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPlace.jsp
				successView.forward(req, res);							
				
				/***************************其他可能的錯誤處理**********************************/
        } catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/Place/addPlace.jsp");
			failureView.forward(req, res);
		} 
        }
		
		if ("delete".equals(action)) { // 來自listAllPlace.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String place_no = req.getParameter("place_no");

		        
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO =placeSvc.getOnePlace(place_no);
			

				
				placeSvc.updatePlace(place_no, placeVO.getSup_no(), placeVO.getPlace_type(),
						placeVO.getPlace_name(), placeVO.getPlace_area(), placeVO.getPlace_adds(), placeVO.getPlace_pic(), 
						placeVO.getPlace_note(),0);
				
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/Place/listAllPlace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/listAllPlace.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listMenus_ByPlace_no_A".equals(action) || "listMenus_ByPlace_no_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String place_no = new String(req.getParameter("place_no"));
			

				/*************************** 2.開始查詢資料 ****************************************/
				PlaceService PlaceSvc = new PlaceService();
				Set<MenuVO> set = PlaceSvc.getMenusByPlace_no(place_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listMenus_ByPlace_no", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listMenus_ByPlace_no_A".equals(action))
					url = "/front_end/Place/listMenus_ByPlace_no.jsp";        // 成功轉交 sup/listPlace_BySup_no.jsp
				else if ("listMenus_ByPlace_no_B".equals(action))
					url = "/front_end/Place/listAllPlace.jsp";              // 成功轉交 sup/listAllSup.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}		
		
		
	}//doPost

	
	
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
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

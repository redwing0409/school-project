package com.appointment.controller;

import java.io.*;
import java.util.*;
import java.sql.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.appointment.model.*;
import com.member.model.*;
import com.place.model.PlaceService;

@WebServlet("/Appointment/AppointmentServlet.do")
public class AppointmentServlet extends HttpServlet {

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
				String str = req.getParameter("app_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String app_no = null;
				try {
					app_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AppointmentService appSvc = new AppointmentService();
				AppointmentVO appointmentVO = appSvc.getOneAppointment(app_no);
				
				if (appointmentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("appointmentVO", appointmentVO); // 資料庫取出的AppointmentVO物件,存入req
				String url = "/front_end/Appointment/listOneAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAppointment.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAppointment.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數****************************************/
				String app_no = new String(req.getParameter("app_no"));
				
				/***************************2.開始查詢資料****************************************/
				AppointmentService appSvc = new AppointmentService();
				AppointmentVO appointmentVO = appSvc.getOneAppointment(app_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("appointmentVO", appointmentVO);         // 資料庫取出的appointmentVO物件,存入req
				String url = "/supplier_end/Appointment/update_Appointment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_appointment_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/Appointment/listAllAppointment.jsp");
				failureView.forward(req, res);
			}
		}		
		
		if ("update".equals(action)) { // 來自update_appointment_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			String requestURL = req.getParameter("requestURL");

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String app_no = req.getParameter("app_no").trim();
				String place_no = req.getParameter("place_no").trim();
				String member_no = req.getParameter("member_no").trim();
				java.sql.Date app_date =null;
				try {
					app_date = java.sql.Date.valueOf(req.getParameter("app_date").trim());
				} catch (IllegalArgumentException e) {
					app_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入預約訂單成立日期!");
				}
				java.sql.Date app_place_date = null;
				try {
					app_place_date = java.sql.Date.valueOf(req.getParameter("app_place_date").trim());
				} catch (IllegalArgumentException e) {
					app_place_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入預約場地日期!");
				}
				Integer app_status = new Integer(req.getParameter("app_status").trim());
				
				AppointmentVO appointmentVO = new AppointmentVO();
				appointmentVO.setApp_no(app_no);
				appointmentVO.setPlace_no(place_no);
				appointmentVO.setMember_no(member_no);
				appointmentVO.setApp_date(app_date);
				appointmentVO.setApp_place_date(app_place_date);
				appointmentVO.setApp_status(app_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appointmentVO", appointmentVO); // 含有輸入格式錯誤的appointmentVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/Appointment/update_Appointment_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AppointmentService appSvc = new AppointmentService();
				appointmentVO = appSvc.updateAppointment(app_no, place_no, member_no, app_date, app_place_date,app_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				PlaceService placeSvc = new PlaceService();
				if(requestURL.equals("/supplier-end/app/listSupAppointment.jsp") || requestURL.equals("/front_end/Appointment/listAllAppointment.jsp"))
					req.setAttribute("listAppointment_ByPlace_no",placeSvc.getAppointmentByPlace_no(place_no)); // 資料庫取出的list物件,存入request

//				req.setAttribute("appointmentVO", appointmentVO); // 資料庫update成功後,正確的的APPOINTMENTVO物件,存入req
//				String url = "/front_end/Appointment/listAllAppointment.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAppointment.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} 
		catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/Appointment/update_Appointment_input.jsp");
				failureView.forward(req, res);
			}
		}
        if ("check".equals(action)) { // 來自addAppointment.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session=req.getSession();
			
				String place_no = req.getParameter("place_no");
				System.out.println("XXXXXXXXXXXXXXXXXXX"+place_no);
				Date app_date = new java.sql.Date(System.currentTimeMillis());
				//System.out.println(req.getParameter("app_place_date"));
				Date app_place_date = java.sql.Date.valueOf(req.getParameter("app_place_date").trim());
				Integer app_status = 1;

				AppointmentVO appointmentVO = new AppointmentVO();
				
				appointmentVO.setPlace_no(place_no);
				appointmentVO.setApp_date(app_date);
				appointmentVO.setApp_place_date(app_place_date);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appointmentVO", appointmentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Appointment/addAppointment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				session.setAttribute("appointmentVO_S", appointmentVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/Appointment/checkAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/Appointment/addAppointment.jsp");
//				failureView.forward(req, res);
//			}
		}
		
        if ("insert".equals(action)) { // 來自addAppointment.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session=req.getSession();
				String place_no = req.getParameter("place_no");
				String member_no = req.getParameter("member_no");
				Date app_date = java.sql.Date.valueOf(req.getParameter("app_date"));
				Date app_place_date = java.sql.Date.valueOf(req.getParameter("app_place_date"));
				Integer app_status = new Integer(req.getParameter("app_status"));

				


				AppointmentVO appointmentVO = new AppointmentVO();
				
				appointmentVO.setPlace_no(place_no);
				appointmentVO.setMember_no(member_no);
				appointmentVO.setApp_date(app_date);
				appointmentVO.setApp_place_date(app_place_date);
				appointmentVO.setApp_status(app_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appointmentVO", appointmentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/place/place.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AppointmentService appSvc = new AppointmentService();
				appointmentVO = appSvc.addAppointment(place_no, member_no, app_date, app_place_date,app_status);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/Appointment/listOwnAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAppointment.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/place/place.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllAppointment.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String app_no = req.getParameter("app_no");
				
				/***************************2.開始刪除資料***************************************/
				AppointmentService appSvc = new AppointmentService();
				appSvc.deleteAppointment(app_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/Appointment/listOwnAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Appointment/listOwnAppointment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

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
		
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("app_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�q��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String app_no = null;
				try {
					app_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�q��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AppointmentService appSvc = new AppointmentService();
				AppointmentVO appointmentVO = appSvc.getOneAppointment(app_no);
				
				if (appointmentVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("appointmentVO", appointmentVO); // ��Ʈw���X��AppointmentVO����,�s�Jreq
				String url = "/front_end/Appointment/listOneAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAppointment.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Appointment/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllAppointment.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String app_no = new String(req.getParameter("app_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				AppointmentService appSvc = new AppointmentService();
				AppointmentVO appointmentVO = appSvc.getOneAppointment(app_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("appointmentVO", appointmentVO);         // ��Ʈw���X��appointmentVO����,�s�Jreq
				String url = "/supplier_end/Appointment/update_Appointment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_appointment_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/Appointment/listAllAppointment.jsp");
				failureView.forward(req, res);
			}
		}		
		
		if ("update".equals(action)) { // �Ӧ�update_appointment_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			String requestURL = req.getParameter("requestURL");

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String app_no = req.getParameter("app_no").trim();
				String place_no = req.getParameter("place_no").trim();
				String member_no = req.getParameter("member_no").trim();
				java.sql.Date app_date =null;
				try {
					app_date = java.sql.Date.valueOf(req.getParameter("app_date").trim());
				} catch (IllegalArgumentException e) {
					app_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�w���q�榨�ߤ��!");
				}
				java.sql.Date app_place_date = null;
				try {
					app_place_date = java.sql.Date.valueOf(req.getParameter("app_place_date").trim());
				} catch (IllegalArgumentException e) {
					app_place_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�w�����a���!");
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
					req.setAttribute("appointmentVO", appointmentVO); // �t����J�榡���~��appointmentVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/Appointment/update_Appointment_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				AppointmentService appSvc = new AppointmentService();
				appointmentVO = appSvc.updateAppointment(app_no, place_no, member_no, app_date, app_place_date,app_status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				PlaceService placeSvc = new PlaceService();
				if(requestURL.equals("/supplier-end/app/listSupAppointment.jsp") || requestURL.equals("/front_end/Appointment/listAllAppointment.jsp"))
					req.setAttribute("listAppointment_ByPlace_no",placeSvc.getAppointmentByPlace_no(place_no)); // ��Ʈw���X��list����,�s�Jrequest

//				req.setAttribute("appointmentVO", appointmentVO); // ��Ʈwupdate���\��,���T����APPOINTMENTVO����,�s�Jreq
//				String url = "/front_end/Appointment/listAllAppointment.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneAppointment.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} 
		catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/Appointment/update_Appointment_input.jsp");
				failureView.forward(req, res);
			}
		}
        if ("check".equals(action)) { // �Ӧ�addAppointment.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
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
					req.setAttribute("appointmentVO", appointmentVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Appointment/addAppointment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				session.setAttribute("appointmentVO_S", appointmentVO);
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/Appointment/checkAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/Appointment/addAppointment.jsp");
//				failureView.forward(req, res);
//			}
		}
		
        if ("insert".equals(action)) { // �Ӧ�addAppointment.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
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
					req.setAttribute("appointmentVO", appointmentVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/place/place.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				AppointmentService appSvc = new AppointmentService();
				appointmentVO = appSvc.addAppointment(place_no, member_no, app_date, app_place_date,app_status);

				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/Appointment/listOwnAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllAppointment.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/place/place.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllAppointment.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String app_no = req.getParameter("app_no");
				
				/***************************2.�}�l�R�����***************************************/
				AppointmentService appSvc = new AppointmentService();
				appSvc.deleteAppointment(app_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front_end/Appointment/listOwnAppointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Appointment/listOwnAppointment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

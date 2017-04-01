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
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("place_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���a�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String place_no = null;
				try {
					place_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���a�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO = placeSvc.getOnePlace(place_no);
				if (placeVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("placeVO", placeVO); // ��Ʈw���X��placeVO����,�s�Jreq
				String url = "/front_end/Place/listOnePlace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnePLACE.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getType_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("place_type");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���a�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer	place_type = Integer.valueOf(str);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PlaceService placeSvc = new PlaceService();
				List<PlaceVO> list = placeSvc.getOnePlaceType(place_type);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("listType", list); // ��Ʈw���X��placeVO����,�s�Jreq
				HttpSession session=req.getSession();
				session.setAttribute("listType", list);
//				String url = "/front_end/Place/listPlaceType.jsp";
				String url = "/front_end/place/place.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listPlaceType.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("getArea_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String place_area = req.getParameter("place_area");
			
				if (place_area == null || (place_area.trim()).length() == 0) {
					errorMsgs.add("�п�J���a�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PlaceService placeSvc = new PlaceService();
				List<PlaceVO> list = placeSvc.getOnePlaceArea(place_area);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/Place/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("listArea", list); // ��Ʈw���X��placeVO����,�s�Jreq
				HttpSession session=req.getSession();
				session.setAttribute("listArea", list);
//				String url = "/front_end/Place/listPlaceType.jsp";
				String url = "/front_end/place/place.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listPlaceType.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllplace.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String place_no = new String(req.getParameter("place_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO = placeSvc.getOnePlace(place_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("placeVO", placeVO);         // ��Ʈw���X��placeVO����,�s�Jreq
				String url = "/supplier_end/Place/update_Place_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_PLACE_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_PLACE_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
			try {

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String place_no = req.getParameter("place_no").trim();
				String sup_no = req.getParameter("sup_no").trim();
				Integer place_type =new Integer(req.getParameter("place_type").trim());	
				String place_name = req.getParameter("place_name").trim();
				if(place_name == null || place_name.trim().length()==0){
					errorMsgs.add("�п�J���a�W��!");
				}
				
				String place_area = req.getParameter("place_area").trim();
				String place_adds = req.getParameter("place_adds").trim(); 
				if(place_adds == null || place_adds.trim().length()==0){
					errorMsgs.add("�п�J���a�a�}!");
				}
				
				String place_note = req.getParameter("place_note").trim();
				if(place_note == null || place_note.trim().length()==0){
					errorMsgs.add("�п�J���a����!");
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
					req.setAttribute("placeVO", placeVO); // �t����J�榡���~��placeVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/supplier_end/Place/update_Place_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				placeVO = placeSvc.updatePlace(place_no, sup_no, place_type, place_name, place_area, place_adds, place_pic, place_note, place_status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//�e��ݾ�X�t��
//				req.setAttribute("placeVO", placeVO); // ��Ʈwupdate���\��,���T����placeVO����,�s�Jreq
//				String url = "/front_end/Place/listAllPlace.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
				SupService SupSvc = new SupService();
				if(requestURL.equals("/supplier_end/sup/listPlace_BySup_no.jsp") || requestURL.equals("/Place/listAllPlace.jsp"))
					req.setAttribute("listPlace_BySup_no",SupSvc.getPlaceBySup_no(sup_no)); // ��Ʈw���X��list����,�s�Jrequest
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/update_Place_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addPlace.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String sup_no = req.getParameter("sup_no").trim();
				Integer place_type =new Integer(req.getParameter("place_type").trim());	
				String place_name = req.getParameter("place_name").trim();
				if(place_name == null || place_name.trim().length()==0){
					errorMsgs.add("�п�J���a�W��!");
				}
				
				String place_area = req.getParameter("place_area").trim();
				String place_adds = req.getParameter("place_adds").trim();
				if(place_adds == null || place_adds.trim().length()==0){
					errorMsgs.add("�п�J���a�a�}!");
				}
				
				String place_note = req.getParameter("place_note").trim();
				if(place_note == null || place_note.trim().length()==0){
					errorMsgs.add("�п�J���a����!");
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
					req.setAttribute("placeVO", placeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/Place/addPlace.jsp");
					failureView.forward(req, res);
					return;
				}

				
//�e��ݾ�X�t��				
//				/***************************2.�}�l�s�W���***************************************/
//				PlaceService placeSvc = new PlaceService();
//				placeVO = placeSvc.addPlace(sup_no, place_type, place_name, place_area, place_adds, place_pic, place_note, place_status);
//				
//				List<PlaceVO> list = placeSvc.getAll();
//				PlaceVO vo = list.get(list.size()-1);
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/front_end/Place/listAllPlace.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllPlace.jsp
//				successView.forward(req, res);				
//				
				/***************************2.�}�l�s�W���***************************************/
				PlaceService placeSvc = new PlaceService();
				placeVO = placeSvc.addPlace(sup_no, place_type, place_name, place_area, place_adds, place_pic, place_note, place_status);
				
				List<PlaceVO> list = placeSvc.getAll();
				PlaceVO vo = list.get(list.size()-1);
				SupService SupSvc = new SupService();
				Set<PlaceVO> set = SupSvc.getPlaceBySup_no(sup_no);
				req.setAttribute("listPlace_BySup_no", set);
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/				
				String url = "/supplier_end/sup/listPlace_BySup_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllPlace.jsp
				successView.forward(req, res);							
				
				/***************************��L�i�઺���~�B�z**********************************/
        } catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/Place/addPlace.jsp");
			failureView.forward(req, res);
		} 
        }
		
		if ("delete".equals(action)) { // �Ӧ�listAllPlace.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String place_no = req.getParameter("place_no");

		        
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO =placeSvc.getOnePlace(place_no);
			

				
				placeSvc.updatePlace(place_no, placeVO.getSup_no(), placeVO.getPlace_type(),
						placeVO.getPlace_name(), placeVO.getPlace_area(), placeVO.getPlace_adds(), placeVO.getPlace_pic(), 
						placeVO.getPlace_note(),0);
				
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front_end/Place/listAllPlace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/Place/listAllPlace.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listMenus_ByPlace_no_A".equals(action) || "listMenus_ByPlace_no_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String place_no = new String(req.getParameter("place_no"));
			

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PlaceService PlaceSvc = new PlaceService();
				Set<MenuVO> set = PlaceSvc.getMenusByPlace_no(place_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listMenus_ByPlace_no", set);    // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listMenus_ByPlace_no_A".equals(action))
					url = "/front_end/Place/listMenus_ByPlace_no.jsp";        // ���\��� sup/listPlace_BySup_no.jsp
				else if ("listMenus_ByPlace_no_B".equals(action))
					url = "/front_end/Place/listAllPlace.jsp";              // ���\��� sup/listAllSup.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}		
		
		
	}//doPost

	
	
	
	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}

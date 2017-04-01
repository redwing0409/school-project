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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("menu_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String menu_no = null;
				try {
					menu_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MenuService MenuSvc = new MenuService();
				MenuVO MenuVO = MenuSvc.getOneMenu(menu_no);
				if (MenuVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("MenuVO", MenuVO); // ��Ʈw���X��MenuVO����,�s�Jreq
				String url = "/supplier_end/menu/listOneMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMenu.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllMenu.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String menu_no = new String(req.getParameter("menu_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MenuService MenuSvc = new MenuService();
				MenuVO MenuVO = MenuSvc.getOneMenu(menu_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("MenuVO", MenuVO);         // ��Ʈw���X��MenuVO����,�s�Jreq
				String url = "/supplier_end/menu/update_menu_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_menu_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} 
				catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/listAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_menu_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String menu_no = new String (req.getParameter("menu_no").trim());
				String place_no = new String (req.getParameter("place_no").trim());
				String menu_name = req.getParameter("menu_name").trim();
				if( menu_name == null || (menu_name.trim()).length()==0){
			    	errorMsgs.add("�п�J���W��");
			    }
				
				String menu_note = req.getParameter("menu_note").trim();	
				if( menu_note == null || (menu_note.trim()).length()==0){
			    	errorMsgs.add("�п�J��⤶��");
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
					errorMsgs.add("�ж�J������");
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
					req.setAttribute("MenuVO", MenuVO); // �t����J�榡���~��SupVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/update_menu_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MenuVO = MenuSvc.updateMenu(menu_no, place_no, menu_name, menu_note, menu_pic, menu_price );
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				PlaceService placeSvc = new PlaceService();
				if(requestURL.equals("/supplier_end/menu/listSupMenu.jsp") || requestURL.equals("/supplier_end/menu/listAllMenu.jsp"))
					req.setAttribute("listMenus_ByPlace_no",placeSvc.getMenusByPlace_no(place_no)); // ��Ʈw���X��list����,�s�Jrequest
	
//				req.setAttribute("MenuVO", MenuVO); // ��Ʈwupdate���\��,���T����MenuVO����,�s�Jreq
//				String url = "/supplier_end/menu/listAllMenu.jsp";
				String url = requestURL ;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} 
		      catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/update_menu_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addSup.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String place_no = new String(req.getParameter("place_no").trim());
				String menu_name = req.getParameter("menu_name");
				if( menu_name == null || (menu_name.trim()).length()==0){
			    	errorMsgs.add("�п�J���W��");
			    }
				String menu_note = req.getParameter("menu_note").trim();	
				if( menu_note == null || (menu_note.trim()).length()==0){
			    	errorMsgs.add("�п�J��⤶��");
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
					errorMsgs.add("�п�J����");
				}
	
				MenuVO MenuVO = new MenuVO();
				MenuVO.setPlace_no(place_no);
				MenuVO.setMenu_name(menu_name);
				MenuVO.setMenu_note(menu_note);
				MenuVO.setMenu_pic(menu_pic);
				MenuVO.setMenu_price(menu_price);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MenuVO", MenuVO); // �t����J�榡���~��SupVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/menu/addMenu.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MenuService MenuSvc = new MenuService();
				MenuVO = MenuSvc.addMenu(place_no, menu_name, menu_note, menu_pic, menu_price);
				
				List<MenuVO> list = MenuSvc.getAll();
				MenuVO vo = list.get(list.size()-1);
				PlaceService placeSvc = new PlaceService();
				Set<MenuVO> set = placeSvc.getMenusByPlace_no(place_no);
				req.setAttribute("listMenus_ByPlace_no", set);
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/supplier_end/menu/listSupMenus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMenu.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} 
                 catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/addMenu.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllSup.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String menu_no = new String(req.getParameter("menu_no"));
				
				/***************************2.�}�l�R�����***************************************/
				MenuService MenuSvc = new MenuService();
				MenuSvc.deleteMenu(menu_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/supplier_end/menu/listAllMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/menu/listAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
	}
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


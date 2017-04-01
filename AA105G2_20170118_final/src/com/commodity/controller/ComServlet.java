package com.commodity.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.commodity.model.*;
import com.sup.model.SupVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ComServlet extends HttpServlet {

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
				String str = req.getParameter("com_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�s��");
				}
				// Send the use back to the form, if there were errors
						
					
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
						
				String com_no = "";
				
				try {
					com_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}		
				/***************************2.�}�l�d�߸��*****************************************/
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(com_no);
				if (comVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String temp = sf.format(comVO.getCom_shelf_date());
//				req.setAttribute("temp", temp);
        
            	req.setAttribute("comVO", comVO);// ��Ʈw���X��comVO����,�s�Jreq
				String url = "/front_end/shoppingcar/ShoppingPageDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���ShoppingPageDetail.jsp
				successView.forward(req, res);
				return;
              
				/***************************��L�i�઺���~�B�z*************************************/
			   } catch (Exception e) {	
					errorMsgs.add("�L�k���o���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);				
			   }
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String com_no = req.getParameter("com_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(com_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("comVO", comVO);         // ��Ʈw���X��comVO����,�s�Jreq
				String url = "/supplier_end/com/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_com_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			   } catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_com_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String com_no = req.getParameter("com_no").trim();
				String sup_no = req.getParameter("sup_no").trim();
								
				Integer pcm_no = null;
				try {
					pcm_no = new Integer(req.getParameter("pcm_no").trim());
				} catch (NumberFormatException e) {
					pcm_no = null;
					errorMsgs.add("�п�ܰӫ~���O.");
				}
				
				String com_name = req.getParameter("com_name").trim();
				if(com_name == null || com_name.trim().length()==0){
					errorMsgs.add("�п�J�ӫ~�W��!");
				}
				
				String com_desc = req.getParameter("com_desc").trim();
				if(com_desc == null || com_desc.trim().length()==0){
					errorMsgs.add("�п�J�ӫ~�y�z!");
				}
				
				Integer com_price = null;
				try {
					com_price = new Integer(req.getParameter("com_price").trim());
				} catch (NumberFormatException e) {
					com_price = null;
					errorMsgs.add("�п�J�ӫ~����.");
				}
				
				Integer com_status = null;
				try {
					com_status = new Integer(req.getParameter("com_status").trim());
				} catch (NumberFormatException e) {
					com_price = null;
					errorMsgs.add("�п�ܰӫ~���A.");
				}
					
				java.sql.Date com_shelf_date = null;
				java.sql.Timestamp shelfDate= null;
				
				try {
					 com_shelf_date = java.sql.Date.valueOf(req.getParameter("com_shelf_date").trim());
					 shelfDate = new java.sql.Timestamp(com_shelf_date.getTime());
				} catch (IllegalArgumentException e) {
					com_shelf_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�W�[���!");
				}

				java.sql.Date com_off_date = null;
				java.sql.Timestamp offDate= null;
				try {
					com_off_date = java.sql.Date.valueOf(req.getParameter("com_off_date").trim());
					offDate = new java.sql.Timestamp(com_off_date.getTime());
				} catch (IllegalArgumentException e) {
					com_off_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�U�[���!");
				}
				
				String com_note = req.getParameter("com_note").trim();
				
				
	            ComService getOriginalPic = new ComService();
				Part part = req.getPart("com_pic");
				byte[] com_pic = null;
				InputStream in ;
				
				if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
			    in = part.getInputStream();
			    com_pic = new byte[in.available()];
			    in.read(com_pic);
				in.close();	
				} 
				else {
			     com_pic = getOriginalPic.getOneCom(com_no).getCom_pic();
				}
					
				ComVO comVO = new ComVO();
				comVO.setCom_no(com_no);
				comVO.setSup_no(sup_no);
				comVO.setPcm_no(pcm_no);
				comVO.setCom_name(com_name);
				comVO.setCom_desc(com_desc);
				comVO.setCom_price(com_price);
				comVO.setCom_status(com_status);
				comVO.setCom_shelf_date(shelfDate);
				comVO.setCom_off_date(offDate);
				comVO.setCom_note(com_note);
				comVO.setCom_pic(com_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("comVO", comVO); // �t����J�榡���~��comVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.updateCom(com_no, sup_no, pcm_no, com_name, com_desc, com_price, 
						com_status, shelfDate, offDate, com_note, com_pic);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("comVO2", comVO); // ��Ʈwupdate���\��,���T����comVO����,�s�Jreq
				String url = "/supplier_end/com/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listAllCommodity.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // �Ӧ�addCom.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				//String com_no = new String(req.getParameter("com_no").trim());
				String sup_no = req.getParameter("sup_no").trim();
				
				Integer pcm_no = null;
				try {
					pcm_no = new Integer(req.getParameter("pcm_no").trim());
				} catch (NumberFormatException e) {
					pcm_no =null;
					errorMsgs.add("�п�ܰӫ~���O");
				}
				
				String com_name = req.getParameter("com_name").trim();
				if(com_name == null || com_name.trim().length()==0){
					errorMsgs.add("�п�J�ӫ~�W��!");
				}
				
				String com_desc = req.getParameter("com_desc").trim();
				if(com_desc == null || com_desc.trim().length()==0){
					errorMsgs.add("�п�J�ӫ~�y�z!");
				}
				
				Integer com_price = null;
				try {
					com_price = new Integer(req.getParameter("com_price").trim());
				} catch (NumberFormatException e) {
					com_price = null;
					errorMsgs.add("�п�J�ӫ~����.");
				}
				
				Integer com_status = null;
				try {
					com_status = new Integer(req.getParameter("com_status").trim());
				} catch (NumberFormatException e) {
					com_status = null;
					errorMsgs.add("�п�ܰӫ~���A.");
				}
				
				java.sql.Date com_shelf_date = null;
				java.sql.Timestamp shelfDate= null;
				try {
					com_shelf_date = java.sql.Date.valueOf(req.getParameter("com_shelf_date").trim());
					shelfDate = new java.sql.Timestamp(com_shelf_date.getTime());
				} catch (IllegalArgumentException e) {
					
					com_shelf_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�W�[���!");
				}

				java.sql.Date com_off_date = null;
				java.sql.Timestamp offDate= null;
				try {
					com_off_date = java.sql.Date.valueOf(req.getParameter("com_off_date").trim());
					offDate = new java.sql.Timestamp(com_off_date.getTime());
				} catch (IllegalArgumentException e) {
					
					com_off_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�U�[���!");
				}
				
				String com_note = req.getParameter("com_note").trim();
				
				byte[] com_pic = null;
				Part part = req.getPart("com_pic");
			    InputStream in = part.getInputStream();
			    com_pic = new byte[in.available()];
				in.read(com_pic);
				in.close();
				
				ComVO comVO = new ComVO();
				comVO.setSup_no(sup_no);
				comVO.setPcm_no(pcm_no);
				comVO.setCom_name(com_name);
				comVO.setCom_desc(com_desc);
				comVO.setCom_price(com_price);
				comVO.setCom_status(com_status);
				comVO.setCom_shelf_date(shelfDate);
				comVO.setCom_off_date(offDate);
				comVO.setCom_note(com_note);
				comVO.setCom_pic(com_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("comVO3", comVO); // �t����J�榡���~��comVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.addCom(sup_no, pcm_no, com_name, com_desc, com_price, 
						com_status, shelfDate, offDate, com_note, com_pic);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("comVO3", comVO); // ��Ʈwinsert���\��,���T����comVO����,�s�Jreq
				String url = "/supplier_end/com/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllCommodity.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String com_no = req.getParameter("com_no");
				
				/***************************2.�}�l�R�����***************************************/
				ComService comSvc = new ComService();
				comSvc.deleteCom(com_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/com/listAllCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/com/listAllCom.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listCom_ByCompositeQuery".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				System.out.println(map);
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.���d�X�Ҧ��ӫ~***************************************/
				ComService comSvc = new ComService();
				List<ComVO> list  = comSvc.getAll(map);
				
				/***************************2.�A�w���@�t�Ӷi��ƦX�d��***********************/
				List<ComVO> ComVOList_ToPrint  = new ArrayList<ComVO>();                   //�ۦ�w�q��List<comVO>����
				SupVO supVO=(SupVO)session.getAttribute("supAccount");                    //���o�bseesion���t�Ӫ���
				for(ComVO comVO_item : list) {
					if(supVO.getSup_no().equals(comVO_item.getSup_no())){                 //�qcomVO�����sup_no�M�w�s�b��session���t�Ӥ��t�ӽs���C
						ComVOList_ToPrint.add(comSvc.getOneCom(comVO_item.getCom_no()));  //���Y�t�ӽs���ŦX�n�J���t�Ө����A�N�ӫ~����s�J�ۦ�w�q��List<comVO>���󤺡C
					}
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listCom_ByCompositeQuery", ComVOList_ToPrint); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp"); // ���\���listAllCommodity.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listCom_ByCompositeQuery_front".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				System.out.println(map);
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.�}�l�ƦX�d��***************************************/
				ComService comSvc = new ComService();
				List<ComVO> list  = comSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				HttpSession session2 = req.getSession();
				session2.setAttribute("CompositeQuery_front", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageComposite.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageComposite.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		//System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		//System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}

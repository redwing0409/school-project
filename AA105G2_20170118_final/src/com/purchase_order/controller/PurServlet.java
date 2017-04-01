package com.purchase_order.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.commodity.model.*;
import com.order_item.model.*;
import com.purchase_order.model.*;
import com.sup.model.SupVO;

import email.MailService;

public class PurServlet extends HttpServlet {

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
            String num = req.getParameter("number");
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("pur_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʭq��s��");
				}
				// Send the use back to the form, if there were errors
				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				
				String pur_no = "";
				try {
					pur_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���ʭq��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				
				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				/***************************2.�}�l�d�߸��*****************************************/
				PurService purSvc = new PurService();
				PurVO purVO = purSvc.getOnePurOrd(pur_no);
				if (purVO == null) {
					errorMsgs.add("�d�L�����q����");
				}
				// Send the use back to the form, if there were errors
				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				if(num.equals("2")){
					req.setAttribute("purVO", purVO); // ��Ʈw���X��empVO����,�s�Jreq
					String url = "/back_end/purord/listOnePurOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
					successView.forward(req, res);
				}
				if(num.equals("1")){
					req.setAttribute("purVO", purVO); // ��Ʈw���X��empVO����,�s�Jreq
					String url = "/front_end/purord/listOnePurOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
					successView.forward(req, res);
				}
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				if(num.equals("2")){
					errorMsgs.add("�L�k���o���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/purord/select_page.jsp");
					failureView.forward(req, res);
				}
				if(num.equals("1")){
					errorMsgs.add("�L�k���o���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/purord/select_page.jsp");
					failureView.forward(req, res);
				}
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllPurOrd.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String pur_no = new String(req.getParameter("pur_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				PurService purSvc = new PurService();
				PurVO purVO = purSvc.getOnePurOrd(pur_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("purVO", purVO);         // ��Ʈw���X��purVO����,�s�Jreq
				String url = "/supplier_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_Purord_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/	
				String pur_no = req.getParameter("pur_no").trim();
				String member_no = req.getParameter("member_no").trim();				
				
				java.sql.Date pur_date = null;
				java.sql.Timestamp pur_time_date= null;
				try {
					pur_date = java.sql.Date.valueOf(req.getParameter("pur_date").trim());
					pur_time_date = new java.sql.Timestamp(pur_date.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�q����!");
				}
				
				java.sql.Date pur_money = null;
				java.sql.Timestamp pur_time_money= null;
				try {
					pur_money = java.sql.Date.valueOf(req.getParameter("pur_money").trim());
					pur_time_money = new java.sql.Timestamp(pur_money.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���ڤ��!");
				}
				
				java.sql.Date pur_product = null;
				java.sql.Timestamp pur_time_product= null;
				try {
					pur_product = java.sql.Date.valueOf(req.getParameter("pur_product").trim());
					pur_time_product = new java.sql.Timestamp(pur_product.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�X�f���!");
				}
				
				java.sql.Date pur_close = null;
				java.sql.Timestamp pur_time_close= null;
				try {
					pur_close = java.sql.Date.valueOf(req.getParameter("pur_close").trim());
					pur_time_close = new java.sql.Timestamp(pur_close.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���פ��!");
				}
				
				Integer pur_status = null;
				try {
					pur_status = new Integer(req.getParameter("pur_status").trim());
				} catch (NumberFormatException e) {
					pur_status = null;
					errorMsgs.add("�п�ܭq�檬�A.");
				}
				
				Integer pur_sum = null;
				try {
					pur_sum = new Integer(req.getParameter("pur_sum").trim());
				} catch (NumberFormatException e) {
					pur_sum = null;
					errorMsgs.add("�п�J�q����B.");
				}

				String pur_name = req.getParameter("pur_name").trim();
				if( pur_name == null || (pur_name.trim()).length()==0){
			    	errorMsgs.add("�п�J����H.");
			    }
				String pur_add = req.getParameter("pur_add").trim();
				if( pur_add == null || (pur_add.trim()).length()==0){
			    	errorMsgs.add("�п�J����H�a�}.");
			    }
				String pur_tel = req.getParameter("pur_tel").trim();
				if( pur_tel == null || (pur_tel.trim()).length()==0){
			    	errorMsgs.add("�п�J����H�q��.");
			    }
				
				String pur_memo = req.getParameter("pur_memo").trim();
				
				PurVO purVO = new PurVO();
				purVO.setPur_no(pur_no);
				purVO.setMember_no(member_no);
				purVO.setPur_date(pur_time_date);
				purVO.setPur_money(pur_time_money);
				purVO.setPur_product(pur_time_product);
				purVO.setPur_close(pur_time_close);
				purVO.setPur_status(pur_status);
				purVO.setPur_sum(pur_sum);
				purVO.setPur_name(pur_name);
				purVO.setPur_add(pur_add);
				purVO.setPur_tel(pur_tel);
				purVO.setPur_memo(pur_memo);	 

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("purVO", purVO); // �t����J�榡���~��purVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				PurService purSvc = new PurService();
				purVO = purSvc.updatePurOrd(pur_no, member_no, pur_time_date, pur_time_money, 
						pur_time_product, pur_time_close, pur_status, pur_sum, pur_name, pur_add, pur_tel, pur_memo);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("purVO2", purVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/supplier_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOnePurOrd.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addPurOrd.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
           
		try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String member_no = req.getParameter("member_no").trim();				
				
     			java.sql.Date pur_date = null;
				java.sql.Timestamp pur_time_date= null;
				try {
					pur_date = java.sql.Date.valueOf(req.getParameter("pur_date").trim());
	                pur_time_date = new java.sql.Timestamp(pur_date.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�q����!");
				}
		
				java.sql.Date pur_money = null;
				java.sql.Timestamp pur_time_money= null;
				try {
					pur_money = java.sql.Date.valueOf(req.getParameter("pur_money").trim());
					pur_time_money =new java.sql.Timestamp(pur_money.getTime());
				} catch (IllegalArgumentException e) {
					pur_money = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���ڤ��!");
				}
				
				java.sql.Date pur_product= null;
				java.sql.Timestamp pur_time_product= null;
				try {
					pur_product = java.sql.Date.valueOf(req.getParameter("pur_product").trim());
					pur_time_product =new java.sql.Timestamp(pur_product.getTime());
				} catch (IllegalArgumentException e) {
					pur_product = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�X�f���!");
				}
				
				java.sql.Date pur_close= null;
				java.sql.Timestamp pur_time_close= null;
				try {
					pur_close = java.sql.Date.valueOf(req.getParameter("pur_close").trim());
					pur_time_close = new java.sql.Timestamp(pur_close.getTime());
				} catch (IllegalArgumentException e) {
					pur_close = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���פ��!");
				}
				
				Integer pur_status = null;
				try {
					pur_status = new Integer(req.getParameter("pur_status").trim());
				} catch (NumberFormatException e) {
					pur_status = null;
					errorMsgs.add("�п�ܭq�檬�A.");
				}
				
				Integer pur_sum = null;
				try {
					pur_sum = new Integer(req.getParameter("pur_sum").trim());
				} catch (NumberFormatException e) {
					pur_sum = null;
					errorMsgs.add("�п�J�q����B.");
				}

				String pur_name = req.getParameter("pur_name").trim();
				String pur_add = req.getParameter("pur_add").trim();
				String pur_tel = req.getParameter("pur_tel").trim();
				String pur_memo = req.getParameter("pur_memo").trim();

				PurVO purVO = new PurVO();
				purVO.setMember_no(member_no);
				purVO.setPur_date(pur_time_date);
				purVO.setPur_money(pur_time_money);
				purVO.setPur_product(pur_time_product);
				purVO.setPur_close(pur_time_close);
				purVO.setPur_status(pur_status);
				purVO.setPur_sum(pur_sum);
				purVO.setPur_name(pur_name);
				purVO.setPur_add(pur_add);
				purVO.setPur_tel(pur_tel);
				purVO.setPur_memo(pur_memo);	 

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("purVO", purVO); // �t����J�榡���~��purVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/purord/addPurOrd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				PurService purSvc = new PurService();
				purVO = purSvc.addPurOrd(member_no, pur_time_date, pur_time_money, pur_time_product, 
						pur_time_close, pur_status, pur_sum, pur_name, pur_add, pur_tel, pur_memo);
				
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllPurOrd.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/purord/addPurOrd.jsp");
				failureView.forward(req, res);
			}
		}
        
 if ("insert2".equals(action)) { // �Ӧ�TransmitInfo.jsp���ШD  
			
			List<String> purerrorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			 req.setAttribute("purerrorMsgs", purerrorMsgs);
			 MailService mail = new MailService();
             String useremail = req.getParameter("member_email");
             String subject = "�ӫ~�q�ʳq��";
             String mailcontext = null;

		try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String member_no = req.getParameter("member_no").trim();				
				
     			java.sql.Date pur_date = null;
				java.sql.Timestamp pur_time_date= null;
				try {
					pur_date = java.sql.Date.valueOf(req.getParameter("pur_date").trim());
	                pur_time_date = new java.sql.Timestamp(pur_date.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("�п�J�q����!");
				}
		
				java.sql.Date pur_money = null;
				java.sql.Timestamp pur_time_money= null;
				try {
					pur_money = java.sql.Date.valueOf(req.getParameter("pur_money").trim());
					pur_time_money =new java.sql.Timestamp(pur_money.getTime());
				} catch (IllegalArgumentException e) {
					pur_money = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("�п�J���ڤ��!");
				}
				
				java.sql.Date pur_product= null;
				java.sql.Timestamp pur_time_product= null;
				try {
					pur_product = java.sql.Date.valueOf(req.getParameter("pur_product").trim());
					pur_time_product =new java.sql.Timestamp(pur_product.getTime());
				} catch (IllegalArgumentException e) {
					pur_product = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("�п�J�X�f���!");
				}
				
				java.sql.Date pur_close= null;
				java.sql.Timestamp pur_time_close= null;
				try {
					pur_close = java.sql.Date.valueOf(req.getParameter("pur_close").trim());
					pur_time_close = new java.sql.Timestamp(pur_close.getTime());
				} catch (IllegalArgumentException e) {
					pur_close = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("�п�J���פ��!");
				}
				
				Integer pur_status = null;
				try {
					pur_status = new Integer(req.getParameter("pur_status").trim());
				} catch (NumberFormatException e) {
					pur_status = null;
					purerrorMsgs.add("�п�ܭq�檬�A.");
				}
				
				Integer pur_sum = null;
				try {
					pur_sum = new Integer(req.getParameter("pur_sum").trim());
				} catch (NumberFormatException e) {
					pur_sum = null;
					purerrorMsgs.add("�п�J�q����B.");
				}
				            
				String pur_name = req.getParameter("pur_name").trim();
				if( pur_name == null || (pur_name.trim()).length()==0){
					purerrorMsgs.add("�п�J����H.");
			    }
				String pur_add = req.getParameter("pur_add").trim();
				if( pur_add == null || (pur_add.trim()).length()==0){
					purerrorMsgs.add("�п�J�a�}.");
			    }
				String pur_tel = req.getParameter("pur_tel").trim();
				if( pur_tel == null || (pur_tel.trim()).length()==0){
					purerrorMsgs.add("�п�J���.");
			    }
				String pur_memo = req.getParameter("pur_memo").trim();
				
				PurVO purVO = new PurVO();
				purVO.setMember_no(member_no);
				purVO.setPur_date(pur_time_date);
				purVO.setPur_money(pur_time_money);
				purVO.setPur_product(pur_time_product);
				purVO.setPur_close(pur_time_close);
				purVO.setPur_status(pur_status);
				purVO.setPur_sum(pur_sum);
				purVO.setPur_name(pur_name);
				purVO.setPur_add(pur_add);
				purVO.setPur_tel(pur_tel);
				purVO.setPur_memo(pur_memo);	 
				
				String com_no = req.getParameter("com_no").trim();
				Integer ord_price =  new Integer(req.getParameter("ord_price").trim());
				Integer ord_qty =  new Integer(req.getParameter("ord_qty").trim());
				Integer return_qty = new Integer(req.getParameter("return_qty").trim());
				Integer ship_status = new Integer(req.getParameter("ship_status").trim());
				
				HttpSession session = req.getSession();
				Vector<OrdVO> ordlist = (Vector<OrdVO>)session.getAttribute("shoppingcart");
				List<OrdVO> list = new ArrayList();
				for(int i=0;i<ordlist.size();i++) {
					OrdVO ordVO = new OrdVO();
					ordVO.setCom_no(ordlist.elementAt(i).getCom_no().toString());
					ordVO.setOrd_price(ordlist.elementAt(i).getOrd_price()*ordlist.elementAt(i).getOrd_qty());
					ordVO.setOrd_qty(ordlist.elementAt(i).getOrd_qty());
					ordVO.setReturn_qty(return_qty);
					ordVO.setShip_status(ship_status);
					list.add(ordVO);
				}		
				
				// Send the use back to the form, if there were errors
				if (!purerrorMsgs.isEmpty()) {
					req.setAttribute("purVO", purVO); // �t����J�榡���~��purVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shoppingcar/TransmitInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				PurService purSvc = new PurService();
				purVO = purSvc.addPurOrd2(member_no, pur_time_date, pur_time_money, pur_time_product, 
						pur_time_close, pur_status, pur_sum, pur_name, pur_add, pur_tel, pur_memo, list);
				
				mailcontext = pur_name + " �z�n�A�P�±z�������ʶR�A�����ʶR���B��" +"$"+pur_sum+"���C";
				mail.sendMail(useremail, subject, mailcontext);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				ordlist = null;
				session.setAttribute("shoppingcart", ordlist);
				String url = "/front_end/shoppingcar/ShoppingPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����ShoppingPage.jsp
				successView.forward(req, res);				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				purerrorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/shoppingcar/TransmitInfo.jsp");
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
				String pur_no = new String(req.getParameter("pur_no"));
				
				/***************************2.�}�l�R�����***************************************/
				PurService purSvc = new PurService();
				purSvc.deletePurOrd(pur_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listPurord_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
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
		
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.�}�l�ƦX�d��***************************************/
				PurService purSvc = new PurService();
				List<PurVO> list  = purSvc.getAll(map);
				/***************************3.�w���@�t�Ӫ��ƦX�d��***************************************/
				SupVO supVO=(SupVO)session.getAttribute("supAccount");              //���o�bseesion���t�Ӫ���
				
				ComService comSvc = new ComService();
				List<ComVO> ComVOList  = comSvc.getAll();                           //�ƦX�d�߰ӫ~���
				
				OrdService ordSvc = new OrdService();
				List<OrdVO> OrdVOList  = ordSvc.getAll();                           //�w�q�@��List<OrdVO>����A��X���󤺪��q��s��
				
				List<PurVO> PurVOList_ToPrint  = new ArrayList<PurVO>();            //�ۦ�w�q�@��List<PurVO>����A�H����᪺���G
				
				for(ComVO comVO_item : ComVOList) {
					if(comVO_item.getSup_no().equals(supVO.getSup_no())) {          //���session���t�ӽs���O�_���b�ӫ~���̪��t�ӽs���A�H��X�ӫ~��檺�ӫ~�s��
						for(OrdVO ordVO_item : OrdVOList) {
							if(ordVO_item.getCom_no().equals(comVO_item.getCom_no())) {             //�A�z�L�ӫ~��檺�ӫ~�s���A���q����Ӹ̪��ӫ~�s���A�H��X�n�J�t�Ӫ��q��s��
								PurVOList_ToPrint.add(purSvc.getOnePurOrd(ordVO_item.getPur_no())); //�̫�z�L�ŦX�Ӽt�Ӫ��q��s���A��X�q��é�J�ۦ�w�q��List<PurVO>����
							}
						}
					}
				}
				
				List<PurVO> PurVOList_ForComposite  = new ArrayList<PurVO>();
				
				for(PurVO PurVOList_ToPrint_item : PurVOList_ToPrint) {
					for(PurVO list_item: list)
						if(PurVOList_ToPrint_item.getPur_no().equals(list_item.getPur_no())){
							PurVOList_ForComposite.add(list_item);
						//System.out.println("PurVOList_ForComposite.add(list_item):"+list_item.getPur_no());
					}
				}		
				
				//�]�H�W������(map��PurVOList_ToPrint)�Ҥ�諸��Ʀ����ơA�G�HSet�覡�z�ﭫ�Ƹ�ơC
				Set<String> setTemp=new TreeSet<String>();
				List<PurVO> listOftemp = new ArrayList<PurVO>();
				for(PurVO listTemp : PurVOList_ForComposite){
					setTemp.add(listTemp.getPur_no());	
				}
				for(String pur_no:setTemp){
					listOftemp.add(purSvc.getOnePurOrd(pur_no));
				}
				
				
				/***************************4.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listPurord_ByCompositeQuery", listOftemp); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp"); // ���\���listPurord_ByCompositeQuery.jsp
				successView.forward(req, res);
//System.out.println("listPurord_ByCompositeQuery"+PurVOList_ForComposite.size());			
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}

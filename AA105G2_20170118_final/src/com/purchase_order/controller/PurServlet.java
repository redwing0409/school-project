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
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
            String num = req.getParameter("number");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pur_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入選購訂單編號");
				}
				// Send the use back to the form, if there were errors
				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				
				String pur_no = "";
				try {
					pur_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("選購訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				
				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				/***************************2.開始查詢資料*****************************************/
				PurService purSvc = new PurService();
				PurVO purVO = purSvc.getOnePurOrd(pur_no);
				if (purVO == null) {
					errorMsgs.add("查無此筆訂單資料");
				}
				// Send the use back to the form, if there were errors
				if(num.equals("2")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				if(num.equals("1")){
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/purord/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				if(num.equals("2")){
					req.setAttribute("purVO", purVO); // 資料庫取出的empVO物件,存入req
					String url = "/back_end/purord/listOnePurOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
				}
				if(num.equals("1")){
					req.setAttribute("purVO", purVO); // 資料庫取出的empVO物件,存入req
					String url = "/front_end/purord/listOnePurOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
				}
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				if(num.equals("2")){
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/purord/select_page.jsp");
					failureView.forward(req, res);
				}
				if(num.equals("1")){
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/purord/select_page.jsp");
					failureView.forward(req, res);
				}
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPurOrd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String pur_no = new String(req.getParameter("pur_no"));
				
				/***************************2.開始查詢資料****************************************/
				PurService purSvc = new PurService();
				PurVO purVO = purSvc.getOnePurOrd(pur_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("purVO", purVO);         // 資料庫取出的purVO物件,存入req
				String url = "/supplier_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Purord_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/	
				String pur_no = req.getParameter("pur_no").trim();
				String member_no = req.getParameter("member_no").trim();				
				
				java.sql.Date pur_date = null;
				java.sql.Timestamp pur_time_date= null;
				try {
					pur_date = java.sql.Date.valueOf(req.getParameter("pur_date").trim());
					pur_time_date = new java.sql.Timestamp(pur_date.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				
				java.sql.Date pur_money = null;
				java.sql.Timestamp pur_time_money= null;
				try {
					pur_money = java.sql.Date.valueOf(req.getParameter("pur_money").trim());
					pur_time_money = new java.sql.Timestamp(pur_money.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入收款日期!");
				}
				
				java.sql.Date pur_product = null;
				java.sql.Timestamp pur_time_product= null;
				try {
					pur_product = java.sql.Date.valueOf(req.getParameter("pur_product").trim());
					pur_time_product = new java.sql.Timestamp(pur_product.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期!");
				}
				
				java.sql.Date pur_close = null;
				java.sql.Timestamp pur_time_close= null;
				try {
					pur_close = java.sql.Date.valueOf(req.getParameter("pur_close").trim());
					pur_time_close = new java.sql.Timestamp(pur_close.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結案日期!");
				}
				
				Integer pur_status = null;
				try {
					pur_status = new Integer(req.getParameter("pur_status").trim());
				} catch (NumberFormatException e) {
					pur_status = null;
					errorMsgs.add("請選擇訂單狀態.");
				}
				
				Integer pur_sum = null;
				try {
					pur_sum = new Integer(req.getParameter("pur_sum").trim());
				} catch (NumberFormatException e) {
					pur_sum = null;
					errorMsgs.add("請輸入訂單金額.");
				}

				String pur_name = req.getParameter("pur_name").trim();
				if( pur_name == null || (pur_name.trim()).length()==0){
			    	errorMsgs.add("請輸入收件人.");
			    }
				String pur_add = req.getParameter("pur_add").trim();
				if( pur_add == null || (pur_add.trim()).length()==0){
			    	errorMsgs.add("請輸入收件人地址.");
			    }
				String pur_tel = req.getParameter("pur_tel").trim();
				if( pur_tel == null || (pur_tel.trim()).length()==0){
			    	errorMsgs.add("請輸入收件人電話.");
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
					req.setAttribute("purVO", purVO); // 含有輸入格式錯誤的purVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PurService purSvc = new PurService();
				purVO = purSvc.updatePurOrd(pur_no, member_no, pur_time_date, pur_time_money, 
						pur_time_product, pur_time_close, pur_status, pur_sum, pur_name, pur_add, pur_tel, pur_memo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("purVO2", purVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/supplier_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePurOrd.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPurOrd.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
           
		try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String member_no = req.getParameter("member_no").trim();				
				
     			java.sql.Date pur_date = null;
				java.sql.Timestamp pur_time_date= null;
				try {
					pur_date = java.sql.Date.valueOf(req.getParameter("pur_date").trim());
	                pur_time_date = new java.sql.Timestamp(pur_date.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
		
				java.sql.Date pur_money = null;
				java.sql.Timestamp pur_time_money= null;
				try {
					pur_money = java.sql.Date.valueOf(req.getParameter("pur_money").trim());
					pur_time_money =new java.sql.Timestamp(pur_money.getTime());
				} catch (IllegalArgumentException e) {
					pur_money = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入收款日期!");
				}
				
				java.sql.Date pur_product= null;
				java.sql.Timestamp pur_time_product= null;
				try {
					pur_product = java.sql.Date.valueOf(req.getParameter("pur_product").trim());
					pur_time_product =new java.sql.Timestamp(pur_product.getTime());
				} catch (IllegalArgumentException e) {
					pur_product = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期!");
				}
				
				java.sql.Date pur_close= null;
				java.sql.Timestamp pur_time_close= null;
				try {
					pur_close = java.sql.Date.valueOf(req.getParameter("pur_close").trim());
					pur_time_close = new java.sql.Timestamp(pur_close.getTime());
				} catch (IllegalArgumentException e) {
					pur_close = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結案日期!");
				}
				
				Integer pur_status = null;
				try {
					pur_status = new Integer(req.getParameter("pur_status").trim());
				} catch (NumberFormatException e) {
					pur_status = null;
					errorMsgs.add("請選擇訂單狀態.");
				}
				
				Integer pur_sum = null;
				try {
					pur_sum = new Integer(req.getParameter("pur_sum").trim());
				} catch (NumberFormatException e) {
					pur_sum = null;
					errorMsgs.add("請輸入訂單金額.");
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
					req.setAttribute("purVO", purVO); // 含有輸入格式錯誤的purVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/purord/addPurOrd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PurService purSvc = new PurService();
				purVO = purSvc.addPurOrd(member_no, pur_time_date, pur_time_money, pur_time_product, 
						pur_time_close, pur_status, pur_sum, pur_name, pur_add, pur_tel, pur_memo);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPurOrd.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/purord/addPurOrd.jsp");
				failureView.forward(req, res);
			}
		}
        
 if ("insert2".equals(action)) { // 來自TransmitInfo.jsp的請求  
			
			List<String> purerrorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			 req.setAttribute("purerrorMsgs", purerrorMsgs);
			 MailService mail = new MailService();
             String useremail = req.getParameter("member_email");
             String subject = "商品訂購通知";
             String mailcontext = null;

		try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String member_no = req.getParameter("member_no").trim();				
				
     			java.sql.Date pur_date = null;
				java.sql.Timestamp pur_time_date= null;
				try {
					pur_date = java.sql.Date.valueOf(req.getParameter("pur_date").trim());
	                pur_time_date = new java.sql.Timestamp(pur_date.getTime());
				} catch (IllegalArgumentException e) {
					pur_date = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("請輸入訂單日期!");
				}
		
				java.sql.Date pur_money = null;
				java.sql.Timestamp pur_time_money= null;
				try {
					pur_money = java.sql.Date.valueOf(req.getParameter("pur_money").trim());
					pur_time_money =new java.sql.Timestamp(pur_money.getTime());
				} catch (IllegalArgumentException e) {
					pur_money = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("請輸入收款日期!");
				}
				
				java.sql.Date pur_product= null;
				java.sql.Timestamp pur_time_product= null;
				try {
					pur_product = java.sql.Date.valueOf(req.getParameter("pur_product").trim());
					pur_time_product =new java.sql.Timestamp(pur_product.getTime());
				} catch (IllegalArgumentException e) {
					pur_product = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("請輸入出貨日期!");
				}
				
				java.sql.Date pur_close= null;
				java.sql.Timestamp pur_time_close= null;
				try {
					pur_close = java.sql.Date.valueOf(req.getParameter("pur_close").trim());
					pur_time_close = new java.sql.Timestamp(pur_close.getTime());
				} catch (IllegalArgumentException e) {
					pur_close = new java.sql.Date(System.currentTimeMillis());
					purerrorMsgs.add("請輸入結案日期!");
				}
				
				Integer pur_status = null;
				try {
					pur_status = new Integer(req.getParameter("pur_status").trim());
				} catch (NumberFormatException e) {
					pur_status = null;
					purerrorMsgs.add("請選擇訂單狀態.");
				}
				
				Integer pur_sum = null;
				try {
					pur_sum = new Integer(req.getParameter("pur_sum").trim());
				} catch (NumberFormatException e) {
					pur_sum = null;
					purerrorMsgs.add("請輸入訂單金額.");
				}
				            
				String pur_name = req.getParameter("pur_name").trim();
				if( pur_name == null || (pur_name.trim()).length()==0){
					purerrorMsgs.add("請輸入收件人.");
			    }
				String pur_add = req.getParameter("pur_add").trim();
				if( pur_add == null || (pur_add.trim()).length()==0){
					purerrorMsgs.add("請輸入地址.");
			    }
				String pur_tel = req.getParameter("pur_tel").trim();
				if( pur_tel == null || (pur_tel.trim()).length()==0){
					purerrorMsgs.add("請輸入手機.");
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
					req.setAttribute("purVO", purVO); // 含有輸入格式錯誤的purVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shoppingcar/TransmitInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PurService purSvc = new PurService();
				purVO = purSvc.addPurOrd2(member_no, pur_time_date, pur_time_money, pur_time_product, 
						pur_time_close, pur_status, pur_sum, pur_name, pur_add, pur_tel, pur_memo, list);
				
				mailcontext = pur_name + " 您好，感謝您本次的購買，本次購買金額為" +"$"+pur_sum+"元。";
				mail.sendMail(useremail, subject, mailcontext);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				ordlist = null;
				session.setAttribute("shoppingcart", ordlist);
				String url = "/front_end/shoppingcar/ShoppingPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交ShoppingPage.jsp
				successView.forward(req, res);				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				purerrorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/shoppingcar/TransmitInfo.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String pur_no = new String(req.getParameter("pur_no"));
				
				/***************************2.開始刪除資料***************************************/
				PurService purSvc = new PurService();
				purSvc.deletePurOrd(pur_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/purord/listAllPurOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listPurord_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {			
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
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
				
				/***************************2.開始複合查詢***************************************/
				PurService purSvc = new PurService();
				List<PurVO> list  = purSvc.getAll(map);
				/***************************3.針對單一廠商的複合查詢***************************************/
				SupVO supVO=(SupVO)session.getAttribute("supAccount");              //取得在seesion的廠商物件
				
				ComService comSvc = new ComService();
				List<ComVO> ComVOList  = comSvc.getAll();                           //複合查詢商品表格
				
				OrdService ordSvc = new OrdService();
				List<OrdVO> OrdVOList  = ordSvc.getAll();                           //定義一個List<OrdVO>物件，找出物件內的訂單編號
				
				List<PurVO> PurVOList_ToPrint  = new ArrayList<PurVO>();            //自行定義一個List<PurVO>物件，以放比對後的結果
				
				for(ComVO comVO_item : ComVOList) {
					if(comVO_item.getSup_no().equals(supVO.getSup_no())) {          //比對session的廠商編號是否有在商品表格裡的廠商編號，以找出商品表格的商品編號
						for(OrdVO ordVO_item : OrdVOList) {
							if(ordVO_item.getCom_no().equals(comVO_item.getCom_no())) {             //再透過商品表格的商品編號，比對訂單明細裡的商品編號，以找出登入廠商的訂單編號
								PurVOList_ToPrint.add(purSvc.getOnePurOrd(ordVO_item.getPur_no())); //最後透過符合該廠商的訂單編號，找出訂單並放入自行定義的List<PurVO>物件內
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
				
				//因以上兩份資料(map跟PurVOList_ToPrint)所比對的資料有重複，故以Set方式篩選重複資料。
				Set<String> setTemp=new TreeSet<String>();
				List<PurVO> listOftemp = new ArrayList<PurVO>();
				for(PurVO listTemp : PurVOList_ForComposite){
					setTemp.add(listTemp.getPur_no());	
				}
				for(String pur_no:setTemp){
					listOftemp.add(purSvc.getOnePurOrd(pur_no));
				}
				
				
				/***************************4.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listPurord_ByCompositeQuery", listOftemp); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp"); // 成功轉交listPurord_ByCompositeQuery.jsp
				successView.forward(req, res);
//System.out.println("listPurord_ByCompositeQuery"+PurVOList_ForComposite.size());			
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/purord/listAllPurOrd.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}

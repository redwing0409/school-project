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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("com_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
						
					
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				String com_no = "";
				
				try {
					com_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}		
				/***************************2.開始查詢資料*****************************************/
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(com_no);
				if (comVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String temp = sf.format(comVO.getCom_shelf_date());
//				req.setAttribute("temp", temp);
        
            	req.setAttribute("comVO", comVO);// 資料庫取出的comVO物件,存入req
				String url = "/front_end/shoppingcar/ShoppingPageDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交ShoppingPageDetail.jsp
				successView.forward(req, res);
				return;
              
				/***************************其他可能的錯誤處理*************************************/
			   } catch (Exception e) {	
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("front_end/shoppingcar/ShoppingPageDetail.jsp");
					failureView.forward(req, res);				
			   }
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String com_no = req.getParameter("com_no");
				
				/***************************2.開始查詢資料****************************************/
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(com_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("comVO", comVO);         // 資料庫取出的comVO物件,存入req
				String url = "/supplier_end/com/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_com_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			   } catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_com_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String com_no = req.getParameter("com_no").trim();
				String sup_no = req.getParameter("sup_no").trim();
								
				Integer pcm_no = null;
				try {
					pcm_no = new Integer(req.getParameter("pcm_no").trim());
				} catch (NumberFormatException e) {
					pcm_no = null;
					errorMsgs.add("請選擇商品類別.");
				}
				
				String com_name = req.getParameter("com_name").trim();
				if(com_name == null || com_name.trim().length()==0){
					errorMsgs.add("請輸入商品名稱!");
				}
				
				String com_desc = req.getParameter("com_desc").trim();
				if(com_desc == null || com_desc.trim().length()==0){
					errorMsgs.add("請輸入商品描述!");
				}
				
				Integer com_price = null;
				try {
					com_price = new Integer(req.getParameter("com_price").trim());
				} catch (NumberFormatException e) {
					com_price = null;
					errorMsgs.add("請輸入商品價格.");
				}
				
				Integer com_status = null;
				try {
					com_status = new Integer(req.getParameter("com_status").trim());
				} catch (NumberFormatException e) {
					com_price = null;
					errorMsgs.add("請選擇商品狀態.");
				}
					
				java.sql.Date com_shelf_date = null;
				java.sql.Timestamp shelfDate= null;
				
				try {
					 com_shelf_date = java.sql.Date.valueOf(req.getParameter("com_shelf_date").trim());
					 shelfDate = new java.sql.Timestamp(com_shelf_date.getTime());
				} catch (IllegalArgumentException e) {
					com_shelf_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				java.sql.Date com_off_date = null;
				java.sql.Timestamp offDate= null;
				try {
					com_off_date = java.sql.Date.valueOf(req.getParameter("com_off_date").trim());
					offDate = new java.sql.Timestamp(com_off_date.getTime());
				} catch (IllegalArgumentException e) {
					com_off_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下架日期!");
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
					
					req.setAttribute("comVO", comVO); // 含有輸入格式錯誤的comVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.updateCom(com_no, sup_no, pcm_no, com_name, com_desc, com_price, 
						com_status, shelfDate, offDate, com_note, com_pic);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("comVO2", comVO); // 資料庫update成功後,正確的的comVO物件,存入req
				String url = "/supplier_end/com/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllCommodity.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addCom.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				//String com_no = new String(req.getParameter("com_no").trim());
				String sup_no = req.getParameter("sup_no").trim();
				
				Integer pcm_no = null;
				try {
					pcm_no = new Integer(req.getParameter("pcm_no").trim());
				} catch (NumberFormatException e) {
					pcm_no =null;
					errorMsgs.add("請選擇商品類別");
				}
				
				String com_name = req.getParameter("com_name").trim();
				if(com_name == null || com_name.trim().length()==0){
					errorMsgs.add("請輸入商品名稱!");
				}
				
				String com_desc = req.getParameter("com_desc").trim();
				if(com_desc == null || com_desc.trim().length()==0){
					errorMsgs.add("請輸入商品描述!");
				}
				
				Integer com_price = null;
				try {
					com_price = new Integer(req.getParameter("com_price").trim());
				} catch (NumberFormatException e) {
					com_price = null;
					errorMsgs.add("請輸入商品價格.");
				}
				
				Integer com_status = null;
				try {
					com_status = new Integer(req.getParameter("com_status").trim());
				} catch (NumberFormatException e) {
					com_status = null;
					errorMsgs.add("請選擇商品狀態.");
				}
				
				java.sql.Date com_shelf_date = null;
				java.sql.Timestamp shelfDate= null;
				try {
					com_shelf_date = java.sql.Date.valueOf(req.getParameter("com_shelf_date").trim());
					shelfDate = new java.sql.Timestamp(com_shelf_date.getTime());
				} catch (IllegalArgumentException e) {
					
					com_shelf_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				java.sql.Date com_off_date = null;
				java.sql.Timestamp offDate= null;
				try {
					com_off_date = java.sql.Date.valueOf(req.getParameter("com_off_date").trim());
					offDate = new java.sql.Timestamp(com_off_date.getTime());
				} catch (IllegalArgumentException e) {
					
					com_off_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下架日期!");
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
					
					req.setAttribute("comVO3", comVO); // 含有輸入格式錯誤的comVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				ComService comSvc = new ComService();
				comVO = comSvc.addCom(sup_no, pcm_no, com_name, com_desc, com_price, 
						com_status, shelfDate, offDate, com_note, com_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("comVO3", comVO); // 資料庫insert成功後,正確的的comVO物件,存入req
				String url = "/supplier_end/com/listAllCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCommodity.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp");
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
				String com_no = req.getParameter("com_no");
				
				/***************************2.開始刪除資料***************************************/
				ComService comSvc = new ComService();
				comSvc.deleteCom(com_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/com/listAllCom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
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
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
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
				
				/***************************2.先查出所有商品***************************************/
				ComService comSvc = new ComService();
				List<ComVO> list  = comSvc.getAll(map);
				
				/***************************2.再針對單一廠商進行複合查詢***********************/
				List<ComVO> ComVOList_ToPrint  = new ArrayList<ComVO>();                   //自行定義的List<comVO>物件
				SupVO supVO=(SupVO)session.getAttribute("supAccount");                    //取得在seesion的廠商物件
				for(ComVO comVO_item : list) {
					if(supVO.getSup_no().equals(comVO_item.getSup_no())){                 //從comVO物件找sup_no和已存在於session的廠商比對廠商編號。
						ComVOList_ToPrint.add(comSvc.getOneCom(comVO_item.getCom_no()));  //比對若廠商編號符合登入的廠商身分，將商品物件存入自行定義的List<comVO>物件內。
					}
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listCom_ByCompositeQuery", ComVOList_ToPrint); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/supplier_end/com/listAllCommodity.jsp"); // 成功轉交listAllCommodity.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
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
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
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
				
				/***************************2.開始複合查詢***************************************/
				ComService comSvc = new ComService();
				List<ComVO> list  = comSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				HttpSession session2 = req.getSession();
				session2.setAttribute("CompositeQuery_front", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/shoppingcar/ShoppingPageComposite.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
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
		//System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		//System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
